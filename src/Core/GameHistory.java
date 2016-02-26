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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Board.IBoard;

/**
 * A game in which the list of played moves matters. For example a Chess game
 * ends if 50 moves are played without check nor captured piece.
 * 
 * @author Fabian Pijcke
 * @param <B>
 * @param <M>
 * @param <DM>
 */
public abstract class GameHistory<B extends IBoard<?, ?>, M extends IMove<B>, DM extends IDecisionMaker<? super B, M, ?>>
		implements Game<B, M, DM> {

	private final List<DM> players;
	private final List<M> moves;

	/**
	 * Constructs a game with an empty history of played moves.
	 * 
	 * @param players
	 */
	public GameHistory(List<DM> players) {
		this.players = players;
		this.moves = new ArrayList<>();
	}

	@Override
	public List<DM> getPlayers() {
		return players; // Remember that the Game instance will not be passed to
						// the players (hence they can not alter this list, and
						// there is no need to protect it).
	}

	@Override
	public void applyMove(M m) {
		Game.super.applyMove(m);
		addMove(m);
	}

	/**
	 * @return the number of moves already played.
	 */
	public int getTurn() {
		return moves.size();
	}

	@Override
	public DM getCurrentPlayer() {
		return players.get(getTurn() % players.size());
	}

	/**
	 * Reverses the last move played.
	 */
	public void cancelLastMove() {
		if (!moves.isEmpty()) {
			M lastMove = moves.remove(moves.size() - 1);
			lastMove.cancel(getBoard());
		}
	}

	/**
	 * This method returns the move played at a given time (0 is the first move,
	 * 1 the second one, etc.). Negative numbers can be used to get moves played
	 * recently (-1 is the last move, -2 the move before, etc.).
	 * 
	 * @param i
	 *            a positive or negative number.
	 * @return the ith move played.
	 */
	public M getMove(int i) {
		int index = i < 0 ? moves.size() + i : i;
		return (index < 0 || index >= getTurn()) ? null : moves.get(index);
	}

	/**
	 * Adds a move to the list of played moves. By default this method is
	 * already called by applyMove.
	 * 
	 * @param m
	 */
	public void addMove(M m) {
		moves.add(m);
	}

	/**
	 * @return the complete read-only list of played moves.
	 */
	public List<M> getMoves() {
		return Collections.unmodifiableList(moves);
	}

}
