package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.ResetAction;
import game.enums.Status;
import game.items.Bottle;
import game.reset.Resettable;
import game.wallet.Wallet;

/**
 * Class representing the Player.
 */
public class Player extends BattleActor implements Resettable {

	private final Wallet wallet;

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, 5);
		this.addCapability(Status.PLAYER);

		// For resettable
		this.addCapability(Status.CAN_RESET);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.JUMP);
		this.wallet = new Wallet(this);
		wallet.registerInstance();
	}

	/**
	 * Define what is the action next
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action chosen by the player
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// display message regarding Power Star effect
		if (this.hasCapability(Status.POWER_STAR)){
			display.println(this + " is INVINCIBLE!");
		}
		// displays message regarding Fire Flower effect.
		if (this.hasCapability(Status.FIRE_FLOWER)){
			display.println(this + " have Fire Attack!");
		}


		// return/print the console menu
		display.println(this + " Health: " + printHp());
		display.println("Wallet Balance: " + wallet.getBalance());

		if (this.hasCapability(Status.CAN_RESET)){
			actions.add(new ResetAction());
		}

		return menu.showMenu(this, actions, display);
	}

	/**
	 * get the display character of the player
	 * @return single character representing the player
	 * @see Actor#getDisplayChar()
	 */
	@Override
	public char getDisplayChar(){
		return (this.hasCapability(Status.TALL) || this.hasCapability(Status.SUPER_MUSHROOM))? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * method called when actor receives damage
	 * @param points number of hitpoints to deduct.
	 * @see Actor#hurt(int)
	 */
	@Override
	public void hurt(int points) {
		if (this.hasCapability(Status.POWER_STAR)){
			points = 0;
		}
		if (this.hasCapability(Status.SUPER_MUSHROOM)){
			this.removeCapability(Status.SUPER_MUSHROOM);
		}
		super.hurt(points);
	}

	/**
	 * Allow the manager to record player as resettable instance
	 * @see Resettable#resetInstance()
	 */
	@Override
	public void resetInstance() {
		addCapability(Status.RESET);
	}

	/**
	 * Get the intrinsic weapon of the player
	 * @return intrinsic weapon
	 * @see Actor#getIntrinsicWeapon()
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(getBaseDamage(), "punches");
	}
}
