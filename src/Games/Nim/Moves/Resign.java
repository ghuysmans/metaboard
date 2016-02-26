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

package Games.Nim.Moves;

import Games.Nim.Board;
import Games.Nim.Game;

/**
 * The Resign move discards the player until the end of the game.
 * 
 * Note that this can lead to interresting strategies when there are more than
 * two players, to get a group of players into a winning position.
 * 
 * @author Fabian Pijcke
 */
public class Resign extends Move {

	@Override
	public void apply(Board board) {
		// Nothing to do : The board checks whether the last move was Resign, if
		// yes the current player has won.
	}

	@Override
	public void cancel(Board board) {
		// Nothing to do as the last move will be removed from the stack of
		// moves played already.
	}

	@Override
	public boolean isLegal(Game game) {
		return true;
	}

}
