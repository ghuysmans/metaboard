package Games.Nim.Moves;

import Core.IMove;
import Games.Nim.Board;
import Games.Nim.Game;

public abstract class Move implements IMove<Board> {
	Move() {
		// Package-visibility constructor; therefore only package classes can
		// extend it, this avoids creation of moves applying illegal things to
		// the board.
	}
	
	public abstract boolean isLegal(Game game);
}
