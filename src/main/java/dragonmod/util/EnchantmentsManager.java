package dragonmod.util;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.ExhaustPileViewScreen;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dragonmod.cards.AbstractDragonCard;
import dragonmod.interfaces.TurnStartEnchantment;
import dragonmod.interfaces.onAttackedEnchantment;
import dragonmod.interfaces.onExhaustedEnchantment;
import dragonmod.interfaces.onRemoveOrbEnchantment;
import dragonmod.patches.CardCounterPatch;
import javassist.CtBehavior;

import java.util.ArrayList;

import static dragonmod.DragonMod.makeID;

public class EnchantmentsManager {
    public static final float Y_OFFSET = 140f * Settings.scale;
    public static final float X_OFFSET = 400f * Settings.scale;
    private static final BobEffect bob = new BobEffect(3.0f * Settings.scale, 3.0f);
    public static CardGroup BanishedCards;
    @SpireEnum
    public static AbstractCard.CardTags Cantrip;
    @SpireEnum
    public static AbstractCard.CardTags Sleeved;
    public static AbstractCard hovered;
    public static void render(SpriteBatch sb) {
        if (EnchantmentsField.Enchantments.get(Wiz.Player()) != null) {
            for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
                if (card != hovered) {
                    card.render(sb);
                }
            }
        } else {
            EnchantmentsField.Enchantments.set(Wiz.Player(),new ArrayList<>());
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (EnchantmentsField.Enchantments.get(m) != null && !m.isDeadOrEscaped()) {
                    for (AbstractCard card : EnchantmentsField.Enchantments.get(m)) {
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
        if (EnchantmentsField.Enchantments.get(Wiz.Player()) != null){
            for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
                card.target_y = Wiz.Player().hb.cY + Wiz.Player().hb.height / 2f + Y_OFFSET + bob.y;
                card.target_x = Wiz.Player().hb.cX + X_OFFSET * (EnchantmentsField.Enchantments.get(Wiz.Player()).size() - 1) / 2f - X_OFFSET * i;
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
                if (EnchantmentsField.Enchantments.get(m) != null) {
                    for (AbstractCard card : EnchantmentsField.Enchantments.get(m)) {
                        card.target_y = m.hb.cY + m.hb.height / 2f + Y_OFFSET + bob.y;
                        card.target_x = m.hb.cX + X_OFFSET * (EnchantmentsField.Enchantments.get(m).size() - 1) / 2f - X_OFFSET * i;
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
                       EmptyEnchantments(m);
                    }
                }
            }
        }
    }

    public static void startOfTurnPlayer() {
        for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
            if (card instanceof TurnStartEnchantment) {
                Wiz.Player().limbo.group.add(card);
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        EnchantmentsField.Enchantments.get(Wiz.Player()).remove(card);
                        Wiz.atb(new UnlimboAction(card));
                         ((TurnStartEnchantment) card).EnchantedTurnStart(Wiz.Player());
                        addToBot(new AbstractGameAction() {
                            @Override
                            public void update() {
                                ActivateEnchantments(card,Wiz.Player());
                                isDone = true;
                            }
                        });
                        isDone = true;
                    }
                });
            }
        }
    }
    public static boolean EmptyBagOfTricks() {
        boolean empty = true;
        for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
            if (card.hasTag(Cantrip) || card.hasTag(Sleeved)){
                empty = false;
            }
        }
        return empty;
    }
    public static AbstractCard getSleevedCard() {
        AbstractCard Sleeved;
        CardGroup pool = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
            if (card.hasTag(Cantrip) || card.hasTag(EnchantmentsManager.Sleeved)){
                pool.addToBottom(card);
            }
        }
        Sleeved = pool.getRandomCard(true);
        return Sleeved;
    }
    public static void startOfTurnMonster(AbstractMonster m) {
            if (EnchantmentsField.Enchantments.get(m) != null && !m.isDeadOrEscaped()) {
                for (AbstractCard card : EnchantmentsField.Enchantments.get(m)) {
                    if (card instanceof TurnStartEnchantment) {
                        Wiz.Player().limbo.group.add(card);
                        Wiz.atb(new AbstractGameAction() {
                            @Override
                            public void update() {
                                EnchantmentsField.Enchantments.get(m).remove(card);
                                Wiz.atb(new UnlimboAction(card));
                                ((TurnStartEnchantment) card).EnchantedTurnStart(m);
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        ActivateEnchantments(card,m);
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
    public static void RemoveOrbPlayer(AbstractOrb orb) {
        if (EnchantmentsField.Enchantments.get(Wiz.Player()) != null && !Wiz.Player().isDeadOrEscaped()) {
            for (AbstractCard card : EnchantmentsField.Enchantments.get(Wiz.Player())) {
                if (card instanceof onRemoveOrbEnchantment) {
                    Wiz.Player().limbo.group.add(card);
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            EnchantmentsField.Enchantments.get(Wiz.Player()).remove(card);
                            Wiz.atb(new UnlimboAction(card));
                            boolean triggered = ((onRemoveOrbEnchantment) card).EnchantedOnRemoveOrb(Wiz.Player(),orb);
                            if (triggered){
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        ActivateEnchantments(card,Wiz.Player());
                                        isDone = true;
                                    }
                                });
                            }
                            isDone = true;
                        }
                    });
                }
            }
        }
    }
    public static void RemoveOrbMonster(AbstractOrb orb, AbstractMonster m) {
        if (EnchantmentsField.Enchantments.get(m) != null && !m.isDeadOrEscaped()) {
            for (AbstractCard card : EnchantmentsField.Enchantments.get(m)) {
                if (card instanceof onRemoveOrbEnchantment) {
                    Wiz.Player().limbo.group.add(card);
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            EnchantmentsField.Enchantments.get(m).remove(card);
                            Wiz.atb(new UnlimboAction(card));
                            boolean triggered = ((onRemoveOrbEnchantment) card).EnchantedOnRemoveOrb(m,orb);
                            if (triggered){
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        ActivateEnchantments(card,m);
                                        isDone = true;
                                    }
                                });
                            }
                            isDone = true;
                        }
                    });
                }
            }
        }
    }
    public static void addCard(AbstractCard card, boolean playSFX, AbstractCreature target) {
        card.targetAngle = 0f;
        card.beginGlowing();
        card.unfadeOut();
        card.initializeDescription();
        if (EnchantmentsField.Enchantments.get(target) == null){
            EnchantmentsField.Enchantments.set(target,new ArrayList<>());
            EnchantmentsField.Enchantments.get(target).add(card);
        } else {
            EnchantmentsField.Enchantments.get(target).add(card);
        }

        if (playSFX) {
            CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
        }
        EnchantmentsManager.update();
        CardCounterPatch.cardsProjectedThisTurn++;
        CardCounterPatch.cardsProjectedThisCombat++;
    }
    public static void InitCantrips(){
        ArrayList<AbstractCard> moveToDiscard = new ArrayList<>();
        for (AbstractCard c : Wiz.Player().drawPile.group) {
            if (c.hasTag(Cantrip)) {
                moveToDiscard.add(c);
            }
        }
        for (AbstractCard c : moveToDiscard) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (AbstractDungeon.player.hoveredCard == c) {
                        AbstractDungeon.player.releaseCard();
                    }
                    AbstractDungeon.actionManager.removeFromQueue(c);
                    c.unhover();
                    c.untip();
                    c.stopGlowing();
                    AbstractDungeon.player.drawPile.group.remove(c);
                    c.shrink();
                    AbstractDungeon.getCurrRoom().souls.empower(c);
                    EnchantmentsManager.addCard(c,false,Wiz.Player());
                }
            });
        }
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                System.out.println("Enchantment Field Render Count :" + (EnchantmentsField.Enchantments.get(Wiz.Player()).size()));
                UpdatePile.update(Wiz.Player());
            }
        });
    }
    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class PlayCards {
        @SpirePrefixPatch
        public static void playCards() {
           EnchantmentsManager.startOfTurnPlayer();
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = Locator.class)
        public static void render(OverlayMenu __instance, SpriteBatch sb) {
            EnchantmentsManager.render(sb);
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
            EnchantmentsManager.update();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "preBattlePrep")
    @SpirePatch2(clz = AbstractPlayer.class, method = "onVictory")
    public static class EmptyCards {
        @SpirePrefixPatch
        public static void yeet() {
            if (EnchantmentsField.Enchantments.get(Wiz.Player()) != null) {
                EnchantmentsField.Enchantments.set(AbstractDungeon.player, new ArrayList<>());
            }
        }
    }
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class PlayerDamage {
        public PlayerDamage() {
        }
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"damageAmount", "hadBlock"}
        )
        public static void Insert(AbstractCreature __instance, DamageInfo info, int damageAmount, @ByRef boolean[] hadBlock) {
            if (!AbstractDungeon.getCurrRoom().isBattleOver && info.type == DamageInfo.DamageType.NORMAL) {
                for (AbstractCard enchantment : EnchantmentsField.Enchantments.get(__instance)) {
                    if (enchantment instanceof onAttackedEnchantment) {
                        Wiz.Player().limbo.group.add(enchantment);
                        Wiz.atb(new AbstractGameAction() {
                            @Override
                            public void update() {
                                EnchantmentsField.Enchantments.get(__instance).remove(enchantment);
                                Wiz.atb(new UnlimboAction(enchantment));
                                ((onAttackedEnchantment) enchantment).EnchantedOnAttacked(__instance, damageAmount, info);
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        EnchantmentsManager.ActivateEnchantments(enchantment, __instance);
                                        isDone = true;
                                    }
                                });
                                isDone = true;
                            }});
                    }
                }
            }
        }
    }
    @SpirePatch(
            clz = CardGroup.class,
            method = "moveToExhaustPile"
    )
    public static class OnExhaust {
        public OnExhaust() {
        }
        @SpireInsertPatch(
                locator = PowersLocator.class
        )
        public static void Insert(CardGroup __instance, AbstractCard c) {
            for (AbstractCard enchantment : EnchantmentsField.Enchantments.get(Wiz.Player())) {
                if (enchantment instanceof onExhaustedEnchantment) {
                    Wiz.Player().limbo.group.add(enchantment);
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            EnchantmentsField.Enchantments.get(Wiz.Player()).remove(enchantment);
                            Wiz.atb(new UnlimboAction(enchantment));
                            ((onExhaustedEnchantment) enchantment).EnchantedOnExhaust(c);
                            addToBot(new AbstractGameAction() {
                                @Override
                                public void update() {
                                    EnchantmentsManager.ActivateEnchantments(enchantment, Wiz.Player());
                                    isDone = true;
                                }
                            });
                            isDone = true;
                        }});
                }
            }
        }
        private static class PowersLocator extends SpireInsertLocator {
            private PowersLocator() {
            }
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
                return offset(LineFinder.findInOrder(ctMethodToPatch, finalMatcher), 0);
            }
            private static int[] offset(int[] originalArr, int offset) {
                for(int i = 0; i < originalArr.length; ++i) {
                    originalArr[i] += offset;
                }
                return originalArr;
            }
        }
    }
    public static class ExhaustPileViewScreenPatches {

        public static boolean showCollection = false;
        private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CantripPile"));

        @SpirePatch(
                clz = ExhaustPileViewScreen.class,
                method = "open"
        )
        public static class OpenExhaustPileViewScreenPatch {
            @SpireInsertPatch(locator = ExhaustPileViewScreenPatches.OpenExhaustPileViewScreenPatchLocator.class)
            public static void Insert(ExhaustPileViewScreen _instance) {
                CardGroup group = ReflectionHacks.getPrivate(_instance, ExhaustPileViewScreen.class, "exhaustPileCopy");
                group.group.addAll(BanishedCards.group);
                for (AbstractCard c : group.group) {
                    c.resetAttributes();
                    c.unhover();
                    c.stopGlowing();
                    c.setAngle(0.0F, true);
                    c.lighten(true);
                }
            }
        }

        private static class OpenExhaustPileViewScreenPatchLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(ExhaustPileViewScreen.class, "hideCards");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "damage"
    )
    public static class MonsterDamage {
        public MonsterDamage() {
        }
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"damageAmount", "hadBlock"}
        )
        public static void Insert(AbstractMonster __instance, DamageInfo info, int damageAmount, @ByRef boolean[] hadBlock) {
            if (EnchantmentsField.Enchantments.get(__instance) != null && !AbstractDungeon.getCurrRoom().isBattleOver && info.type == DamageInfo.DamageType.NORMAL) {
                for (AbstractCard enchantment : EnchantmentsField.Enchantments.get(__instance)) {
                    if (enchantment instanceof onAttackedEnchantment) {
                        Wiz.Player().limbo.group.add(enchantment);
                        Wiz.atb(new AbstractGameAction() {
                            @Override
                            public void update() {
                                EnchantmentsField.Enchantments.get(__instance).remove(enchantment);
                                Wiz.atb(new UnlimboAction(enchantment));
                                ((onAttackedEnchantment) enchantment).EnchantedOnAttacked(__instance, damageAmount, info);
                                addToBot(new AbstractGameAction() {
                                    @Override
                                    public void update() {
                                        EnchantmentsManager.ActivateEnchantments(enchantment, __instance);
                                        isDone = true;
                                    }
                                });
                                isDone = true;
                            }});
                    }
                }
            }
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "decrementBlock");
                return offset(LineFinder.findInOrder(ctMethodToPatch, finalMatcher), 0);
            }
            private static int[] offset(int[] originalArr, int offset) {
                for(int i = 0; i < originalArr.length; ++i) {
                    originalArr[i] += offset;
                }
                return originalArr;
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "decrementBlock");
            return offset(LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), finalMatcher),0);
        }

        private static int[] offset(int[] originalArr, int offset) {
            for(int i = 0; i < originalArr.length; ++i) {
                originalArr[i] += offset;
            }

            return originalArr;
        }
    }
    public static void EmptyEnchantments(AbstractCreature owner){
        for (AbstractCard card : EnchantmentsField.Enchantments.get(owner)) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    EnchantmentsField.Enchantments.get(owner).remove(card);
                    if (card.type != AbstractCard.CardType.POWER) {
                        if (card.exhaust) {
                            ((AbstractDragonCard)card).energyCosts.put(TypeEnergyHelper.Mana.Charge,((AbstractDragonCard) card).energyCosts.get(TypeEnergyHelper.Mana.BaseCharge));
                            AbstractDungeon.player.exhaustPile.moveToExhaustPile(card);
                        } else {
                            ((AbstractDragonCard)card).energyCosts.put(TypeEnergyHelper.Mana.Charge,((AbstractDragonCard) card).energyCosts.get(TypeEnergyHelper.Mana.BaseCharge));
                            AbstractDungeon.player.discardPile.moveToDiscardPile(card);
                        }
                    }
                    card.initializeDescription();
                    isDone = true;
                }
            });
        }
    }
    public static void ActivateEnchantments(AbstractCard enchantment,AbstractCreature owner){
        if (((AbstractDragonCard)enchantment).energyCosts.get(TypeEnergyHelper.Mana.Charge) > 0){
            ((AbstractDragonCard)enchantment).energyCosts.put(TypeEnergyHelper.Mana.Charge,((AbstractDragonCard) enchantment).energyCosts.get(TypeEnergyHelper.Mana.Charge)-1);
        }
        if (((AbstractDragonCard) enchantment).energyCosts.get(TypeEnergyHelper.Mana.Charge) == 0){
            Wiz.att(new AbstractGameAction() {
                @Override
                public void update() {
                    ((AbstractDragonCard)enchantment).energyCosts.put(TypeEnergyHelper.Mana.Charge,
                            ((AbstractDragonCard) enchantment).energyCosts.get(TypeEnergyHelper.Mana.BaseCharge));
                    if (enchantment.type != AbstractCard.CardType.POWER) {
                        if (enchantment.exhaust) {
                            AbstractDungeon.player.exhaustPile.moveToExhaustPile(enchantment);
                        } else {
                            AbstractDungeon.player.discardPile.moveToDiscardPile(enchantment);
                        }
                    }
                    enchantment.initializeDescription();
                    isDone = true;
                }
            });
        }else {
            EnchantmentsField.Enchantments.get(owner).add(enchantment);
        }
    }
}

