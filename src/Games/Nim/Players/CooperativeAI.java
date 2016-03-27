package Games.Nim.Players;

import Core.NameAvatar;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;

import java.util.List;

/**
 * Created by florentdelgrange on 20/03/16.
 */
public class CooperativeAI extends RandomAI {

    /**
     * Creates a cooperative AI.
     * You must respect the order : This AI must play just before a WinnerAI
     * to allow it to win.
     *
     * @param avatar The name of the AI.
     */
    public CooperativeAI(NameAvatar avatar) {
        super(avatar);
    }

    @Override
    public Move pickMove() {
        int maxLeap = getMaxLeap();
        int position = getBoard().getTokenPosition().getI();
        if (position % (maxLeap + 1) == 0 || (position % (maxLeap + 1) == position))
            return super.pickMove();
        else if (((position - 1) % (maxLeap + 1)) == 0)
            return new MoveToken(2);
        else
            return new MoveToken(1);
    }

    @Override
    public void informEnd(List<NameAvatar> winners) {

    }
}
