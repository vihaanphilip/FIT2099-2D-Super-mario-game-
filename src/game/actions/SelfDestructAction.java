package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special action for self-destruct
 */
public class SelfDestructAction extends Action {
    /**
     * Empty constructor
     */
    public SelfDestructAction() {}

    /**
     * Execute the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String stating the outcome of the action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " self-destructs and dies";
    }

    /**
     * Get the brief description of the action
     * @param actor The actor performing the action.
     * @return description of the action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " self-destructs";
    }
}
