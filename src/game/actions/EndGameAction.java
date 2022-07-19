package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * This is special class of action for ending the game (Interact with Princess Peach)
 */
public class EndGameAction extends Action {
    /**
     * TargetActor - the actor that this action is targeted (Princess Peach)
     */
    private Actor targetActor;

    /**
     * Constructor
     * @param target Actor whose is the target of this action
     */
    public EndGameAction(Actor target) {
        targetActor = target;
    }

    /**
     * perform the action, i.e. save the PrincessPeach
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message telling the outcome of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Congratulations!\n" + actor + " has saved the princess!!!\nThey have a wedding and live happily!!!";
    }

    /**
     * Message telling the description of this action
     * @param actor The actor performing the action.
     * @return message about the description of this action
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Save " + targetActor + " using the key";
    }
}
