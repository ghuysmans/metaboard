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

package Board;

import Core.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The board is the main component of a board game. It must provide access to
 * the pieces involved in the game and give informations about the playable
 * area. It should not be used to alter the state of the game, it is just a view
 * over DataBoard, which is not given to DecisionMakers.
 * 
 * BoardProxy is designed to use the Proxy pattern over Board.
 *
 * @author Fabian Pijcke
 * @param <P>
 * @param <C>
 */
public interface IBoardProxy<P extends Piece, C extends ICoordinate> {

    P getPieceAt(C coord);

    void forEach(Consumer<P> c);
    
    default List<P> getPieces(Predicate<P> predicate) {
        List<P> pieces = getPieces();
        pieces.removeIf(predicate.negate());
        return pieces;
    }

    default List<P> getPieces() {
        List<P> pieces = new ArrayList();
        forEach((P piece) -> pieces.add(piece));
        return pieces;
    }
    
}
