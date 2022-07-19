package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import static game.enums.Status.FIRE_FLOWER;

/**
 * Fire Flower class
 */
public class FireFlower extends Consumable {
    /**
     * count to keep track of the time after FireFlower is consumed
     */
    private int count;

    /**
     * Constructor
     */
    public FireFlower() {
        super("Fire Flower", 'f', false, FIRE_FLOWER);
    }

    /**
     * Method called when actor consumes the fire flower
     * @param actor actor consuming this item
     * @return message showing effect of item/output of consume action
     * @see Consumable#consume(Actor)
     */
    @Override
    public String consume(Actor actor) {
        actor.addCapability(this.getItemStatus());
        count = 0;
        removeAction(this.getConsumeAction());
        return actor + " consumes "+ this;
    }

    @Override
    public String toString() {
        return "Fire Flower";
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (actor.hasCapability(FIRE_FLOWER)){
            count +=1;
        }
        if (count > 20){
            actor.removeCapability(FIRE_FLOWER);
            actor.removeItemFromInventory(this);
        }

    }

}
