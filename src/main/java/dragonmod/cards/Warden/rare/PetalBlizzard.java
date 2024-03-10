package dragonmod.cards.Warden.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.powers.Warden.AmberBlossomPower;
import dragonmod.powers.Warden.AmethystBlossomPower;
import dragonmod.powers.Warden.EmeraldBlossomPower;
import dragonmod.util.Wiz;

public class PetalBlizzard extends AbstractWardenCard {
    public static final String ID = PetalBlizzard.class.getSimpleName();

    public PetalBlizzard() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        setDamage(4, 2);
        setMagic(4,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            int Blossom = AbstractDungeon.miscRng.random(0,2);
            Wiz.atb(new DamageRandomEnemyAction(new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            switch (Blossom){
                case 0:Wiz.applyToSelf(new EmeraldBlossomPower(1)); break;
                case 1:Wiz.applyToSelf(new AmethystBlossomPower(p,p,1)); break;
                case 2:Wiz.applyToSelf(new AmberBlossomPower(p,p,1)); break;
            }
        }
    }
}
