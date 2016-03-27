package Games.Nim.Players;

import Core.NameAvatar;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;
import Games.Nim.Player;

import java.util.List;

/**
 * This AI always plays the best hit
 */
public class WinnerAI extends Player {

    /**
     * Creates a player the standard way.
     *
     * @param avatar
     */
    public WinnerAI(NameAvatar avatar) {
        super(avatar);
    }

    @Override
    public Move pickMove() {
        int maxLeap = getMaxLeap();
        int position = getBoard().getTokenPosition().getI();
        if(position%(maxLeap+1) == 0)
            return new MoveToken(1);
        return new MoveToken(position % (maxLeap + 1));
    }

    @Override
    public void informEnd(List<NameAvatar> winners) {

    }
}
