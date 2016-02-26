/*
 Copyright 2015-2016 Fabian Pijcke

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

import java.util.ArrayList;

import Board.IBoard;
import Core.Piece;
import Utils.Consumer;

/**
 * The complete implementation of a Path board. Instances of this class are meant to be passed to the game
 * implementation, not to the users or AIs.
 * 
 * @author Fabian Pijcke
 * @param <P> 
 * @param <C> 
 */
public class Path<P extends Piece, C extends PathCoordinate> implements IBoard<P, C>, IPath<P, C> {
	
	private final ArrayList<P> elements;
	private final int length;
	
	/**
	 * Constructs an empty path.
	 * 
	 * @param length
	 */
	public Path(int length) {
		elements = new ArrayList<>(length);
		for (int i = 0; i < length; ++i) {
			elements.add(null);
		}
		
		this.length = length;
	}
	
	@Override
	public P getPieceAt(C c) {
		if (has(c)) {
			return elements.get(c.getI());
		}
		return null;
	}
	
	@Override
	public int getLength() {
		return length;
	}
	
	@Override
	public void setPieceAt(C c, P e) {
		if (has(c)) {
			elements.set(c.getI(), e);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void forEach(Consumer<P> c) {
		elements.forEach(c.filter((v) -> v != null));
	}
	
	@Override
	public boolean has(C c) {
		return c.getI() >= 0 && c.getI() < length;
	}
	
}
