package Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Igrica.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;


	public Login(String userName) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("User Name");
			contentPanel.add(lblNewLabel);
		}
		{
			textFieldUserName = new JTextField();
			textFieldUserName.setEditable(false);
			textFieldUserName.setText(userName);
			contentPanel.add(textFieldUserName);
			textFieldUserName.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Password");
			contentPanel.add(lblNewLabel_1);
		}
		{
			textFieldPassword = new JTextField();
			contentPanel.add(textFieldPassword);
			textFieldPassword.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseConnection dbCon = new DatabaseConnection();
						Connection connection = dbCon.createConnection();
						String password =null;
						
						try {
							PreparedStatement ps;
							String sqlSt1 = "select class from Player where name_player = ?";
							ps = connection.prepareStatement(sqlSt1);
							ps.setString(1, userName);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								password =rs.getString(1);
								System.out.println(password);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
							if(textFieldPassword.getText().equals(String.valueOf(password)))
							{
							 dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "You entered the wrong password TryAgain!", "Error!",
										JOptionPane.ERROR_MESSAGE);
							}
						
						
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
