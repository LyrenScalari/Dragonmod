package DragonMod.cards.Ironclad;

import DragonMod.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DragonMod.DragonMod.makeID;
import static DragonMod.util.Wiz.atb;

public class MoltenShell extends AbstractEasyCard {
    public final static String ID = makeID("MoltenShell");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MoltenShell() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, CardColor.RED);
        baseBlock = 16;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        blck();
    }

    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }
}