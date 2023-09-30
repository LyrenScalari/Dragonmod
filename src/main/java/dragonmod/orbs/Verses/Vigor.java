package dragonmod.orbs.Verses;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import dragonmod.DragonMod;
import dragonmod.actions.CureAction;
import dragonmod.util.AbstractSeal;
import dragonmod.util.Wiz;

public class Vigor extends AbstractSeal {
    public static final String ORB_ID = DragonMod.makeID("Vigor");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    public Vigor(int Pow, int Pain) {
        super();
        name = orbString.NAME;
        basePainAmount = PainAmount = Pain;
        BreakAmount = Pow;
    }
    public void Coda(){
        Wiz.atb(new CureAction(BreakAmount));
    }
    public void Chant(){
        super.Chant();
        switch (PainAmount){
            case 3 :{
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f , DESCRIPTIONS[4],true));
                    }
                });
                break;
            }
            case 2:{
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f , DESCRIPTIONS[5],true));
                    }
                });
                break;
            }
            case 1:{
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f , DESCRIPTIONS[6],true));
                    }
                });
                break;
            }
        }

    }
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PainAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + BreakAmount + DESCRIPTIONS[3];
    }
}
