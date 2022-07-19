package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.items.FireFlower;
import game.reset.Resettable;
import game.spawnables.SpawnManager;

import java.util.Random;

/**
 * The abstract class that handles the common behaviours of the tree
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * Age of the tree
     */
    private int age;

    /**
     * Spawn Manager of the tree
     */
    protected final SpawnManager manager = new SpawnManager();

    /**
     * Spawn chance of the tree
     */
    protected int spawnChance;

    /**
     * Reset chance of the tree
     */
    private final int resetChance;

    /**
     * Constructor.
     *
     */
    public Tree(String name, char displayChar, int fallDamage, int jumpSuccessRate) {
        super(name, displayChar, fallDamage, jumpSuccessRate);
        age = 1;
        resetChance = 50;
        registerInstance();
    }

    /**
     * Show that a turn has passed and execute the behaviours of the tree
     * @param location The location of the Ground
     * @see edu.monash.fit2099.engine.positions.Ground#tick(Location)
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // Turn the tree into Dirt if tree is reset
        if (hasCapability(Status.RESET)) {
            location.setGround(new Dirt());
            return;
        }

        // Increase age every turn
        age++;

        // spawn fire flower at transition phase
        if (age >= 10) {
            manager.spawnItem(new FireFlower(), 50, location);
        }
    }

    /**
     * Allow the child tree to get their age
     * @return age of the tree
     */
    protected final int getAge() {
        return this.age;
    }

    /**
     * Reset the Tree if randomly based on the reset chance of the tree during reset action
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        double randomChance = Math.floor(Math.random() * 100);

        if (randomChance < resetChance) {
            addCapability(Status.RESET);
        }
    }
}
