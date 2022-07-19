package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SelfDestructAction;

import java.util.Random;

/**
 * Behaviour for self destruction
 */
public class SelfDestructBehaviour implements Behaviour {
    /**
     * Random number generator for deciding the success rate of self-destruct
     */
    private final Random rand = new Random();

    /**
     * Success rate of self-destruct
     */
    private final int selfDestructChance;

    /**
     * Constructor
     * @param chance success rate of the self-destruct
     */
    public SelfDestructBehaviour(int chance) {
        selfDestructChance = chance;
    }

    /**
     * Get the action related to this behaviour if applicable
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action if applicable
     * @see Behaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (rand.nextInt(100) < selfDestructChance) {
            return new SelfDestructAction();
        }
        return null;
    }
}
