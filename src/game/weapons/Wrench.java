package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * Wrench Class
 */
public class Wrench extends WeaponItem {
    /**
     * Constructor
     */
    public Wrench() {
        super("Wrench", 'W', 50, "attacks", 80);
        addCapability(Status.DESTROY_DORMANT);
    }
}
