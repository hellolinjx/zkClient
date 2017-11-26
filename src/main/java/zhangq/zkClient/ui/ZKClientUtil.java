package zhangq.zkClient.ui;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class ZKClientUtil {
	public static String statToString(Stat stat){
		String statStr = "";
		if	(null==stat){
			return "No Stat Info!";
		}
		statStr += ("cZxid = " + stat.getCzxid() + "\n");
		statStr += ("cTime = " + new java.util.Date(stat.getCtime()).toString() + "\n");
		statStr += ("mZxid = " + stat.getMzxid() + "\n");
		statStr += ("mTime = " + new java.util.Date(stat.getMtime()).toString()+ "\n");
		statStr += ("pZxid = " + stat.getPzxid() + "\n");
		statStr += ("cVersion = " + stat.getCversion() + "\n");
		statStr += ("dataVersion = " + stat.getVersion()+ "\n");
		statStr += ("aclVersion = " + stat.getAversion() + "\n");
		statStr += ("ephemralOwner = " + stat.getEphemeralOwner() + "\n");
		statStr += ("dataLength = " + stat.getDataLength() + "\n");
		statStr += ("numChildern = " + stat.getNumChildren() + "\n");
		return statStr;
	}
	
	// 树的深度遍历
	public static DefaultMutableTreeNode createTree(String zkNodePath, int depth){
		ZKNode zkNode = new ZKNode(zkNodePath, null, null);
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(zkNode);
		if	(--depth < 0)
			return treeNode;
		
		List<String> children = ZookeeperManager.getInstance().getChildren(zkNodePath);
		if	(children==null) return treeNode;

		zkNodePath +=  zkNodePath.length()>1 ? "/" : "";
		for (String child : children){
			treeNode.add(createTree(zkNodePath + child, depth));
		}
		if	(depth > 0)
			zkNode.setExpanded(true);
		return treeNode;
	}
	
	public static List<ACL> stringToAcls(String aclStr){
		List<ACL> listACL = null;
		
		return Ids.OPEN_ACL_UNSAFE;
	}
}
