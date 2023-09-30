package dragonmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dragonmod.characters.TheWarden;
import dragonmod.interfaces.FieldCard;
import dragonmod.util.Wiz;
import javassist.CtBehavior;

import java.util.ArrayList;

public class FieldsManager {
    public static final float Y_OFFSET = 140f * Settings.scale;
    public static final float X_OFFSET = 100f * Settings.scale;
    public static final CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static final BobEffect bob = new BobEffect(3.0f * Settings.scale, 3.0f);
    public static AbstractCard hovered;
    public static boolean didbacklash = false;
    public static void render(SpriteBatch sb) {
        if (FieldsField.Fields.get(Wiz.adp()) != null) {
            for (AbstractCard card : FieldsField.Fields.get(Wiz.adp())) {
                if (card != hovered) {
                    card.render(sb);
                }
            }
        } else {
            FieldsField.Fields.set(Wiz.adp(),new ArrayList<>());
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (FieldsField.Fields.get(m) != null && !m.isDeadOrEscaped()) {
                    for (AbstractCard card : FieldsField.Fields.get(m)) {
                        if (card != hovered) {
                            card.render(sb);
                        }
                    }
                }
            }
        }
        if (hovered != null) {
            hovered.render(sb);
            TipHelper.renderTipForCard(hovered, sb, hovered.keywords);
        }
    }

    public static void update() {
        bob.update();
        int i = 0;
        hovered = null;
        if (FieldsField.Fields.get(Wiz.adp()) != null){
            for (AbstractCard card : FieldsField.Fields.get(Wiz.adp())) {
                card.target_y = Wiz.adp().hb.cY + Wiz.adp().hb.height / 2f + Y_OFFSET + bob.y;
                card.target_x = Wiz.adp().hb.cX + X_OFFSET * (FieldsField.Fields.get(Wiz.adp()).size() - 1) / 2f - X_OFFSET * i;
                card.targetAngle = 0f;
                card.update();
                card.hb.update();
                if (card.hb.hovered && hovered == null) {
                    card.targetDrawScale = 0.75f;
                    hovered = card;
                } else {
                    card.targetDrawScale = 0.2f;
                }
                card.applyPowers();
                i++;
            }
        }

        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (FieldsField.Fields.get(m) != null) {
                    for (AbstractCard card : FieldsField.Fields.get(m)) {
                        card.target_y = m.hb.cY + m.hb.height / 2f + Y_OFFSET + bob.y;
                        card.target_x = m.hb.cX + X_OFFSET * (FieldsField.Fields.get(m).size() - 1) / 2f - X_OFFSET * i;
                        card.targetAngle = 0f;
                        card.update();
                        card.hb.update();
                        if (card.hb.hovered && hovered == null) {
                            card.targetDrawScale = 0.75f;
                            hovered = card;
                        } else {
                            card.targetDrawScale = 0.2f;
                        }
                        card.applyPowers();
                        i++;
                    }
                    if (m.isDeadOrEscaped()) {
                       EmptyFields(m);
                    }
                }
            }
        }
    }

    public static void playCards() {
        for (AbstractCard card : FieldsField.Fields.get(Wiz.adp())) {
            if (card instanceof FieldCard) {
                Wiz.adp().limbo.group.add(card);
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        FieldsField.Fields.get(Wiz.adp()).remove(card);
                        Wiz.atb(new UnlimboAction(card));
                         ((FieldCard) card).AttachedTurnStart(Wiz.adp());
                        addToBot(new AbstractGameAction() {
                            @Override
                            public void update() {
                                FieldsField.Fields.get(Wiz.adp()).add(card);
                                isDone = true;
                            }
                        });
                        isDone = true;
                    }
                });
            }
        }
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (FieldsField.Fields.get(m) != null && !m.isDeadOrEscaped()) {
                for (AbstractCard card : FieldsField.Fields.get(m)) {
                    if (card instanceof FieldCard) {
                        Wiz.adp().limbo.group.add(card);
                        Wiz.atb(new AbstractGameAction() {
                            @Override
                            public void update() {
                                FieldsField.Fields.get(m).remove(card);
                                Wiz.atb(new UnlimboAction(card));
                                ((FieldCard) card).AttachedTurnStart(m);
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        if (!AbstractDungeon.player.discardPile.contains(card) && !AbstractDungeon.player.exhaustPile.contains(card)) {
                                            FieldsField.Fields.get(m).add(card);
                                        }
                                        isDone = true;
                                    }
                                });
                                isDone = true;
                            }
                        });
                    }
                }
            }
        }
    }

    public static void addCard(AbstractCard card, boolean playSFX, AbstractCreature target) {
        card.targetAngle = 0f;
        card.beginGlowing();
        card.initializeDescription();
        if (FieldsField.Fields.get(target) == null){
            FieldsField.Fields.set(target,new ArrayList<>());
            FieldsField.Fields.get(target).add(card);
        } else {
            FieldsField.Fields.get(target).add(card);
        }

        if (playSFX) {
            CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
        }
        CardCounterPatch.cardsProjectedThisTurn++;
        CardCounterPatch.cardsProjectedThisCombat++;
    }

    @SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class ProjectedCardField {
        public static SpireField<Boolean> projectedField = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = UseCardAction.class, method = SpirePatch.CLASS)
    public static class ProjectedActionField {
        public static SpireField<Boolean> projectedField = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class InheritProjectedField {
        @SpirePrefixPatch
        public static void pushProjected(UseCardAction __instance, AbstractCard card) {
            if (ProjectedCardField.projectedField.get(card)) {
                ProjectedActionField.projectedField.set(__instance, true);
                ProjectedCardField.projectedField.set(card, false);
            }
        }
    }
    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class PlayCards {
        @SpirePrefixPatch
        public static void playCards() {
           FieldsManager.playCards();
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = Locator.class)
        public static void render(OverlayMenu __instance, SpriteBatch sb) {
            FieldsManager.render(sb);
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(ExhaustPanel.class, "render");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "combatUpdate")
    public static class UpdatePile {
        @SpirePostfixPatch
        public static void update(AbstractPlayer __instance) {
            FieldsManager.update();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "preBattlePrep")
    @SpirePatch2(clz = AbstractPlayer.class, method = "onVictory")
    public static class EmptyCards {
        @SpirePostfixPatch
        public static void yeet() {
            if (FieldsField.Fields.get(Wiz.adp()) != null) {
                if (AbstractDungeon.player instanceof TheWarden || AbstractDungeon.player.hasRelic(PrismaticShard.ID)) {
                    FieldsField.Fields.set(AbstractDungeon.player, new ArrayList<>());
                } else if (!FieldsField.Fields.get(AbstractDungeon.player).isEmpty()) {
                    FieldsField.Fields.set(AbstractDungeon.player, new ArrayList<>());
                }
            }
        }
    }

    public static void EmptyFields (AbstractCreature owner){
        for (AbstractCard card : FieldsField.Fields.get(owner)) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    FieldsField.Fields.get(owner).remove(card);
                    if (card.type != AbstractCard.CardType.POWER) {
                        if (card.exhaust) {
                            AbstractDungeon.player.exhaustPile.moveToExhaustPile(card);
                        } else {
                            AbstractDungeon.player.discardPile.moveToDiscardPile(card);
                        }
                    }
                    card.initializeDescription();
                    isDone = true;
                }
            });
        }
    }
}

