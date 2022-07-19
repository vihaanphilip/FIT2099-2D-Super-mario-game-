package game.warps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.PiranhaPlant;
import game.enums.Status;
import game.reset.Resettable;

/**
 * Warp Pipe class
 */
public class WarpPipe extends Ground implements AllowWarp, Resettable {
    /**
     * Destination to where this warp pipe can bring the actor
     */
    private Location destination;

    /**
     * The map that this warp pipe is stayed
     */
    private final Status mapStatus;

    /**
     * Show whether the warp pipe has piranha plant on it
     */
    private boolean hasPlant;

    /**
     * Constructor
     * @param location location of the warp pipe
     * @param newDestination destination of the warp pipe
     * @param warpMap map that the warp pipe is staying on
     */
    public WarpPipe (Location location, Location newDestination, Status warpMap){
        super('C');
        hasPlant = false;
        mapStatus = warpMap;
        destination = newDestination;
        register(this, location);
        registerInstance();
    }

    /**
     * List of allowable actions that other actor can perform onto this warp pipe
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of actions
     * @see Ground#allowableActions(Actor, Location, String)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // If no piranha plant, and actor is player, allow the player to jump onto the pipe
        if (location.getActor() == null) {
            if (actor.hasCapability(Status.JUMP)) {
                return new ActionList(new MoveActorAction(location, "(jumps) to the pipe at " + direction));
            }
        } else {
            // If the player is standing on the pipe and this pipe is functioning, allow player to warp
            if (location.getActor().hasCapability(Status.PLAYER) && destination != null) {
                return new ActionList(new WarpAction(destination, mapStatus.toString()));
            }
        }

        return new ActionList();
    }

    /**
     * No actor can enter the pipe except piranha plant that grows from the pipe
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Tell the pipe one turn has passed
     * @param location The location of the Ground
     * @see Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        // Resetted, can grow new piranha plant
        if (hasCapability(Status.RESET)) {
            hasPlant = false;
        }

        // Grow piranha plant at second turn of the game
        if (!hasPlant && location.getActor() == null) {
            location.addActor(new PiranhaPlant());
            hasPlant = true;
        }
    }

    /**
     * Set the destination that this warp pipe
     * @param newDestination destination of the warp pipe
     * @see AllowWarp#setDestination(Location)
     */
    public void setDestination(Location newDestination) {
        destination = newDestination;
    }

    /**
     * Tell whether this warp pipe can change destination
     * @return boolean
     * @see AllowWarp#canChangeDestination()
     */
    @Override
    public boolean canChangeDestination() {
        return mapStatus == Status.LAVA_MAP;
    }

    /**
     * Reset the warp pipe once reset action is executed
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
