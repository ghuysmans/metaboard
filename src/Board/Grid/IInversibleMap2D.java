/*
 Copyright 2015 Fabian Pijcke

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

package Board.Grid;

import Core.Piece;

/**
 * Extends the 2D map interface with an operation returning the coordinates of a given piece.
 * This common interface is meant to be passed both to the user (or AI) and to the game implementation.
 * 
 * @author Fabian Pijcke
 * @param <P> A piece, as in IMap2D.
 * @param <C> A 2D coordinate.
 */
public interface IInversibleMap2D<P extends Piece, C extends GridCoordinate> extends IMap2D<P, C> {
	/**
	 * @param piece
	 * @return the coordinate of the given piece.
	 */
    C getCoordinate(P piece);
}
