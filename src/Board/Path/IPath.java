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

package Board.Path;

import Board.IBoardProxy;
import Core.Piece;

/**
 * Common interface to Path2D and PathProxy, meant to be passed to both the user (AI) and to the Game implementation.
 * 
 * @author Fabian Pijcke
 * @param <P>
 * @param <C> A 1D (Path) Coordinate type.
 */
public interface IPath<P extends Piece, C extends PathCoordinate> extends IBoardProxy<P, C> {
	/**
	 * @return the length of the board.
	 */
	int getLength();
	
	/**
	 * @param c
	 * @return true if the coordinate belongs to the limits of the board. 
	 */
	boolean has(C c);
}
