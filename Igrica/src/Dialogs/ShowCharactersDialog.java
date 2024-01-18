package Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowCharactersDialog extends JDialog {
	private JTable tablePlayer;
	private JComboBox<String> jcbPlayer;
	private JScrollPane scrollPane;

	public ShowCharactersDialog() {
		setBounds(100, 100, 921, 520);
		getContentPane().setLayout(new BorderLayout());
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
		{
			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.NORTH);
			{
				setTablePlayer(new JTable());
				getTablePlayer().setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "New column", "New column", "New column", "New column", "New column",
								"New column", "New column", "New column", "New column", "New column" }));
			}
		}
	}

	public JTable getTablePlayer() {
		return tablePlayer;
	}

	public JComboBox<String> getJcbPlayer() {
		return jcbPlayer;
	}

	public String getJcbPlayerText() {
		return (String) jcbPlayer.getSelectedItem();
	}

	public void setTablePlayer(JTable tablePlayer) {
		this.tablePlayer = tablePlayer;
		scrollPane.setViewportView(tablePlayer);
	}

}
