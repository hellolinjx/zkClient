package zhangq.zkClient.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.swing.AddNodeDialog;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnAddDialogOkBtnActionListener implements ActionListener{
	private JDialog dialog;
	private JComboBox comboBox;
	private JTextPane dataTxtPane;
	private JTextPane aclTxtPane;
	private JTextPane namePane;
	private JTree tree;
	public OnAddDialogOkBtnActionListener(AddNodeDialog dialog, JComboBox comboBox, 
											JTextPane dataTxtPane, 
											JTextPane aclTxtPane,
											JTextPane namePane){
		this.dialog = dialog;
		this.comboBox = comboBox;
		this.dataTxtPane = dataTxtPane;
		this.aclTxtPane = aclTxtPane;
		this.namePane = namePane;
		this.tree = dialog.getTree();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("selected : " + comboBox.getSelectedIndex() + " ; ");
		System.out.println("data : " + dataTxtPane.getText());
		System.out.println("acl : " + aclTxtPane.getText());
		
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		ZKNode zkNode = (ZKNode) treeNode.getUserObject();
		String parentPath = zkNode.getPath();
		if (parentPath.length() > 1) {
			parentPath += "/";
		}
		String path = parentPath + namePane.getText();
		ZKNode addZKNode = new ZKNode(path, dataTxtPane.getText().getBytes(), null);
		if (ZookeeperManager.getInstance().addNode(addZKNode, aclTxtPane.getText(), comboBox.getSelectedIndex())){
			OnRefreshItemMouseListener.onRefreshNode();
		}
		
		dialog.dispose();
	}

	

}
