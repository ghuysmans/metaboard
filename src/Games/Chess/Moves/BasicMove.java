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

import Board.Grid.GridMove;
import Games.Chess.Board;
import Games.Chess.Coordinate;
import Games.Chess.Move;
import Games.Chess.Piece;
import Move.Movement.MovementMove;

/**
 * @author Fabian Pijcke
 */
public class BasicMove
        extends MovementMove<Piece, Coordinate, Board>
        implements Move, GridMove<Board> {
    
    private final Piece captured;
    private final Piece movingPiece;
    
    public BasicMove(Coordinate start, Coordinate destination, Piece movingPiece, Piece captured) {
        super(start, destination);
        this.movingPiece = movingPiece;
        this.captured = captured;
    }
    
    @Override
    public boolean isCapture() {
        return captured != null;
    }
    
    public Piece getMovingPiece() {
        return movingPiece;
    }

    @Override
    public Piece getCaptured() {
        return captured;
    }
    
    @Override
    public String getNotation() {
        return getStart().getNotation() + (isCapture() ? "x" : " ") + getDestination().getNotation();
    }
    
    @Override
    public String toString() {
        char letter = movingPiece != null ? movingPiece.getLetter() : ' ';
        return "" + letter + " " + getStart() + " -> " + getDestination();
    }
}
