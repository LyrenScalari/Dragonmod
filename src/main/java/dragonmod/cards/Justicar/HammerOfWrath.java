package dragonmod.cards.Justicar;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.CardMods.PlusDamageBlockCardMod;
import dragonmod.util.CustomTags;


public class HammerOfWrath extends AbstractHolyCard {

    public static final String ID = HammerOfWrath.class.getSimpleName();

    public HammerOfWrath() {
        super(ID,2,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        setDamage(14);
        setMagic(3,1);
        tags.add(CustomTags.Smite);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group){
                    if (c.hasTag(CustomTags.Smite)){
                        CardModifierManager.addModifier(c,new PlusDamageBlockCardMod(magicNumber));
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group){
                    if (c.hasTag(CustomTags.Smite)){
                        CardModifierManager.addModifier(c,new PlusDamageBlockCardMod(magicNumber));
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group){
                    if (c.hasTag(CustomTags.Smite)){
                        CardModifierManager.addModifier(c,new PlusDamageBlockCardMod(magicNumber));
                    }
                }
                isDone = true;
            }
        });

        super.use(p,m);
    }
}