package dragonmod.cards.Justicar;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import dragonmod.DragonMod;
import dragonmod.orbs.MightSeal;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.CustomTags;
import dragonmod.util.Wiz;


public class BlessingofMight extends AbstractHolyCard{

    public static final String ID = DragonMod.makeID(BlessingofMight.class.getSimpleName());
    public BlessingofMight() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        setMagic(3,1);
        setMagic2(8);
        tags.add(CustomTags.Blessing);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS)));
        Wiz.applyToSelfTemp(new DivineConvictionpower(p,p,magicNumber));
        addToBot(new VFXAction(new InflameEffect(AbstractDungeon.player)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new MightSeal(magicNumber, SecondMagicNumber));
                isDone = true;
            }
        });
        super.use(p,m);
    }
}