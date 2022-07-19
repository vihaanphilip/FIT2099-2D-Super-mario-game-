package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.enums.Status;

/**
 * Consumable Class
 */
public abstract class Consumable extends Item {
    /**
     * Status of the consumable (effect from the item)
     */
    private final Status itemStatus;

    /**
     * Action to consume the consumable
     */
    protected final Action consumeAction;

    /**
     * Constructor
     * @param name name of the item
     * @param displayChar display character of the item
     * @param portable whether this item can be dropped/picked up
     * @param itemStatus effect of the item
     */
    public Consumable(String name, char displayChar, boolean portable, Status itemStatus) {
        super(name, displayChar, portable);
        this.itemStatus = itemStatus;
        consumeAction = new ConsumeAction(this);
        addAction(consumeAction);
    }

    /**
     * Get the effect of the item
     * @return Status
     */
    public Status getItemStatus() {
        return itemStatus;
    }

    /**
     * Method called when this item is consumed
     * @param actor actor consuming this item
     * @return message of the effect of the item/action
     */
    public abstract String consume(Actor actor);

    public Action getConsumeAction() {
        return consumeAction;
    }
}
