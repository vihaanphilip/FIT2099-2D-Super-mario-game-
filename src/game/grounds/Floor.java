package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Check whether an actor can enter
	 * @param actor the Actor to check
	 * @return Boolean that decides the entrance of the actor
	 * @see Ground#canActorEnter(Actor)
	 * @see Status#ENEMY
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		// Will not allow the enemy to enter
		return !actor.hasCapability(Status.ENEMY);
	}
}
