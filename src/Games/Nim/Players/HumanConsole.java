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
