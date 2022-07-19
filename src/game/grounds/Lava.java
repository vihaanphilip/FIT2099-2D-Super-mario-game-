package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Lava ground
 */
public class Lava extends Ground {
    /**
     * damage inflict to the actor standing on it
     */
    private final int damage;

    /**
     * Constructor
     */
    public Lava() {
        super('L');
        damage = 15;
    }

    /**
     * Tell that 1 turn has passed
     * @param location The location of the Ground
     * @see Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();

        if (actor != null) {
            actor.hurt(damage);
            if (!actor.isConscious()) {
                location.map().removeActor(actor);
            }
        }
    }

    /**
     * Block the enemy to enter
     * @param actor the Actor to check
     * @return boolean
     * @see Ground#canActorEnter(Actor)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.ENEMY);
    }
}
