package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.HighGround;
import game.enums.Status;

import java.util.Random;

/**
 * Special Action for jump to HighGround
 */
public class JumpAction extends Action {
    /**
     * The destination to where the actor would jump
     */
    private final Location jumpToLocation;

    /**
     * The direction of the destination relative to actor's location
     */
    private final String direction;

    /**
     * The ground of the destination to where the actor would jump
     */
    private final HighGround ground;

    /**
     * Random number generator to decide whether the jump is successful
     */
    private final Random rand = new Random();

    /**
     * Simple constructor that assigns the attributes
     *
     * @param jumpToLocation Location to where the actor jump
     * @param direction Direction of the location relative to actor's location
     * @param ground Ground of the location where the actor jump to
     */
    public JumpAction(Location jumpToLocation, String direction, HighGround ground) {
        this.jumpToLocation = jumpToLocation;
        this.direction = direction;
        this.ground = ground;
    }


    /**
     * Execute the jump action during the actor's turn
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String stating the result of the action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean success;
        if (actor.hasCapability(Status.SUPER_MUSHROOM)) {
            success = true;
        } else {
            success = rand.nextInt(100) < ground.getJumpSuccessRate();
        }

        if (success) {
            map.moveActor(actor, jumpToLocation);
            return menuDescription(actor, success);
        }

        actor.hurt(ground.getFallDamage());

        if (!actor.isConscious()) {
            map.removeActor(actor);
        }

        return menuDescription(actor, success);
    }

    /**
     * Brief description of the action shown on the display
     *
     * @param actor The actor performing the action.
     * @return String stating the description of the action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + ground + "(" + jumpToLocation.x() + "," + jumpToLocation.y() + ")"+ " at " + direction;
    }

    /**
     * Brief description of the result of this action
     *
     * @param actor The actor performing the action
     * @param success Indicator of whether the jump is successful
     * @return String stating the outcome of the action
     * @see #execute(Actor, GameMap)
     */
    public String menuDescription(Actor actor, boolean success) {
        if (success) {
            return actor + " jumps successfully to " + ground + "(" + jumpToLocation.x() + "," + jumpToLocation.y() +
                    ")" + "located at " + direction;
        } else {
            String res = actor + " fails the jump and receive " + ground.getFallDamage() + " damages.";

            if (!actor.isConscious()) {
                return res + " Oops! " + actor + " dies in the jump! So sad...";
            }

            return res;
        }
    }
}
