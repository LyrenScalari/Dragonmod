package dragonmod.cards.Justicar.common;

/*
public class Clarity extends AbstractJusticarCard {

    public static final String ID = Clarity.class.getSimpleName();
    public Clarity(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        setCostUpgrade(0);
        setMagic(2);
        energyCosts.put(TypeEnergyHelper.Mana.Exalt,2);
    }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(ZealPower.POWER_ID)) {
            if (AbstractDungeon.player.getPower(ZealPower.POWER_ID).amount >= energyCosts.get(TypeEnergyHelper.Mana.Exalt)){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            } else {
                this.glowColor = Color.RED.cpy();
            }
        } else {
            this.glowColor = Color.RED.cpy();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ExaltAction(energyCosts,()->new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                Wiz.atb(new DrawCardAction(magicNumber));
                Wiz.atb(new GainEnergyAction(2));
            }
        }));
    }
}
*/
