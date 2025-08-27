package dragonmod.cards.Rimedancer.Starter;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.actions.ThrowObjectAction;
import dragonmod.actions.ThrowShurikenAction;
import dragonmod.cards.Rimedancer.AbstractRimedancerCard;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;


public class RimedancerStrike extends AbstractRimedancerCard {
    public static final String ID = RimedancerStrike.class.getSimpleName();
    public RimedancerStrike(){
        super(ID,1,CardType.ATTACK,CardRarity.BASIC,CardTarget.ENEMY);
        setDamage(6,3);
        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int rand = AbstractDungeon.miscRng.random(0,2);
        if (rand == 1){
            Wiz.att(new ThrowShurikenAction("shuriken", 1, m.hb, Color.GRAY.cpy(), false));
        } else if (rand == 2){
            Wiz.atb(new ThrowObjectAction(TextureLoader.getTexture(DragonMod.itemPath("kunai.png")),1,m.hb,Color.WHITE.cpy(),false));
        } else Wiz.att(new ThrowShurikenAction("iceshuriken", 1,m.hb, Color.GRAY.cpy(), false));

        Wiz.dmg(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }
}