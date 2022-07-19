package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.reset.ResetManager;

/**
 * A special action that allow the actor to reset the map
 */
public class ResetAction extends Action {

    /**
     * The manager that handles the resettable objects
     */
    private ResetManager manager;

    /**
     * Constructor to assign the manager
     */
    public ResetAction() {
        manager = ResetManager.getInstance();
    }

    /**
     * Hotkey for executing the reset action
     * @return Char 'r'
     * @see Action#hotkey()
     */
    public String hotkey() {return "r";}

    /**
     * Execute the action on player's turn
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String related to the outcome of the action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Can only be used once per game
        actor.removeCapability(Status.CAN_RESET);

        // Revert the status of the player
        if (actor.hasCapability(Status.PLAYER)) {
            actor.increaseMaxHp(0);
            actor.removeCapability(Status.POWER_STAR);
            actor.removeCapability(Status.SUPER_MUSHROOM);
        }

        // Run the manager to reset
        manager.run();
        return "The Map is RESETTING...";
    }

    /**
     * Brief description of the action
     * @param actor The actor performing the action.
     * @return String about the description
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the map";
    }
}