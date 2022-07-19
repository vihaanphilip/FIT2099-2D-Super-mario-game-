package game.warps;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Warp Action extending from MoveActorAction
 */
public class WarpAction extends MoveActorAction {
    /**
     * Manager that handles this action
     */
    private final WarpManager manager;

    /**
     * Constructor
     * @param destination location to where the warp action is destined
     * @param map map of the current location of the warp object
     */
    public WarpAction(Location destination, String map) {
        super(destination, map);
        this.manager = WarpManager.getInstance();
    }

    /**
     * Execute the warp action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this action
     * @see MoveActorAction#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the warp object at destination
        AllowWarp warp = manager.getWarp(moveToLocation);

        // If the warp object can change destination, set it
        // destination to current warp object (travel back)
        if (warp.canChangeDestination()) {
            warp.setDestination(map.locationOf(actor));
        }

        // If there is actor on the destination, remove that actor
        Actor anotherActor = moveToLocation.getActor();
        if (anotherActor != null) {
            moveToLocation.map().removeActor(anotherActor);
        }

        return super.execute(actor, map);
    }

    /**
     * description of the action
     * @param actor The actor performing the action.
     * @return message telling the description
     * @see MoveActorAction#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + direction;
    }
}
