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

package Core;

import Board.IBoard;

/**
 * A move is made of two actions. One going forward and the other backward.
 * 
 * @author Fabian Pijcke
 * @param <B>
 */
public interface IMove<B extends IBoard<?, ?>> {
	/**
	 * Applies the move on the board.
	 * 
	 * @param board
	 */
    void apply(B board);
    
    /**
     * Cancels the move.
     * Assuming the state of the board is the one after apply has been called, the board should be in its state just before after this method is applied.
     * 
     * @param board
     */
    void cancel(B board);
}
