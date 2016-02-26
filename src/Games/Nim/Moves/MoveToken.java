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

import Board.Path.PathCoordinate;
import Games.Nim.Board;
import Games.Nim.Game;

public final class MoveToken extends Move {
	
	final int leapLength;
	
	public MoveToken(final int leapLength) {
		this.leapLength = leapLength;
	}

	@Override
	public void apply(final Board board) {
		PathCoordinate curC = board.getTokenPosition();
		PathCoordinate newC = new PathCoordinate(curC.getI() - leapLength);
		board.setPieceAt(newC, null);
	}

	@Override
	public void cancel(final Board board) {
		PathCoordinate curC = board.getTokenPosition();
		PathCoordinate newC = new PathCoordinate(curC.getI() + leapLength);
		board.setPieceAt(newC, null);
	}
	
	@Override
	public boolean isLegal(Game game) {
		return leapLength > 0 && leapLength <= game.getMaxLeap();
	}

}
