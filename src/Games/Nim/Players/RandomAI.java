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
