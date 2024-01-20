package MVC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dialogs.PlayerDialog;
import Igrica.DatabaseConnection;
import Igrica.Player;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> jcbPlayer;

	private Controller controller = new Controller();

	DatabaseConnection dbConnection = new DatabaseConnection();
	Connection connection = dbConnection.createConnection();
	private JTextArea textAreaCharacter;
	private JTextArea textAreaBoss;
	private JButton btnAtack;
	private JButton btnHeal;
	private JButton btnSkipTurn;
	private JButton btnPotion;
	private JTextArea textArea;
	private JTextArea textAreaBossAction;
	private JComboBox<Integer> comboBoxLevel;
	private JButton btnShop;
	private JComboBox<String> comboBox;


	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Frame() {
		this.setSize(1300, 750);
		this.setTitle("Adventure Game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 644, 33);

		JButton btnNewButton = new JButton("New Character");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.createNewCharacter();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);

		contentPane.setLayout(null);
		contentPane.add(panel);

		jcbPlayer = new JComboBox<String>();
		jcbPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.fillJcb();
					controller.FillCharacters();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

//		try {
//			Statement st;
//			st = connection.createStatement();
//			ResultSet rs1;
//			rs1 = st.executeQuery("Select * from Player");
//
//			while (rs1.next()) {
//				jcbPlayer.addItem(rs1.getString("name_player"));
//			}
//			st.close();
//		} catch (SQLException e2) {
//			e2.printStackTrace();
//		}

		jcbPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
//				controller.fillJcb();
				try {
					controller.UpdateTxtField();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JLabel lblNewLabel = new JLabel("Select A Character->");
		panel.add(lblNewLabel);
		panel.add(jcbPlayer);

		btnShop = new JButton("Shop");
		panel.add(btnShop);
		btnShop.setVisible(false);
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openShop();
			}
		});

		textAreaBoss = new JTextArea();
		textAreaBoss.setEditable(false);
		textAreaBoss.setBounds(0, 82, 360, 20);
		contentPane.add(textAreaBoss);

		textAreaCharacter = new JTextArea();
		textAreaCharacter.setEditable(false);
		textAreaCharacter.setBounds(0, 49, 939, 22);
		contentPane.add(textAreaCharacter);

		btnAtack = new JButton("Atack!");
		btnAtack.setEnabled(false);
		btnAtack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.Atack();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAtack.setBounds(10, 113, 89, 23);
		contentPane.add(btnAtack);

		btnHeal = new JButton("Heal ");
		btnHeal.setEnabled(false);
		btnHeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Heal();
			}
		});
		btnHeal.setBounds(125, 113, 89, 23);
		contentPane.add(btnHeal);

		btnSkipTurn = new JButton("Skip turn ");
		btnSkipTurn.setEnabled(false);
		btnSkipTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.SkipTurn();
			}
		});
		btnSkipTurn.setBounds(243, 113, 103, 23);
		contentPane.add(btnSkipTurn);

		textArea = new JTextArea();
		textArea.setVisible(false);
		textArea.setEditable(false);
		textArea.setBounds(0, 147, 569, 22);
		contentPane.add(textArea);

		btnPotion = new JButton("Potion");
		btnPotion.setEnabled(false);
		btnPotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.UsePotion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPotion.setBounds(368, 113, 89, 23);
		contentPane.add(btnPotion);

		comboBoxLevel = new JComboBox<Integer>();
		comboBoxLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				controller.SetLevel();

			}
		});
		comboBoxLevel.addItem(1);
		comboBoxLevel.setVisible(false);
		comboBoxLevel.setBounds(483, 113, 53, 22);
		contentPane.add(comboBoxLevel);

		textAreaBossAction = new JTextArea();
		textAreaBossAction.setVisible(false);
		textAreaBossAction.setEditable(false);
		textAreaBossAction.setBounds(586, 147, 336, 22);
		contentPane.add(textAreaBossAction);
	}

	public void setVisible(Player player) {
		setTxtAreaBoss("Boss->" + player.getBoss().getName() + " Health: " + player.getBoss().getHealth());
		getComboBoxLevel().setVisible(true);
		getTextArea().setVisible(true);
		getTxtAreaBossAction().setVisible(true);
		getBtnShop().setVisible(true);
		getTxtAreaBossAction().setText("");
		setBtns(true);
		getBtnAtack().setToolTipText(player.getWeapon().getWeaponDamage() + " damage");
		getBtnHeal().setToolTipText("+20 Health");
		getBtnSkipTurn().setToolTipText("+3 Energy");
		getBtnPotion().setToolTipText(player.getPotion() + " Potions");
	}

	public void setBtns(boolean b) {
		this.btnAtack.setEnabled(b);
		this.btnHeal.setEnabled(b);
		this.btnSkipTurn.setEnabled(b);
		this.btnPotion.setEnabled(b);
	}

	public JComboBox<String> getJcbPlayer() {
		return this.jcbPlayer;
	}

	public JComboBox<String> getComboBox() {
		return this.comboBox;
	}

	public JTextArea getTxtAreaCharacter() {
		return textAreaCharacter;
	}

	public void setTxtAreaCharacter(String string) {
		textAreaCharacter.setText(string);
		;
	}

	public JTextArea getTxtAreaBoss() {
		return textAreaBoss;
	}

	public JTextArea getTxtArea() {
		return textArea;
	}

	public void setTextArea(String string) {
		textArea.setText(string);
		;
	}

	public JTextArea getTxtAreaBossAction() {
		return textAreaBossAction;
	}

	public void setTextAreaBossAction(String string) {
		textAreaBossAction.setText(string);
		;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTxtAreaBoss(String string) {
		textAreaBoss.setText(string);
		;
	}

	public String getJcbPlayerText() {
		return (String) jcbPlayer.getSelectedItem();
	}

	public JButton getBtnAtack() {
		return btnAtack;
	}

	public void setBtnAtack(JButton btnAtack) {
		this.btnAtack = btnAtack;
	}

	public JButton getBtnShop() {
		return btnShop;
	}

	public void setBtnShop(JButton btnShop) {
		this.btnShop = btnShop;
	}

	public JButton getBtnHeal() {
		return btnHeal;
	}

	public void setBtnHeal(JButton btnHeal) {
		this.btnHeal = btnHeal;
	}

	public JButton getBtnSkipTurn() {
		return btnSkipTurn;
	}

	public void setBtnSkipTurn(JButton btnSkipTurn) {
		this.btnSkipTurn = btnSkipTurn;
	}

	public JButton getBtnPotion() {
		return btnPotion;
	}

	public void setBtnPotion(JButton btnLevel) {
		this.btnPotion = btnLevel;
	}

	public JComboBox<Integer> getComboBoxLevel() {
		return comboBoxLevel;
	}

	public int getComboBoxLevelValue() {
		return (int) comboBoxLevel.getSelectedItem();
	}

	public void setComboBoxLevel(JComboBox<Integer> comboBoxLevel) {
		this.comboBoxLevel = comboBoxLevel;
	}
}
