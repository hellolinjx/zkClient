package zhangq.zkClient.ui.listener;

import java.io.UnsupportedEncodingException;

import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnTreeSelectionListener implements TreeSelectionListener{
	JTree tree;
	JTextPane dataTxtPane;
	JTextPane statTxtPane;
	public OnTreeSelectionListener(JTree tree, JTextPane dataTxtPane, JTextPane statTxtPane){
		this.tree = tree;
		this.dataTxtPane = dataTxtPane;
		this.statTxtPane = statTxtPane;
	}
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!ZookeeperManager.getInstance().isConnected()){
			return;
		}
		String zkPath = "";
		DefaultMutableTreeNode treeNode =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if	(null==treeNode)
			return;
		ZKNode zkTreeNode = (ZKNode)treeNode.getUserObject();
		zkPath = zkTreeNode.getPath();
		if	(zkTreeNode.getStat()==null){
			ZKNode zkNode = ZookeeperManager.getInstance().getZKNode(zkPath);
			zkTreeNode.setData(zkNode.getData());
			zkTreeNode.setStat(zkNode.getStat());
		}
		String zkDataStr;
		try {
			zkDataStr = new String(zkTreeNode.getData(), "UTF-8");
			dataTxtPane.setText(zkDataStr);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			dataTxtPane.setText(zkTreeNode.getData().toString());
		}
		
		statTxtPane.setText(ZKClientUtil.statToString(zkTreeNode.getStat()));
	}
}
