package dragonmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import dragonmod.actions.OnBeingScriedInterface;
import dragonmod.actions.ScryCallbackAction;
import dragonmod.powers.Drifter.onScriedPower;
import dragonmod.relics.onScryRelic;
import dragonmod.interfaces.CardSeenScriedInterface;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.ArrayList;

@SpirePatch(clz = ScryAction.class, method = "update")
public class OnBeingScriedPatch
{
    public static ExprEditor Instrument()
    {
        return new ExprEditor()
        {
            @Override
            public void edit(MethodCall m) throws CannotCompileException
            {

                if (m.getMethodName().equals("moveToDiscardPile"))
                {
                    m.replace("$proceed($$);" +
                            "if ($1 instanceof "+ OnBeingScriedInterface.class.getName() + ") ((" + OnBeingScriedInterface.class.getName() + ")$1).onBeingScried();");
                }
            }
        };
    }

    @SpireInsertPatch(locator =  LocatorOpen.class, localvars = {"tmpGroup"})
    public static void Insert(ScryAction __instance, CardGroup tmpGroup)
    {
        for (AbstractCard c : tmpGroup.group)
        {
            if (c instanceof CardSeenScriedInterface)
            {
                ((CardSeenScriedInterface) c).onSeenScried();
            }
        }
    }
    @SpireInsertPatch(locator = LocatorClear.class)
    public static void InsertCallback(ScryAction __instance)
    {
        if (__instance instanceof ScryCallbackAction)
        {
            ((ScryCallbackAction)__instance).callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
        }
        for (AbstractPower p : AbstractDungeon.player.powers){
            if (p instanceof onScriedPower){
                ((onScriedPower) p).onCardsScried(AbstractDungeon.gridSelectScreen.selectedCards);
            }
        }
    }

    private static class LocatorOpen extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(GridCardSelectScreen.class, "open");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    private static class LocatorClear extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static void scry(CardGroup grp)
    {
        for (AbstractCard c : grp.group)
        {
            if (c instanceof CardSeenScriedInterface)
            {
                ((CardSeenScriedInterface) c).onSeenScried();
            }
        }
        for (AbstractRelic r : AbstractDungeon.player.relics){
            if (r instanceof onScryRelic){
                ((onScryRelic) r).onScry();
            }
        }
    }
}
