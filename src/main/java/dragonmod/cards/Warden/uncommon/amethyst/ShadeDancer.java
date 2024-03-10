package dragonmod.cards.Warden.uncommon.amethyst;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import dragonmod.DragonMod;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.powers.Warden.EmeraldBlossomPower;
import dragonmod.util.Wiz;
@NoCompendium
@NoPools
public class ShadeDancer extends AbstractReflexiveCard {

    public static final String ID = ShadeDancer.class.getSimpleName();
    public ShadeDancer(){
        super(ID,1, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        setDamage(7,2);
        setBackgroundTexture(DragonMod.WARDEN_AMETHYST_ATTACK,DragonMod.WARDEN_AMETHYST_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new DrawCardAction(1));
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
                Wiz.applyToSelf(new EmeraldBlossomPower(1));
            }
        }, PoisonPower.POWER_ID,m));

    }
}
