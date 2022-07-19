package game.warps;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that allow warp action
 */
public interface AllowWarp {
    /**
     * Check whether the object can change its destination
     * @return boolean
     */
    boolean canChangeDestination();

    /**
     * Set the warp destination
     * @param destination location to where the warp action would bring the actor
     */
    void setDestination(Location destination);

    /**
     * Register this object to be AllowWarp in the manager
     * @param warp warp object
     * @param location location of the warp object
     */
    default void register(AllowWarp warp, Location location) {
        WarpManager.getInstance().add(warp, location);
    }
}
