package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interactable interface is implemented by interactable objects
 */
public interface Speakable {

    /**
     * Get a monologue instance from an interactable object
     * @param otherActor the actor who wishes to speak with the interactable object
     * @return the monologue chosen by the interactable object
     */
    String monologueInstance(Actor otherActor);

    /**
     * Adds itself into the Monologue Manager
     */
    default void addToMonologueManager(){
        MonologueManager.getInstance().appendInteractionInstance(this);
    }

}
