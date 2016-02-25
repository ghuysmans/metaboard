package Games.Nim.Players;

import java.util.ArrayList;

import Games.Nim.Player;

public class PlayersList {
	
	public static ArrayList<Class<? extends Player>> playersList() {
		ArrayList<Class<? extends Player>> list = playersListFX();
		list.add(HumanConsole.class);
		
		return list;
	}
	
	public static ArrayList<Class<? extends Player>> playersListFX() {
		ArrayList<Class<? extends Player>> list = new ArrayList<>();
		list.add(HumanDialogBox.class);
		
		return list;
	}
}
