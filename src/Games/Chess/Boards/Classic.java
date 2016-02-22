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

package Games.Chess.Boards;

import Core.Avatar;
import Games.Chess.Board;
import Games.Chess.Coordinate;
import Games.Chess.Pieces.Bishop;
import Games.Chess.Pieces.King;
import Games.Chess.Pieces.Knight;
import Games.Chess.Pieces.Pawn;
import Games.Chess.Pieces.Queen;
import Games.Chess.Pieces.Rook;

/**
 * @author Fabian Pijcke
 */
public class Classic extends Board {
        
    public Classic(Avatar white, Avatar black) {
        super();
        
        Avatar[] avatars = new Avatar[2];
        avatars[0] = white;
        avatars[1] = black;
        
        for (int i = 0; i < 2; ++i) {
            int row = i * 7;
            Avatar player = avatars[i];

            setPieceAt(new Coordinate(0, row), new Rook(player));
            setPieceAt(new Coordinate(1, row), new Knight(player));
            setPieceAt(new Coordinate(2, row), new Bishop(player));
            setPieceAt(new Coordinate(3, row), new Queen(player));
            setPieceAt(new Coordinate(4, row), new King(player));
            setPieceAt(new Coordinate(5, row), new Bishop(player));
            setPieceAt(new Coordinate(6, row), new Knight(player));
            setPieceAt(new Coordinate(7, row), new Rook(player));
            
            int row2 = row == 0 ? 1 : 6;
            for (int j = 0; j < 8; ++j) {
                setPieceAt(new Coordinate(j, row2), new Pawn(player, new Coordinate(j, row2)));
            }
        }
    }
}
