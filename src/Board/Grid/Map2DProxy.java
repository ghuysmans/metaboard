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
import Utils.Consumer;

/**
 * When asking to the user/AI what action to take, we want to give him read-only information.
 * This wrapper allows the game to create a read-only version of the board using the proxy design pattern.
 * Instances of this class are meant to be passed to the user/AI instead of Map2D instances.
 * 
 * @author Fabian Pijcke
 * @param <P> 
 * @param <C> 
 * @param <D> 
 */
public class Map2DProxy<P extends Piece, C extends GridCoordinate, D extends Map2D<P, C>> implements IMap2D<P, C> {

    private final D pieces;
    
    /**
     * Constructs a read-only version of a Map2D board. 
     * @param pieces
     */
    public Map2DProxy(D pieces) { // Game should keep control over the pieces variable, as GridBoard will not alter it.
        this.pieces = pieces;
    }
    
    @Override
    public int getWidth() {
        return pieces.getWidth();
    }
    
    @Override
    public int getHeight() {
        return pieces.getHeight();
    }
    
    @Override
    public P getPieceAt(C c) {
        return pieces.getPieceAt(c);
    }
    
    @Override
    public void forEach(Consumer<P> c) {
        pieces.forEach(c);
    }
    
    @Override
    public boolean has(C c) {
        return pieces.has(c);
    }
}
