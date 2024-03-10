package dragonmod.cards.Warden.rare.amber;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.util.Wiz;
@NoCompendium
public class WaterfowlDance extends AbstractReflexiveCard {

    public static final String ID = WaterfowlDance.class.getSimpleName();
    public WaterfowlDance(){
        super(ID,2, AbstractCard.CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        setDamage(6,2);
        setMagic(4,2);
        setBackgroundTexture(DragonMod.WARDEN_AMBER_ATTACK,DragonMod.WARDEN_AMBER_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        Wiz.atb(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        Wiz.atb(new ChangeStanceAction(new NeutralStance()));
        Wiz.atb(new DrawCardAction(1));
    }
}