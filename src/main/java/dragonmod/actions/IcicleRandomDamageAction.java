package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import dragonmod.DragonMod;
import dragonmod.orbs.Icicle;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class IcicleRandomDamageAction extends AbstractGameAction {
    private DamageInfo info;

    public IcicleRandomDamageAction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        Icicle tothrow = null;
        for (AbstractOrb o : Wiz.Player().orbs) {
            if (o instanceof Icicle) {
                tothrow = (Icicle) o;
            }
        }
        if (this.target != null) {
            this.addToTop(new DamageAction(this.target, this.info, this.attackEffect));
            if (tothrow != null){
                Wiz.att(new ThrowIcicleAction(tothrow, target.hb, Color.CYAN));
            } else {
                Wiz.att(new ThrowIcicleAction(TextureLoader.getTexture(DragonMod.orbPath("Icicle.png")),1.0f,target.hb,Color.CYAN));
            }
        }
        this.isDone = true;
    }
}
