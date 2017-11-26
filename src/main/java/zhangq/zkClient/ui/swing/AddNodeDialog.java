package zhangq.zkClient.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.experimental.theories.Theories;
import org.w3c.dom.Node;

import zhangq.zkClient.ui.listener.OnAddDialogOkBtnActionListener;
import zhangq.zkClient.ui.listener.OnDialogCancelBtnActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;

public class AddNodeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static String[] NodeTypeStrings = {"Node", "SerialNode",
												"EphemeralNode", "EphemeralSeral"};
	private JTree tree;
	/**
	 * Launch the application.
	 */
	public static void main(JTree tree) {
		
		try {
			AddNodeDialog dialog = new AddNodeDialog(tree);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JTree getTree(){
		return tree;
	}

	/**
	 * Create the dialog.
	 */
	public AddNodeDialog(JTree tree) {
		this.tree = tree;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 239, 450, 39);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);
	
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(94, 9, 128, 27);
		getContentPane().add(comboBox);
		for	(int i=0; i<NodeTypeStrings.length; i++)
			comboBox.addItem(NodeTypeStrings[i]);
		
		
		JLabel lblNewLabel = new JLabel("Node Type:");
		lblNewLabel.setBounds(21, 13, 103, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setBounds(21, 35, 61, 16);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 63, 423, 121);
		getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(61, 196, 383, 39);
		getContentPane().add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("ACL:");
		lblNewLabel_2.setBounds(21, 196, 45, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(224, 13, 45, 16);
		getContentPane().add(lblName);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(268, 13, 176, 44);
		getContentPane().add(scrollPane_2);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new OnAddDialogOkBtnActionListener(this, comboBox, textPane, 
																textPane_1, textPane_2));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new OnDialogCancelBtnActionListener(this));
		buttonPane.add(cancelButton);
		
	}
}
