package MVC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Dialogs.PlayerDialog;
import Dialogs.ShopDialog;
import Igrica.DatabaseConnection;
import Igrica.Player;


public class Controller {

	private DatabaseConnection dbCon = new DatabaseConnection();
	private Connection connection = dbCon.createConnection();
	private Frame frame;
	private Player player;
	private Model model;

	// mora imati vezu sa frame i model
	public Controller() {

	}

	public Controller(Frame frame, Model model) {
		this.frame = frame;
		this.setModel(model);
	}

	public void createNewCharacter() throws SQLException {
		PlayerDialog dialog = new PlayerDialog();
		dialog.setTxtName(dialog.getTxtName());
		dialog.setVisible(true);

		JComboBox<String> jcbMap = dialog.getJComboBoxMap();
		ResultSet rs =model.SelectMap();
		while (rs.next()) {
			jcbMap.addItem(rs.getString("name_map"));
		}
		rs.close();
	}

	public void FillCharacters() throws SQLException {

		Statement st = connection.createStatement();
		JComboBox<String> jcb = frame.getJcbPlayer();
		ResultSet rs = model.getPlayersWepMap();
		jcb.removeAllItems();
		while (rs.next()) {
			jcb.addItem(rs.getString(2));
		}
	}

	public void fillJcb() throws SQLException
	{
		ResultSet rs =model.getPlayersRS();
		while (rs.next()) {
			frame.getJcbPlayer().addItem(rs.getString("name_player"));
		}
	}
	public void UpdateTxtField() throws SQLException {

		ResultSet rs =model.getPlayersWepMap();
		while (rs.next()) {
			if (model.getPlayers().size() >= rs.getRow()) {
			} else {
				player = Player.createPlayer(rs.getString(2), rs.getString(6), rs.getInt(17), rs.getInt(7));
				player.setRemainingValues(rs.getInt(9), rs.getInt(10), rs.getInt(3), rs.getInt(5), rs.getInt(8));
				model.add(player);

			}

		}

		ResultSet rs1 =model.getPlayersWepMap(frame.getJcbPlayerText());
		while (rs1.next()) {
			for (int i = frame.getComboBoxLevel().getItemCount(); i > 1; i--) {
				frame.getComboBoxLevel().removeItemAt(i - 1);
			}
			for (int i = 1; i <= rs1.getInt(7) - 1; i++) {
				frame.getComboBoxLevel().addItem(i + 1);
			}
			player = model.get(rs1.getInt(1) - 1);
			frame.setTxtAreaCharacter("SELECTED PLAYER -> " + "  Name:  " + rs1.getString(2) + "  Health:  "
					+ player.getHealth() + "  Energy:  " + rs1.getInt(4) + " Potions: " + rs1.getInt(5) + "  Class:  "
					+ rs1.getString(6) + " Weapon: " + rs1.getString(15) + " -> " + player.getWeapon().getWeaponDamage()
					+ " damage " + "  Map:  " + rs1.getString(18));
			frame.setVisible(player);
			frame.setBtns(true);
		}



	}

	public void SetLevel() {

		if (player != null) {
			player.getBoss().levelUp(frame.getComboBoxLevelValue());
			frame.getTxtAreaBoss().setText("Boss Health: " + player.getBoss().getHealth());
			frame.getTxtAreaBossAction().setText("");
			player.reset();
			frame.getTxtAreaCharacter()
					.setText("Health player :" + player.getHealth() + " Energy :" + player.getEnergy() + " Potions: "
							+ player.getPotion() + " Weapon Damage : " + player.getWeapon().getWeaponDamage());
			frame.setBtns(true);
		}

	}

	public void Atack() throws SQLException {

		frame.getTextArea().setText("Last Action: " + player.Atack());
		if (player.getBoss().getHealth() > 0) {
			frame.setTextAreaBossAction(player.getBoss().Atack());
		}
		if (player.getBoss().getHealth() <= 0) {
			frame.setTextArea("You successfully beat the level!");
			Random rand = new Random();
			if (rand.nextInt(3) == 0) {
				player.setPotion(player.getPotion() + 1);
				model.UpdatePotion(player.getPotion(), player.getUserName());
				JOptionPane.showMessageDialog(null, "A Boss dropped a potion for you!", "Potion!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			frame.setBtns(false);
			if (player.getMaxLevel() == frame.getComboBoxLevelValue()) {
				player.setGold(player.getGold() + player.getMaxLevel() * 2);
				frame.setTextArea("You successfully beat the level!" + "You just earned " + player.getMaxLevel() * 2
						+ " gold ! Max Gold:" + player.getGold());
				player.setMaxLevel(player.getMaxLevel() + 1);
				frame.getComboBoxLevel().addItem(player.getMaxLevel());

				PreparedStatement ps;
				String sqlSt = " update player set max_level = ?,gold =? where name_player=?";
				ps = connection.prepareStatement(sqlSt);
				ps.setInt(1, player.getMaxLevel());
				ps.setInt(2, player.getGold());
				ps.setString(3, player.getUserName());
				ps.execute();

				ps.close();

			}
		}
		if (player.getHealth() <= 0) {
			frame.setTextAreaBossAction("You died ! Restart Level to try again!");
			JOptionPane.showMessageDialog(null, "You died ! Restart Level to try again!", "Potion!",
					JOptionPane.INFORMATION_MESSAGE);
			frame.setBtns(false);
		}
		frame.getTxtAreaCharacter().setText("Health player :" + player.getHealth() + " Energy :" + player.getEnergy()
				+ " Potions: " + player.getPotion() + " Weapon Damage : " + player.getWeapon().getWeaponDamage());

		frame.getTxtAreaBoss().setText("Boss Health: " + player.getBoss().getHealth());

	}

	public void Heal() {
		frame.getTextArea().setText("Last Action: " + player.Heal());
		frame.setTextAreaBossAction(player.getBoss().Atack());
		if (player.getHealth() <= 0) {
			frame.setTextAreaBossAction("You died ! Restart Level to try again!");
			JOptionPane.showMessageDialog(null, "You died ! Restart Level to try again!", "Potion!",
					JOptionPane.INFORMATION_MESSAGE);
			frame.setBtns(false);
		}
		frame.getTxtAreaCharacter().setText("Health player :" + player.getHealth() + " Energy :" + player.getEnergy()
				+ " Potions: " + player.getPotion() + " Weapon Damage : " + player.getWeapon().getWeaponDamage());

	}

	public void SkipTurn() {
		frame.getTextArea().setText("Last Action: " + player.SkipTurn());
		frame.setTextAreaBossAction(player.getBoss().Atack());
		if (player.getHealth() <= 0) {
			frame.setTextAreaBossAction("You died ! Restart Level to try again!");
			JOptionPane.showMessageDialog(null, "You died ! Restart Level to try again!", "Potion!",
					JOptionPane.INFORMATION_MESSAGE);
			frame.setBtns(false);
		}
		frame.getTxtAreaCharacter().setText("Health player :" + player.getHealth() + " Energy :" + player.getEnergy()
				+ " Potions: " + player.getPotion() + " Weapon Damage : " + player.getWeapon().getWeaponDamage());

	}

	public void UsePotion() throws SQLException {
		if (player.getPotion() == 0) {
			frame.getTextArea().setText("You have 0 potions!");

		} else {

			frame.getTextArea().setText("Last Action: " + player.UsePotion());
			model.UpdatePotion(player.getPotion(),player.getUserName());
			

		}
		frame.getTxtAreaCharacter().setText("Health player :" + player.getHealth() + " Energy :" + player.getEnergy()
				+ " Potions: " + player.getPotion() + " Weapon Damage : " + player.getWeapon().getWeaponDamage());

	}


	public void openShop() {
		ShopDialog shopDialog = new ShopDialog(player);
		player.reset();
		shopDialog.setVisible(true);
		shopDialog.getTextField().setText("You have :" + player.getGold() + " gold");

	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
