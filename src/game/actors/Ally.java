package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SelfDestructAction;
import game.behaviours.Behaviour;
import game.enums.Status;
import game.monologue.Monologue;
import game.monologue.Speakable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Ally extends Actor implements Speakable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();


    private ArrayList<Monologue> monologues = new ArrayList<Monologue>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ally(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.ALLY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    protected final void addBehaviour(int priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
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
}
