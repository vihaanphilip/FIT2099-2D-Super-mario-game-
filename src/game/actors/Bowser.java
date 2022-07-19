package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SpeakBehaviour;
import game.enums.Status;
import game.items.Key;

/**
 * Bowser class (Boss enemy)
 */
public class Bowser extends Enemy {
    /**
     * Original Location of Bowser
     */
    private final Location originalLocation;

    /**
     * boolean telling whether Bowser is following the actor
     */
    private boolean followed;

    /**
     * Constructor
     * @param position Original position of bowser
     */
    public Bowser(Location position) {
        super("Bowser", 'B', 500, 80);
        addBehaviour(1, new AttackBehaviour());
        followed = false;
        originalLocation = position;
        addCapability(Status.BOWSER);
        addItemToInventory(new Key());

        // Add monologues
        this.addToMonologueManager();
        addMonologue("What was that sound? Oh, just a fire.");
        addMonologue("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        addMonologue("Never gonna let you down!");
        addMonologue("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
        addBehaviour(2, new SpeakBehaviour(this));
    }

    /**
     * List of actions that can be done by other actor on bowser
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (!followed && otherActor.hasCapability(Status.PLAYER)) {
            followed = true;
            this.addBehaviour(2, new FollowBehaviour(otherActor));
        }

        return super.allowableActions(otherActor, direction, map);
    }

    /**
     * Action to be done on the actor turn
     * @param actions List of actions allowable
     * @param lastAction Action that this actor took last turn
     * @param map map where the actor is located at
     * @param display I/O to display the message
     * @return action to be done in this turn
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            resetMaxHp(0);
            return new MoveActorAction(originalLocation, "to original position");
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getBaseDamage(), "punch");
    }

    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
        followed = false;
        deleteBehaviour(2);
    }
}
