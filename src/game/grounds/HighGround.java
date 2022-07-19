package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;

/**
 * An abstract class that is responsible for High Ground behaviours
 */
public abstract class HighGround extends Ground {
    /**
     * Name of the high ground, either Wall or Tree at the moment
     */
    private final String name;

    /**
     * The fall damage if actor fails the jump to this high ground
     */
    private final int fallDamage;

    /**
     * The success rate of the actor to jump
     */
    private final int jumpSuccessRate;

    /**
     * Constructor
     * @param name name of the ground (Wall and Tree at the moment)
     * @param displayChar character of the ground
     * @param fallDamage fall damage when actor fails the jump to the ground
     * @param jumpSuccessRate success rate when actor jumps to the ground
     */
    public HighGround(String name, char displayChar, int fallDamage, int jumpSuccessRate) {
        super(displayChar);
        this.name = name;
        this.fallDamage = fallDamage;
        this.jumpSuccessRate = jumpSuccessRate;
    }

    /**
     * Stringify the instance into its name
     * @return name of the ground
     */
    public String toString() { return this.name; }

    /**
     * Provide list of actions that is allowed for the actor acting to this ground
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of actions that is allowable
     * @see Ground#allowableActions(Actor, Location, String)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.getActor() != actor) {
            if (actor.hasCapability(Status.JUMP) && !actor.hasCapability(Status.POWER_STAR)) {
                return new ActionList(new JumpAction(location, direction, this));
            }
        }

        return new ActionList();
    }

    /**
     * Check whether the actor can enter this ground
     *
     * @param actor the Actor to check
     * @return boolean that decides whether an actor can enter this ground
     * @see Ground#canActorEnter(Actor)
     * @see Status#JUMP
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.POWER_STAR) || actor.hasCapability(Status.FLY);
    }

    /**
     * Block any thrownable objects to pass through this ground
     * @return boolean
     * @see Ground#blocksThrownObjects()
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Get the fall damage related to this ground
     * @return fall damage
     */
    public final int getFallDamage() {
        return this.fallDamage;
    }

    /**
     * get the success rate of jump to this ground
     * @return success rate of jumping
     */
    public final int getJumpSuccessRate() {
        return this.jumpSuccessRate;
    }

    @Override
    public void tick(Location location) {
        if(location.containsAnActor() && location.getActor().hasCapability(Status.POWER_STAR)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }
}
