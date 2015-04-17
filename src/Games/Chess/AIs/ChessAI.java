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
import Games.Chess.IBoard;
import Games.Chess.Move;
import Move.Picking.IPickingDecisionMaker;
import java.util.List;

/**
 * @author Fabian Pijcke
 */
public abstract class ChessAI implements IPickingDecisionMaker<IBoard, Move, NameAvatar> {
    private final NameAvatar avatar;
    private IBoard board;
    private List<Move> moves;
    
    public ChessAI(NameAvatar avatar) {
        this.avatar = avatar;
    }
    
    @Override
    public final NameAvatar getAvatar() {
        return avatar;
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
        this.moves = moves;
    }
    
    public final List<Move> getPossibleMoves() {
        return moves;
    }

    @Override
    public final void informEnd(List<NameAvatar> winners) {
    }
}
