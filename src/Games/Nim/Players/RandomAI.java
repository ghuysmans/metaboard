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
import java.util.Random;

import Core.NameAvatar;
import Games.Nim.Player;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;

public class RandomAI extends Player {
	
	private Random randomizer;
	
	public RandomAI(NameAvatar avatar) {
		super(avatar);
		randomizer = new Random();
	}

	@Override
	public Move pickMove() {
		return new MoveToken(randomizer.nextInt(Math.min(getBoard().getTokenPosition().getI(), getMaxLeap())) + 1);
	}

	@Override
	public void informEnd(List<NameAvatar> winners) {
		// "I don't care, let's just start another game!"
	}

}
