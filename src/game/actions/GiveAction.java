package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action class for giving item
 */
public class GiveAction extends Action {
    /**
     * Item to be given
     */
    private Item item;

    /**
     * Actor who gives the item
     */
    private Actor giver;

    /**
     * Constructor
     * @param item Item to be given
     * @param giver Actor who gives the item
     */
    public GiveAction(Item item, Actor giver) {
        this.item = item;
        this.giver = giver;
    }

    /**
     * Execute the action. The actor is the taker, i.e. who receives item from the giver
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of the action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(item);
        giver.removeItemFromInventory(item);
        return giver + " gives " + item + " to " + actor;
    }

    /**
     * Description of the action
     * @param actor The actor performing the action.
     * @return message telling the description of the action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " takes " + item + " from " + giver;
    }
}
