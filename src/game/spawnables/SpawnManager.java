package game.spawnables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A manager class that is responsible for spawning behaviour
 */
public class SpawnManager {
    /**
     * Random number generator (this decides the spawning at each turn)
     */
    private final Random rand = new Random();

    /**
     * Empty constructor
     */
    public SpawnManager() {}

    /**
     * Spawn the Enemy onto the map
     * @param enemy Enemy (Goomba/Koopa at the moment)
     * @param chance spawning chance (spawn only when success)
     * @param location spawning location
     */
    public void spawnEnemy(Actor enemy, int chance, Location location) {
        if (location.getActor() == null) {
            if (rand.nextInt(100) < chance) {
                location.addActor(enemy);
            }
        }
    }

    /**
     * Spawn the item onto the map
     * @param item Item to be spawned
     * @param chance Chance of successful spawning
     * @param location spawning location
     */
    public void spawnItem(Item item, int chance, Location location) {
        if (rand.nextInt(100) < chance) {
            location.addItem(item);
        }
    }

    /**
     * Grow the tree at adjacent fertile ground
     * @param ground New tree
     * @param location Location of the mature tree
     */
    public void growTree(Ground ground, Location location) {
        List<Exit> exits = location.getExits();
        List<Integer> fertileExitInd = new ArrayList<>();
        int counter = 0;

        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(Status.FERTILE)) {
                fertileExitInd.add(counter);
            }
            counter++;
        }

        int selection = fertileExitInd.get(rand.nextInt(fertileExitInd.size()));
        exits.get(selection).getDestination().setGround(ground);
    }
}
