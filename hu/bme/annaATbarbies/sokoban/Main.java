package hu.bme.annaATbarbies.sokoban;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.Game;
import hu.bme.annaATbarbies.sokoban.model.field.Block;
import hu.bme.annaATbarbies.sokoban.model.field.Field;
import hu.bme.annaATbarbies.sokoban.model.Floor;
import hu.bme.annaATbarbies.sokoban.model.field.Hole;
import hu.bme.annaATbarbies.sokoban.model.field.Switch;
import hu.bme.annaATbarbies.sokoban.model.field.Target;
import hu.bme.annaATbarbies.sokoban.model.field.Trap;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		logger.debug("ujrakezdtunk");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				//Szetszedjuk a beolvasott sort, aminek az elso szava lesz a parancs
				String[] parts = line.split(" "); 
				if (parts[0].equals("loadGame")) {
					//A sor masodik szava a beolvasando fajl neve
					Game.getInstance().start(parts[1]);
				}

				else if (parts[0].equals("bindPlayer")) {
					//A jatekos player_01 formatumban van megadva
					String[] player = parts[1].split("_");
					if (player[1].startsWith("0"))
						Floor.getInstance().setActiveWorker(Integer.parseInt(player[1].substring(1, 1)));
					else
						Floor.getInstance().setActiveWorker(Integer.parseInt(player[1]));

				} else if (parts[0].equals("move")) {
					switch(parts[1]) {
					default:
						break;
					case "left":
						Floor.getInstance().activePlayerMoves(Direction.LEFT);
						break;
					case "right":
						Floor.getInstance().activePlayerMoves(Direction.RIGHT);
						break;
					case "up":
						Floor.getInstance().activePlayerMoves(Direction.UP);
						break;
					case "down":
						Floor.getInstance().activePlayerMoves(Direction.DOWN);
						break;
					
					}

				} else if (parts[0].equals("lubricate")) {
					Floor.getInstance().activePlayerlubricates(parts[1]);

				} else if (parts[0].equals("listPlayers")) {
					Floor.getInstance().list("workers");

				} else if (parts[0].equals("listBoxes")) {
					Floor.getInstance().list("boxes");

				} else if (parts[0].equals("listFields")) {
					Floor.getInstance().list("fields");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
