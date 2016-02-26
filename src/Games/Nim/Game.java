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

package Games.Nim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Core.NameAvatar;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.Resign;
import Move.Movement.IllegalMovementException;

public class Game implements Core.Game<Board, Move, Player> {
	
	private Board board;
	private ArrayList<Player> players;
	private int maxLeap;
	
	public Game(ArrayList<Player> players, int maxLeap, int initialPosition) {
		this.players = players;
		this.maxLeap = maxLeap;
		this.board = new Board(initialPosition + 1);
		
		for (Player p : players) {
			p.informBoard(new BoardProxy(this.board));
			p.informMaxLeap(maxLeap);
		}
	}
	
	public int getMaxLeap() {
		return maxLeap;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Player getCurrentPlayer() {
		return getPlayers().get(0);
	}

	@Override
	public boolean isGameEnded() {
		return board.getTokenPosition().getI() == 0 || getPlayers().size() == 1;
	}

	@Override
	public List<Player> getWinners() {
		int winner = (board.getTokenPosition().getI() == 0) ? players.size() - 1 : 0;
		return Collections.singletonList(getPlayers().get(winner));
	}
	
	@Override
	public void applyMove(Move move) {
		if (!move.isLegal(this)) {
			throw new IllegalMovementException();
		}
		move.apply(getBoard());
		if (move instanceof Resign) {
			players.remove(0);
		}
		else {
			players.add(players.remove(0));
		}
		
		if (isGameEnded()) {
			List<NameAvatar> avatars = new ArrayList<>();
			for (Player p : getWinners()) {
				avatars.add(p.getAvatar());
			}
			players.forEach((player) -> player.informEnd(avatars));
		}
	}
	
	public void printStatus() {
		if (isGameEnded()) {
			System.out.println("Winner : " + getWinners().get(0).getAvatar().getName());
		}
		else {
			System.out.println("Token position : " + board.getTokenPosition());
			System.out.println("Player in hand : " + getCurrentPlayer().getAvatar().getName());
			System.out.println();
		}
	}
	
	public void step() {
		if (isGameEnded()) {
			throw new IllegalStateException();
		}
		try {
			applyMove(getCurrentPlayer().pickMove());
		}
		catch (IllegalMovementException e) {
			System.out.println("Illegal move, one more try granted.");
			applyMove(getCurrentPlayer().pickMove());
		}
	}
}
