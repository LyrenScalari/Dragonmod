package dragonmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dragonmod.DragonMod;
import dragonmod.ui.TextureLoader;
import dragonmod.util.Wiz;

public class pokerdartaction extends AbstractGameAction {
    private DamageInfo info;

    public pokerdartaction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.addToTop(new DamageAction(this.target, this.info, this.attackEffect));
            int rand = AbstractDungeon.miscRng.random(0,3);
            if (rand == 1){
                Wiz.att(new ThrowShurikenAction("shuriken", 1, target.hb, Color.GRAY.cpy(), false));
            } else if (rand == 2){
                Wiz.att(new ThrowObjectAction(TextureLoader.getTexture(DragonMod.itemPath("kunai.png")),1,target.hb,Color.WHITE.cpy(),false));
            } else Wiz.att(new ThrowShurikenAction("iceshuriken", 1, target.hb, Color.GRAY.cpy(), false));
        }
        this.isDone = true;
    }
}
