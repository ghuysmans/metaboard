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

import Board.IBoard;
import Core.Game;
import Core.IMove;
import java.util.Collections;
import java.util.List;

/**
 * @author Fabian Pijcke
 * @param <B>
 * @param <M>
 * @param <DM>
 */
public interface IPickingGame<B extends IBoard, M extends IMove<B>, DM extends IPickingDecisionMaker<? super B, M, ?>> extends Game<B, M, DM> {
    
    public List<M> getPossibleMoves();
    public List<M> getMoves();
    
    default void step() {
        if (isGameEnded()) {
            throw new IllegalStateException();
        }
        getCurrentPlayer().informPastMoves(Collections.unmodifiableList(getMoves()));
        getCurrentPlayer().informMoves(Collections.unmodifiableList(getPossibleMoves()));
        M m = getCurrentPlayer().pickMove();
        applyMove(m);
    }
    
}
