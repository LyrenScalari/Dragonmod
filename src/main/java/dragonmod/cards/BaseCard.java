package dragonmod.cards;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.util.CardStats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static dragonmod.ui.TextureLoader.getCardTextureString;


public abstract class BaseCard extends CustomCard {
    final private static Map<String, DynamicVariable> customVars = new HashMap<>();

    protected static String makeID(String name) { return DragonMod.makeID(name); }
    protected CardStrings cardStrings;
    private boolean needsArtRefresh = false;
    private float rotationTimer = 0;
    private int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    protected boolean upgradesDescription;

    protected int baseCost;

    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;
    protected boolean upgradeMagic2;
    protected boolean upgradeDamage2;
    protected boolean upgradeHeal;

    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;
    protected int magic2Upgrade;
    protected int damage2Upgrade;
    protected int healUpgrade;

    protected boolean baseExhaust = false;
    protected boolean upgExhaust = false;
    protected boolean baseEthereal = false;
    protected boolean upgEthereal = false;
    protected boolean baseInnate = false;
    protected boolean upgInnate = false;
    protected boolean baseRetain = false;
    protected boolean upgRetain = false;
	
    public int SecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int BaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)


    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    public int ThirdDamage;
    public int baseThirdDamage;
    public boolean upgradedThirdDamage;
    public boolean isThirdDamageModified;

    final protected Map<String, LocalVarInfo> cardVariables = new HashMap<>();

    public BaseCard(String ID, CardStats info) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
    }
    public BaseCard(String ID, CardStats info, boolean upgradesDescription) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
    }
    public BaseCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color)
    {
        super(ID, getName(ID), getCardTextureString(ID.replace(DragonMod.modID + ":", ""),cardType), cost, getInitialDescription(ID), cardType, color, rarity, target);
        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        if (cardStrings.UPGRADE_DESCRIPTION != null){
            upgradesDescription = true;
        }
        this.originalName = cardStrings.NAME;
        this.baseCost = cost;
        initializeTitle();
        initializeDescription();
	}

    private static String getName(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    }
    private static String getInitialDescription(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
    }

    //Methods meant for constructor use
    protected final void setDamage(int damage)
    {
        this.setDamage(damage, 0);
    }
    protected final void setDamage(int damage, int damageUpgrade)
    {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0)
        {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }
    protected final void setDamage2(int damage)
    {
        this.setDamage2(damage, 0);
    }
    protected final void setMagic2(int magic)
    {
        this.setMagic2(magic, 0);
    }
    protected final void setDamage2(int damage, int damageUpgrade) {
        this.baseSecondDamage = this.secondDamage = damage;
        if (damageUpgrade != 0) {
            this.upgradeDamage2 = true;
            this.damage2Upgrade = damageUpgrade;
        }
    }
    protected final void setMagic2(int magic, int magicUpgrade)
    {
        this.BaseSecondMagicNumber = this.SecondMagicNumber = magic;
        if (magicUpgrade != 0)
        {
            this.upgradeMagic2 = true;
            this.magic2Upgrade = magicUpgrade;
        }
    }

    protected final void setBlock(int block)
    {
        this.setBlock(block, 0);
    }
    protected final void setBlock(int block, int blockUpgrade)
    {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0)
        {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }

    protected final void setMagic(int magic)
    {
        this.setMagic(magic, 0);
    }
    protected final void setMagic(int magic, int magicUpgrade)
    {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0)
        {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }
    protected final void setCustomVar(String key, int base) {
        this.setCustomVar(key, base, 0);
    }
    protected final void setCustomVar(String key, int base, int upgrade) {
        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key);
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
        }
        cardVariables.put(key, new LocalVarInfo(base, upgrade));
    }

    protected LocalVarInfo getCustomVar(String key) {
        return cardVariables.get(key);
    }

    protected void calculateVarAsDamage(String key) {
        setVarCalculation(key, (m, base)->{
            int tmp = this.baseDamage;

            this.baseDamage = base;
            if (m != null)
                super.calculateCardDamage(m);
            else
                super.applyPowers();

            this.baseDamage = tmp;
            return damage;
        });
    }
    protected void calculateVarAsBlock(String key) {
        setVarCalculation(key, (m, base)->{
            int tmp = this.baseBlock;

            this.baseBlock = base;
            if (m != null)
                super.calculateCardDamage(m);
            else
                super.applyPowers();

            this.baseBlock = tmp;
            return block;
        });
    }
    protected void setVarCalculation(String key, BiFunction<AbstractMonster, Integer, Integer> calculation) {
        cardVariables.get(key).calculation = calculation;
    }

    public int customVarBase(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return -1;
        return var.base;
    }
    public void setCustomVarBase(String key, int amount) {
        LocalVarInfo var = cardVariables.get(key);
        var.base = amount;
    }
    public int customVar(String key) {
        if (cardVariables == null) return -1;
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return -1;
        return var.value;
    }
    public void setCustomVarValue(String key,int amount) {
        LocalVarInfo var = cardVariables.get(key);
        var.value = amount;
    }
    public boolean isCustomVarModified(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.isModified();
    }
    public boolean customVarUpgraded(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.upgraded;
    }

    protected final void setCostUpgrade(int costUpgrade)
    {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }
    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust)
    {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }
    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal)
    {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = upgEthereal;
        this.isEthereal = baseEthereal;
    }
    protected void setInnate(boolean baseInnate, boolean upgInnate)
    {
        this.baseInnate = baseInnate;
        this.upgInnate = upgInnate;
        this.isInnate = baseInnate;
    }
    protected void setSelfRetain(boolean baseRetain, boolean upgRetain)
    {
        this.baseRetain = baseRetain;
        this.upgRetain = upgRetain;
        this.selfRetain = baseRetain;
    }
    protected final void setExhaust(boolean baseExhaust)
    {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = baseExhaust;
        this.exhaust = baseExhaust;
    }
    protected final void setEthereal(boolean baseEthereal)
    {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = baseEthereal;
        this.isEthereal = baseEthereal;
    }
    protected void setInnate(boolean baseInnate)
    {
        this.baseInnate = baseInnate;
        this.upgInnate = baseInnate;
        this.isInnate = baseInnate;
    }
    protected void setSelfRetain(boolean baseRetain)
    {
        this.baseRetain = baseRetain;
        this.upgRetain = baseRetain;
        this.selfRetain = baseRetain;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();

        if (card instanceof BaseCard)
        {
            card.rawDescription = this.rawDescription;
            ((BaseCard) card).upgradesDescription = this.upgradesDescription;

            ((BaseCard) card).baseCost = this.baseCost;

            ((BaseCard) card).upgradeCost = this.upgradeCost;
            ((BaseCard) card).upgradeDamage = this.upgradeDamage;
            ((BaseCard) card).upgradeDamage2 = this.upgradeDamage2;
            ((BaseCard) card).upgradeBlock = this.upgradeBlock;
            ((BaseCard) card).upgradeMagic = this.upgradeMagic;
            ((BaseCard) card).upgradeMagic2 = this.upgradeMagic2;

            ((BaseCard) card).costUpgrade = this.costUpgrade;
            ((BaseCard) card).damageUpgrade = this.damageUpgrade;
            ((BaseCard) card).blockUpgrade = this.blockUpgrade;
            ((BaseCard) card).magicUpgrade = this.magicUpgrade;

            ((BaseCard) card).baseExhaust = this.baseExhaust;
            ((BaseCard) card).upgExhaust = this.upgExhaust;
            ((BaseCard) card).baseEthereal = this.baseEthereal;
            ((BaseCard) card).upgEthereal = this.upgEthereal;
            ((BaseCard) card).baseInnate = this.baseInnate;
            ((BaseCard) card).upgInnate = this.upgInnate;
            ((BaseCard) card).baseRetain = this.baseRetain;
            ((BaseCard) card).upgRetain = this.upgRetain;

            for (Map.Entry<String, LocalVarInfo> varEntry : cardVariables.entrySet()) {
                LocalVarInfo target = ((BaseCard) card).getCustomVar(varEntry.getKey()),
                        current = varEntry.getValue();
                if (target == null) {
                    ((BaseCard) card).setCustomVar(varEntry.getKey(), current.base, current.upgrade);
                    target = ((BaseCard) card).getCustomVar(varEntry.getKey());
                }
                target.base = current.base;
                target.value = current.value;
                target.upgrade = current.upgrade;
                target.calculation = current.calculation;
            }
        }

        return card;
    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            this.upgradeName();

            if (this.upgradesDescription)
            {
                if (cardStrings.UPGRADE_DESCRIPTION == null)
                {
                    DragonMod.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
                }
                else
                {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                }
            }

            if (upgradeCost)
            {
                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
                    this.upgradeBaseCost(this.cost + diff);
                    if (this.cost < 0)
                        this.cost = 0;
                }
                else {
                    upgradeBaseCost(costUpgrade);
                }
            }

            if (upgradeDamage)
                this.upgradeDamage(damageUpgrade);

            if (upgradeDamage2)
                this.upgradeDamage2(damage2Upgrade);

            if (upgradeBlock)
                this.upgradeBlock(blockUpgrade);

            if (upgradeMagic)
                this.upgradeMagicNumber(magicUpgrade);
            if (upgradeMagic2)
                this.upgradeMagicNumber2(magic2Upgrade);
            for (LocalVarInfo var : cardVariables.values()) {
                if (var.upgrade != 0) {
                    var.base += var.upgrade;
                    var.value = var.base;
                    var.upgraded = true;
                }
            }

            if (baseExhaust ^ upgExhaust)
                this.exhaust = upgExhaust;

            if (baseInnate ^ upgInnate)
                this.isInnate = upgInnate;

            if (baseEthereal ^ upgEthereal)
                this.isEthereal = upgEthereal;

            if (baseRetain ^ upgRetain)
                this.selfRetain = upgRetain;


            this.initializeDescription();
        }
    }
    public void update() {
        super.update();
        if (!cardToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    cardsToPreview = cardToPreview.get(previewIndex);
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }
    protected float getRotationTimeNeeded() {
        return 1f;
    }
    protected void upgradeDamage2(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }
    public void upgradeMagicNumber2(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        BaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        SecondMagicNumber = BaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
    boolean inCalc = false;
    @Override
    public void applyPowers() {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(null, var.base);
            }
            inCalc = false;
        }

        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(m, var.base);
            }
            inCalc = false;
        }

        super.calculateCardDamage(m);
    }

    private static class QuickDynamicVariable extends DynamicVariable {
        final String localKey, key;

        public QuickDynamicVariable(String key) {
            this.localKey = key;
            this.key = makeID(key);
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void setIsModified(AbstractCard c, boolean v) {
            if (c instanceof BaseCard) {
                LocalVarInfo var = ((BaseCard) c).getCustomVar(localKey);
                if (var != null)
                    var.forceModified = v;
            }
        }

        @Override
        public boolean isModified(AbstractCard c) {
            return c instanceof BaseCard && ((BaseCard) c).isCustomVarModified(localKey);
        }

        @Override
        public int value(AbstractCard c) {
            return c instanceof BaseCard ? ((BaseCard) c).customVar(localKey) : 0;
        }

        @Override
        public int baseValue(AbstractCard c) {
            return c instanceof BaseCard ? ((BaseCard) c).customVarBase(localKey) : 0;
        }

        @Override
        public boolean upgraded(AbstractCard c) {
            return c instanceof BaseCard && ((BaseCard) c).customVarUpgraded(localKey);
        }
    }


    private static class LocalVarInfo {
        int base, value, upgrade;
        boolean upgraded = false;
        boolean forceModified = false;

        BiFunction<AbstractMonster, Integer, Integer> calculation = LocalVarInfo::noCalc;

        public LocalVarInfo(int base, int upgrade) {
            this.base = this.value = base;
            this.upgrade = upgrade;
        }

        private static int noCalc(AbstractMonster m, int base) {
            return base;
        }

        public boolean isModified() {
            return forceModified || base != value;
        }
    }
}