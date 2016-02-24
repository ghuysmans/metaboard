package Games.Nim;

import Board.Path.PathCoordinate;
import Board.Path.PathProxy;

public class BoardProxy extends PathProxy<Token, PathCoordinate, Board> implements IBoard {
	
	private Board board;
	
	public BoardProxy(Board board) {
		super(board);
		this.board = board;
	}
	
	@Override
	public PathCoordinate getTokenPosition() {
		return board.getTokenPosition();
	}

}
