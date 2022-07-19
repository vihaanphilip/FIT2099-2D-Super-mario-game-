package game.monologue;

import java.util.ArrayList;
import java.util.List;

/**
 * MonologueManager class represents a class that contains the Interactable objects in the game
 */
public class MonologueManager {

    /**
     * List of interactable objects
     */
    private List<Speakable> interactableList;

    /**
     * An instance of Monologue Manager
     */
    private static MonologueManager instance;

    /**
     * Gets an instance of Monologue Manager
     * @return an instance of Monologue Manager
     */
    public static MonologueManager getInstance(){
        if(instance == null){
            instance = new MonologueManager();
        }
        return instance;
    }

    /**
     * Private constructor of Monologue Manager
     */
    private MonologueManager(){
        interactableList = new ArrayList<>();
    }

    /**
     * Get the list of Interactable objects
     * @return a list of Interavtable objects
     */
    public ArrayList<Speakable> getInteractableActors(){
        return new ArrayList<Speakable>(this.interactableList);
    }

    /**
     * Adds an Interactable object into the list
     * @param actor the interactable object to be added
     */
    public void appendInteractionInstance(Speakable actor){
        this.interactableList.add(actor);
    }

}
