package Games.Nim.Moves;

import Games.Nim.Board;
import Games.Nim.Game;

public class Resign extends Move {
	
	@Override
	public void apply(Board board) {
    	// Nothing to do : The board checks whether the last move was Resign, if yes the current player has won.
	}
	
	@Override
	public void cancel(Board board) {
    	// Nothing to do as the last move will be removed from the stack of moves played already.
	}
	
	@Override
	public boolean isLegal(Game game) {
		return true;
	}

}
