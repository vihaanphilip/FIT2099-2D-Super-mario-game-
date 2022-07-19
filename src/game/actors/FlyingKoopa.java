package game.actors;

import game.enums.Status;

/**
 * Flying Koopa class
 */
public class FlyingKoopa extends AbstractKoopa {
    /**
     * Constructor
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        // has ability to fly
        addCapability(Status.FLY);

        addMonologue("Pam pam pam!");
    }
}
