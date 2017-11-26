package zhangq.zkClient.ui.listener;

import java.nio.file.Path;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;

public class OnTreeWillExpandListener implements TreeWillExpandListener{

	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub
		TreePath path= event.getPath();
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		ZKNode zkNode = (ZKNode) treeNode.getUserObject();
		if	(zkNode.isExpanded())
			return;
		int numChildren = treeNode.getChildCount();
		for	(int i=0;i<numChildren; i++){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) treeNode.getChildAt(i);
			ZKNode zkChild = (ZKNode) child.getUserObject();
			treeNode.remove(child);
			treeNode.add(ZKClientUtil.createTree(zkChild.getPath(), 1));
		}
		zkNode.setExpanded(true);
//		JTree tree = (JTree) event.getSource();;
//		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
//		treeModel.reload(treeNode);
		return;
	}

	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

	

}
