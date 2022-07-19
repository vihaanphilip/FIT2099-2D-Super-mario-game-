package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.*;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {
	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20, 10);
		addBehaviour(10, new WanderBehaviour());
		addBehaviour(1, new SelfDestructBehaviour(10));
		addBehaviour(2, new AttackBehaviour());
		addBehaviour(3, new DrinkBehaviour());

		// Add monologues
		this.addToMonologueManager();
		addMonologue("Mugga mugga!");
		addMonologue("Ugha ugha... (Never gonna run around and desert you...)");
		addMonologue("Ooga-Chaka Ooga-Ooga!");
		addBehaviour(4, new SpeakBehaviour(this));
	}

	/**
	 * Intrinsic Weapon of Goomba
	 * @return IntrinsicWeapon (either punch/kick)
	 * @see IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(getBaseDamage(), "kicks");
	}
}
