package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;


import static game.enums.Status.NO_DROP_TRADE;
import static game.enums.Status.POWER_STAR;
/**
 * Class representing PowerStar
 */
public class PowerStar extends Consumable {
    /**
     * age of power star on map or in player inventory.
     */
    private int age;
    /**
     * count to keep tract of the rounds after Consumable is consumed.
     */
    private int count = -1;
    /**
     * Constructor for PowerStar.
     *
     */
    public PowerStar() {
        super("Power Star", '*', true, POWER_STAR);
        this.addCapability(NO_DROP_TRADE);
        age = 0;
    }

    /**
     * Method to be called when actor consumes this PowerStar which heals actor for 200hp.
     */
    @Override
    public String consume(Actor actor) {
        actor.heal(200);
        count = 0;
        removeAction(this.getConsumeAction());
        return actor + " consumed " + this + " - 10 turns remaining for invincibility";
    }

    /**
     * Keeps track of age. increment by 1 after each round. removes from location once age == 10.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        age++;
        if (age == 10)
            currentLocation.removeItem(this);
    }
    /**
     * Keeps track of the age, increment by 1 after each round. removes this item from inventory once age == 10.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        age++;
        if (age == 10){
            if (age == 10 && count<0){
                actor.removeItemFromInventory(this);
            }
            if (actor.hasCapability(POWER_STAR)){
                count += 1;
            }
            if (count>10){
                actor.removeCapability(POWER_STAR);
                actor.removeItemFromInventory(this);
            }
        }
    }
}
