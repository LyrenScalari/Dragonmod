package dragonmod.cards.Rimedancer.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.Chillpower;
import dragonmod.util.Wiz;

public class LeadAstray extends AbstractRimedancerCard {

    public static final String ID = LeadAstray.class.getSimpleName();
    public LeadAstray(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setMagic(2);
        setBlock(7);
        setMagic2(2,2);
    }
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == AbstractCard.CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    public float[] _lightsOutGetXYRI() {
        return new float[] {hb.x, hb.y, 100f, 1.25f};
    }

    public Color[] _lightsOutGetColor() {
        return new Color[] {Color.SKY.cpy()};
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
            Wiz.applyToEnemy(m,new Chillpower(m,p,SecondMagicNumber));
        }
    }
}