package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.enums.Status;
import game.items.SuperMushroom;

/**
 * Dormant Stage of the Koopa
 */
public class KoopaDormant extends Enemy {
    /**
     * Constructor
     */
    public KoopaDormant() {
        super("KoopaDormant", 'D', 0, 0);
        addItemToInventory(new SuperMushroom());
    }

    /**
     * Return list of actions that can be done onto this actor
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.DESTROY_DORMANT)) {
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }
}
