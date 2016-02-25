package Games.Nim;

import Core.IDecisionMaker;
import Core.NameAvatar;
import Games.Nim.Moves.Move;

public abstract class Player implements IDecisionMaker<IBoard, Move, NameAvatar> {
	
	private final NameAvatar avatar;
	
	private IBoard board;
	private int maxLeap;
	
	public Player(NameAvatar avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public NameAvatar getAvatar() {
		return avatar;
	}
	
	public IBoard getBoard() {
		return board;
	}
	
	public int getMaxLeap() {
		return maxLeap;
	}
	
	@Override
	public void informBoard(IBoard board) {
		this.board = board;
	}
	
	/**
	 * Will be called at the beginning of a game.
	 * 
	 * @param maxLeap
	 */
	public void informMaxLeap(int maxLeap) {
		this.maxLeap = maxLeap;
	}
	
}
