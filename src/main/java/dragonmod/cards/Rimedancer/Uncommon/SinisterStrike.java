package dragonmod.cards.Rimedancer.Uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.FlourishAction;
import dragonmod.actions.ThrowObjectAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.powers.Rimedancer.powercards.PrecisionPower;
import dragonmod.ui.TextureLoader;
import dragonmod.util.EnchantmentsManager;
import dragonmod.util.Wiz;

public class SinisterStrike extends AbstractRimedancerCard {
    public static final String ID = SinisterStrike.class.getSimpleName();

    public SinisterStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDamage(4, 2);
        tags.add(PrecisionPower.Shiv);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ThrowObjectAction(TextureLoader.getTexture(DragonMod.itemPath("kunai.png")),1,m.hb, Color.WHITE.cpy(),false));
        Wiz.atb(new ThrowObjectAction(TextureLoader.getTexture(DragonMod.itemPath("kunai.png")),1,m.hb, Color.WHITE.cpy(),false));
        Wiz.dmg(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        Wiz.atb(new FlourishAction());
        Wiz.atb(new FlourishAction());
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard target = Wiz.Player().drawPile.getTopCard();
                Wiz.Player().drawPile.removeCard(target);
                target.tags.add(EnchantmentsManager.Sleeved);
                EnchantmentsManager.addCard(target,true,Wiz.Player());
            }
        });
    }
}
