package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;


/**
 * Class representing the sapling tree
 */
public class Sapling extends Tree {
    /**
     * Constructor
     */
    public Sapling() {
        super("Sapling",'t', 20, 80);
        this.spawnChance = 10;
    }

    /**
     * Show that a turn has passed and execute behaviours of the tree
     * @param location Location of the tree on map
     * @see edu.monash.fit2099.engine.positions.Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        if (getAge() >= 10) {
            location.setGround(new Mature());
        }

        manager.spawnItem(new Coin(20), spawnChance, location);
        super.tick(location);
    }
}
