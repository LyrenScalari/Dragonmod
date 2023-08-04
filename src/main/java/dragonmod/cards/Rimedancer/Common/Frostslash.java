package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.FrozenMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.util.Wiz;

public class Frostslash extends AbstractRimedancerCard {
    public static final String ID = Frostslash.class.getSimpleName();
    public Frostslash(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(7,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.dmg(m,new DamageInfo(p,baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (!upgraded){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractCard target = p.hand.getRandomCard(true);
                    CardModifierManager.addModifier(target,new FrozenMod());
                    isDone = true;
                }
            });
        } else {
            Wiz.atb(new SelectCardsCenteredAction(p.hand.group,1, cardStrings.EXTENDED_DESCRIPTION[0],false,(card)->true,(cards)->{
                for (AbstractCard c : cards){
                    CardModifierManager.addModifier(c,new FrozenMod());
                }
            }));
        }
    }
}
