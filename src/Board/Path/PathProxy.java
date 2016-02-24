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

import Core.Piece;
import Utils.Consumer;

/**
 * 
 * @author Fabian Pijcke
 *
 * @param <P>
 * @param <C>
 * @param <D>
 */
public class PathProxy<P extends Piece, C extends PathCoordinate, D extends Path<P, C>> implements IPath<P, C> {

	private final D pieces;

	/**
	 * Constructs a read-only version of a Path board.
	 * @param pieces
	 */
	public PathProxy(D pieces) {
		this.pieces = pieces;
	}
	
    @Override
    public int getLength() {
        return pieces.getLength();
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