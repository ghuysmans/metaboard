package Games.Nim;

import Board.Path.Path;
import Board.Path.PathCoordinate;

public class Board extends Path<Token, PathCoordinate>  implements IBoard {
	
	private Token token;
	private PathCoordinate tokenPosition;

	public Board(int length) {
		super(length);
		token = new Token();
		tokenPosition = new PathCoordinate(length - 1);
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
