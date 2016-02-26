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

import Core.IMove;
import Games.Nim.Game;
import Games.Nim.Board;

/**
 * The moves allowed in the Nim game are very simple. Essentially a player can
 * either give up or move the token. The only difficulty is that the maximal
 * displacement allowed is not encoded in the board but in the game rules. The
 * moves are augmented with an isLegal(Game) method to circumvent this
 * difficulty.
 * 
 * In addition, to avoid the implementation of illegal moves declared legal, we
 * do not allow classes outside this package to extend its Move class.
 * 
 * @author Fabian Pijcke
 */
public abstract class Move implements IMove<Board> {
	
	Move() {
		// Package-visibility constructor; therefore only package classes can
		// extend it, this avoids creation of moves applying illegal things to
		// the board.
	}

	/**
	 * @param game
	 * @return true if and only if the game rules allow this move.
	 */
	public abstract boolean isLegal(Game game);
	
}
