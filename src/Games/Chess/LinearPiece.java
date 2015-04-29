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

package Games.Chess;

import Games.Chess.Moves.BasicMove;
import Board.Grid.GridCoordinate;
import Core.NameAvatar;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabian Pijcke
 */
public abstract class LinearPiece extends Piece {
    
    public LinearPiece(NameAvatar player) {
        super(player);
    }
    
    @Override
    public List<BasicMove> getAvailableMoves(IBoard board) {
        Coordinate position = board.getCoordinate(this);
        int x = position.getX();
        int y = position.getY();

        ArrayList<BasicMove> moves = new ArrayList();

        for (GridCoordinate direction : getDirections()) {
            int dx = direction.getX();
            int dy = direction.getY();
            
            try {
                for (int i = 1; i < 8; ++i) {
                    Coordinate c = new Coordinate(x + i * dx, y + i * dy);
                    Piece p = board.getPieceAt(c);

                    if (p == null) {
                        moves.add(new BasicMove(position, c, this, null));
                    }
                    else if (p.getAvatar() == getAvatar()) {
                        break;
                    }
                    else {
                        moves.add(new BasicMove(position, c, this, p));
                        break;
                    }
                }
            } catch (Coordinate.BadCoordinateException e) {}
        }
        
        return moves;
    }
    
}
