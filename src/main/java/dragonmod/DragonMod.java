package dragonmod;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import dragonmod.characters.TheJusticar;
import dragonmod.characters.TheRimedancer;
import dragonmod.potions.Dragonkin.DraughtofFervor;
import dragonmod.potions.Dragonkin.GatlokBrew;
import dragonmod.potions.Dragonkin.NaruuinsGlow;
import dragonmod.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;
import dragonmod.patches.TemporalStressField;
import dragonmod.patches.TemporalStressField.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import static dragonmod.characters.TheJusticar.Enums.Justicar_Red_COLOR;
import static dragonmod.characters.TheJusticar.Enums.THE_JUSTICAR;
import static dragonmod.characters.TheRimedancer.Enums.THE_RIMEDANCER;
import static dragonmod.characters.TheWarden.Enums.THE_WARDEN;
import static dragonmod.characters.TheWarden.Enums.Warden_Bronze_COLOR;
import static dragonmod.characters.TheRimedancer.Enums.Rimedancer_Cyan_COLOR;
@SpireInitializer
public class DragonMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber ,
        RelicGetSubscriber,
        PreMonsterTurnSubscriber,
        OnPlayerTurnStartSubscriber,
        StartGameSubscriber,
        OnStartBattleSubscriber {
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.
    private static final String resourcesFolder = "dragonmod";
    public static Texture DIVINE_ARMOR_ICON;
    public static Texture FLAME_BARRIER_ICON;
    public static Texture ICE_ARMOR_ICON;
    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }
    //Generic char select
    public static final Color JUSTICAR_RED = CardHelper.getColor(209.0f, 53.0f, 18.0f);
    public static final Color WARDEN_BRONZE = CardHelper.getColor(209.0f, 180.0f, 18.0f);
    public static final Color RIMEDANCER_CYAN = CardHelper.getColor(18.0f, 180.0f, 209.0f);
    private static final String MODNAME = "Dragon Mod";
    private static final String AUTHOR = "Silver";
    private static final String DESCRIPTION = "4 new Spire slaying hero's rise from origins far beyond the spire. each with varied tools and skills for climbing the tower and escaping to return to their lives";

    //Justicar assets
    private static final String JUSTICAR_RED_BUTTON = characterPath("Justicar/select/Dragonbutton.png");
    private static final String JUSTICAR_RED_PORTRAIT = characterPath("Justicar/select/dragonbg.png");
    private static final String JUSTICAR_RED_ATTACK = characterPath("Justicar/cardback/attack.png");
    private static final String JUSTICAR_RED_ATTACK_P = characterPath("Justicar/cardback/attack_p.png");
    private static final String JUSTICAR_RED_SKILL = characterPath("Justicar/cardback/skill.png");
    private static final String JUSTICAR_RED_SKILL_P = characterPath("Justicar/cardback/skill_p.png");
    private static final String JUSTICAR_RED_POWER = characterPath("Justicar/cardback/power.png");
    private static final String JUSTICAR_RED_POWER_P = characterPath("Justicar/cardback/power_p.png");
    private static final String JUSTICAR_RED_ENERGY_ORB = characterPath("Justicar/cardback/energy_orb.png");
    private static final String JUSTICAR_RED_ENERGY_ORB_P = characterPath("Justicar/cardback/energy_orb_p.png");
    private static final String JUSTICAR_RED_SMALL_ORB = characterPath("Justicar/cardback/small_orb.png");
    private static final String HOLY_ORB = characterPath("Justicar/cardback/energy_orb.png");
    private static final String HOLY_ORB_P = characterPath("Justicar/cardback/energy_orb_p.png");
    private static final String PRIMAL_ORB = characterPath("Justicar/cardback/energy_orb.png");
    private static final String PRIMAL_ORB_P = characterPath("Justicar/cardback/energy_orb_p.png");
    public static final String JUSTICAR_SHOULDER_1 = characterPath("Justicar/animation/Dragonkinshoulder.png");
    public static final String JUSTICAR_SHOULDER_2 = characterPath("Justicar/animation/Dragonkinshoulder2.png");
    public static final String JUSTICAR_CORPSE = characterPath("Justicar/animation/DragonkinCorpse.png");
    public static final String JUSTICAR_SKELETON_ATLAS = characterPath("Justicar/animation/TheDragonkin.atlas");
    public static final String JUSTICAR_SKELETON_JSON = characterPath("Justicar/animation/TheDragonkin.json");
    //Warden card assets
    private static final String WARDEN_BRONZE_ATTACK = characterPath("Warden/cardback/attack.png");
    private static final String WARDEN_BRONZE_ATTACK_P =  characterPath("Warden/cardback/attack_p.png");
    private static final String WARDEN_BRONZE_SKILL = characterPath("Warden/cardback/bg_skill.png");
    private static final String WARDEN_BRONZE_SKILL_P = characterPath("Warden/cardback/skill_p.png");
    private static final String WARDEN_BRONZE_POWER = characterPath("Warden/cardback/power.png");
    private static final String WARDEN_BRONZE_POWER_P = characterPath("Warden/cardback/power_p.png");
    private static final String WARDEN_BRONZE_ENERGY_ORB = characterPath("Warden/cardback/energy_orb.png");
    private static final String WARDEN_BRONZE_ENERGY_ORB_P= characterPath("Warden/cardback/energy_orb_p.png");
    private static final String WARDEN_BRONZE_SMALL_ORB = characterPath("Warden/cardback/small_orb.png");
    //Rimedancer card assets
    private static final String RIMEDANCER_CYAN_ATTACK = characterPath("Rimedancer/cardback/attack.png");
    private static final String RIMEDANCER_CYAN_ATTACK_P =  characterPath("Rimedancer/cardback/attack_p.png");
    private static final String RIMEDANCER_CYAN_SKILL = characterPath("Rimedancer/cardback/bg_skill.png");
    private static final String RIMEDANCER_CYAN_SKILL_P = characterPath("Rimedancer/cardback/skill_p.png");
    private static final String RIMEDANCER_CYAN_POWER = characterPath("Rimedancer/cardback/power.png");
    private static final String RIMEDANCER_CYAN_POWER_P = characterPath("Rimedancer/cardback/power_p.png");
    //Vars
    public static ArrayList<AbstractNotOrb> Seals = new ArrayList<>();
    public static int StatusesCycledThisCombat = 0;
    public static int StatusesCycledThisTurn = 0;
    public static int CardsCycledThisCombat = 0;
    public static int CardsCycledThisTurn = 0;
    public static int BurnsCycledThisCombat = 0;
    public static int BurnsCycledThisTurn = 0;
    public static boolean damagetaken = false;
    public static final String ENABLE_CHIMERA_CROSSOVER = "Enable Chimera Crossover";
    public static boolean enableChimeraCrossover = true;
    public static TemporalStressUI StressUI;
    public static boolean renderStressUI = false;
    public static final String SHOW_SUBTYPE_TUTORIAL = "Duality Tutorial Seen";
    public static boolean showSubtypeTutorial = true;
    public static final String SHOW_TS_TUTORIAL = "Temporal Stress Tutorial Seen";
    public static boolean showTsTutorial = true;
    public static SpireConfig justicarConfig;
    public static UIStrings uiStrings;
    public static boolean DecayStagger = false;
    public static final String DOVAH_FONT = resourcePath("Font/DovahkiinItalic-2BDv.ttf");
    public static BitmapFont DovahFont;
    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {
        new DragonMod();
    }
    @SpireEnum
    public static AbstractCard.CardTags Field;
    public DragonMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");
        BaseMod.addColor(Justicar_Red_COLOR, JUSTICAR_RED.cpy(), JUSTICAR_RED.cpy(), JUSTICAR_RED.cpy(),
                JUSTICAR_RED.cpy(), JUSTICAR_RED.cpy(), JUSTICAR_RED.cpy(), JUSTICAR_RED.cpy(),
                JUSTICAR_RED_ATTACK, JUSTICAR_RED_SKILL, JUSTICAR_RED_POWER, JUSTICAR_RED_ENERGY_ORB,
                JUSTICAR_RED_ATTACK_P, JUSTICAR_RED_SKILL_P, JUSTICAR_RED_POWER_P,
                JUSTICAR_RED_ENERGY_ORB_P, JUSTICAR_RED_SMALL_ORB);
        BaseMod.addColor(Warden_Bronze_COLOR, WARDEN_BRONZE.cpy(), WARDEN_BRONZE.cpy(), WARDEN_BRONZE.cpy(),
                WARDEN_BRONZE.cpy(), WARDEN_BRONZE.cpy(), WARDEN_BRONZE.cpy(), WARDEN_BRONZE.cpy(),
                WARDEN_BRONZE_ATTACK, WARDEN_BRONZE_SKILL, WARDEN_BRONZE_POWER, WARDEN_BRONZE_ENERGY_ORB,
                WARDEN_BRONZE_ATTACK_P, WARDEN_BRONZE_SKILL_P, WARDEN_BRONZE_POWER_P,
                WARDEN_BRONZE_ENERGY_ORB_P, WARDEN_BRONZE_SMALL_ORB);
        BaseMod.addColor(Rimedancer_Cyan_COLOR, RIMEDANCER_CYAN.cpy(), RIMEDANCER_CYAN.cpy(), RIMEDANCER_CYAN.cpy(),
                RIMEDANCER_CYAN.cpy(), RIMEDANCER_CYAN.cpy(), RIMEDANCER_CYAN.cpy(), RIMEDANCER_CYAN.cpy(),
                RIMEDANCER_CYAN_ATTACK, RIMEDANCER_CYAN_SKILL, RIMEDANCER_CYAN_POWER, WARDEN_BRONZE_ENERGY_ORB,
                RIMEDANCER_CYAN_ATTACK_P,RIMEDANCER_CYAN_SKILL_P, RIMEDANCER_CYAN_POWER_P,
                WARDEN_BRONZE_ENERGY_ORB_P, WARDEN_BRONZE_SMALL_ORB);
        Properties justicarDefaults = new Properties();
        justicarDefaults.getProperty("Duality Tutorial Seen","TRUE");
        justicarDefaults.getProperty("Temporal Stress Tutorial Seen","TRUE");
        justicarDefaults.getProperty("Enable Chimera Crossover","TRUE");
        try {
            justicarConfig = new SpireConfig("The Justicar", "DragonkinMod", justicarDefaults);
        } catch (IOException e) {
            logger.error("DragonkinMod SpireConfig initialization failed:");
            e.printStackTrace();
        }
        logger.info("Justicar CONFIG OPTIONS LOADED:");
        logger.info("Duality tutorial seen: " + justicarConfig.getString("Duality Tutorial Seen") + ".");
        logger.info("Temporal Stress tutorial seen: " + justicarConfig.getString("Temporal Stress Tutorial Seen") + ".");
        logger.info("Enable Chimera Crossover: " + justicarConfig.getString("Blessing Tutorial Seen") + ".");
        logger.info("Done creating the color");


        logger.info("Adding mod settings");
        logger.info("Done adding mod settings");
    }

    @Override
    public void receivePostInitialize() {
        //This loads the image used as an icon in the in-game mods menu.
        logger.info("Loading badge image and mod options");
        DIVINE_ARMOR_ICON = ImageMaster.loadImage("theDragonkinResources/images/ui/DivineArmor.png");
        FLAME_BARRIER_ICON = ImageMaster.loadImage("theDragonkinResources/images/ui/FlameBarrier.png");
        ICE_ARMOR_ICON = ImageMaster.loadImage("theDragonkinResources/images/ui/IceArmor.png");
        DovahFont = prepFont(new FreeTypeFontGenerator(Gdx.files.internal(DOVAH_FONT)), 46f, false);
        Texture badgeTexture = TextureLoader.getTexture(resourcePath("badge.png"));
        ModPanel settingsPanel = new ModPanel();

        // Create the on/off button:
        float currentYposition = 740f;
        float spacingY = 55f;
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":UIText");
        ModLabeledToggleButton DualityButton = new ModLabeledToggleButton(uiStrings.TEXT[0], 350.0f, currentYposition, Settings.CREAM_COLOR, FontHelper.charDescFont,
                justicarConfig.getBool(SHOW_SUBTYPE_TUTORIAL), settingsPanel, (label) -> {}, (button) -> {
            justicarConfig.setBool(SHOW_SUBTYPE_TUTORIAL, button.enabled);
            showSubtypeTutorial = button.enabled;
            try {justicarConfig.save();} catch (IOException e) {e.printStackTrace();}
        });
        currentYposition -= spacingY;
        ModLabeledToggleButton TemporalStressButton = new ModLabeledToggleButton(uiStrings.TEXT[2], 350.0f, currentYposition, Settings.CREAM_COLOR, FontHelper.charDescFont,
                justicarConfig.getBool(SHOW_TS_TUTORIAL), settingsPanel, (label) -> {}, (button) -> {
            justicarConfig.setBool(SHOW_TS_TUTORIAL, button.enabled);
            showTsTutorial = button.enabled;
            try {justicarConfig.save();} catch (IOException e) {e.printStackTrace();}
        });
        currentYposition -= spacingY;
        //Starting position
        //Button already multiplies by Settings.scale, so we pass raw X and Y values
        ModLabeledToggleButton enableChimeraCrossoverButton = new ModLabeledToggleButton(uiStrings.TEXT[1], 350.0f, currentYposition, Settings.CREAM_COLOR, FontHelper.charDescFont,
                justicarConfig.getBool(ENABLE_CHIMERA_CROSSOVER), settingsPanel, (label) -> {}, (button) -> {
            justicarConfig.setBool(ENABLE_CHIMERA_CROSSOVER, button.enabled);
            enableChimeraCrossover = button.enabled;
            try {justicarConfig.save();} catch (IOException e) {e.printStackTrace();}
        });
        settingsPanel.addUIElement(DualityButton);
        settingsPanel.addUIElement(TemporalStressButton);
        settingsPanel.addUIElement(enableChimeraCrossoverButton);

        currentYposition -= spacingY; //reduce the spacing
//        settingsPanel.addUIElement(BlessingButton);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        if (Loader.isModLoaded("CardAugments")) {
            AugmentHelper.register();
        }
        logger.info("Done loading badge Image and mod options");
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, null);
    }
    private static BitmapFont prepFont(FreeTypeFontGenerator g, float size, boolean isLinearFiltering) {
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.characters = "";
        p.incremental = true;
        p.size =(int) size/2;
        p.gamma = 0.9F;
        p.spaceX = (int)(-0.9F * Settings.scale);
        p.spaceY = p.size;
        p.borderColor = new Color(0.4F, 0.1F, 0.1F, 1.0F);
        p.borderStraight = false;
        p.borderWidth =  1.25F * Settings.scale;
        p.borderGamma = 0.9F;
        p.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
        p.shadowOffsetX = (int)(1.0F * Settings.scale);
        p.shadowOffsetY = Math.round(1.0F * Settings.scale);
        if (isLinearFiltering) {
            p.minFilter = TextureFilter.Linear;
            p.magFilter = TextureFilter.Linear;
        } else {
            p.minFilter = TextureFilter.Nearest;
            p.magFilter = TextureFilter.MipMapLinearNearest;
        }

        g.scaleForPixelHeight(p.size);
        BitmapFont font = g.generateFont(p);
        font.setUseIntegerPositions(!isLinearFiltering);
        font.getData().markupEnabled = true;
        if (LocalizedStrings.break_chars != null) {
            font.getData().breakChars = LocalizedStrings.break_chars.toCharArray();
        }
        return font;
    }
    public static boolean getStressRender(){
        if (CardCrawlGame.dungeon != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (renderStressUI) {
                if (StressUI == null) {
                    StressUI = new TemporalStressUI(ImageMaster.loadImage("theDragonkinResources/images/ui/Stressvfx.png"));
                }
                return true;
            } else if (TemporalStressField.Stress.get(AbstractDungeon.player) > 0 || AbstractDungeon.player.chosenClass == THE_WARDEN) {
                renderStressUI = true;
                if (StressUI == null) {
                    StressUI = new TemporalStressUI(ImageMaster.loadImage("theDragonkinResources/images/ui/Stressvfx.png"));
                }
                return true;
            }
        }
        return false;
    }
    public static void renderStressCounter(SpriteBatch sb, float current_x){
        StressUI.render(sb, current_x);
    }

    public static void updateStressCounter()
    {
        StressUI.update();
    }
    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
    }

    @Override
    public void receiveEditKeywords()
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String resourcePath(String file) {
        return resourcesFolder + "/" + file;
    }
    public static String uiPath(String file) {
        return resourcesFolder + "/UI/" + file;
    }
    public static String characterPath(String file) { return resourcesFolder + "/character/" + file;}
    public static String powerPath(String file) {
        return resourcesFolder + "/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/relics/" + file;
    }

    //This determines the mod's ID based on information stored by ModTheSpire.
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(DragonMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCards() {

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheJusticar("the Justicar", THE_JUSTICAR),
                JUSTICAR_RED_BUTTON, JUSTICAR_RED_PORTRAIT, THE_JUSTICAR);
       /* BaseMod.addCharacter(new TheDrifter("the Drifter", TheDrifter.Enums.THE_DRIFTER),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, TheDrifter.Enums.THE_DRIFTER);*/
        BaseMod.addCharacter(new TheRimedancer("the Rimedancer", THE_RIMEDANCER),
                JUSTICAR_RED_BUTTON, JUSTICAR_RED_PORTRAIT, THE_RIMEDANCER);
        receiveEditPotions();
    }
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        BaseMod.addPotion(GatlokBrew.class, Color.FIREBRICK.cpy() , null, Color.LIGHT_GRAY.cpy(), GatlokBrew.POTION_ID, THE_JUSTICAR);
        BaseMod.addPotion(NaruuinsGlow.class, Color.YELLOW.cpy() ,Color.SKY.cpy() , null, NaruuinsGlow.POTION_ID, THE_JUSTICAR);
        BaseMod.addPotion(DraughtofFervor.class, Color.GOLDENROD.cpy() ,Color.RED.cpy() , null, DraughtofFervor.POTION_ID, THE_JUSTICAR);

        logger.info("Done editing potions");
    }
    @Override
    public void receiveEditRelics() {

    }

    @Override
    public void receiveOnPlayerTurnStart() {

    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {

    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        return false;
    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {

    }

    @Override
    public void receiveStartGame() {

    }
}
