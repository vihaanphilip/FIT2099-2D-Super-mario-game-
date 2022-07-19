package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Consumable;

import static game.enums.Status.NO_DROP_TRADE;
import static game.enums.Status.SUPER_MUSHROOM;

/**
 * Class represents Super Mushroom consumables
 */
public class SuperMushroom extends Consumable {

    /**
     * Constructor for SuperMushroom
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true, SUPER_MUSHROOM);
        this.addCapability(NO_DROP_TRADE);
    }

    /**
     * consume method which is used when SuperMushroom is consumed to increase actor max HP by 50.
     */
    @Override
    public String consume(Actor actor) {
        actor.increaseMaxHp(50);
        actor.addCapability(this.getItemStatus());
        actor.removeItemFromInventory(this);
        return actor + " consumed " + this + " and become large";
    }
}
