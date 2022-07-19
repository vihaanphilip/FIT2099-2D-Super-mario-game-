package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Fountains.Water;
import game.actions.RefillAction;
import game.enums.Status;
import game.Fountains.Fountain;

/**
 * Water bottle to store the water from fountain
 */
public class Bottle extends Consumable implements Refillable {
    /**
     * Water from fountain
     */
    private Water water;

    /**
     * Action to refill the bottle
     */
    private Action refillAction;

    /**
     * Constructor
     */
    public Bottle() {
        super("Bottle", 'b', false, null);
        // Cannot consume/drink water in bottle unless the bottle is filled
        removeAction(consumeAction);
        addStatus();
    }

    /**
     * Tell that 1 turn has passed
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     * @see edu.monash.fit2099.engine.items.Item#tick(Location)
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Check whether the bottle is filled
        if (water == null) {
            // Cannot drink if not filled
            removeAction(consumeAction);

            // Check whether player holding this item is on the fountain
            if (currentLocation.getGround().hasCapability(Status.FOUNTAIN)) {
                Fountain fountain = (Fountain) currentLocation.getGround();

                // If fountain can be used, add the refill action
                if (fountain.canUse()) {
                    if (refillAction != null) {
                        removeAction(refillAction);
                    }
                    refillAction = new RefillAction(this, fountain);
                    addAction(refillAction);
                }
            }
        } else {
            // If bottle is filled, allow the actor to drink this bottle
            if (!getAllowableActions().contains(consumeAction)) {
                addAction(consumeAction);
            }
            removeAction(refillAction);
        }
    }

    /**
     * Drink the bottle
     * @param actor actor performing consume action
     * @return message telling the effect of the water
     * @see Consumable#consume(Actor)
     */
    @Override
    public String consume(Actor actor) {
        String message = actor + " drinks " + water + "\n" + water.consumeEffect(actor);
        water = null;
        return message;
    }

    /**
     * Refill the water bottle using water from fountain
     * @param fountain fountain that generates the water
     * @see Refillable#refill(Fountain)
     */
    @Override
    public void refill(Fountain fountain) {
        water = fountain.getWater();
    }

    /**
     * Tell that this item is refillable
     */
    @Override
    public void addStatus() {
        addCapability(Status.REFILLABLE);
    }
}
