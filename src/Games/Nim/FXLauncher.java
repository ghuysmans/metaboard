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

package Games.Nim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Core.NameAvatar;
import Games.Nim.Players.PlayersList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A graphical launcher for the game of Nim.
 * 
 * @author Fabian Pijcke
 */
public class FXLauncher extends Application {

	private int playersGenerated = 0;
	private VBox vbox_players = new VBox();

	private class PlayerNode extends HBox {

		private TextField textfield_name;
		private ComboBox<Class<? extends Player>> combobox_type;

		public PlayerNode() {
			super();

			textfield_name = new TextField("Player " + (playersGenerated++));
			combobox_type = new ComboBox<>();
			combobox_type.getItems().addAll(PlayersList.playersListFX());

			Button button_drop = new Button("X");
			button_drop.setOnAction((event) -> {
				vbox_players.getChildren().remove(this);
			});

			this.getChildren().addAll(textfield_name, combobox_type, button_drop);
		}

		public Player getPlayer() {
			Class<? extends Player> class_player = combobox_type.getValue();
			String name = textfield_name.getText();
			try {
				Constructor<? extends Player> constructor = class_player.getConstructor(NameAvatar.class);
				return constructor.newInstance(new NameAvatar() {
					@Override
					public String getName() {
						return name;
					}
				});
			} catch (NoSuchMethodException e) {
				System.err.println(class_player.getName() + " does not declare a NameAvatar constructor.");
				return null;
			} catch (InvocationTargetException e) {
				System.err.println(class_player.getName() + " failed to initialize properly.");
				return null;
			} catch (IllegalAccessException e) {
				System.err.println("Constructor of " + class_player.getName() + " is not visible");
				return null;
			} catch (InstantiationException e) {
				System.err.println("Cannot instanciate " + class_player.getName());
				return null;
			}
		}

	}

	private Spinner<Integer> spinner_maxLeap = new Spinner<>(1, 1000, 3);
	private Spinner<Integer> spinner_initialPosition = new Spinner<>(10, 100000, 42);

	/**
	 * Usage : java Games.Nim.FXLauncher
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("MetaBoard - Nim Launcher");

		Button button_addPlayer = new Button("Add player");
		button_addPlayer.setOnAction((event) -> addPlayer());

		Button button_launch = new Button("Start game!");
		button_launch.setOnAction((event) -> startGame());

		Button button_exit = new Button("Quit");
		button_exit.setOnAction((event) -> exit());

		GridPane topPane = new GridPane();
		topPane.setHgap(10);
		topPane.setVgap(15);
		topPane.add(new Label("Max Leap"), 0, 0);
		topPane.add(spinner_maxLeap, 1, 0);
		topPane.add(new Label("Start position"), 0, 1);
		topPane.add(spinner_initialPosition, 1, 1);

		GridPane bottomPane = new GridPane();
		bottomPane.setHgap(10);
		bottomPane.add(button_addPlayer, 0, 0);
		bottomPane.add(button_launch, 1, 0);
		bottomPane.add(button_exit, 2, 0);

		BorderPane mainPane = new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setBottom(bottomPane);

		mainPane.setCenter(vbox_players);
		addPlayer();
		addPlayer();

		primaryStage.setScene(new Scene(mainPane, 640, 480));
		primaryStage.show();
	}

	/**
	 * Adds a player to the list of players. This player has a default name and
	 * no player class associated.
	 */
	public void addPlayer() {
		vbox_players.getChildren().add(new PlayerNode());
	}

	/**
	 * Starts the game.
	 */
	public void startGame() {
		ArrayList<Player> players = new ArrayList<>();
		for (Node node : vbox_players.getChildren()) {
			PlayerNode playerNode = (PlayerNode) node;
			Player player = playerNode.getPlayer();
			if (player != null) {
				players.add(player);
			}
		}
		Game game = new Game(players, spinner_maxLeap.getValue(), spinner_initialPosition.getValue());
		while (!game.isGameEnded()) {
			game.printStatus();
			game.step();
		}
		game.printStatus();
	}

	/**
	 * Exits the launcher and interrupts any game in progress.
	 */
	public void exit() {
		System.exit(0);
	}
}
