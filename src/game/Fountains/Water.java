package game.Fountains;

import edu.monash.fit2099.engine.actors.Actor;
import game.Fountains.Fountain;

/**
 * Water class
 */
public class Water {
    /**
     * Fountain from where this water is collected
     */
    private final Fountain fountain;

    /**
     * Name of the water
     */
    private final String name;

    /**
     * Constructor
     * @param name name of the water
     * @param fountain fountain where this water is collected
     */
    public Water(String name, Fountain fountain) {
        this.name = name;
        this.fountain = fountain;
    }

    /**
     * Effect of drinking this water
     * @param actor actor who drinks the water
     * @return string telling the effect of this water
     * @see Fountain#getEffect(Actor)
     */
    public String consumeEffect(Actor actor) {
        return fountain.getEffect(actor);
    }

    /**
     * convert this object to string
     * @return name of the water
     */
    @Override
    public String toString() {
        return name;
    }
}
