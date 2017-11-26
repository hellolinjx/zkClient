package zhangq.zkClient.ui.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import org.w3c.dom.events.EventException;
 
public class TreeDemo {
    public static void main(String[] args) {
 
        // 创建没有父节点和子节点、但允许有子节点的树节点，并使用指定的用户对象对它进行初始化。
        // public DefaultMutableTreeNode(Object userObject)
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("软件部");
        node1.add(new DefaultMutableTreeNode(new User("小花")));
        node1.add(new DefaultMutableTreeNode(new User("小虎")));
        node1.add(new DefaultMutableTreeNode(new User("小龙")));
 
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("销售部");
        node2.add(new DefaultMutableTreeNode(new User("小叶")));
        node2.add(new DefaultMutableTreeNode(new User("小雯")));
        node2.add(new DefaultMutableTreeNode(new User("小夏")));
 
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("职员管理"); 
        top.add(new DefaultMutableTreeNode(new User("总经理")));
        top.add(node1);
        top.add(node2);
        final JTree tree = new JTree(top);
        JFrame f = new JFrame("JTreeDemo");
        f.add(tree);
        f.setSize(500, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tree.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		if	(SwingUtilities.isRightMouseButton (e))
        			JOptionPane.showMessageDialog(null, "PLAIN_MESSAGE", "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
        	}
		});
        
        
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
			
			public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
				// TODO Auto-generated method stub
				TreePath path = event.getPath();
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)path.getLastPathComponent();
				treeNode.add(new DefaultMutableTreeNode(new User("zhangq")));
				System.out.println(path);
			}
			
			public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "PLAIN_MESSAGE", "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
			}
		});
        // 添加选择事件
        tree.addTreeSelectionListener(new TreeSelectionListener() {
 
        	public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
 
                if (node == null)
                    return;
 
                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    User user = (User) object;
                    System.out.println("你选择了：" + user.toString());
                }
 
            }
        });
    }
}
 
class User {
    private String name;
 
    public User(String n) {
        name = n;
    }
 
    // 重点在toString，节点的显示文本就是toString
    public String toString() {
        return name;
    }
}
