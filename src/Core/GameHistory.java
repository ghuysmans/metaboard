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

package Core;

import Board.IBoard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Fabian Pijcke
 * @param <B>
 * @param <M>
 * @param <DM>
 */
public abstract class GameHistory<B extends IBoard, M extends IMove<B>, DM extends IDecisionMaker<? super B, M, ?>>
        implements Game<B, M, DM> {
    
    private final List<DM> players;
    private final List<M> moves;
    
    public GameHistory(List<DM> players) {
        this.players = players;
        this.moves = new ArrayList();
    }
    
    @Override
    public List<DM> getPlayers() {
        return players;
    }
    
    @Override
    public void applyMove(M m) {
        Game.super.applyMove(m);
        addMove(m);
    }
    
    public int getTurn() {
        return moves.size();
    }
    
    @Override
    public DM getCurrentPlayer() {
        return players.get(getTurn() % players.size());
    }
    
    public void cancelLastMove() {
        if (!moves.isEmpty()) {
            M lastMove = moves.remove(moves.size() - 1);
            lastMove.cancel(getBoard());
        }
    }
    
    public M getMove(int i) {
        int index = i < 0 ? moves.size() + i : i;
        return (index < 0 || index >= getTurn()) ? null : moves.get(index);
    }
    
    public void addMove(M m) {
        moves.add(m);
    }
    
    public List<M> getMoves() {
        return Collections.unmodifiableList(moves);
    }
}
