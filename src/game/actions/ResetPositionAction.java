package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class ResetPositionAction extends Action {
    private Location targetLocation;

    public ResetPositionAction(Location position) {
        targetLocation = position;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        map.addActor(actor, targetLocation);
        actor.increaseMaxHp(0);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is reset to original position";
    }
}
