package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

/**
 * Class representing sprout tree
 */
public class Sprout extends Tree {
    /**
     * Constructor
     */
    public Sprout() {
        super("Sprout",'+', 10, 90);
        this.spawnChance = 10;
    }

    /**
     * Show a turn has passed and execute behaviours of the tree
     * @param location Location of the tree
     * @see edu.monash.fit2099.engine.positions.Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        if (getAge() >= 10) {
            location.setGround(new Sapling());
        }
        manager.spawnEnemy(new Goomba(), spawnChance, location);
        super.tick(location);
    }
}
