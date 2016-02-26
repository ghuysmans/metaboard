/*
 Copyright 2015-2016 Fabian Pijcke

 This file is part of MetaBoard.

 MetaBoard is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 MetaBoard is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with MetaBoard. If not, see <http://www.gnu.org/licenses/>.
 */

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
