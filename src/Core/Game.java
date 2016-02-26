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

package Core;

import Board.IBoard;
import java.util.List;

/**
 * This class defines the rules of the game. The DecisionMaker need not have
 * access to instances of this class, as it would give them access to the other
 * DecisionMaker and would allow them to ask for next moves of the opponents.
 * Note that this is not an issue if only the game can create new boards and the
 * DecisionMaker opponents are sufficiently specific.
 *
 * @author Fabian Pijcke
 * @param <M>
 * @param <B>
 * @param <DM>
 */
public interface Game<B extends IBoard<?, ?>, M extends IMove<B>, DM extends IDecisionMaker<? super B, M, ?>> {

	/**
	 * @return the game board.
	 */
	B getBoard();

	/**
	 * The players list may vary as the game goes on.
	 * 
	 * The game implementation decides whether the players having lost still
	 * appear in this list or not.
	 * 
	 * Note that the players will not have direct access to this method. Hence
	 * it is not necessary to protect it with, for example,
	 * Collections.unmodifiableList.
	 * 
	 * @return the players list.
	 */
	List<DM> getPlayers();

	/**
	 * @return the player which has to make the next move.
	 */
	DM getCurrentPlayer();

	/**
	 * Applies a move.
	 * 
	 * @param m
	 */
	default void applyMove(M m) {
		m.apply(getBoard());
	}

	/**
	 * @return true if the winners list is known already.
	 */
	boolean isGameEnded();

	/**
	 * @return the winners list.
	 */
	List<DM> getWinners();

}
