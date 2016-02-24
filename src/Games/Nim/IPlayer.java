package Games.Nim;

import Core.IDecisionMaker;
import Core.NameAvatar;
import Games.Nim.Moves.Move;

public interface IPlayer extends IDecisionMaker<Board, Move, NameAvatar> {
	// Wrapper (yes, this is an anti-pattern ...)
	// As the space of possibilites is a priori infinite, this should not be a
	// problem (one can not come with a generalized approach to make some AI /
	// GUI for this game).
}
