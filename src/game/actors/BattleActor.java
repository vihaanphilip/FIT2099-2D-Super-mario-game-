package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

/**
 * Special Class of Actor that can battle
 */
public abstract class BattleActor extends Actor {
    /**
     * Base Damage of the actor
     */
    private int baseDamage;

    /**
     * Maximum Damage to which the base Damage can be increased
     */
    private final int maxDamage;

    /**
     * constructor
     * @param name name of the actor
     * @param displayChar display character of the actor
     * @param hitpoints hit point of the actor
     * @param baseDamage base damage of the actor
     * @see Actor
     */
    public BattleActor(String name, char displayChar, int hitpoints, int baseDamage) {
        super(name, displayChar, hitpoints);
        maxDamage = 100;
        this.baseDamage = baseDamage;
    }

    /**
     * get the base damage
     * @return base damage of the actor
     */
    protected int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Increase the base damage based on input
     * @param increment value to increase the base damage
     * @return boolean telling whether the increment is successful (false means no increment can be done)
     */
    public boolean setBaseDamage(int increment) {
        baseDamage = Math.min(baseDamage + increment, maxDamage);
        return baseDamage < maxDamage;
    }

    /**
     * decrease hit point of the actor
     * @param points number of hitpoints to deduct.
     * @see Actor#hurt(int)
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        addCapability(Status.NEED_HEAL);
    }
}
