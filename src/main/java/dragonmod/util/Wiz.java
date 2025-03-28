package dragonmod.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dragonmod.actions.TimedVFXAction;
import dragonmod.powers.LosePowerPower;
import dragonmod.powers.LosePowerPowerStart;
import dragonmod.powers.NextTurnPowerPower;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Wiz {
    //The wonderful Wizard of Oz allows access to most easy compilations of data, or functions.

    public static AbstractPlayer Player() {
        return AbstractDungeon.player;
    }

    public static void forAllCardsInList(Consumer<AbstractCard> consumer, ArrayList<AbstractCard> cardsList) {
        for (AbstractCard c : cardsList) {
            consumer.accept(c);
        }
    }
    public static CardGroup hand() {
        return AbstractDungeon.player.hand;
    }

    public static CardGroup drawPile() {
        return AbstractDungeon.player.drawPile;
    }

    public static CardGroup discardPile() {
        return AbstractDungeon.player.discardPile;
    }
    public static AbstractMonster getRandomEnemy() {
        return AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);// 361
    }
    public static CardGroup deck() {
        return AbstractDungeon.player.masterDeck;
    }
    public static ArrayList<AbstractCard> getAllCardsInCardGroups(boolean includeHand, boolean includeExhaust) {
        ArrayList<AbstractCard> masterCardsList = new ArrayList<>();
        masterCardsList.addAll(AbstractDungeon.player.drawPile.group);
        masterCardsList.addAll(AbstractDungeon.player.discardPile.group);
        if (includeHand) {
            masterCardsList.addAll(AbstractDungeon.player.hand.group);
        }
        if (includeExhaust) {
            masterCardsList.addAll(AbstractDungeon.player.exhaustPile.group);
        }
        return masterCardsList;
    }

    public static void forAllMonstersLiving(Consumer<AbstractMonster> consumer) {
        for (AbstractMonster m : getEnemies()) {
            consumer.accept(m);
        }
    }
    public static void dmg(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        atb(new DamageAction(target, info, effect));
    }
    public static void dmgtop(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        att(new DamageAction(target, info, effect));
    }
    public static void dmg(AbstractCreature target, DamageInfo info) {
        dmg(target, info, AbstractGameAction.AttackEffect.NONE);
    }

    public static void block(AbstractCreature target, int amount) {
        atb(new GainBlockAction(target, amount));
    }
    public static ArrayList<AbstractMonster> getEnemies() {
        ArrayList<AbstractMonster> monsters = new ArrayList<>(AbstractDungeon.getMonsters().monsters);
        monsters.removeIf(m -> m.isDead || m.isDying);
        return monsters;
    }

    public static ArrayList<AbstractCard> getCardsMatchingPredicate(Predicate<AbstractCard> pred) {
        return getCardsMatchingPredicate(pred, false);
    }

    public static ArrayList<AbstractCard> getCardsMatchingPredicate(Predicate<AbstractCard> pred, boolean allcards) {
        if (allcards) {
            ArrayList<AbstractCard> cardsList = new ArrayList<>();
            for (AbstractCard c : CardLibrary.getAllCards()) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            return cardsList;
        } else {
            ArrayList<AbstractCard> cardsList = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
                if (pred.test(c)) cardsList.add(c.makeStatEquivalentCopy());
            }
            return cardsList;
        }
    }

    public static AbstractCard returnTrulyRandomPrediCardInCombat(Predicate<AbstractCard> pred, boolean allCards) {
        return getRandomItem(getCardsMatchingPredicate(pred, allCards));
    }


    public static AbstractCard returnTrulyRandomPrediCardInCombat(Predicate<AbstractCard> pred) {
        return returnTrulyRandomPrediCardInCombat(pred, false);
    }

    public static <T> T getRandomItem(ArrayList<T> list, Random rng) {
        return list.isEmpty() ? null : list.get(rng.random(list.size() - 1));
    }

    public static <T> T getRandomItem(ArrayList<T> list) {
        return getRandomItem(list, AbstractDungeon.cardRandomRng);
    }

    private static boolean actuallyHovered(Hitbox hb) {
        return InputHelper.mX > hb.x && InputHelper.mX < hb.x + hb.width && InputHelper.mY > hb.y && InputHelper.mY < hb.y + hb.height;
    }

    public static boolean isInCombat() {
        return CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
    }

    public static void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public static void att(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public static void vfx(AbstractGameEffect gameEffect) {
        atb(new VFXAction(gameEffect));
    }

    public static void vfx(AbstractGameEffect gameEffect, float duration) {
        atb(new VFXAction(gameEffect, duration));
    }

    public static void tfx(AbstractGameEffect gameEffect) {
        atb(new TimedVFXAction(gameEffect));
    }

    public static void makeInHand(AbstractCard c, int i) {
        atb(new MakeTempCardInHandAction(c, i));
    }

    public static void makeInHand(AbstractCard c) {
        makeInHand(c, 1);
    }

    public static void shuffleIn(AbstractCard c, int i) {
        atb(new MakeTempCardInDrawPileAction(c, i, true, true));
    }

    public static void shuffleIn(AbstractCard c) {
        shuffleIn(c, 1);
    }

    public static void topDeck(AbstractCard c, int i) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, i, false, true));
    }

    public static void topDeck(AbstractCard c) {
        topDeck(c, 1);
    }

    public static void applyToEnemy(AbstractMonster m, AbstractPower po) {
        atb(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }
    public static void applyToEnemyTemp(AbstractMonster m,AbstractPower po) {
        atb(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
        atb(new ApplyPowerAction(m, AbstractDungeon.player, new LosePowerPower(m, po, po.amount)));
    }
    public static void applyToEnemyTop(AbstractMonster m, AbstractPower po) {
        att(new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount));
    }

    public static void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public static void applyToSelfTop(AbstractPower po) {
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public static void applyToSelfTemp(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LosePowerPower(AbstractDungeon.player, po, po.amount)));
    }
    public static void applyToSelfTempstart(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LosePowerPowerStart(AbstractDungeon.player, po, po.amount)));
    }
    public static void applyToSelfTempstartTop(AbstractPower po) {
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LosePowerPowerStart(AbstractDungeon.player, po, po.amount)));
        att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }
    public static void applyToSelfNextTurn(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NextTurnPowerPower(AbstractDungeon.player, po)));
    }
}