package dragonmod.cards.Justicar;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import dragonmod.DragonMod;
import dragonmod.orbs.SanctuarySeal;

public class BlessedShield extends AbstractHolyCard {

    public static final String ID = BlessedShield.class.getSimpleName();
    public BlessedShield() {
        super(ID, 1,CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setMagic(3);
        setMagic2(6);
        setBlock(6,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BorderFlashEffect(Color.SKY.cpy())));
        addToBot(new GainBlockAction(p,block));
        AbstractCard that = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                DragonMod.Seals.add(new SanctuarySeal(magicNumber,SecondMagicNumber,that));
                isDone = true;
            }
        });
        super.use(p,m);
    }
}