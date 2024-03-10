package dragonmod.cards.Warden.uncommon.amber;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.powers.Warden.EmeraldBlossomPower;
import dragonmod.util.Wiz;
@NoCompendium
@NoPools
public class FieryDance extends AbstractReflexiveCard {

    public static final String ID = FieryDance.class.getSimpleName();
    public FieryDance(){
        super(ID,2, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        setDamage(7,3);
        setBackgroundTexture(DragonMod.WARDEN_AMBER_ATTACK,DragonMod.WARDEN_AMBER_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.atb(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                if (DrawCardAction.drawnCards.get(0).type == CardType.ATTACK){
                    Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    Wiz.applyToSelf(new EmeraldBlossomPower(1));
                }
                isDone = true;
            }
        }));

    }
}
