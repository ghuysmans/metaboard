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

package Games.Chess;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Fabian Pijcke
 */
public class GameView extends Canvas {
    
    public final Color blackCell = Color.CHOCOLATE;
    public final Color whiteCell = Color.WHITE;
    public final Color blackPiece = Color.BLACK;
    public final Color whitePiece = Color.BURLYWOOD;
    
    public GameView() {
        super(640, 640);
    }
    
    public void update(Game game) {
        GraphicsContext gc = getGraphicsContext2D();
        
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                // Drawing cell
                gc.setFill((x + y) % 2 == 1 ? blackCell : whiteCell);
                gc.fillRect(x * 64, y * 64, 64, 64);
                
                // Drawing piece, if any
                Piece p = game.getBoard().getPieceAt(new Coordinate(x, 7 - y));
                if (p != null) {
                    gc.setFill(game.getWhite().getAvatar() == p.getAvatar() ? whitePiece : blackPiece);
                    gc.setFont(new Font(64));
                    gc.fillText("" + p.getLetter(), x * 64 + 8, y * 64 + 56);
                }
            }
        }
    }
}
