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

package Move.Picking;

import Board.IBoardProxy;
import Core.Avatar;
import Core.IMove;
import java.util.List;
import java.util.Random;

/**
 * @author Fabian Pijcke
 * @param <B>
 * @param <M>
 * @param <A>
 */
public class RandomAI<B extends IBoardProxy, M extends IMove<? extends B>, A extends Avatar> implements IPickingDecisionMaker<B, M, A> {
    
    private final A avatar;
    private List<M> moves;
    private B board;
    private final Random randomizer;
    
    public RandomAI(A avatar) {
        this.avatar = avatar;
        this.randomizer = new Random();
    }
    
    @Override
    public A getAvatar() {
        return avatar;
    }

    @Override
    public void informMoves(List<M> moves) {
        this.moves = moves;
    }

    @Override
    public void informBoard(B board) {
        this.board = board;
    }

    @Override
    public M pickMove() {
        M m = moves.get(randomizer.nextInt(moves.size()));
        if (m instanceof IResign) {
            return pickMove();
        }
        else {
            return m;
        }
    }

    @Override
    public void informEnd(List<A> winners) {
    }
    
}
