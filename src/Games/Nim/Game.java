package Games.Nim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Games.Nim.Moves.Move;
import Move.Movement.IllegalMovementException;

public class Game implements Core.Game<Board, Move, IPlayer> {
	
	private Board board;
	private ArrayList<IPlayer> players;
	private int maxLeap;
	
	public Game(ArrayList<IPlayer> players, int maxLeap, int initialPosition) {
		this.players = players;
		this.maxLeap = maxLeap;
		this.board = new Board(initialPosition + 1);
	}
	
	public int getMaxLeap() {
		return maxLeap;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<IPlayer> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return getPlayers().get(0);
	}

	@Override
	public boolean isGameEnded() {
		return board.getTokenPosition().getI() == 0 || getPlayers().size() == 1;
	}

	@Override
	public List<IPlayer> getWinners() {
		int winner = (board.getTokenPosition().getI() == 0) ? -1 : 0;
		return Collections.singletonList(getPlayers().get(winner));
	}
	
	@Override
	public void applyMove(Move move) {
		if (!move.isLegal(this)) {
			throw new IllegalMovementException();
		}
		move.apply(getBoard());
	}
}
