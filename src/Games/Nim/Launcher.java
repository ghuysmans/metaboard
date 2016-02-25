package Games.Nim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Core.NameAvatar;

public class Launcher {

	public static void main(String[] args) {
		int maxLeap = Integer.parseInt(args[0]);
		int initialPosition = Integer.parseInt(args[1]);
		ArrayList<Player> players = new ArrayList<>();
		for (int i = 2; i < args.length; ++i) {
			try {
				final int n = i;
				String className = "Games.Nim.Players." + args[i];
				Class<? extends Player> c = Class.forName(className).asSubclass(Player.class);
				Constructor<? extends Player> constructor = c.getConstructor(NameAvatar.class);
				players.add(constructor.newInstance(new NameAvatar() {
					@Override
					public String getName() {
						return "Player " + (n - 2);
					}
				}));
			}
			catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
				System.err.println(e.toString());
				System.out.println("Player skipped");
			}
		}
		
		Game game = new Game(players, maxLeap, initialPosition);
		while (!game.isGameEnded()) {
			game.printStatus();
			game.step();
		}
		
		game.printStatus();
	}

}
