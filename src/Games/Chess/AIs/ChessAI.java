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
package Games.Chess.AIs;

import java.util.List;

import Core.Avatar;
import Games.Chess.Game;
import Games.Chess.IBoard;
import Games.Chess.Move;
import Games.Chess.Piece;
import Move.Picking.IPickingDecisionMaker;
import Move.Picking.RandomAI;

/**
 * @author Fabian Pijcke
 *
 * In order to implement an AI for the chess game, one must override this class.
 * The main component to implement is the pickMove() method.
 */
public abstract class ChessAI implements IPickingDecisionMaker<IBoard, Move, Avatar> {

    private final Avatar avatar;
    private IBoard board;
    private List<Move> possibleMoves, pastMoves;

    /**
     * Your constructor can instantiate internal state, but must call
     * super(avatar) for the game to be playable.
     *
     * @param avatar
     */
    public ChessAI(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     * @return your avatar name, useful to know which pieces on the board belong
     * to you.
     */
    @Override
    public final Avatar getAvatar() {
        return avatar;
    }

    /**
     * Facility method that returns your opponent's avatar name.
     *
     * @return the avatar name of your opponent.
     */
    public final Avatar getOpponent() {
        for (Piece p : getBoard().getPieces()) {
            if (p.getAvatar() != getAvatar()) {
                return p.getAvatar();
            }
        }
        return null;
    }

    /**
     * Whenever its your turn to play, this method will be called with the
     * actual state of the board. You have no write access onto this board. This
     * is intended as it would allow you to cheat, for example by filling every
     * non-opponent king cell with a queen of yours, ensuring your victory
     * without even playing.
     *
     * Should you override this method, it is not necessary to call
     * super(board), but this would break the getBoard() method.
     *
     * @param board the actual state of the game.
     */
    @Override
    public void informBoard(IBoard board) {
        this.board = board;
    }

    /**
     * When pickMove is called, you can rely on this method to retrieve the
     * actual state of the board.
     *
     * Note that if your AI takes part into several games, this method will
     * return the board state of the last game that asked you to play. That is,
     * if you use your AI in another game -- e.g., a simulation --, getBoard()
     * will return the board state of the simulation, not the game you're
     * supposed to play into.
     *
     * @return the state of the board of the last game your AI was asked to play
     * into.
     */
    public IBoard getBoard() {
        return board;
    }

    /**
     * Whenever its your turn to play, this method will be called with the list
     * of moves your AI is eligible to play.
     *
     * Should you override this method, it is not mandatory to call
     * super(moves), but this would break the getPossibleMoves() method.
     *
     * @param moves the list of authorized moves.
     */
    @Override
    public final void informMoves(List<Move> moves) {
        this.possibleMoves = moves;
    }

    /**
     * When pickMove is called, you can rely on this method to retrieve the list
     * of moves that you are allowed to play.
     *
     * Note that if your AI takes part into several games, this method will
     * return the list of moves authorized in the last game that it was asked to
     * play into. That is, if you use your AI in another game -- e.g., a
     * simulation --, getPossibleMoves() will return the moves allowed in THAT
     * game, and not in the game that you must provide with a chosen move.
     *
     * @return the list of allowed moves in the last game your AI was asked to
     * play into.
     */
    public final List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    /**
     * When the game ends, this method is called with the list of players who
     * have won.
     *
     * @param winners
     */
    @Override
    public void informEnd(List<Avatar> winners) {
    }

    /**
     * This method is used in order to create a game copy, but you can take
     * profit of it if you feel the need.
     *
     * If you don't plan on using getPastMoves() nor getGameCopy(), you may
     * overwrite this method and not call super(pastMoves).
     *
     * @param pastMoves the list of moves already played in the game.
     */
    @Override
    public void informPastMoves(List<Move> pastMoves) {
        this.pastMoves = pastMoves;
    }

    /**
     * @return the list of moves already played in the last game the AI was
     * asked to play into.
     */
    public List<Move> getPastMoves() {
        return pastMoves;
    }

    /**
     * @return A game copy of the last game your AI was asked to play into. The
     * two AIs used are your AI (this is dangerous, maybe you don't want this,
     * then you have to overwrite this method, or implement another one, this is
     * a facility anyway), and a random AI.
     */
    public Game getGameCopy() {
        IPickingDecisionMaker white, black;
        if (getPastMoves().size() % 2 == 0) {
            white = this;
            black = new RandomAI(getOpponent());
        } else {
            white = new RandomAI(getOpponent());
            black = this;
        }
        return new Game(white, black, getBoard(), getPastMoves());
    }
}
