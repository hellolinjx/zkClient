package zhangq.zkClient.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;

public class OnRefreshItemMouseListener implements MouseListener{
	
	private static JTree tree;
	
	public OnRefreshItemMouseListener(JTree tree){
		this.tree = tree;
	}
	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		onRefreshNode();
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void onRefreshNode(){
		if (null==tree)
			return;
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		ZKNode zkNode = (ZKNode) treeNode.getUserObject();
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeNode.getParent();
		
		if	(null != parent){
			int childIndex = parent.getIndex(treeNode);
			treeModel.removeNodeFromParent(treeNode);
			parent.insert(ZKClientUtil.createTree(zkNode.getPath(), 2), childIndex);
		}else{
			treeModel.setRoot(ZKClientUtil.createTree(zkNode.getPath(), 2));	
		}
		
		treeModel.reload();
	}

}
