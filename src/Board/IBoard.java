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

package Board;

import Core.Piece;

/**
 * The board contains the current state of the game.
 * 
 * The board uses the Proxy design pattern. That is, a subset of the methods
 * defined here are also defined in a proxy class, which is meant to be given to
 * the players. The complete board is meant to be used by the game engine, which
 * needs to be able to alter its state directly.
 * 
 * @author Fabian Pijcke
 * @param <P>
 *            The class of pieces that will be put on the board.
 * @param <C>
 *            The coordinates used on the board.
 */
public interface IBoard<P extends Piece, C extends ICoordinate> extends IBoardProxy<P, C> {

	/**
	 * Puts the given piece at the given coordinate. The behaviour is not
	 * specified if the piece was already present on the board.
	 * 
	 * @param coord
	 * @param piece
	 */
	void setPieceAt(C coord, P piece);

}
