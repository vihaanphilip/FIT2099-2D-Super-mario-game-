package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * This is the attack action related to actor that can enter
 * Dormant State (second stage)
 */
public class AttackDormantAction extends AttackAction {
    /**
     * The dormant state enemy
     */
    private final Actor targetDormant;

    /**
     * Constructor
     * @param target actor to be attacked
     * @param targetDormant dormant state of target
     * @param direction direction of target relative to actor executing this action
     */
    public AttackDormantAction(Actor target, Actor targetDormant, String direction) {
        super(target, direction);
        this.targetDormant = targetDormant;
    }

    /**
     * Execute the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String stating the outcome of the action
     * @see edu.monash.fit2099.engine.actions.Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the location of the target
        Location targetLocation = map.locationOf(target);

        // Run the attack action
        String result =  super.execute(actor, map);

        // Check the death of the target and enter dormant state
        if (!target.isConscious()) {
            map.addActor(targetDormant, targetLocation);
            result += System.lineSeparator() + target + " enters dormant state";
        }

        return result;
    }
}
