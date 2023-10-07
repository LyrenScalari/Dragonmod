package dragonmod.cards.Justicar.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.Wiz;

public class RelentlessPursuit extends AbstractJusticarCard {
    public static final String ID = RelentlessPursuit.class.getSimpleName();
    public RelentlessPursuit(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setCustomVar("WOG",0,0);
        setVarCalculation("WOG", (m, base) -> {
            if (AbstractDungeon.player != null){
                int tmp = this.baseDamage;
                this.baseDamage = base;
                if (m != null)
                    super.calculateCardDamage(m);
                else
                    super.applyPowers();
                this.baseDamage = tmp;
                return damage + AbstractDungeon.player.currentBlock;
            } else {
                return base;
            }
        });

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,customVar("WOG"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.atb(new RemoveAllBlockAction(p,p));
    }
}