package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.reset.Resettable;

/**
 * Fire class
 */
public class Fire extends Item {
    /**
     * number of turns that this fire can last
     */
    private int turns;
    /**
     * Damage that this fire inflicts on the actor
     */
    private int damage;
    /**
     * Actor who released fire.
     */
    private Actor attacker;
    /**
     * Constructor
     * @param lastTurns number of turns this fire can last
     */
    public Fire(int lastTurns) {
        super("Fire", 'v', false);
        turns = lastTurns;
        damage = 20;
    }

    /**
     * tell that 1 turn has passed
     * @param currentLocation The location of the ground on which we lie.
     * @see Item#tick(Location)
     */
    @Override
    public void tick(Location currentLocation) {
        // If fire has finished, remove the fire
        if (turns == 0) {
            currentLocation.removeItem(this);
        }

        // If actor is standing on it, inflict damage to the actor
        Actor actor = currentLocation.getActor();
        if (actor != null) {
            if (actor != attacker) // ensures actor who releases fire is immune to fire
                actor.hurt(damage);

            // If actor is dead, remove that actor
            if (!actor.isConscious()) {
                ActionList dropActions = new ActionList();

                for (Item item : actor.getInventory())
                    dropActions.add(item.getDropAction(actor));
                for (Action drop : dropActions)
                    drop.execute(actor, currentLocation.map());

                currentLocation.map().removeActor(actor);
            }
        }

        // Decrease the number of turns it can last
        turns--;
    }

    public void setAttacker(Actor attacker) {
        this.attacker = attacker;
    }
}
