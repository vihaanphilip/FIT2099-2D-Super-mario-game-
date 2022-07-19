package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;

/**
 * Key class
 */
public class Key extends Item {
    /**
     * Constructor
     */
    public Key() {
        super("Key", 'k', true);
        addCapability(Status.HOLD_KEY);
    }
}
