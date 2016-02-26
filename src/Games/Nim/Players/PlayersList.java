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

package Games.Nim.Players;

import java.util.ArrayList;

import Games.Nim.Player;

/**
 * Reflection mecanisms do not allow us to find all the classes implemented in a
 * given package, or extending a given class. The classes can not register
 * themselve statically as they will not be loaded unless they are needed.
 * Another solution would have been to check the Games/Nim/Players directory for
 * classes and to determine them using their name, but this seems unsafe and
 * daunty. The solution we use is merely to store the classes in a list. Not
 * very elegant but functional and easy.
 * 
 * Note that the terminal launcher does not need this as the classes are given
 * as textual command-line arguments.
 * 
 * @author Fabian Pijcke
 */
public class PlayersList {

	/**
	 * @return the list of classes playable using a graphical interface.
	 */
	public static ArrayList<Class<? extends Player>> playersListFX() {
		ArrayList<Class<? extends Player>> list = new ArrayList<>();
		list.add(RandomAI.class);
		list.add(HumanDialogBox.class);

		return list;
	}
}
