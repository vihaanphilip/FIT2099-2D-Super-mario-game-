package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.FireAttackAction;
import game.enums.Status;
import game.actions.AttackAction;

/**
 * Class implement AttackBehaviour for NPC
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Get the action related to this behaviour
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action if applicable
     * @see Behaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.PLAYER)) {
                if (actor.hasCapability(Status.BOWSER)) {
                    return new FireAttackAction(target, exit.getName());
                }
                return new AttackAction(target, exit.getName());
            }
        }

        return null;
    }
}
