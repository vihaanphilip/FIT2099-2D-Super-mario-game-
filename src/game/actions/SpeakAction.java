package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.monologue.Speakable;
import game.monologue.MonologueManager;

/**
 * SpeakAction class represents an action in which two actors interact
 */
public class SpeakAction extends Action {

    /**
     * The target to have a conversation with
     */
    protected Actor speaker;

    /**
     * Constructor.
     *
     * @param speaker the Actor who speaks
     */
    public SpeakAction(Actor speaker) {
        this.speaker = speaker;
    }

    /**
     * Execution of SpeakAction action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the monologue randomly chosen by the speaker
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int i = 0;
        for (Speakable item : MonologueManager.getInstance().getInteractableActors()){
            if (item == speaker){
                break;
            }
            i++;
        }

        return speaker + ": " + MonologueManager.getInstance().getInteractableActors().get(i).monologueInstance(actor);
    }

    /**
     * Description of the interaction to be displayed in the console menu
     * @param actor The actor performing the action.
     * @return the description of the interaction in String format
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + speaker;
    }
}
