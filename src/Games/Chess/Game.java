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

package Games.Chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Core.Avatar;
import Core.GameHistory;
import Games.Chess.Moves.AcceptDraw;
import Games.Chess.Moves.BasicMove;
import Games.Chess.Moves.Castling;
import Games.Chess.Moves.DrawOffer;
import Games.Chess.Moves.EnPassant;
import Games.Chess.Moves.PawnLeap;
import Games.Chess.Moves.Promotion;
import Games.Chess.Moves.RejectDraw;
import Games.Chess.Moves.Resign;
import Games.Chess.Pieces.King;
import Games.Chess.Pieces.Pawn;
import Move.Picking.IAuxiliaryMove;
import Move.Picking.IPickingDecisionMaker;
import Move.Picking.IPickingGame;

/**
 * @author Fabian Pijcke
 */
public class Game extends GameHistory<Board, Move, IPickingDecisionMaker<IBoard, Move, Avatar>> implements IPickingGame<Board, Move, IPickingDecisionMaker<IBoard, Move, Avatar>> {
    
    private final Board board;
    private final List<List<Move>> acceptedMovesLists;
    
    public Game(IPickingDecisionMaker<IBoard, Move, Avatar> white, IPickingDecisionMaker<IBoard, Move, Avatar> black, Board board) {
        super(Arrays.asList(white, black));
        
        this.board = board;
        
        acceptedMovesLists = new ArrayList();
        acceptedMovesLists.add(getPossibleMoves(getCurrentPlayer().getAvatar()));

        informPlayer();
    }
    
    public Game(IPickingDecisionMaker<IBoard, Move, Avatar> white, IPickingDecisionMaker<IBoard, Move, Avatar> black, IBoard board, List<Move> moves) {
        super(Arrays.asList(white, black));
        
        this.board = new Board(board);
        
        for (Move m : moves) {
            addMove(m);
        }
        
        acceptedMovesLists = new ArrayList();
        acceptedMovesLists.add(getPossibleMoves(getCurrentPlayer().getAvatar()));
        
        informPlayer();
    }
    
    public IPickingDecisionMaker<IBoard, Move, Avatar> getWhite() {
        return getPlayers().get(0);
    }

    public IPickingDecisionMaker<IBoard, Move, Avatar> getBlack() {
        return getPlayers().get(1);
    }
    
    @Override
    public Board getBoard() {
        return board;
    }
    
    public boolean isCheckMate() {
        if (!inCheck(getCurrentPlayer().getAvatar()))
            return false;
        
        for (Move m : getPossibleMoves(getCurrentPlayer().getAvatar())) {
            if (!(m instanceof IAuxiliaryMove)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean hasResign() {
        return getTurn() > 0 && getMove(-1) instanceof Resign;
    }
    
    public boolean isStalemate() {
        for (Move m : getPossibleMoves(getCurrentPlayer().getAvatar())) {
            if (!(m instanceof IAuxiliaryMove)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isAcceptedDraw() {
        return getTurn() > 0 && getMove(-1) instanceof AcceptDraw;
    }
    
    public boolean isFiftyMoveDraw() {
        int i = getTurn() - 1;
        int nbTurns = 0;
        
        while (i >= 0 && nbTurns < 100) {
            Move m = getMove(i--);
            if (m instanceof BasicMove) {
                BasicMove b = (BasicMove) m;
                if (b.getMovingPiece() instanceof Pawn) {
                    return false;
                }
                else {
                    ++nbTurns;
                }
            }
            else if (m instanceof Promotion) {
                return false;
            }
            else if (m instanceof Castling) {
                ++nbTurns;
            }
            // Other cases ignored (Draw stuff)
        }
        
        return nbTurns == 100;
    }

    @Override
    public boolean isGameEnded() {
        boolean ended = isCheckMate() || hasResign() || isStalemate() || isFiftyMoveDraw();
        if (ended) {
            System.out.println("End of game");
        }
        return ended || isAcceptedDraw();
    }

    @Override
    public List<IPickingDecisionMaker<IBoard, Move, Avatar>> getWinners() {
        if (isCheckMate()) {
            new Resign().apply(board);
        }
        if (hasResign()) {
            return Arrays.asList(getCurrentPlayer());
        }
        return Arrays.asList();
    }
    
    private boolean hasMoved(Coordinate p) {
        if (isFree(p)) {
            return true;
        }
        for (int i = 0; i < getTurn(); ++i) {
            Move m = getMove(i);
            if (m instanceof BasicMove) {
                BasicMove b = (BasicMove) m;
                if (b.getDestination().equals(p)) {
                    return true;
                }
            }
            if (m instanceof Castling) {
                Castling c = (Castling) m;
                if (c.getDestination1().equals(p) || c.getDestination2().equals(p)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean hasMoved(int x, int y) {
        return hasMoved(new Coordinate(x, y));
    }
    
    private boolean isFree(Coordinate c) {
        return board.getPieceAt(c) == null;
    }
    
    private boolean isFree(int x, int y) {
        return isFree(new Coordinate(x, y));
    }

    public Coordinate getKingPosition(Avatar avatar) {
        Piece king = getBoard().getPieces(p -> p instanceof King && p.getAvatar() == avatar).get(0);
        return getBoard().getCoordinate(king);
    }
    
    public List<BasicMove> getBasicMoves(Avatar avatar) {
        ArrayList<BasicMove> moves = new ArrayList();
        
        board.getPieces(p -> p.getAvatar() == avatar).forEach(p -> moves.addAll(p.getAvailableMoves(board)));
        
        return moves;
    }
    
    public List<Castling> getCastlingMoves(Avatar avatar) {
        int baserow = avatar == getWhite().getAvatar() ? 0 : 7;
        List<Castling> moves = new ArrayList();

        Coordinate king = new Coordinate(4, baserow);
        BasicMove moveRight = new BasicMove(king, new Coordinate(5, baserow), board.getPieceAt(king), null);
        if (!hasMoved(king) && !hasMoved(7, baserow) && isFree(5, baserow) && isFree(6, baserow) && !inCheck(avatar) && !inCheck(avatar, moveRight)) {
            moves.add(new Castling(king, new Coordinate(6, baserow)));
        }
        BasicMove moveLeft = new BasicMove(king, new Coordinate(3, baserow), board.getPieceAt(king), null);
        if (!hasMoved(king) && !hasMoved(0, baserow) && isFree(3, baserow) && isFree(2, baserow) && isFree(1, baserow) && !inCheck(avatar) && !inCheck(avatar, moveLeft)) {
            moves.add(new Castling(king, new Coordinate(2, baserow)));
        }
        
        return moves;
    }
    
    public List<EnPassant> getEnPassantMoves(Avatar avatar) {
        List<EnPassant> moves = new ArrayList();
        
        if (getTurn() > 0 && getMove(-1) instanceof PawnLeap) {
            PawnLeap leap = (PawnLeap) getMove(-1);
            Coordinate c = leap.getDestination();
            Pawn p = leap.getMovingPiece();
            for (int x2 : Arrays.asList(c.getX() - 1, c.getX() + 1)) {
                try {
                    Coordinate c2 = new Coordinate(x2, c.getY());
                    Piece p2 = board.getPieceAt(c2);
                    if (p2 instanceof Pawn && p2.getAvatar() == avatar) {
                        moves.add(new EnPassant(c2, c, (Pawn) p2, p));
                    }
                } catch (Coordinate.BadCoordinateException e) {}
            }
        }
        
        return moves;
    }
    
    public List<Move> getPossibleMoves(Avatar avatar) {
        List<Move> moves = new ArrayList();
        
        if (getTurn() > 0 && getMove(-1) instanceof DrawOffer) {
            moves.add(new AcceptDraw());
            moves.add(new RejectDraw());
        }
        else {
            moves.addAll(getBasicMoves(avatar));
            moves.addAll(getCastlingMoves(avatar));
            moves.addAll(getEnPassantMoves(avatar));

            // No suicide allowed
            moves.removeIf(move -> inCheck(avatar, move));

            // Offer draw or resign
            moves.add(new DrawOffer()); // FIXME Limit overuse of draw proposal
            moves.add(new Resign());
        }
        
        return Collections.unmodifiableList(moves);
    }
    
    @Override
    public List<Move> getPossibleMoves() {
        return acceptedMovesLists.get(acceptedMovesLists.size() - 1);
    }
    
    private Avatar getOpponent(Avatar avatar) {
        return (getWhite().getAvatar() == avatar) ? getBlack().getAvatar() : getWhite().getAvatar();
    }
    
    public boolean inCheck(Avatar avatar) {
        for (BasicMove m : getBasicMoves(getOpponent(avatar))) { // We do not need to take into account Castling nor EnPassant, because these moves can not take a king
            if (m.getCaptured() instanceof King) {
                return true;
            }
        }
        
        return false;
    }

    public boolean inCheck(Avatar avatar, Move move) {
        move.apply(board); // Applies move without changing history nor current player
        boolean ret = inCheck(avatar);
        move.cancel(board);
        return ret;
    }
    
    @Override
    public void applyMove(Move m) {
        if (getPossibleMoves().contains(m)) {
            super.applyMove(m);
            acceptedMovesLists.add(getPossibleMoves(getCurrentPlayer().getAvatar()));
            informPlayer();
        }
        else {
            System.out.println("???");
        }
    }
    
    private void informPlayer() {
        getCurrentPlayer().informPastMoves(Collections.unmodifiableList(getMoves()));
        getCurrentPlayer().informBoard(new BoardProxy(getBoard()));
        getCurrentPlayer().informMoves(Collections.unmodifiableList(getPossibleMoves()));
    }
    
    @Override
    public void cancelLastMove() {
        if (acceptedMovesLists.size() < 2)
            return;
        
        super.cancelLastMove();
        acceptedMovesLists.remove(acceptedMovesLists.size() - 1);
        informPlayer();
    }
    
    @Override
    public void step() {
        if (isGameEnded()) {
            throw new IllegalStateException();
        }
        applyMove(getCurrentPlayer().pickMove());
    }

}
