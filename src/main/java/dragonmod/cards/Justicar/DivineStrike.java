package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import dragonmod.DragonMod;
import dragonmod.util.CustomTags;

public class DivineStrike extends AbstractHolyCard {


    // TEXT DECLARATION

    public static final String ID = DragonMod.makeID(DivineStrike.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public DivineStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(8,2);
        setMagic(2);
        tags.add(CustomTags.Smite);
        isMultiDamage = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p,damage,damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new VFXAction(new WhirlwindEffect()));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (mo.hasPower(WeakPower.POWER_ID)){
                addToBot(new VFXAction(new LightningEffect(mo.hb_x,mo.hb_y)));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,new DamageInfo(p,multiDamage[AbstractDungeon.getCurrRoom().monsters.monsters.indexOf(mo)],damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            }
        }
        addToBot(new ApplyPowerAction(m,p,new WeakPower(m,magicNumber,false)));
        super.use(p,m);
    }
}