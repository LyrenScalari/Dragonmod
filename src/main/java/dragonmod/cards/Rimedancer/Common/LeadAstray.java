package dragonmod.cards.Rimedancer.Common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class LeadAstray extends AbstractRimedancerCard {

    public static final String ID = LeadAstray.class.getSimpleName();
    public LeadAstray(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setMagic(2);
        setBlock(7,3);
    }
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == AbstractCard.CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY));
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
        Wiz.block(p,block);
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&
                ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type
                        == AbstractCard.CardType.SKILL) {
            Wiz.applyToSelf(new BlurPower(p,magicNumber));
        }
    }
}