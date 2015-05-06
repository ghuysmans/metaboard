/*
 Copyright 2015 Fabian Pijcke

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

import Core.NameAvatar;
import Games.Chess.Game;
import Games.Chess.IBoard;
import Games.Chess.Move;
import Games.Chess.Piece;
import Move.Picking.IPickingDecisionMaker;
import Move.Picking.RandomAI;
import java.util.List;

/**
 * @author Fabian Pijcke
 */
public abstract class ChessAI implements IPickingDecisionMaker<IBoard, Move, NameAvatar> {
    private final NameAvatar avatar;
    private IBoard board;
    private List<Move> possibleMoves, pastMoves;
    
    public ChessAI(NameAvatar avatar) {
        this.avatar = avatar;
    }
    
    @Override
    public final NameAvatar getAvatar() {
        return avatar;
    }
    
    public final NameAvatar getOpponent() {
        for (Piece p : getBoard().getPieces()) {
            if (p.getAvatar() != getAvatar()) {
                return p.getAvatar();
            }
        }
        return null;
    }
    
    @Override
    public void informBoard(IBoard board) {
        this.board = board;
    }
    
    public IBoard getBoard() {
        return board;
    }
    
    @Override
    public final void informMoves(List<Move> moves) {
        this.possibleMoves = moves;
    }
    
    public final List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public void informEnd(List<NameAvatar> winners) {
    }
    
    @Override
    public void informPastMoves(List<Move> pastMoves) {
        this.pastMoves = pastMoves;
    }
    
    public List<Move> getPastMoves() {
        return pastMoves;
    }
    
    public Game getGameCopy() {
        IPickingDecisionMaker white, black;
        if (getPastMoves().size() % 2 == 0) {
            white = this;
            black = new RandomAI(getOpponent());
        }
        else {
            white = new RandomAI(getOpponent());
            black = this;
        }
        return new Game(white, black, getBoard(), getPastMoves());
    }
}
