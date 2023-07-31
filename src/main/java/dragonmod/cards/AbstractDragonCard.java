package dragonmod.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.Icons.ExaltIcon;
import dragonmod.DamageModifiers.Icons.TemporalIcon;
import dragonmod.DragonMod;
import dragonmod.powers.Dragonkin.DivineConvictionpower;
import dragonmod.util.CardArtRoller;
import dragonmod.util.CardInfo;
import dragonmod.util.TypeEnergyHelper;

import java.util.ArrayList;
import java.util.EnumMap;

import static dragonmod.DragonMod.makeID;

public abstract class AbstractDragonCard extends BaseCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractJusticarCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.
    public static final CardStrings Manipstrings = CardCrawlGame.languagePack.getCardStrings(makeID("ManipStrings"));
    public static CardStrings tooltip = CardCrawlGame.languagePack.getCardStrings("theDragonkin:AbstractJusticarCard");
    public int basefading;
    public boolean upgradedfading = false;
    public int fading;
    private boolean needsArtRefresh = false;
    private float rotationTimer = 0;
    private int previewIndex;
    public int realBaseDamage;
    public int realBaseMagic;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    public EnumMap<TypeEnergyHelper.Mana, Integer> energyCosts = new EnumMap<>(TypeEnergyHelper.Mana.class);
    public Color renderColor = Color.WHITE.cpy();
    public boolean freeManaOnce = false;
    public AbstractDragonCard(final String cardID,
                              final String name,
                              final int cost,
                              final String rawDescription,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target) {

        super(new CardInfo(cardID,cost,type,target,rarity,color));
        if (textureImg.contains("ui/missing.png")) {
            if (!CardLibrary.cards.isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
        CommonKeywordIconsField.useIcons.set(this,true);
    }
    public void renderInLibrary(SpriteBatch sb) {
        if (energyCosts.get(TypeEnergyHelper.Mana.Exalt) != null){
            ExtraIcons.icon(ExaltIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Exalt))).render(this);
        }
        if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) != null){
            ExtraIcons.icon(TemporalIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Temporal))).render(this);
        }
/*        if (energyCosts.get(TypeEnergyHelper.Mana.Colorless) != null) {
            Color textColor = Color.WHITE.cpy();
            ExtraIcons.icon(GreyIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Colorless))).textColor(textColor).render(this);
        }
        if (energyCosts.get(TypeEnergyHelper.Mana.Blue) != null){
            ExtraIcons.icon(BlueIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Blue))).render(this);
        }
        if (energyCosts.get(TypeEnergyHelper.Mana.Black) != null) {
            Color textColor = Color.WHITE.cpy();
            ExtraIcons.icon(BlackIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Black))).textColor(textColor).render(this);
        }
        if (energyCosts.get(TypeEnergyHelper.Mana.Red) != null) {
            Color textColor = Color.WHITE.cpy();
            ExtraIcons.icon(RedIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Red))).textColor(textColor).render(this);
        }
        if (energyCosts.get(TypeEnergyHelper.Mana.Rakados) != null) {
            Color textColor = Color.WHITE.cpy();
            ExtraIcons.icon(RakadosIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Rakados))).textColor(textColor).render(this);
        }*/
        super.renderInLibrary(sb);
    }
    public void render(SpriteBatch sb) {
        if (!Settings.hideCards) {
            if (energyCosts.get(TypeEnergyHelper.Mana.Exalt) != null) {
                Color textColor = Color.WHITE.cpy();
                if (AbstractDungeon.player != null) {
                    if (!AbstractDungeon.player.hasPower(DivineConvictionpower.POWER_ID) || (AbstractDungeon.player.getPower(DivineConvictionpower.POWER_ID).amount < energyCosts.get(TypeEnergyHelper.Mana.Exalt))) {
                        textColor = Color.RED.cpy();
                    }
                }
                ExtraIcons.icon(ExaltIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Exalt))).textColor(textColor).render(this);
            }
/*            if (energyCosts.get(TypeEnergyHelper.Mana.Temporal) != null) {
                Color textColor = Color.WHITE.cpy();
                ExtraIcons.icon(TemporalIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Temporal))).textColor(textColor).render(this);
            }
            if (energyCosts.get(TypeEnergyHelper.Mana.Colorless) != null) {
                Color textColor = Color.WHITE.cpy();
                ExtraIcons.icon(GreyIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Colorless))).textColor(textColor).render(this);
            }
            if (energyCosts.get(TypeEnergyHelper.Mana.Blue) != null){
                ExtraIcons.icon(BlueIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Blue))).render(this);
            }
            if (energyCosts.get(TypeEnergyHelper.Mana.Black) != null) {
                Color textColor = Color.WHITE.cpy();
                ExtraIcons.icon(BlackIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Black))).textColor(textColor).render(this);
            }
            if (energyCosts.get(TypeEnergyHelper.Mana.Red) != null) {
                Color textColor = Color.WHITE.cpy();
                ExtraIcons.icon(RedIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Red))).textColor(textColor).render(this);
            }
            if (energyCosts.get(TypeEnergyHelper.Mana.Rakados) != null) {
                Color textColor = Color.WHITE.cpy();
                ExtraIcons.icon(RakadosIcon.get().region.getTexture()).text(String.valueOf(energyCosts.get(TypeEnergyHelper.Mana.Rakados))).textColor(textColor).render(this);
            }*/
            super.render(sb);
        }
    }
    @Override
    public void applyPowers(){
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else if (baseThirdDamage > -1) {
            ThirdDamage = baseThirdDamage;

            int tmp = baseDamage;
            baseDamage = baseThirdDamage;

            super.applyPowers();

            ThirdDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isThirdDamageModified = (ThirdDamage != baseThirdDamage);
        }
        else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        }  else if (baseThirdDamage > -1) {
            ThirdDamage = baseThirdDamage;

            int tmp = baseDamage;
            baseDamage = baseThirdDamage;

            super.calculateCardDamage(mo);

            ThirdDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isThirdDamageModified = (ThirdDamage != baseThirdDamage);
        } else super.calculateCardDamage(mo);
    }
    public void resetAttributes() {
        super.resetAttributes();
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
    }
    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        }
        else {
            return super.getPortraitImage();
        }
    }

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
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
    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            SecondMagicNumber = BaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
        super.displayUpgrades();
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }
    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        try {
            sb.draw(img, drawX, drawY,
                    256.0F, 256.0F, 512.0F, 512.0F,
                    this.drawScale * Settings.scale, this.drawScale * Settings.scale,
                    this.angle, 0, 0, 512, 512, false, false);

        } catch (Exception e) {
            ExceptionHandler.handleException(e, DragonMod.logger);
        }
    }
}