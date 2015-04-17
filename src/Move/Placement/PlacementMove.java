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

package Move.Placement;

import Board.ICoordinate;
import Board.IBoard;
import Core.IMove;
import Core.Piece;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fabian Pijcke
 * @param <C>
 * @param <P>
 * @param <D>
 */
public class PlacementMove<P extends Piece, C extends ICoordinate, D extends IBoard<P, C>> implements IMove<D> {
    private final List<Step<C, P>> steps;
    
    public PlacementMove(ICoordinate c, Piece p) {
        Step<C, P> step = new Step(c, p);
        steps = Arrays.asList(step);
    }
    
    public PlacementMove(List<Step<C, P>> steps) {
        this.steps = steps;
    }

    @Override
    public void apply(D board) {
        for (Step<C, P> step : steps) {
            board.setPieceAt(step.getCoordinate(), step.getPiece());
        }
    }

    @Override
    public void cancel(D board) {
        for (Step<C, P> step : steps) {
            board.setPieceAt(step.getCoordinate(), null);
        }
    }
}
