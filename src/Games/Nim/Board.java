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

import Board.Path.Path;
import Board.Path.PathCoordinate;

/**
 * The Nim board is a simple path on which a token is moved towards position 0.
 * As there is only one token, we provide a facility to get the token position
 * in constant time.
 * 
 * @author Fabian Pijcke
 */
public class Board extends Path<Token, PathCoordinate> implements IBoard {

	private Token token;
	private PathCoordinate tokenPosition;

	/**
	 * Constructs a path of a given length and places the token on the last
	 * cell.
	 * 
	 * @param length
	 */
	public Board(int length) {
		super(length);
		token = new Token();
		tokenPosition = new PathCoordinate(length - 1);
		setPieceAt(tokenPosition, token);
	}

	@Override
	public PathCoordinate getTokenPosition() {
		return tokenPosition;
	}

	@Override
	public void setPieceAt(PathCoordinate c, Token dummy) {
		super.setPieceAt(c, token);
		tokenPosition = c;
	}

}
