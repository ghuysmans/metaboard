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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Utils.Consumer;

/**
 * @author Fabian Pijcke
 * @param <P> 
 * @param <C> 
 */
public class InversibleMap2D<P extends Piece, C extends GridCoordinate> extends Map2D<P, C> {
    private final HashMap<P, C> inverse;
    
    /**
     * Constructs a grid board.
     * 
     * @param width
     * @param height
     */
    public InversibleMap2D(int width, int height) {
        super(width, height);
        this.inverse = new HashMap<>();
    }
    
    /**
     * @param e
     * @return the coordinates of the given piece, using a hashmap.
     */
    public C getCoordinate(P e) {
        return inverse.get(e);
    }
    
    @Override
    public void setPieceAt(C c, P e) {
        inverse.remove(getPieceAt(c));
        super.setPieceAt(c, e);
        if (e != null) {
            inverse.put(e, c);
        }
    }
    
    @Override
    public List<P> getPieces() {
        return new ArrayList<>(inverse.keySet());
    }

    @Override
    public void forEach(Consumer<P> c) {
        getPieces().forEach(c);
    }

}
