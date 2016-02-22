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

package Board.Path;

import Board.ICoordinate;

/**
 * An immutable 1D coordinate (optimized version of Grid when h = 1).
 * 
 * @author Fabian Pijcke
 */
public class PathCoordinate implements ICoordinate {
	private final int i;
	
	/**
	 * Constructs a 1D coordinate.
	 * 
	 * @param i
	 */
	public PathCoordinate(int i) {
		this.i = i;
	}
	
	/**
	 * @return the coordinate.
	 */
	public int getI() {
		return i;
	}
	
	@Override
	public String toString() {
		return "(" + i + ")";
	}
}
