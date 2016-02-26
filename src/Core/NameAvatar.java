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

package Core;

/**
 * In some games, like Chess, the players are de facto named (the first player
 * is "white", the other one is "black"). But in most games, in particular when
 * more than two players can take part in the game, it becomes necessary to name
 * players, especially when some players can give up in the middle of the game.
 * 
 * This interface extends the avatars with a name for such cases.
 * 
 * @author Fabian Pijcke
 */
public interface NameAvatar extends Avatar {
	
	/**
	 * @return the name of the avatar.
	 */
	String getName();
	
}
