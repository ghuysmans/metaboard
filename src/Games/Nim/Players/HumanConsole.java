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

package Games.Nim.Players;

import java.util.List;

import Core.NameAvatar;
import Games.Nim.Player;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;
import Games.Nim.Moves.Resign;

public class HumanConsole extends Player {
	
	public HumanConsole(NameAvatar avatar) {
		super(avatar);
	}

	@Override
	public Move pickMove() {
		System.out.println("Move token of how many places (or type resign to resign)? ");
		String s = System.console().readLine();
		if (s.equals("resign")) {
			return new Resign();
		}
		return new MoveToken(Integer.parseInt(s));
	}

	@Override
	public void informEnd(List<NameAvatar> winners) {
		if (winners.get(0) == getAvatar()) {
			System.out.println("Won!");
		}
		else {
			System.out.println("Lost :-(");
		}
	}

}
