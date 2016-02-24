package Games.Nim;

import Core.IDecisionMaker;
import Core.NameAvatar;
import Games.Nim.Moves.Move;

public interface IPlayer extends IDecisionMaker<IBoard, Move, NameAvatar> {
	
	/**
	 * Will be called at the beginning of a game.
	 * 
	 * @param maxLeap
	 */
	void informMaxLeap(int maxLeap);
	
}
