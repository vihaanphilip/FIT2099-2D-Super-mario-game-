package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground implements FertileGround {
	/**
	 * Constructor
	 */
	public Dirt() {
		super('.');
		fertileInstance();
	}

	/**
	 * Show that the ground is fertile
	 * @see Status#FERTILE
	 */
	public void fertileInstance() {
		addCapability(Status.FERTILE);
	}
}
