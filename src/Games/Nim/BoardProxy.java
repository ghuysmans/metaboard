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

import Board.Path.PathCoordinate;
import Board.Path.PathProxy;

/**
 * The board given to the players. The token position can be retrieved in
 * constant time.
 * 
 * @author Fabian Pijcke
 */
public class BoardProxy extends PathProxy<Token, PathCoordinate, Board> implements IBoard {

	private Board board;

	/**
	 * Constructs a proxy by encapsulation.
	 * 
	 * @param board
	 */
	public BoardProxy(Board board) {
		super(board);
		this.board = board;
	}

	@Override
	public PathCoordinate getTokenPosition() {
		return board.getTokenPosition();
	}

}
