package game.Fountains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

public abstract class Fountain extends Ground {
    private final String name;
    private final int maxCapacity;
    private int capacity;
    private final int maxRecoverPeriod;
    private int recoverPeriod;
    protected Status type;

    public Fountain(String name, char displayChar) {
        super(displayChar);
        this.name = name;
        addCapability(Status.FOUNTAIN);
        maxCapacity = 10;
        capacity = maxCapacity;
        maxRecoverPeriod = 5;
        recoverPeriod = maxRecoverPeriod;
    }

    public abstract Water getWater();
    public abstract String getEffect(Actor actor);

    @Override
    public void tick(Location location) {
        if (!canUse()) {
            recoverPeriod = Math.max(0, recoverPeriod - 1);
            capacity = recoverPeriod == 0 ? maxCapacity : capacity;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void use() {
        capacity = Math.max(0, capacity - 1);
        recoverPeriod = capacity == 0 ? maxRecoverPeriod : recoverPeriod;
    }

    public boolean canUse() {
        return capacity > 0;
    }

    public Status getType() {
        return type;
    }
}
