package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackDormantAction;
import game.actions.FireAttackAction;
import game.behaviours.*;
import game.enums.Status;

/**
 * AbstractKoopa Class
 */
public abstract class AbstractKoopa extends Enemy implements EnterDormant {
    /**
     * Constructor
     */
    public AbstractKoopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 30);
        addBehaviour(10, new WanderBehaviour());
        addBehaviour(2, new DrinkBehaviour());
        addBehaviour(1, new AttackBehaviour());

        // Add monologues
        this.addToMonologueManager();
        addMonologue("Never gonna make you cry!");
        addMonologue("Koopi koopi koopii~!");
        addBehaviour(3, new SpeakBehaviour(this));
    }

    /**
     * List of actions that can be done onto this actor
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return List of actions
     * @see Actor#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackDormantAction(this, enterDormant(), direction));
            addBehaviour(3, new FollowBehaviour(otherActor));
        } if (otherActor.hasCapability(Status.FIRE_FLOWER)){
            actions.add(new FireAttackAction(this,direction));
        }

        return actions;
    }

    /**
     * Get the intrinsic weapon of koopa
     * @return intrinsic weapon
     * @see Actor#getIntrinsicWeapon()
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getBaseDamage(), "punches");
    }

    /**
     * Enter the second stage (dormant stage)
     * @return second stage of koopa which is instance of Actor
     * @see EnterDormant#enterDormant()
     */
    @Override
    public Actor enterDormant() {
        return new KoopaDormant();
    }
}
