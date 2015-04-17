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

package Games.Chess.Moves;

import Games.Chess.Coordinate;
import Games.Chess.Pieces.Pawn;

/**
 * @author Fabian Pijcke
 */
public class PawnLeap extends BasicMove {

    public PawnLeap(Coordinate start, Pawn movingPiece) {
        super(start, new Coordinate(start.getX(), start.getY() + 2 * movingPiece.getDirection()), movingPiece, null);
    }
    
    @Override
    public Pawn getMovingPiece() {
        return (Pawn) super.getMovingPiece();
    }

}
