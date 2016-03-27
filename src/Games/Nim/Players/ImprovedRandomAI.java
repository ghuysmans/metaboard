package Games.Nim.Players;

import Core.NameAvatar;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;

/**
 * Created by florentdelgrange on 27/03/16.
 */
public class ImprovedRandomAI extends RandomAI{
    /**
     * Standard Player constructor.
     *
     * @param avatar
     */
    public ImprovedRandomAI(NameAvatar avatar) {
        super(avatar);
    }

    @Override
    public Move pickMove() {
        int maxLeap = getMaxLeap();
        int position = getBoard().getTokenPosition().getI();
        if(position%(maxLeap+1) == position)
            return new MoveToken(position);
        return super.pickMove();
    }
}
