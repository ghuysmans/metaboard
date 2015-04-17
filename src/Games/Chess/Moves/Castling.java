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

package Games.Chess.Moves;

import Games.Chess.Board;
import Games.Chess.Coordinate;
import Games.Chess.Move;

/**
 * @author Fabian Pijcke
 */
public final class Castling implements Move {
    
    private final Coordinate king, tower, kingDest, towerDest;
    private BasicMove m1, m2;

    public Castling(Coordinate king, Coordinate tower) {
        this.king = king;
        this.tower = tower;
        kingDest = new Coordinate(tower.getX() == 0 ? 2 : 6, king.getY());
        towerDest = new Coordinate(tower.getX() == 0 ? 3 : 5, tower.getY());
    }
    
    @Override
    public void apply(Board board) {
        m1 = new BasicMove(king, kingDest, board.getPieceAt(king), null);
        m2 = new BasicMove(tower, towerDest, board.getPieceAt(tower), null);
        
        m1.apply(board);
        m2.apply(board);
    }
    
    @Override
    public void cancel(Board board) {
        m2.cancel(board);
        m1.cancel(board);
    }
    
    @Override
    public String getNotation() {
        return (tower.getX() == 0) ? "O-O-O" : "O-O";
    }
    
    public Coordinate getStart1() {
        return king;
    }
    
    public Coordinate getStart2() {
        return tower;
    }
    
    public Coordinate getDestination1() {
        return kingDest;
    }
    
    public Coordinate getDestination2() {
        return towerDest;
    }
    
}
