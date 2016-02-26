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

package Games.Chess.Moves;

import Games.Chess.Board;
import Games.Chess.Coordinate;
import Games.Chess.Pieces.Pawn;

/**
 * @author Fabian Pijcke
 */
public final class EnPassant extends BasicMove {
    
    private final Coordinate step;
    
    public EnPassant(Coordinate ctaker, Coordinate ctaken, Pawn taker, Pawn taken) {
        super(ctaker, new Coordinate(ctaken.getX(), ctaker.getY() + taker.getDirection()), taker, taken);
        this.step = ctaken;
    }
    
    @Override
    public void apply(Board board) {
        super.apply(board);
        board.setPieceAt(step, null);
    }
    
    @Override
    public void cancel(Board board) {
        board.setPieceAt(step, getCaptured());
        super.cancel(board);
    }
    
    @Override
    public String getNotation() {
        return super.getNotation() + "e.p.";
    }
    
    @Override
    public boolean isCapture() {
        return true;
    }
    
}
