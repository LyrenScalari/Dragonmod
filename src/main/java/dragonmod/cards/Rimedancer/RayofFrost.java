package dragonmod.cards.Rimedancer;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.IceArmor;
import dragonmod.DamageModifiers.Icons.FrostIcon;
import dragonmod.util.Wiz;
public class RayofFrost extends AbstractRimedancerCard {
    public static final String ID = RayofFrost.class.getSimpleName();
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == AbstractCard.CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    public RayofFrost(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(10,3);
        setBlock(6,3);
        BlockModifierManager.addModifier(this,new IceArmor(true));
        CardModifierManager.addModifier(this,new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FrostIcon.get()));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL));
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type == AbstractCard.CardType.SKILL) {
            Wiz.atb(new GainBlockAction(p,block));
        }
    }
}