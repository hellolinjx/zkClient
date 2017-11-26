package zhangq.zkClient.ui.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class WindowDemo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowDemo window = new WindowDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNodestat = new JLabel("NodeStat");
		lblNodestat.setBounds(211, 123, 61, 16);
		frame.getContentPane().add(lblNodestat);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(339, 95, 105, 29);
		frame.getContentPane().add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 28, 182, 244);
		frame.getContentPane().add(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(211, 27, 233, 67);
		frame.getContentPane().add(scrollPane_1);
		
		JEditorPane dtrpnHello = new JEditorPane();
		dtrpnHello.setText("hello");
		scrollPane_1.setViewportView(dtrpnHello);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(211, 143, 233, 129);
		frame.getContentPane().add(scrollPane_2);
		
		JTextPane txtpnStat = new JTextPane();
		txtpnStat.setText("stat");
		scrollPane_2.setViewportView(txtpnStat);
	}
}
