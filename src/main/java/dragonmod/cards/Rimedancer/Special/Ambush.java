package dragonmod.cards.Rimedancer.Special;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.cards.Draconic.AbstractDraconicCard;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import java.util.ArrayList;
import java.util.List;

public class Ambush extends AbstractDraconicCard {
    public static final String ID = Ambush.class.getSimpleName();
    public Ambush(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
        setDamage(6,2);
        setMagic(2);
        tags.add(EnchantmentsManager.Cantrip);
        SoulboundField.soulbound.set(this,true);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(CantripTooltip.TEXT[0], CantripTooltip.TEXT[1]));
        return retVal;
    }
    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retval = new ArrayList<>();
        retval.add(CantripTooltip.TEXT[0]);
        return retval;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.applyToEnemy(m,new VulnerablePower(m,magicNumber,false));
    }
}
