package game.Fountains;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.BattleActor;

/**
 * Power Fountain
 */
public class PowerFountain extends Fountain {
    /**
     * value of damage to be increase
     */
    private int damageIncrement;

    /**
     * Constructor
     */
    public PowerFountain() {
        super("Power Fountain", 'A');
        damageIncrement = 15;
    }

    /**
     * Get the water
     * @return Water
     * @see Fountain#getWater()
     */
    @Override
    public Water getWater() {
        return new Water("Power Water", this);
    }

    /**
     * Get the effect of the fountain
     * @param actor actor drinking this fountain
     * @return effect of the fountain
     * @see Fountain#getEffect(Actor)
     */
    @Override
    public String getEffect(Actor actor) {
        if (actor instanceof BattleActor) {
            BattleActor temp = (BattleActor) actor;
            return temp.setBaseDamage(damageIncrement) ? actor + " base damage is increased by " + damageIncrement : actor + " reaches max damage";
        }

        return getWater() + " has no effect on " + actor;
    }
}
