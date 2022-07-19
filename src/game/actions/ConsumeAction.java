package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.items.Consumable;

/**
 * A special class of action to consume the items/consumables
 */
public class ConsumeAction extends Action {
    /**
     * Item/Consumable to be consumed
     */
    private final Consumable item;

    /**
     * Constructor to record the item to be consumed
     * @param item Consumable to be consumed
     */
    public ConsumeAction(Consumable item) {
        this.item = item;
    }

    /**
     * Performs the action, i.e. consume the items
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check whether the actor has the same ability of the consumable
        // If yes, meaning that the actor has already consumed the item
        // and cannot consume it the second time until the effect goes off
        if (actor.hasCapability(item.getItemStatus())) {
            return actor + " has consumed " + item + " and the effect hasn't worn off";
        }
        // Adding item to inventory to keep track of certain effects that lasts for a given amount of turns
        actor.addItemToInventory(item);

        // Consume the item (items with turns limit would stay in inventory while items without turns limit would be removed immediately from consume method.
        String message = item.consume(actor);

        // Obtain the status from the item
        if (item.getItemStatus() != null) {
            actor.addCapability(item.getItemStatus());
        }

        return message;
    }

    /**
     * Message about the description of this action
     * @param actor The actor performing the action.
     * @return message about the action description
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + item;
    }
}
