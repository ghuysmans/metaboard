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
package Games.Chess.AIs;

import Core.Avatar;
import Games.Chess.Game;
import Games.Chess.Move;
import Move.Picking.IAuxiliaryMove;
import Move.Picking.IPickingDecisionMaker;
import Move.Picking.RandomAI;
import java.util.List;
import java.util.Random;

/**
 *
 * @author shepard
 */
public class MiniMaxAI extends ChessAI {
    
    private Game mmgame;
    private Move chosenMove;
    private final int DEPTH = 2;
    private final Random r = new Random();
    
    public MiniMaxAI(Avatar avatar) {
        super(avatar);
    }
    
    @Override
    public final void informEnd(List<Avatar> winners) {
        if (winners.size() == 2) {
            System.out.println("Égalité");
        }
        else if (winners.contains(getAvatar())) {
            System.out.println("Gagné");
        }
        else {
            System.out.println("Perdu");
        }
    }
    
    @Override
    public Game getGameCopy() {
        IPickingDecisionMaker white, black;
        if (getPastMoves().size() % 2 == 0) {
            white = new RandomAI(getAvatar());
            black = new RandomAI(getOpponent());
        }
        else {
            white = new RandomAI(getOpponent());
            black = new RandomAI(getAvatar());
        }
        Game g = new Game(white, black, getBoard(), getPastMoves());
        return g;
    }

    @Override
    public Move pickMove() {
        mmgame = getGameCopy();
        
        Move best = null;
        int bestValue = Integer.MIN_VALUE;
        int nbBestMoves = 0;
        
        for (Move m : getPossibleMoves()) {
            Move mmmove = null;
            for (Move m2 : mmgame.getPossibleMoves()) {
                if (m.getNotation().equals(m2.getNotation())) {
                    mmmove = m2;
                    break;
                }
            }
            if (m instanceof IAuxiliaryMove) continue;
            mmgame.applyMove(mmmove);
            int value = miniMax(DEPTH - 1, false);
            mmgame.cancelLastMove();
            if (value > bestValue) {
                best = m;
                bestValue = value;
                nbBestMoves = 1;
            }
            else if (value == bestValue) {
                if (r.nextInt(nbBestMoves + 1) == nbBestMoves) {
                    best = m;
                }
                nbBestMoves++;
            }
        }
        
        return best;
    }
    
    public int miniMax(int depth, boolean maximize) {
        if (mmgame.isGameEnded()) {
            if (!mmgame.getWinners().contains(this)) {
                return -1;
            }
            else if (mmgame.getWinners().size() == 2) {
                return Integer.MAX_VALUE - 2;
            }
            else {
                return Integer.MAX_VALUE - 1;
            }
        }
        
        if (maximize) {
            if (depth == 0) {
                return mmgame.getPossibleMoves().size();
            }
            int bestValue = Integer.MIN_VALUE;
            for (Move m : mmgame.getPossibleMoves()) {
                if (m instanceof IAuxiliaryMove) {
                    continue;
                }
                mmgame.applyMove(m);
                int value = miniMax(depth - 1, false);
                mmgame.cancelLastMove();
                if (value > bestValue) {
                    if (depth == DEPTH) {
                        chosenMove = m;
                    }
                    bestValue = value;
                }
            }
            return bestValue + mmgame.getPossibleMoves().size();
        }
        else {
            if (depth == 0) {
                return - mmgame.getPossibleMoves().size();
            }
            int bestValue = Integer.MAX_VALUE;
            for (Move m : mmgame.getPossibleMoves()) {
                if (m instanceof IAuxiliaryMove) {
                    continue;
                }
                mmgame.applyMove(m);
                int value = miniMax(depth - 1, true);
                mmgame.cancelLastMove();
                if (value < bestValue) {
                    bestValue = value;
                }
            }
            return bestValue - mmgame.getPossibleMoves().size();
        }
    }
    
}
