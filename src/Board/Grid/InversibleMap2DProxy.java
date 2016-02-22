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

package Board.Grid;

import Core.Piece;
import java.util.List;

/**
 * Proxy for the InversibleMap2D {@link Map2DProxy}
 * 
 * @author Fabian Pijcke
 * @param <P>
 * @param <C>
 * @param <D>
 */
public class InversibleMap2DProxy<P extends Piece, C extends GridCoordinate, D extends InversibleMap2D<P, C>> extends Map2DProxy<P, C, D> implements IInversibleMap2D<P, C> {
    
    private final D pieces;

    /**
     * Creates a read-only version of a Map2DProxy.
     * @param pieces
     */
    public InversibleMap2DProxy(D pieces) {
        super(pieces);
        this.pieces = pieces;
    }
    
    @Override
    public C getCoordinate(P piece) {
        return pieces.getCoordinate(piece);
    }

    @Override
    public List<P> getPieces() {
        return pieces.getPieces();
    }
}
