package dragonmod.cards.Warden.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dragonmod.DragonMod;
import dragonmod.cards.Warden.AbstractWardenCard;
import dragonmod.interfaces.onExhaustedEnchantment;
import dragonmod.util.EnchantmentsField;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

import static dragonmod.util.EnchantmentsManager.BanishedCards;

public class MirrorBlade extends AbstractWardenCard {
    public static final String ID = MirrorBlade.class.getSimpleName();
    public MirrorBlade(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.ENEMY);
        setCostUpgrade(-1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard left = DragonMod.getLeftCard(this); // Banish
        AbstractCard right = DragonMod.getRightCard(this); // Illude
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (left != null){
                    Wiz.hand().removeCard(left);
                    AbstractDungeon.effectsQueue.add(new PurgeCardEffect(left));
                    left.triggerOnExhaust();
                    for (AbstractCard enchantment : EnchantmentsField.Enchantments.get(Wiz.Player())) {
                        if (enchantment instanceof onExhaustedEnchantment) {
                            Wiz.Player().limbo.group.add(enchantment);
                            Wiz.atb(new AbstractGameAction() {
                                @Override
                                public void update() {
                                    EnchantmentsField.Enchantments.get(Wiz.Player()).remove(enchantment);
                                    Wiz.atb(new UnlimboAction(enchantment));
                                    ((onExhaustedEnchantment) enchantment).EnchantedOnExhaust(left);
                                    addToBot(new AbstractGameAction() {
                                        @Override
                                        public void update() {
                                            EnchantmentsManager.ActivateEnchantments(enchantment, Wiz.Player());
                                            isDone = true;
                                        }
                                    });
                                    isDone = true;
                                }});
                        }
                    }
                    BanishedCards.addToBottom(left);
                }
                if (right != null){
                    AbstractCard copy = right.makeStatEquivalentCopy();
                    Wiz.Player().limbo.addToBottom(copy);
                    copy.current_x = 250;
                    copy.current_y = 250;
                    p.exhaustPile.moveToExhaustPile(copy);
                    p.limbo.removeCard(copy);
                }
                isDone = true;
            }
        });
    }
}