package game.monologue;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

/**
 * Monologue class represents a monologue which have specific conditions to be displayed
 */
public class Monologue {

    /**
     * the monologue in String format
     */
    private final String monologueStr;

    /**
     * the status affecting this monologue
     */
    private final Enum<?> status;

    /**
     * condition of status, if an actor has to have or must not have a status in order for this monologue to be displayed
     */
    private final boolean condition;

    /**
     * Public constructor for monologue class
     * @param monologueStr the monologue in String format
     */
    public Monologue(String monologueStr){
        this.monologueStr = monologueStr;
        this.status = null;
        this.condition = false;
    }

    /**
     * Public constructor for monologue class
     * @param monologueStr the monologue in String format
     * @param status the status affecting this monologue
     * @param condition condition of status, if an actor has to have or must not have a status in order for this monologue to be displayed
     */
    public Monologue(String monologueStr, Enum<?> status, boolean condition){
        this.monologueStr = monologueStr;
        this.status = status;
        this.condition = condition;
    }

    /**
     * gets the monologue in String format
     * @return monologueStr
     */
    public String getMonologueStr(){
        return monologueStr;
    }

    /**
     * determine whether it is valid to display this monologue
     * @param otherActor actor wishing to interact
     * @return true or false
     */
    public boolean isValid(Actor otherActor){
        if (this.status == null){
            return true;
        } else return otherActor.hasCapability(status) == condition;
    }
}


