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

import java.util.ArrayList;

import Board.IBoard;
import Core.Piece;
import Utils.Consumer;

/**
 * The complete implementation of a 2D map (grid) board. Instances of this class are meant to be passed to the game
 * implementation, not to the users or AIs.
 * 
 * @author Fabian Pijcke
 * @param <P> 
 * @param <C> 
 */
public class Map2D<P extends Piece, C extends GridCoordinate> implements IBoard<P, C>, IMap2D<P, C> {
    
    private final ArrayList<P> elements;
    private final int width, height;
    
    /**
     * Constructs an empty Grid map.
     * 
     * @param width
     * @param height
     */
    public Map2D(int width, int height) {
    	elements = new ArrayList<>(width * height);
    	for (int i = width * height; i > 0; --i) {
    		elements.add(null);
    	}
        this.width = width;
        this.height = height;
    }
    
    @Override
    public P getPieceAt(C c) {
        if (has(c)) {
        	return elements.get(c.getY() * width + c.getX());
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
        	elements.set(c.getY() * width + c.getX(), e);
        }
        else {
            assert false;
        }
    }

    @Override
    public void forEach(Consumer<P> c) {
    	elements.forEach(c.filter((v) -> v != null));
    }

    @Override
    public boolean has(C c) {
        return c.getX() >= 0 && c.getX() < getWidth() && c.getY() >= 0 && c.getY() < getHeight();
    }
    
}
