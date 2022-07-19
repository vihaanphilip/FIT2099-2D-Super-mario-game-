package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Execute the action when this is the turn of the actor
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return Message stating the result of the action
	 * @see Action#execute(Actor, GameMap)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		String result;

		if (actor.hasCapability(Status.POWER_STAR)){
			target.resetMaxHp(0); // kills the target immediately
			result = actor + "one shots " + target;
		} else {
			int damage = weapon.damage();
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
		}


		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();

			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			// remove actor
			map.removeActor(target);

			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Give the brief description of the action
	 *
	 * @param actor The actor performing the action.
	 * @return String stating the description of the action
	 * @see Action#menuDescription(Actor)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}