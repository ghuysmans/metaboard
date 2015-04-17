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

import Board.Grid.GridCoordinate;

/**
 * @author Fabian Pijcke
 */
public class Coordinate extends GridCoordinate {
    
    public class BadCoordinateException extends RuntimeException {
        
        private final int x, y;
        
        public BadCoordinateException(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String getMessage() {
            return "" + x + ", " + y;
        }
    
    }
    
    public static final String columns = "abcdefgh";
    public static final String rows = "12345678";
    
    public Coordinate(int x, int y) {
        super(x, y);
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            throw new BadCoordinateException(x, y);
        }
    }
    
    public String getNotation() {
        return "" + columns.charAt(getX()) + rows.charAt(getY());
    }
    
    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }
}
