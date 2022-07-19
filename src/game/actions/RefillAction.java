package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Fountains.Fountain;
import game.items.Refillable;

/**
 * Special Action to refill the refillable (bottle) in this case
 */
public class RefillAction extends Action {
    /**
     * Item to be refilled, bottle in this context
     */
    private Refillable refillable;

    /**
     * The place where the fluid is obtained, fountain in this case
     */
    private Fountain fountain;

    /**
     * Constructor
     * @param refillable Bottle to be refilled
     * @param fountain fountain which provides water
     */
    public RefillAction(Refillable refillable, Fountain fountain) {
        this.refillable = refillable;
        this.fountain = fountain;
    }

    /**
     * Execute the action, refill the bottle with water from fountain
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Update the use limit of the fountain
        fountain.use();
        // Refill the bottle
        refillable.refill(fountain);
        return menuDescription(actor) + " successfully";
    }

    /**
     * Message telling the description of the action
     * @param actor The actor performing the action.
     * @return description of action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills " + refillable;
    }
}
