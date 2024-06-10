package dragonmod.cards.Warden.starter.amber;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractReflexiveCard;
import dragonmod.util.BlossomManager;
import dragonmod.util.Wiz;
@NoCompendium
@NoPools
public class SupernatrualWind extends AbstractReflexiveCard {
    public static final String ID = SupernatrualWind.class.getSimpleName();
    public SupernatrualWind(){
        super(ID,1,CardType.ATTACK,CardRarity.SPECIAL,CardTarget.ENEMY);
        setDamage(3);
        setMagic(3,1);
        setBackgroundTexture(DragonMod.WARDEN_AMBER_ATTACK,DragonMod.WARDEN_AMBER_ATTACK_P);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), DragonMod.getRandomSlash());
        }
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                BlossomManager.addEmeraldBlossom(1);
            }
        });
    }
}

