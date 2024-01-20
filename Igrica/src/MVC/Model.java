package MVC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Igrica.DatabaseConnection;
import Igrica.Player;

public class Model {

	private ArrayList<Player> players = new ArrayList<Player>();
	
	DatabaseConnection dbConnection = new DatabaseConnection();
	Connection connection = dbConnection.createConnection();
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ResultSet getPlayersRS()
	{
		ResultSet rs1 = null;
		try {
			Statement st;
			st = connection.createStatement();
			
			rs1 = st.executeQuery("Select * from Player");

			return rs1;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rs1;
	}
	public ResultSet getPlayersWepMap() throws SQLException
	{
		Statement st = connection.createStatement();
		String sqlSt = "select * from Player p join Weapon w on (p.ID_weapon=w.ID_weapon) join Map m on (m.ID_map=p.ID_map)";
		ResultSet rs = st.executeQuery(sqlSt);
		return rs;
	}
	public ResultSet getPlayersWepMap(String name) throws SQLException
	{
		PreparedStatement ps;
		String sqlSt1 = "select * from Player p join Weapon w on (p.ID_weapon=w.ID_weapon) join Map m on (m.ID_map=p.ID_map) where name_player = ?";
		ps = connection.prepareStatement(sqlSt1);
		ps.setString(1, name);
		ResultSet rs1 = ps.executeQuery();
		return rs1;
	}
	public void UpdatePotion(int potion,String name) throws SQLException
	{
		PreparedStatement ps;
		String sqlSt = " update player set potion = ? where name_player=?";
		ps = connection.prepareStatement(sqlSt);
		ps.setInt(1, potion);
		ps.setString(2,name);
		ps.execute();
	}
	public ResultSet SelectMap() throws SQLException
	{
		Statement st = connection.createStatement();
		ResultSet rs;
		rs = st.executeQuery("Select * from Map");
		return rs;
	}
	public void InsertPlayer(String name,String className,int mapIndex) throws SQLException
	{
	
		PreparedStatement ps = connection
				.prepareStatement("insert into Player values(?,100,10,1,?,1,10,0,0,1,1,?)");
		ps.setString(1, name);
		ps.setString(2, className);
		ps.setInt(3, mapIndex );
		ps.execute();
	}
	public void UpdateHealthLevelGold(int maxLvl,int gold,String name) throws SQLException
	{
		PreparedStatement ps;
		String sqlSt = " update player set max_level = ?,gold =? where name_player=?";
		ps = connection.prepareStatement(sqlSt);
		ps.setInt(1, maxLvl);
		ps.setInt(2, gold);
		ps.setString(3, name);
		ps.execute();

		ps.close();
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

	public void UpdateHealthGoldArmor(int health, int gold, String userName,int armor) throws SQLException {
		PreparedStatement ps;
		String sqlSt = " update player set health_player=?,gold =?,armor=? where name_player=?";
		
			ps = connection.prepareStatement(sqlSt);
			ps.setInt(2, gold);
			ps.setInt(1, health);
			ps.setString(4, userName);
			ps.setInt(3, armor);
			ps.execute();

			ps.close();
		
	}

	public void UpdateWpnEnchGold(int weaponEnchant, int gold, String userName) throws SQLException {
		PreparedStatement ps;
		String sqlSt = " update player set weaponEnchant=?,gold =? where name_player=?";
			ps = connection.prepareStatement(sqlSt);
			ps.setInt(2, gold);
			ps.setInt(1, weaponEnchant);
			ps.setString(3, userName);
			ps.execute();

			ps.close();
		
	
	}
}
