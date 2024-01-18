package MVC;

import java.util.ArrayList;

import Igrica.Player;

public class Model {

	private ArrayList<Player> players = new ArrayList<Player>();

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void add(Player player) {
		players.add(player);
	}

	public void remove(Player player) {
		players.remove(player);
	}

	public Player get(int index) {
		return players.get(index);
	}

}
