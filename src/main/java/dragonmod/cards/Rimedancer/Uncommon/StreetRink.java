package dragonmod.cards.Rimedancer.Uncommon;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dragonmod.CardMods.FrozenMod;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.orbs.Sleet;
import dragonmod.util.Wiz;

public class StreetRink extends AbstractRimedancerCard {
    public static final String ID = StreetRink.class.getSimpleName();

    public StreetRink() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setMagic(1,1);
        setMagic2(2);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            Wiz.atb(new ChannelAction(new Sleet()));
        }
        if (!upgraded){
            Wiz.applyToEnemy(m,new VulnerablePower(m,SecondMagicNumber,false));
        } else {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                Wiz.applyToEnemy(mo,new VulnerablePower(mo,SecondMagicNumber,false));
            }
        }
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard target = p.hand.getRandomCard(true);
                CardModifierManager.addModifier(target,new FrozenMod());
                isDone = true;
            }
        });
    }
    @Override
    public void upgrade() {
        super.upgrade();
        if (!upgraded) {
            upgradeName();
            target = CardTarget.ALL_ENEMY;
        }

    }
}
