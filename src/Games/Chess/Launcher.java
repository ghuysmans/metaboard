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

import Core.NameAvatar;
import Games.Chess.AIs.FirstMove;
import Games.Chess.Boards.Classic;
import Move.Picking.IPickingDecisionMaker;
import Move.Picking.RandomAI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Fabian Pijcke
 */
public class Launcher extends Application {
    
    private Game g;
    private GameView gv;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void initGame() {
        NameAvatar white = new NameAvatar("white");
        NameAvatar black = new NameAvatar("black");
        IPickingDecisionMaker whiteAI = new RandomAI(white);
        IPickingDecisionMaker blackAI = new FirstMove(black);
        
        // Check
        assert (whiteAI.getAvatar() == white);
        assert (blackAI.getAvatar() == black);
        
        g = new Game(whiteAI, blackAI, new Classic(white, black));
    }
    
    public void show() {
        g.getPossibleMoves().forEach(m -> System.out.println("" + m.getNotation()));
        gv.update(g);
    }
    
    public void step() {
        g.step();
        System.out.println("Chosen move: " + g.getMove(-1).getNotation() + " -- " + g.getMove(-1) + " (" + g.getBoard().getPieces().size() + " pieces left)");
        if (g.isGameEnded()) {
            System.out.println("Démarrage d'une nouvelle partie !\n\n\n");
            initGame();
        }
        show();
    }
    
    public void cancel() {
        g.cancelLastMove();
        show();
    }
    
    @Override
    public void start(Stage primaryStage) {
        initGame();
        gv = new GameView();
        
        show();
        
        gv.setOnMousePressed(event -> step());
        gv.setOnScroll(event -> cancel());
        StackPane root = new StackPane();
        root.getChildren().add(gv);
        primaryStage.setScene(new Scene(root, 640, 640));
        primaryStage.show();
    }
    
}
