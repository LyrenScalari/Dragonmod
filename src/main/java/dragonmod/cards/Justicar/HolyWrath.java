package dragonmod.cards.Justicar;

import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DamageModifiers.BlockModifiers.DivineBlock;
import dragonmod.actions.SmiteAction;

public class HolyWrath extends AbstractHolyCard {

    public static final String ID = HolyWrath.class.getSimpleName();

    public HolyWrath() {
        super(ID,2,CardType.ATTACK,CardRarity.RARE,CardTarget.ALL_ENEMY);
        setDamage(10);
        setMagic(2);
        setExhaust(true);
        BlockModifierManager.addModifier(this,new DivineBlock(true));
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (BlockModifierManager.hasCustomBlockType(AbstractDungeon.player)) {
            if (!BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().isEmpty()) {
                if (BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().get(0) instanceof DivineBlock) {
                    this.baseDamage += BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockAmount();
                }
            }
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if (BlockModifierManager.hasCustomBlockType(AbstractDungeon.player)) {
            if (!BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().isEmpty()) {
                if (BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().get(0) instanceof DivineBlock) {
                    this.baseDamage += BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockAmount();
                }
            }
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            addToBot(new SmiteAction(mo, new DamageInfo(p, damage, damageTypeForTurn)));
        }
        if (BlockModifierManager.hasCustomBlockType(AbstractDungeon.player)) {
            if (!BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().isEmpty()) {
                if (BlockModifierManager.getTopBlockInstance(AbstractDungeon.player).getBlockTypes().get(0) instanceof DivineBlock) {
                    this.addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (BlockModifierManager.hasCustomBlockType(p) && BlockModifierManager.getTopBlockInstance(p).getBlockTypes().stream().anyMatch(mod -> mod instanceof DivineBlock)) {
                                if (!upgraded) {
                                    BlockModifierManager.reduceSpecificBlockType(BlockModifierManager.getTopBlockInstance(p), BlockModifierManager.getTopBlockInstance(p).getBlockAmount());
                                } else
                                    BlockModifierManager.reduceSpecificBlockType(BlockModifierManager.getTopBlockInstance(p), BlockModifierManager.getTopBlockInstance(p).getBlockAmount() / 2);
                            }
                            this.isDone = true;
                        }
                    });
                }
            }
        }
        super.use(p,m);
    }
}
