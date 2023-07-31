package dragonmod.cards.Justicar;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.orbs.FortitudeSeal;


public class BlessingofFortitude extends AbstractHolyCard {

    public static final String ID = DragonMod.makeID(BlessingofFortitude.class.getSimpleName());
    public BlessingofFortitude() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(8,2);
        block = baseBlock = 8;
        setMagic(8);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        AbstractCard that = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new FortitudeSeal(magicNumber,magicNumber,that));
                isDone = true;
            }
        });
        super.use(p,m);
    }
}