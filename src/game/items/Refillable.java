package game.items;

import game.Fountains.Fountain;

/**
 * Interface for object that can be refilled
 */
public interface Refillable {
    /**
     * Refilled the item with water from fountain
     * @param fountain fountain that generates water
     */
    void refill(Fountain fountain);

    /**
     * Status showing that the object belongs to refillable
     */
    void addStatus();
}
