package dragonmod.cards.Warden.uncommon.amber;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.uncommon.Solstice;
import dragonmod.cards.Warden.uncommon.amethyst.ShadowStrike;
import dragonmod.util.Wiz;

@NoCompendium
@NoPools
public class DreamLance extends AbstractReflexiveCard {

    public static final String ID = DreamLance.class.getSimpleName();
    public DreamLance(){
        super(ID,1,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(10,3);
        setMagic(1,1);
        setBackgroundTexture(DragonMod.WARDEN_AMBER_ATTACK,DragonMod.WARDEN_AMBER_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
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

                            copy = p.drawPile.getTopCard().makeStatEquivalentCopy();
                            if (copy != null){
                                p.drawPile.removeCard(p.drawPile.getTopCard());
                                p.limbo.addToBottom(copy);
                                copy.current_x = 250;
                                copy.current_y = 250;
                                p.exhaustPile.moveToExhaustPile(copy);
                                p.limbo.removeCard(copy);
                            }
                        }
                        isDone = true;
                    }
                });
                isDone = true;
            }
        });
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
    }
    public AbstractCard getAmberCard(){
        return new DreamLance();
    }
    public AbstractCard getAmethystCard(){
        return new ShadowStrike();
    }
    public AbstractCard getEmeraldCard(){
        return new Solstice();
    }
}