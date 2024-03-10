package dragonmod.cards.Warden.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.uncommon.amber.DreamLance;
import dragonmod.cards.Warden.uncommon.amethyst.ShadowStrike;
import dragonmod.powers.Warden.HexPower;
import dragonmod.util.Wiz;

public class Solstice extends AbstractReflexiveCard {
    public static final String ID = Solstice.class.getSimpleName();
    public Solstice(){
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(10,3);
        setMagic(3,3);
        setReflectivePairing(new DreamLance(), new ShadowStrike());
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.drawPile.isEmpty()){
                    this.addToTop(new EmptyDeckShuffleAction());
                }
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (!p.drawPile.isEmpty()){
                            AbstractCard copy = p.drawPile.getTopCard().makeStatEquivalentCopy();
                            p.drawPile.removeCard(p.drawPile.getTopCard());
                            p.limbo.addToBottom(copy);
                            copy.current_x = 250;
                            copy.current_y = 250;
                            p.exhaustPile.moveToExhaustPile(copy);
                            p.limbo.removeCard(copy);
                        }
                        isDone = true;
                    }
                });
                isDone = true;
            }
        });
        Wiz.applyToEnemy(m,new HexPower(m,p,magicNumber));
    }
}
