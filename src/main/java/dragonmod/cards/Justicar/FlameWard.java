package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.AddIconToDescriptionMod;
import dragonmod.DamageModifiers.BlockModifiers.FireBlock;
import dragonmod.DamageModifiers.Icons.FireIcon;
import dragonmod.DragonMod;


public class FlameWard extends AbstractPrimalCard {
        public static final String ID = DragonMod.makeID(FlameWard.class.getSimpleName());

        public FlameWard() {
                super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
                setBlock(10, 4);
                cardsToPreview = new Burn();
                BlockModifierManager.addModifier(this, new FireBlock(true));
                CardModifierManager.addModifier(this, new AddIconToDescriptionMod(AddIconToDescriptionMod.BLOCK, FireIcon.get()));
        }
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
                AbstractDungeon.actionManager.addToBottom(new GainCustomBlockAction(this, AbstractDungeon.player, block));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Burn(), 1, true, false));
                super.use(p, m);
        }
}