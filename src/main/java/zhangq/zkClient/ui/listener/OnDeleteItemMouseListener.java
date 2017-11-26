package zhangq.zkClient.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnDeleteItemMouseListener implements MouseListener{
	private JTree tree;
	private JTextPane dataPane;
	private JTextPane statPane;
	
	
	public OnDeleteItemMouseListener(JTree tree, JTextPane dataPane, JTextPane statPane){
		this.tree = tree;
		this.dataPane = dataPane;
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
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		ZKNode zkNode = (ZKNode) treeNode.getUserObject();
		Object[] options = { "OK ", "CANCEL " }; 
		int selectedIndex = JOptionPane.showOptionDialog(null, 
				"确定删除节点("+ zkNode.getPath()+")?", 
				"删除节点", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.WARNING_MESSAGE,null, options, options[0]);
		boolean retBool = false;
		if	(selectedIndex==0){
			retBool = ZookeeperManager.getInstance().deleteNode(zkNode.getPath(), -1);
		}else{
			return;
		}
		if	(!retBool){
			dataPane.setText("");
			statPane.setText("Delete Node Failed!");
			return;
		}else{
			dataPane.setText("");
			statPane.setText("");
			
			DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
			TreeNode parent = treeNode.getParent();
			treeModel.removeNodeFromParent(treeNode);
			treeModel.reload(parent);
			
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
