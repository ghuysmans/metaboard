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

public class HumanDialogBox extends Player {
	
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
