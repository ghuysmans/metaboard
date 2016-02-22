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

import Board.IBoardProxy;
import Core.Piece;

/**
 * Common interface to Map2D and Map2DProxy, meant to be passed to both the user (AI) and to the Game implementation.
 * 
 * @author Fabian Pijcke
 * @param <P>
 * @param <C> A 2D Coordinate type.
 */
public interface IMap2D<P extends Piece, C extends GridCoordinate> extends IBoardProxy<P, C> {
	/**
	 * @return the width of the board.
	 */
    int getWidth();
    
    /**
     * @return the height of the board.
     */
    int getHeight();
    
    /**
     * @param c
     * @return true if the coordinate belongs to the limits of the board.
     */
    boolean has(C c);
}