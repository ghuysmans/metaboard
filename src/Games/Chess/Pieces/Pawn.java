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

package Games.Chess.Pieces;

import Board.Grid.GridCoordinate;
import Core.NameAvatar;
import Games.Chess.Moves.Promotion;
import Games.Chess.Piece;
import Games.Chess.Coordinate;
import Games.Chess.IBoard;
import Games.Chess.Moves.BasicMove;
import Games.Chess.Moves.PawnLeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fabian Pijcke
 */
public class Pawn extends Piece {
    
    private final int dir;
    
    public Pawn(NameAvatar player, GridCoordinate initial) {
        super(player);
        dir = initial.getY() == 1 ? 1 : -1;
    }
    
    public int getDirection() {
        return dir;
    }
    
    @Override
    public List<GridCoordinate> getDirections() {
        return Arrays.asList(new GridCoordinate(0, dir));
    }
    
    @Override
    public List<BasicMove> getAvailableMoves(IBoard board) {
        final Coordinate position = board.getCoordinate(this);
        final int x = position.getX();
        final int y = position.getY();
        
        final ArrayList<BasicMove> moves = new ArrayList();
        
        // Ahead
        final Coordinate ahead = new Coordinate(x, y + dir);
        if (board.getPieceAt(ahead) == null) {
            moves.add(new BasicMove(position, ahead, this, null));
        }
        
        // Ahead, 2 places
        if (dir == 1 && y == 1 || dir == -1 && y == 6) {
            final Coordinate ahead2 = new Coordinate(x, y + 2 * dir);
            if (board.getPieceAt(ahead) == null && board.getPieceAt(ahead2) == null) {
                moves.add(new PawnLeap(position, this));
            }
        }
        
        // Take left/right
        for (int x2 : Arrays.asList(x + 1, x - 1)) {
            try {
                final Coordinate c2 = new Coordinate(x2, y + getDirection());
                final Piece p2 = board.getPieceAt(c2);
                if (p2 != null && p2.getAvatar() != getAvatar()) {
                    moves.add(new BasicMove(position, c2, this, p2));
                }
            } catch (Coordinate.BadCoordinateException e) {}
        }
        
        // Promotion
        if (y == 1 && dir == -1 || y == 6 && dir == 1) {
            for (int i = moves.size() - 1; i >= 0; --i) {
                BasicMove m = moves.remove(i);
                moves.add(new Promotion(m, new Bishop(getAvatar())));
                moves.add(new Promotion(m, new Rook(getAvatar())));
                moves.add(new Promotion(m, new Knight(getAvatar())));
                moves.add(new Promotion(m, new Queen(getAvatar())));
            }
        }
        
        return moves;
    }
    
    @Override
    public char getLetter() {
        return 'P';
    }

}
