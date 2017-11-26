package zhangq.zkClient.ui.listener;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import zhangq.zkClient.ui.zkManager.ZookeeperManager;


public class OnTreeMouseListener extends MouseAdapter{
	private static final int NUM_ITEM_REFRESH = 0;
	private static final int NUM_ITEM_ADD = 2;
	private static final int NUM_ITEM_DELETE = 4;
	private static final int NUM_ITEM_PROP = 6;
	JTree tree;
	JPopupMenu rootMenu;
	JPopupMenu nodeMenu;
	
	public OnTreeMouseListener(JTree tree, JPopupMenu rootMenu, JPopupMenu nodeMenu) {
		// TODO Auto-generated constructor stub
		this.tree = tree;
		this.rootMenu = rootMenu;
		this.nodeMenu = nodeMenu;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if	(SwingUtilities.isRightMouseButton (e)){
			TreePath paths = tree.getPathForLocation(e.getX(), e.getY());
			if	(null==paths) 
				return;
			
			tree.setSelectionPath(paths);
			
			// 根节点
			if	(paths.getPathCount()==1){
				JMenuItem propertyItem = (JMenuItem) rootMenu.getComponent(NUM_ITEM_REFRESH);
				// 没有连接到server
    			if	(!ZookeeperManager.getInstance().isConnected()){
    				propertyItem.setVisible(false);
    			}else{
    				propertyItem.setVisible(true);
    			}
    
    			rootMenu.show(tree, e.getX() , e.getY() );
			}else{
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) paths.getLastPathComponent();
				JMenuItem deleteItem = (JMenuItem) nodeMenu.getComponent(NUM_ITEM_DELETE);
				JMenuItem refreshItem = (JMenuItem) nodeMenu.getComponent(NUM_ITEM_REFRESH);
				// 如果不是根节点
				if	(treeNode.getChildCount()!=0){
					deleteItem.setVisible(false);;
				}else{
					deleteItem.setVisible(true);
				}
				// 如果没有连接到server
				if	(!ZookeeperManager.getInstance().isConnected()){
					refreshItem.setVisible(false);
				}else{
					refreshItem.setVisible(true);
				}
    			System.out.println(treeNode.getUserObject());
				nodeMenu.show(tree, e.getX() , e.getY() );
			}
		}
	}
}
