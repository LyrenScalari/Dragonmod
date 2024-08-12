package dragonmod.cards.Warden.uncommon.amethyst;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.ExploitAction;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.cards.Warden.uncommon.Solstice;
import dragonmod.cards.Warden.uncommon.amber.DreamLance;
import dragonmod.powers.Warden.HexPower;
import dragonmod.util.Wiz;

@NoCompendium
@NoPools
public class ShadowStrike extends AbstractReflexiveCard {

    public static final String ID = ShadowStrike.class.getSimpleName();
    public ShadowStrike(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setMagic(12,4);
        setBackgroundTexture(DragonMod.WARDEN_AMETHYST_ATTACK,DragonMod.WARDEN_AMETHYST_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new HexPower(m,m,magicNumber));
        Wiz.att(new ExploitAction(()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.atb(new DrawCardAction(2));
            }
        }, HexPower.POWER_ID,m));
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
