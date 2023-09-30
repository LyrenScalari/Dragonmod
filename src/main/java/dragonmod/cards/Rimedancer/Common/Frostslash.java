package dragonmod.cards.Rimedancer.Common;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.CardMods.FrozenMod;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowIcicleAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class Frostslash extends AbstractRimedancerCard {
    public static final String ID = Frostslash.class.getSimpleName();
    public Frostslash(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(7,2);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Icicle tothrow = null;
        Icicle tothrow2 = null;
        for (AbstractOrb o : Wiz.adp().orbs) {
            if (o instanceof Icicle) {
                if (tothrow == null){
                    tothrow = (Icicle) o;
                } else if (tothrow2 == null){
                   tothrow2 = (Icicle) o;
                } else {
                    break;
                }
            }
        }
        if (tothrow != null){
            Wiz.atb(new ThrowIcicleAction(tothrow,m.hb, Color.CYAN));
        } else  Wiz.atb(new ThrowIcicleAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,m.hb,Color.CYAN));
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY);
        if (tothrow != null){
            Wiz.atb(new ThrowIcicleAction(tothrow2,m.hb, Color.CYAN));
        } else  Wiz.atb(new ThrowIcicleAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,m.hb,Color.CYAN));

        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
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
