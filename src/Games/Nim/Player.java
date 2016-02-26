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

package Games.Nim;

import Core.IDecisionMaker;
import Core.NameAvatar;
import Games.Nim.Moves.Move;

public abstract class Player implements IDecisionMaker<IBoard, Move, NameAvatar> {
	
	private final NameAvatar avatar;
	
	private IBoard board;
	private int maxLeap;
	
	public Player(NameAvatar avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public NameAvatar getAvatar() {
		return avatar;
	}
	
	public IBoard getBoard() {
		return board;
	}
	
	public int getMaxLeap() {
		return maxLeap;
	}
	
	@Override
	public void informBoard(IBoard board) {
		this.board = board;
	}
	
	/**
	 * Will be called at the beginning of a game.
	 * 
	 * @param maxLeap
	 */
	public void informMaxLeap(int maxLeap) {
		this.maxLeap = maxLeap;
	}
	
}
