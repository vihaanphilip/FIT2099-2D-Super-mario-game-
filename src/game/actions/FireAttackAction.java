package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Bowser;
import game.enums.Status;
import game.items.Fire;

/**
 * Special class for fire attack
 */
public class FireAttackAction extends AttackAction {

    /**
     * Constructor
     * @param target actor that is target of this attack
     * @param direction direction of the target relative to the actor attacking
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Execute the action, i.e. launch fire attack
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this attack.
     * @see AttackAction#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Carry out the attack procedure
        Fire fire = new Fire(3);
        // Let fire know who released fire to make actor immune to fire.
        fire.setAttacker(actor);
        // Left the fire on the ground no matter whether the attack misses
        map.locationOf(target).addItem(fire);
        if (actor.hasCapability(Status.BOWSER)){
            return super.execute(actor, map);
        }
        return actor + " burns " + target + " with fire";
    }

    /**
     * Description of this action
     * @param actor The actor performing the action.
     * @return message telling the description of the action
     * @see AttackAction#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor) + " with fire";
    }
}
