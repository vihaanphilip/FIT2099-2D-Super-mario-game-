package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GiveAction;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.behaviours.SpeakBehaviour;
import game.items.Bottle;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.monologue.Speakable;
import game.enums.Status;
import game.weapons.Wrench;

import java.util.Random;

public class Toad extends Ally {

    /**
     * Constructor for Toad class.
     */
    public Toad() {
        super("Toad", 'O', Integer.MAX_VALUE);
        this.addToMonologueManager();
        addBehaviour(2, new SpeakBehaviour(this));

        // Add monologues
        addMonologue("You might need a wrench to smash Koopa's hard shells.", Status.DESTROY_DORMANT, false);
        addMonologue("You better get back to finding the Power Stars.", Status.POWER_STAR, false);
        addMonologue("The Princess is depending on you! You are our only hope.");
        addMonologue("Being imprisoned in these walls can drive a fungus crazy :(");

        // Add bottle to first item
        addItemToInventory(new Bottle());
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        actions.add(new TradeAction(otherActor, new PowerStar(), 600));
        actions.add(new TradeAction(otherActor, new SuperMushroom(), 400));
        if (!otherActor.hasCapability(Status.DESTROY_DORMANT)) {
            actions.add(new TradeAction(otherActor, new Wrench(), 200));
        }
        if (getInventory().size() > 0 && getInventory().get(0).hasCapability(Status.REFILLABLE)) {
            actions.add(new GiveAction(getInventory().get(0), this));
        }
        actions.add(new SpeakAction(this));

        return actions;
    }

}
