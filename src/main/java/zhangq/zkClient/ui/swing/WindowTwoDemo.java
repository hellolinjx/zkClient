package zhangq.zkClient.ui.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeModelListener;
import javax.swing.text.html.parser.DTD;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.listener.OnAddItemMouseListener;
import zhangq.zkClient.ui.listener.OnDeleteItemMouseListener;
import zhangq.zkClient.ui.listener.OnFrameWindowListener;
import zhangq.zkClient.ui.listener.OnRootPropertyItemMouseListener;
import zhangq.zkClient.ui.listener.OnRefreshItemMouseListener;
import zhangq.zkClient.ui.listener.OnTreeMouseListener;
import zhangq.zkClient.ui.listener.OnTreeSelectionListener;
import zhangq.zkClient.ui.listener.OnTreeWillExpandListener;
import zhangq.zkClient.ui.listener.OnUpdateButtonMouseListener;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class WindowTwoDemo {
	private String zkAddress = "127.0.0.1:2181";
	private ZookeeperManager zookeeperManager = ZookeeperManager.getInstance();

	private JFrame frame;
	private JTree tree;
	private JTextPane dataPane;
	private JTextPane statPane;
	private JPopupMenu rootMenu;
	private JPopupMenu nodeMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowTwoDemo window = new WindowTwoDemo();
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
	public WindowTwoDemo() {
//		zookeeperManager = ZookeeperManager.getInstance();
//		zookeeperManager.connect(zkAddress);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new OnFrameWindowListener());
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(6, 6, 438, 266);
		frame.getContentPane().add(splitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setBounds(6, 6, 330, 91);
		panel.add(scrollPane_1);
		
		dataPane = new JTextPane();
		dataPane.setText("data");
		scrollPane_1.setViewportView(dataPane);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new OnUpdateButtonMouseListener(tree, dataPane, statPane));
		
		btnUpdate.setBounds(225, 100, 88, 29);
		btnUpdate.addMouseListener(new OnUpdateButtonMouseListener(tree, dataPane, statPane));
		panel.add(btnUpdate);
		
		JLabel lblStat = new JLabel("Stat");
		
		
		lblStat.setBounds(16, 109, 61, 16);
		panel.add(lblStat);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		scrollPane_2.setBounds(6, 137, 330, 119);
		panel.add(scrollPane_2);
		
		statPane = new JTextPane();
		statPane.setText("Stats");
		scrollPane_2.setViewportView(statPane);
		
		rootMenu = new JPopupMenu();
		JMenuItem refreshItem = new JMenuItem("Refesh");
		rootMenu.add(refreshItem);
		refreshItem.addMouseListener(new OnRefreshItemMouseListener(tree));
		rootMenu.addSeparator();
		JMenuItem propItem = new JMenuItem("Property");
		rootMenu.add(propItem);
		propItem.addMouseListener(new OnRootPropertyItemMouseListener(tree, statPane, zkAddress));
		
		
		nodeMenu = new JPopupMenu();
		JMenuItem nodeRefreshItem = new JMenuItem("Refresh");
		nodeRefreshItem.addMouseListener(new OnRefreshItemMouseListener(tree));
		nodeMenu.add(nodeRefreshItem);
		nodeMenu.addSeparator();
		
		JMenuItem nodeAddItem = new JMenuItem("Add");
		nodeAddItem.addMouseListener(new OnAddItemMouseListener(tree));
		nodeMenu.add(nodeAddItem);
		nodeMenu.addSeparator();
		
		JMenuItem nodeDeleteItem = new JMenuItem("Delete");
		nodeDeleteItem.addMouseListener(new OnDeleteItemMouseListener(tree, dataPane, statPane));
		nodeMenu.add(nodeDeleteItem);
		nodeMenu.addSeparator();
		
		JMenuItem nodePropertyItem = new JMenuItem("Property");
		nodeMenu.add(nodePropertyItem);
		
		initialJTree();
	}
	
	public void initialJTree(){
		tree.addTreeSelectionListener(new OnTreeSelectionListener(tree, dataPane, statPane));
		tree.addMouseListener(new OnTreeMouseListener(tree, rootMenu, nodeMenu));
		tree.addTreeWillExpandListener(new OnTreeWillExpandListener());
		//tree.addTreeExpansionListener(null);
	}
}
