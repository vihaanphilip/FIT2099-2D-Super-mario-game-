package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special class of action to heal the actor
 */
public class HealAction extends Action {
    /**
     * Check whether it is healing/increase max hitpoints
     */
    private boolean healing;

    /**
     * Constructor
     * @param healing boolean to differentiate between healing and increase hit points
     */
    public HealAction(boolean healing) {
        this.healing = healing;
    }

    /**
     * Execute the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (healing) {
            actor.heal(50);
            return actor + " has been healed 50 hit points";
        } else {
            actor.increaseMaxHp(50);
            return actor + " hit points is increased by 50";
        }
    }

    /**
     * Description of the action to be displayed on the menu
     * @param actor The actor performing the action.
     * @return telling description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks health fountain water";
    }
}
