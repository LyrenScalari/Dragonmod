package dragonmod.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import dragonmod.DragonMod;
import dragonmod.cards.Rimedancer.Special.*;
import dragonmod.cards.Rimedancer.Starter.*;
import dragonmod.relics.Rimedancer.CryoniteShard;
import dragonmod.ui.EnergyOrbRimedancer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static dragonmod.DragonMod.*;
import static dragonmod.characters.TheRimedancer.Enums.Rimedancer_Cyan_COLOR;


public class TheRimedancer extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(DragonMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static PlayerClass THE_RIMEDANCER;
        @SpireEnum(name = "Rimedancer_Cyan_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor Rimedancer_Cyan_COLOR;
        @SpireEnum(name = "Rimedancer_Cyan_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 65;
    public static final int MAX_HP = 65;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("TheRimedancer");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;
    public static float[] layerSpeeds = new float[]{-20.0F, 20.0F, -40.0F, 40.0F, 0.0F};
    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============


    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public TheRimedancer(String name, PlayerClass setClass) {
        super(name, setClass, new EnergyOrbRimedancer(),
                new SpineAnimation(JUSTICAR_SKELETON_ATLAS,JUSTICAR_SKELETON_JSON,1.0f));


        // =============== TEXTURES, ENERGY, LOADOUT =================

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DragonkinMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                JUSTICAR_SHOULDER_2, // campfire pose
                JUSTICAR_SHOULDER_1, // another campfire pose
                JUSTICAR_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 217.0F, 380.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================

        loadAnimation(
                JUSTICAR_SKELETON_ATLAS,
                JUSTICAR_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        AnimationState.TrackEntry e1 = state.setAnimation(1, "WingFlap", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 80.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 300.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");
        for (int i = 0; i < 4 ; i++){
            retVal.add(DragonMod.makeID(RimedancerStrike.ID));
        }
        retVal.add(DragonMod.makeID(CrystalChakram.ID));

        for (int i = 0; i < 4 ; i++){
            retVal.add(DragonMod.makeID(RimedancerDefend.ID));
        }
        retVal.add(DragonMod.makeID(HailstoneHaze.ID));

        retVal.add(DragonMod.makeID(FrostyCaltrops.ID));
        retVal.add(DragonMod.makeID(Nightrunner.ID));
        retVal.add(DragonMod.makeID(Ambush.ID));
        retVal.add(DragonMod.makeID(SilverGlimmer.ID));
        retVal.add(DragonMod.makeID(BladedSleeves.ID));
        return retVal;
    }

    // Starting Relics
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(CryoniteShard.ID);
        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }
    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e1 = this.state.setAnimation(1, "Hurt", false);
            this.state.addAnimation(1, "WingFlap", true, 0.0F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Hurt", false);
            this.state.addAnimation(0, "animation", true, 0.0F);
            e.setTimeScale(1.0F);
            e1.setTimeScale(1.0f);
        }

        super.damage(info);
    }
    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_FLAME_BARRIER";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Rimedancer_Cyan_COLOR;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return RIMEDANCER_CYAN;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new HailstoneHaze();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new TheRimedancer(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return RIMEDANCER_CYAN;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return RIMEDANCER_CYAN;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.LIGHTNING,
                AbstractGameAction.AttackEffect.POISON,
                AbstractGameAction.AttackEffect.SLASH_HEAVY};
    }
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel("images/scenes/defect1.png", "ORB_LIGHTNING_EVOKE"));
        panels.add(new CutscenePanel("images/scenes/defect2.png", "POWER_MANTRA"));
        panels.add(new CutscenePanel("images/scenes/defect3.png", "POWER_MANTRA"));
        return panels;
    }
    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }
/*    public String getSensoryStoneText()
    {
        return TEXT[3];
    }*/

}
