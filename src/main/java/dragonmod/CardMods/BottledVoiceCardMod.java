package dragonmod.CardMods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BottledVoiceCardMod extends AbstractCardModifier {
    int modifer = 3;
    public BottledVoiceCardMod(){}

    @Override
    public AbstractCardModifier makeCopy() {
        return new BottledVoiceCardMod();
    }
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage+modifer;
    }
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }
    public float modifyBlock(float block, AbstractCard card) {
        return block+modifer;
    }
}