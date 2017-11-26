package zhangq.zkClient.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnRootPropertyItemMouseListener implements MouseListener{
	private JTree tree;
	private String zkAddress;
	private JTextPane statPane;
	
	public OnRootPropertyItemMouseListener(JTree tree, JTextPane statPane, String zkAddress){
		this.tree = tree;
		this.zkAddress = zkAddress;
		this.statPane = statPane;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		zkAddress = JOptionPane.showInputDialog(null,
                "Input ZooKeeper server address:",
                zkAddress);
		
		ZookeeperManager.getInstance().close();
		boolean bRet = ZookeeperManager.getInstance().connect(zkAddress);
		if	(!bRet){
			statPane.setText("Connect to Server(" + zkAddress + ") failed!");
			return ;
		}
		DefaultTreeModel treeModel = new DefaultTreeModel(ZKClientUtil.createTree("/", 2));
		tree.setModel(treeModel);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
