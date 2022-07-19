package game.Fountains;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

/**
 * Fountain that has healing effect
 */
public class HealthFountain extends Fountain {
    /**
     * How much the hp can be healed
     */
    private int healPoint;

    /**
     * Constructor
     */
    public HealthFountain() {
        super("Health Fountain", 'H');
        healPoint = 50;
        type = Status.NEED_HEAL;
    }

    /**
     * Get the water from the fountain
     * @return water
     * @see Fountain#getWater()
     */
    @Override
    public Water getWater() {
        return new Water("Health Water", this);
    }

    /**
     * Get the healing effect from the fountain
     * @param actor actor drinking this fountain
     * @return effect of the fountain
     * @see Fountain#getEffect(Actor)
     */
    @Override
    public String getEffect(Actor actor) {
        actor.heal(healPoint);
        return actor + " is healed by " + healPoint;
    }
}
