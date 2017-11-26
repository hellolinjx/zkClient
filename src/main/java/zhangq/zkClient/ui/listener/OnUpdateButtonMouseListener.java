package zhangq.zkClient.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.PrivilegedActionException;

import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnUpdateButtonMouseListener implements MouseListener{
	private JTextPane dataPane;
	private JTextPane statPane;
	private JTree tree;
	
	public OnUpdateButtonMouseListener(JTree tree, JTextPane dataPane, JTextPane statPane){
		this.tree = tree;
		this.dataPane = dataPane;
		this.statPane = statPane;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		ZKNode zkNode = (ZKNode) treeNode.getUserObject();
		String dataStr = null;
		try {
			dataStr = new String(zkNode.getData(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			statPane.setText(e1.getMessage());
			return;
		}
		
		String dataTxt = dataPane.getText();
		if	(!dataTxt.equals(dataStr)){
			zkNode.setData(dataTxt.getBytes());
			ZookeeperManager.getInstance().updateZKNode(zkNode);
		}
		
		statPane.setText(ZKClientUtil.statToString(zkNode.getStat()));
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
