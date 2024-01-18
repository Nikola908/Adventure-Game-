package Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Igrica.DatabaseConnection;
import Igrica.Player;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ShopDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton btnBuy_1;
	private JButton btnBuy_2;
	private JButton btnBuy_3;
	private JButton btnBuy;
	private Player player;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	public ShopDialog(Player player) {
		this.player = player;
		setBounds(100, 100, 544, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(157, 11, 165, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}

		JLabel lblHeadArmor = new JLabel("Head Armor -> 20 gold");
		lblHeadArmor.setBounds(10, 44, 136, 14);
		contentPanel.add(lblHeadArmor);

		btnBuy = new JButton("Buy");
		if (player.getArmor().isHeadArmor()) {
			btnBuy.setEnabled(false);
			btnBuy.setText("You have this!");
		}

		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getArmor().setHeadArmor(true);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set health_player=?,gold =?,armor=1 where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getHealth());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

						textField.setText("You have :" + player.getGold() + " gold");
						btnBuy.setEnabled(false);
						btnBuy_1.setEnabled(true);
						btnBuy.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnBuy.setBounds(176, 42, 89, 23);
		contentPanel.add(btnBuy);

		JLabel lblChestArmor = new JLabel("Chest Armor -> 20 gold");
		lblChestArmor.setBounds(10, 80, 136, 14);
		contentPanel.add(lblChestArmor);

		JLabel lblLegArmor = new JLabel("Leg Armor -> 20 gold");
		lblLegArmor.setBounds(10, 120, 136, 14);
		contentPanel.add(lblLegArmor);

		JLabel lblNewLabel = new JLabel("Feet Armor -> 20 gold");
		lblNewLabel.setBounds(10, 157, 136, 14);
		contentPanel.add(lblNewLabel);

		btnBuy_1 = new JButton("Buy");
		btnBuy_1.setEnabled(false);
		if (player.getArmor().isHeadArmor()) {
			btnBuy_1.setEnabled(true);
		}
		if (player.getArmor().isChestArmor()) {
			btnBuy_1.setEnabled(false);
			btnBuy_1.setText("You have this!");
		}

		btnBuy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {

					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {

						player.setGold(player.getGold() - 20);
						player.getArmor().setChestArmor(true);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set health_player=?,gold =?,armor=2 where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getHealth());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnBuy_1.setEnabled(false);
						btnBuy_2.setEnabled(true);
						btnBuy_1.setText("You have this!");
					} else
						dispose();
				}

			}
		});
		btnBuy_1.setBounds(176, 76, 89, 23);
		contentPanel.add(btnBuy_1);

		btnBuy_2 = new JButton("Buy");
		btnBuy_2.setEnabled(false);
		if (player.getArmor().isChestArmor()) {
			btnBuy_2.setEnabled(true);
		}
		if (player.getArmor().isLegArmor()) {
			btnBuy_2.setEnabled(false);
			btnBuy_2.setText("You have this!");
		}

		btnBuy_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getArmor().setLegArmor(true);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set health_player=?,gold =?,armor=3 where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getHealth());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");

						btnBuy_2.setEnabled(false);
						btnBuy_3.setEnabled(true);
						btnBuy_2.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnBuy_2.setBounds(176, 116, 89, 23);
		contentPanel.add(btnBuy_2);

		btnBuy_3 = new JButton("Buy");
		btnBuy_3.setEnabled(false);
		if (player.getArmor().isLegArmor()) {
			btnBuy_3.setEnabled(true);
		}
		if (player.getArmor().isFeetArmor()) {
			btnBuy_3.setEnabled(false);
			btnBuy_3.setText("You have this!");
		}

		btnBuy_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getArmor().setFeetArmor(true);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set health_player=?,gold =?,armor=4 where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getHealth());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnBuy_3.setEnabled(false);
						btnBuy_3.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnBuy_3.setBounds(176, 153, 89, 23);
		contentPanel.add(btnBuy_3);

		JLabel lblNewLabel_1 = new JLabel("Enchant +3 dmg ->20g");
		lblNewLabel_1.setBounds(289, 44, 136, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Enchant2 +3 dmg ->20g");
		lblNewLabel_1_1.setBounds(289, 80, 136, 14);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Enchant3 +3 dmg ->20g");
		lblNewLabel_1_2.setBounds(289, 120, 136, 14);
		contentPanel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Enchant4 +3 dmg ->20g");
		lblNewLabel_1_3.setBounds(289, 157, 136, 14);
		contentPanel.add(lblNewLabel_1_3);

		btnNewButton = new JButton("Buy");
		System.out.println(player.getWeapon().getWeaponEnchant());
		if (player.getWeapon().getWeaponEnchant() > 0) {
			
			btnNewButton.setEnabled(false);
			btnNewButton.setText("You have this!");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getWeapon().setWeaponEnchant(1);
						player.getWeapon().setWeaponDamage(player.getWeapon().getWeaponDamage() + 3);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set weaponEnchant=?,gold =? where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getWeapon().getWeaponEnchant());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnNewButton.setEnabled(false);
						btnNewButton_1.setEnabled(true);
						btnNewButton.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnNewButton.setBounds(429, 40, 89, 23);
		contentPanel.add(btnNewButton);

		btnNewButton_1 = new JButton("Buy");
		btnNewButton_1.setEnabled(false);
		if (player.getWeapon().getWeaponEnchant() == 1) {
			btnNewButton_1.setEnabled(true);
		}
		if (player.getWeapon().getWeaponEnchant() > 1) {
			btnNewButton_1.setEnabled(false);
			btnNewButton_1.setText("You have this!");
		}
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getWeapon().setWeaponEnchant(2);
						player.getWeapon().setWeaponDamage(player.getWeapon().getWeaponDamage() + 3);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set weaponEnchant=?,gold =? where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getWeapon().getWeaponEnchant());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnNewButton_1.setEnabled(false);
						btnNewButton_2.setEnabled(true);
						btnNewButton_1.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnNewButton_1.setBounds(429, 76, 89, 23);
		contentPanel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Buy");
		btnNewButton_2.setEnabled(false);
		if (player.getWeapon().getWeaponEnchant() == 2) {
			btnNewButton_2.setEnabled(true);
		}
		if (player.getWeapon().getWeaponEnchant() > 2) {
			btnNewButton_2.setEnabled(false);
			btnNewButton_2.setText("You have this!");
		}
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getWeapon().setWeaponEnchant(3);
						player.getWeapon().setWeaponDamage(player.getWeapon().getWeaponDamage() + 3);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set weaponEnchant=?,gold =? where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getWeapon().getWeaponEnchant());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnNewButton_2.setEnabled(false);
						btnNewButton_3.setEnabled(true);
						btnNewButton_2.setText("You have this!");
					} else
						dispose();
				}
			}
		});
		btnNewButton_2.setBounds(429, 116, 89, 23);
		contentPanel.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Buy");
		btnNewButton_3.setEnabled(false);
		if (player.getWeapon().getWeaponEnchant() == 3) {
			btnNewButton_3.setEnabled(true);
		}
		if (player.getWeapon().getWeaponEnchant() > 3) {
			btnNewButton_3.setEnabled(false);
			btnNewButton_3.setText("You have this!");
		}
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getGold() < 20) {
					JOptionPane.showMessageDialog(null, "You dont have enough gold!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this?",
							"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						player.setGold(player.getGold() - 20);
						player.getWeapon().setWeaponEnchant(4);
						player.getWeapon().setWeaponDamage(player.getWeapon().getWeaponDamage() + 3);
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						PreparedStatement ps;
						String sqlSt = " update player set weaponEnchant=?,gold =? where name_player=?";
						try {
							ps = connection.prepareStatement(sqlSt);
							ps.setInt(2, player.getGold());
							ps.setInt(1, player.getWeapon().getWeaponEnchant());
							ps.setString(3, player.getUserName());
							ps.execute();

							ps.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("You have :" + player.getGold() + " gold");
						btnNewButton_3.setEnabled(false);
						btnNewButton_3.setText("You have this!");
					} else
						dispose();
				}

			}
		});
		btnNewButton_3.setBounds(429, 153, 89, 23);
		contentPanel.add(btnNewButton_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField jtf) {
		this.textField = jtf;
	}

	public JButton getBtnBuy_1() {
		return btnBuy_1;
	}

	public void setBtnBuy_1(JButton btnBuy_1) {
		this.btnBuy_1 = btnBuy_1;
	}

	public JButton getBtnBuy_2() {
		return btnBuy_2;
	}

	public void setBtnBuy_2(JButton btnBuy_2) {
		this.btnBuy_2 = btnBuy_2;
	}

	public JButton getBtnBuy_3() {
		return btnBuy_3;
	}

	public void setBtnBuy_3(JButton btnBuy_3) {
		this.btnBuy_3 = btnBuy_3;
	}

	public JButton getBtnBuy() {
		return btnBuy;
	}

	public void setBtnBuy(JButton btnBuy) {
		this.btnBuy = btnBuy;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public void setBtnNewButton_2(JButton btnNewButton_2) {
		this.btnNewButton_2 = btnNewButton_2;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	public void setBtnNewButton_3(JButton btnNewButton_3) {
		this.btnNewButton_3 = btnNewButton_3;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}
}
