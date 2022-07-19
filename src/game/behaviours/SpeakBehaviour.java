package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;

/**
 * SpeakBehaviour class represents the speak behaviour implementation for actors
 */
public class SpeakBehaviour implements Behaviour {

    /**
     * the actor which is speaking
     */
    private final Actor speaker;

    /**
     * boolean value to check whether the actor spoke in the last turn
     */
    private boolean spokeLastTurn;

    /**
     * public constructor for SpeakBehaviour class
     * @param speaker the actor which is speaking
     */
    public SpeakBehaviour(Actor speaker){
        this.speaker = speaker;
        spokeLastTurn = false;
    }

    /**
     * perform the speak action every alternating turn
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return SpeakAction or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!spokeLastTurn) {
            spokeLastTurn = true;
            return new SpeakAction(speaker);
        } else {
            spokeLastTurn = false;
            return null;
        }

    }
}
