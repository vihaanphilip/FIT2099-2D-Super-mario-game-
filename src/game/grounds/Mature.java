package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.FlyingKoopa;
import game.actors.Koopa;

import java.util.Random;

/**
 * Class that representing a mature tree
 */
public class Mature extends Tree {
    /**
     * Random number generator to decide on the spawn mechanism
     */
    private final Random rand = new Random();

    /**
     * Chance that this tree would wit/die
     */
    private final int witChance;

    /**
     * Constructor
     */
    public Mature() {
        super("Mature",'T', 30, 70);
        this.spawnChance = 15;
        this.witChance = 20;
    }

    /**
     * Allow the tree to know a turn has passed and execute its behaviours
     * @param location Location of the tree on the map
     * @see edu.monash.fit2099.engine.positions.Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        if (rand.nextInt(100) < witChance) {
            location.setGround(new Dirt());
        } else {
            if (rand.nextInt(100) < 50) {
                manager.spawnEnemy(new Koopa(), spawnChance, location);
            } else {
                manager.spawnEnemy(new FlyingKoopa(), spawnChance, location);
            }

            if (getAge() % 5 == 0) {
                manager.growTree(new Sprout(), location);
            }
        }

        super.tick(location);
    }
}
