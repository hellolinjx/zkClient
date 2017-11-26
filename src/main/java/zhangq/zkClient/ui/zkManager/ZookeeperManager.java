package zhangq.zkClient.ui.zkManager;

import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import zhangq.zkClient.ui.ZKClientUtil;
import zhangq.zkClient.ui.bean.ZKNode;

public class ZookeeperManager {
	private static Logger logger = Logger.getLogger(ZookeeperManager.class);
	
	private String connectStr = null;
	private boolean connected = false;
	private static ZookeeperManager instance = null;
	
	private ZooKeeper zk = null;
	
	private CountDownLatch latch;
	
 	
	private class SimpleWatcher implements Watcher{

		public void process(WatchedEvent arg0) {
			// TODO Auto-generated method stub
			logger.info("Receive watched event : " + arg0);
			if	(KeeperState.SyncConnected==arg0.getState()){
				connected = true;
			}
			latch.countDown();
		}
		
	}
	
	private ZookeeperManager() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean isConnected(){
		return connected;
	}
	
	public static ZookeeperManager getInstance(){
		if	(instance == null){
			synchronized(ZookeeperManager.class){
				if	(instance == null)
					instance = new ZookeeperManager();
			}
		}
		return instance;
	}
  	
	@SuppressWarnings("finally")
	public Boolean  connect(String connectString){
		latch = new CountDownLatch(1);
		try {
			zk = new ZooKeeper(connectString, 5000, new SimpleWatcher());
			latch.await(5000, TimeUnit.MILLISECONDS);
		}finally{
			
			return connected;
		}
	}
	
	public ZKNode getZKNode(String path) {
		ZKNode zkNode = null;
		byte[] datas = null;
		Stat stat = new Stat();
		try {
			datas = zk.getData(path, false, stat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}finally{
			zkNode = new ZKNode(path, datas, stat);
			return zkNode;
		}
	}
	
	public void updateZKNode(ZKNode newZKNode){
		try {
			Stat stat = zk.setData(newZKNode.getPath(), newZKNode.getData(), -1);
			newZKNode.setStat(stat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
	}
	
	public List<String> getChildren(String path){
		List<String> children = null;
		try {
			children = zk.getChildren(path, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return children;
		}
	}
	
	public boolean addNode(ZKNode zkNode, String aclStr, int iCreateMode){
		List<ACL> listAcl = ZKClientUtil.stringToAcls(aclStr);
		CreateMode createMode;
		switch (iCreateMode){
		case 0:
			createMode = CreateMode.PERSISTENT;
			break;
		case 1:
			createMode = CreateMode.PERSISTENT_SEQUENTIAL;
			break;
		case 2:
			createMode = CreateMode.EPHEMERAL;
			break;
		case 3:
			createMode = CreateMode.EPHEMERAL_SEQUENTIAL;
			break;
		default:
			createMode = CreateMode.PERSISTENT;
		}
		
		try {
			zk.create(zkNode.getPath(), zkNode.getData(), listAcl, createMode);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteNode(String path, int dVersion){
		try {
			zk.delete(path, dVersion);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void close(){
		try {
			if (zk != null)
				zk.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
	}
	
}
