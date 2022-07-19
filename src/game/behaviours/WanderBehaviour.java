package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Behaviour;

/**
 * Behaviour of NPC wandering around the map
 */
public class WanderBehaviour extends Action implements Behaviour {
	/**
	 * Random generator to decide the wandering path randomly
	 */
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.
	 * If no movement is possible, returns null.
	 *
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();

		for (Exit exit : map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
			}
		}

		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	/**
	 * Execute the action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return action
	 * @see Action#execute(Actor, GameMap)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Brief description of the action
	 * @param actor The actor performing the action.
	 * @return String of brief description about the action
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
