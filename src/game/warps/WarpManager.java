package game.warps;

import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager that handles the warp action
 */
public class WarpManager {
    /**
     * Mapping of location to the warp object
     */
    private Map<Location, AllowWarp> locationToWarp;

    /**
     * singleton instance
     */
    private static WarpManager instance;

    /**
     * Private constructor
     */
    private WarpManager() {
        locationToWarp = new HashMap<>();
    }

    /**
     * Static factory method to access the singleton
     * @return instance
     */
    public static WarpManager getInstance() {
        if (instance == null) {
            instance = new WarpManager();
        }

        return instance;
    }

    /**
     * Add the warp object to the map
     * @param warp warp object to be registered
     * @param location location of the warp object
     */
    public void add(AllowWarp warp, Location location) {
        locationToWarp.put(location, warp);
    }

    /**
     * get the warp object based on the location
     * @param location location of the warp object
     * @return warp object if any
     */
    public AllowWarp getWarp(Location location) {
        return locationToWarp.get(location);
    }
}
