package dragonmod.cards.Justicar;


import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dragonmod.actions.SmiteAction;

public class SacredTrial extends AbstractHolyCard {


    // TEXT DECLARATION

    public static final String ID = SacredTrial.class.getSimpleName(); // USE THIS ONE FOR THE TEMPLATE;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;  //   since they don't change much.
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;       //
    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 1;
    // /STAT DECLARATION/


    public SacredTrial() {
        super(ID,COST,TYPE,RARITY,TARGET);
        baseDamage = damage = DAMAGE;
        magicNumber = baseMagicNumber = 7;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
        addToBot(new VFXAction(new LightningEffect(p.drawX,p.drawY)));
        addToBot(new DamageCallbackAction(p, new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE, integer -> {
            System.out.println(integer);
              for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                  AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
                  addToBot(new SmiteAction(mo, new DamageInfo(p, damage, damageTypeForTurn)));
              }
        }));
        super.use(p,m);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-2);
            upgradeDamage(2);
            initializeDescription();
        }
    }
}