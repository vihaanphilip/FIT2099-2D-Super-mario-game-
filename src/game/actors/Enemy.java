package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.actions.SelfDestructAction;
import game.behaviours.Behaviour;
import game.enums.Status;
import game.monologue.Monologue;
import game.monologue.Speakable;
import game.reset.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract class to generalize common behaviour of enemy
 */
public abstract class Enemy extends BattleActor implements Resettable, Speakable {
    /**
     * Map the behaviour to its priority (priority, behaviour)
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private ArrayList<Monologue> monologues = new ArrayList<Monologue>();

    /**
     * Constructor
     * @param name name of the enemy
     * @param displayChar display character of the enemy
     * @param hitPoints hitpoint of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints, int baseDamage) {
        super(name, displayChar, hitPoints, baseDamage);
        addCapability(Status.ENEMY);
        registerInstance();
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

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        } if (otherActor.hasCapability(Status.FIRE_FLOWER)){
            actions.add(new FireAttackAction(this,direction));
        }

        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            return new SelfDestructAction();
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Register the new behaviour of the enemy
     * @param priority priority of the behaviour
     * @param behaviour behaviour
     */
    protected final void addBehaviour(int priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    protected final void deleteBehaviour(int priority) {
        behaviours.remove(priority);
    }

    protected final void addMonologue(String monologueStr){
        monologues.add(new Monologue(monologueStr));
    }

    protected final void addMonologue(String monologueStr, Enum<?> status, boolean condition){
        monologues.add(new Monologue(monologueStr, status, condition));
    }

    @Override
    public String monologueInstance(Actor otherActor) {

        Random rand = new Random();
        boolean isValid;
        int selection;
        String monologue = "";


        selection = rand.nextInt(monologues.size());

        do {
            isValid = monologues.get(selection).isValid(otherActor);

            if (!isValid){
                selection = rand.nextInt(monologues.size());
            } else {
                monologue = monologues.get(selection).getMonologueStr();
            }
        } while (!isValid);

        return monologue;
    }

    /**
     * Reset the instance
     * @see Status#RESET
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
