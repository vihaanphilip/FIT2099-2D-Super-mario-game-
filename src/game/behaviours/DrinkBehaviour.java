package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.BattleActor;
import game.enums.Status;
import game.Fountains.Fountain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Behaviour that allows the enemies to drink the fountain
 */
public class DrinkBehaviour extends Action implements Behaviour{
    /**
     * Fountain which provides the water
     */
    private Fountain fountain;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Execute the drink action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message showing the outcome of the action
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.use();
        return fountain.getEffect(actor);
    }

    /**
     * Display the description of drink action
     * @param actor The actor performing the action.
     * @return description of action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water from fountain";
    }

    /**
     * Get the action related to this behaviour
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action
     * @see Behaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Enemy performing this action
        BattleActor tempActor = (BattleActor) actor;

        // List of fountains available in surrounding
        List<Fountain> fountains = new ArrayList<>();

        // Get the fountains available in surrounding
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().getGround().hasCapability(Status.FOUNTAIN)) {
                Fountain currFountain = (Fountain) exit.getDestination().getGround();
                if (currFountain.canUse()) {
                    fountains.add(currFountain);
                }
            }
        }

        // If there is fountain, check conditions
        if (fountains.size() > 0) {
            fountain = fountains.get(rand.nextInt(fountains.size()));

            // If health fountain is found, and enemy is hurt before, go for it
            if (fountain.getType() == Status.NEED_HEAL) {
                if (actor.hasCapability(Status.NEED_HEAL)) {
                    actor.removeCapability(Status.NEED_HEAL);
                    return this;
                }

                return null;
            }

            // If attack fountain is found and enemy damage is not maxed, go for it
            if (tempActor.setBaseDamage(0))
                return this;
        }

        return null;
    }
}
