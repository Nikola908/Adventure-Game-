package Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Igrica.DatabaseConnection;
import MVC.Controller;
import MVC.Frame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class PlayerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean isOk;
	private JTextField txtName;
	private JComboBox<String> jcbMap;
	private JComboBox<String> jcbClass;

	public PlayerDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 77, 46, 86, 46, 86, 0 };
		gbl_contentPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtName = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.anchor = GridBagConstraints.NORTHWEST;
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 0;
			contentPanel.add(txtName, gbc_textField_1);
			txtName.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Class");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 1;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			jcbClass = new JComboBox<String>();
			jcbClass.addItem("Warlock");
			jcbClass.addItem("Barbarian");
			jcbClass.addItem("Hunter");
			GridBagConstraints gbc_jcbClass = new GridBagConstraints();
			gbc_jcbClass.insets = new Insets(0, 0, 5, 5);
			gbc_jcbClass.fill = GridBagConstraints.HORIZONTAL;
			gbc_jcbClass.gridx = 1;
			gbc_jcbClass.gridy = 1;
			contentPanel.add(jcbClass, gbc_jcbClass);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Map");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 2;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			jcbMap = new JComboBox<String>();
			GridBagConstraints gbc_jcb = new GridBagConstraints();
			gbc_jcb.anchor = GridBagConstraints.WEST;
			gbc_jcb.insets = new Insets(0, 0, 5, 5);
			gbc_jcb.gridx = 1;
			gbc_jcb.gridy = 2;
			contentPanel.add(jcbMap, gbc_jcb);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String name = getTxtName();
							String mapName = getMapComboBoxText();
							String className = getClassComboBoxText();

							DatabaseConnection dbCon = new DatabaseConnection();
							Connection connection = dbCon.createConnection();
							PreparedStatement ps = connection
									.prepareStatement("insert into Player values(?,100,10,1,?,1,10,0,0,1,1,?)");
							ps.setString(1, name);
							ps.setString(2, className);
							int indexMap = jcbMap.getSelectedIndex();
							ps.setInt(3, indexMap + 1);
							ps.execute();
							connection.close();
							dispose();
						} catch (Exception ex) {
							System.out.println(ex);
							JOptionPane.showMessageDialog(null, "You entered something Wrong!", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
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

	public String getTxtName() {
		return txtName.getText();
	}

	public JTextField getTxtFieldName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName.setText(txtName);
	}

	public String getMapComboBoxText() {
		return (String) jcbMap.getSelectedItem();
	}

	public String getClassComboBoxText() {

		return (String) jcbClass.getSelectedItem();
	}

	public JComboBox<String> getJComboBoxMap() {
		return jcbMap;
	}

	public void setJComboBoxMap(JComboBox<String> jcb) {
		this.jcbMap = jcb;
	}

	public JComboBox<String> getJComboBoxClass() {
		return jcbClass;
	}

	public void setJComboBoxClass(JComboBox<String> jcb) {
		this.jcbClass = jcb;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		this.isOk = ok;
	}

}
