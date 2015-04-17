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

import Board.IBoard;
import Core.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Fabian Pijcke
 * @param <P> 
 * @param <C> 
 */
public class Map2D<P extends Piece, C extends GridCoordinate> implements IBoard<P, C>, IMap2D<P, C> {
    
    private final Object[] elements;
    private final int width, height;
    
    public Map2D(int width, int height) {
        elements = new Object[width * height];
        this.width = width;
        this.height = height;
    }
    
    @Override
    public P getPieceAt(C c) {
        if (has(c)) {
            return (P) elements[c.getY() * width + c.getX()];
        }
        return null;
    }
    
    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public void setPieceAt(C c, P e) {
        if (has(c)) {
            elements[c.getY() * width + c.getX()] = e;
        }
        else {
            assert false;
        }
    }

    @Override
    public void forEach(Consumer<P> c) {
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i] != null) {
                c.accept((P) elements[i]);
            }
        }
    }

    @Override
    public boolean has(C c) {
        return c.getX() >= 0 && c.getX() < getWidth() && c.getY() >= 0 && c.getY() < getHeight();
    }
    
    @Override
    public List<P> getPieces(Predicate<P> predicate) {
        List<P> pieces = getPieces();
        pieces.removeIf(predicate.negate());
        return pieces;
    }

    @Override
    public List<P> getPieces() {
        List<P> pieces = new ArrayList();
        forEach((P piece) -> pieces.add(piece));
        return pieces;
    }


}
