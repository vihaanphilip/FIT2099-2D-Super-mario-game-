package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EndGameAction;
import game.behaviours.SpeakBehaviour;
import game.enums.Status;

/**
 * Princess Peach class
 */
public class PrincessPeach extends Ally {
    /**
     * Constructor
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 0);
        // add monologues
        this.addToMonologueManager();
        addMonologue("Dear Mario, I'll be waiting for you...");
        addMonologue("Never gonna give you up!");
        addMonologue("Release me, or I will kick you!");

        addBehaviour(1, new SpeakBehaviour(this));
    }

    /**
     * List of allowable actions that other actor can perform onto this actor
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(Status.HOLD_KEY)) {
            return new ActionList(new EndGameAction(this));
        }

        return new ActionList();
    }
}
