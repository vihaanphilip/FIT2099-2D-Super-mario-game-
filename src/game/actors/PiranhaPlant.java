package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.HealAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.SpeakBehaviour;
import game.enums.Status;

/**
 * Piranha Plant Class
 */
public class PiranhaPlant extends Enemy {
    /**
     * Constructor
     */
    public PiranhaPlant() {
        super("PiranhaPlant", 'Y', 150, 90);
        // Can only attack
        addBehaviour(1, new AttackBehaviour());

        this.addToMonologueManager();
        addMonologue("Slsstssthshs~! (Never gonna say goodbye~)");
        addMonologue("Ohmnom nom nom nom.");
        addBehaviour(4, new SpeakBehaviour(this));
    }

    /**
     * Return the current action to be done in this turn
     * @param actions list of available actions
     * @param lastAction last action performed in previous turn
     * @param map map containing the actor
     * @param display I/O to display message
     * @return action to be done in this turn
     * @see Enemy#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            return new HealAction(false);
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Get the intrinsic weapon of the actor
     * @return Intrinsic weapon
     * @see edu.monash.fit2099.engine.actors.Actor#getIntrinsicWeapon()
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getBaseDamage(), "chomps");
    }
}
