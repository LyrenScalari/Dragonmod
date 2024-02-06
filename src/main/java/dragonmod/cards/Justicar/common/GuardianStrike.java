package dragonmod.cards.Justicar.common;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.cards.Justicar.AbstractJusticarCard;
import dragonmod.util.AbstractNotOrb;
import dragonmod.util.AbstractSeal;
import dragonmod.util.HymnManager;
import dragonmod.util.Wiz;

import java.util.ArrayList;

public class GuardianStrike extends AbstractJusticarCard {
    public static final String ID = GuardianStrike.class.getSimpleName();
    public GuardianStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        setDamage(5,4);
        tags.add(CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> VerseChoices = new ArrayList<>();
        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL));
        if (HymnManager.ActiveVerses.size() == 2){
            ((AbstractSeal)HymnManager.ActiveVerses.get(1)).Chant();
        } else if  (HymnManager.ActiveVerses.size() >= 3){
            for (AbstractNotOrb seal : HymnManager.ActiveVerses){
                if (HymnManager.ActiveVerses.get(0) != seal && !VerseChoices.contains(((AbstractSeal)seal).VerseSource)){
                    VerseChoices.add(((AbstractSeal)seal).VerseSource);
                }
            }
            if (HymnManager.ActiveVerses.size() <= 4){
                Wiz.atb(new SelectCardsCenteredAction(VerseChoices,cardStrings.EXTENDED_DESCRIPTION[0],(cards)->{
                    for (AbstractNotOrb seal : HymnManager.ActiveVerses){
                        if (((AbstractSeal)seal).VerseSource.uuid.equals(cards.get(0).uuid) && HymnManager.ActiveVerses.get(0) != seal){
                            ((AbstractSeal)seal).Chant();
                            break;
                        }
                    }
                }));
            } else {
                ArrayList<AbstractCard> PrunedChoices = new ArrayList<>();
                for (int i = 0; i < 3;){
                    AbstractCard choice = VerseChoices.get(AbstractDungeon.miscRng.random(0,HymnManager.ActiveVerses.size()-1));
                    if (!PrunedChoices.contains(choice)){
                        PrunedChoices.add(choice);
                        i++;
                    }
                }
                Wiz.atb(new SelectCardsCenteredAction(PrunedChoices,cardStrings.EXTENDED_DESCRIPTION[0],(cards)->{
                    for (AbstractNotOrb seal : HymnManager.ActiveVerses){
                        if (((AbstractSeal)seal).VerseSource.uuid.equals(cards.get(0).uuid) && HymnManager.ActiveVerses.get(0) != seal){
                            ((AbstractSeal)seal).Chant();
                            break;
                        }
                    }
                }));
            }
        }
    }
}
