package game;

import java.io.IOException;
import java.util.Random;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.Fountains.HealthFountain;
import game.Fountains.PowerFountain;
import game.actors.*;
import game.enums.Status;
import game.grounds.*;
import game.warps.WarpPipe;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) throws IOException {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(),
								 		   new Lava(), new PowerFountain(), new HealthFountain());

		GameMap gameMap = new GameMap(groundFactory, "src/game/maps/map.txt");
		world.addGameMap(gameMap);

		GameMap lavaMap = new GameMap(groundFactory, "src/game/maps/lava.txt");
		world.addGameMap(lavaMap);

		// Add pipe to lava map
		lavaMap.at(0, 0).setGround(new WarpPipe(lavaMap.at(0, 0), null, Status.LAVA_MAP));

		// Add pipe to normal map
		Random rand = new Random();
		for (int i = 1; i < 5; i++) {
			Location location = null;
			do {
				int xCoord = rand.nextInt(gameMap.getXRange().max());
				int yCoord = rand.nextInt(gameMap.getYRange().max());
				location = gameMap.at(xCoord, yCoord);
			} while (location == null || location.getActor() != null);

			location.setGround(new WarpPipe(location, lavaMap.at(0, 0), Status.NORMAL_MAP));
		}




		Actor mario = new Player("Mario", 'm', 10000);
		world.addPlayer(mario, gameMap.at(42, 10));
		mario.addCapability(Status.CAN_RESET);
		gameMap.at(44, 11).addActor(new Toad());

		gameMap.at(2, 3).addActor(new FlyingKoopa());



		// Add princess and Bowser
		lavaMap.at(45, 11).addActor(new Bowser(lavaMap.at(45, 11)));
		lavaMap.at(46, 11).addActor(new PrincessPeach());


		world.run();

	}
}
