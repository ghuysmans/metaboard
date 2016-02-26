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

package Games.Nim.Players;

import java.util.List;
import java.util.Optional;

import Core.NameAvatar;
import Games.Nim.Player;
import Games.Nim.Moves.Move;
import Games.Nim.Moves.MoveToken;
import Games.Nim.Moves.Resign;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Allows a human to play using a really basic GUI.
 * 
 * @author Fabian Pijcke
 */
public class HumanDialogBox extends Player {

	/**
	 * Standard Player constructor.
	 * 
	 * @param avatar
	 */
	public HumanDialogBox(NameAvatar avatar) {
		super(avatar);
	}

	@Override
	public Move pickMove() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Your turn to play !");
		dialog.setHeaderText("The token is on position " + getBoard().getTokenPosition());
		dialog.setContentText("How much do you want to move it? (Cancel to resign)");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return new MoveToken(Integer.parseInt(result.get()));
		}
		return new Resign();
	}

	@Override
	public void informEnd(List<NameAvatar> winners) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("The game has ended");
		if (winners.get(0) == getAvatar()) {
			alert.setContentText("You won! Congratulations!");
		}
		else {
			alert.setContentText("You lost. Let's win the next one!");
		}
		alert.showAndWait();
	}

}
