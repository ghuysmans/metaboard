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
