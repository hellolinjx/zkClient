package zhangq.zkClient.ui.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.channels.NetworkChannel;
import java.util.List;

import org.apache.zookeeper.client.ConnectStringParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zhangq.zkClient.ui.bean.ZKNode;
import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class TestZKManager {
	
	private ZookeeperManager zkManager;
	private String conString = "127.0.0.1:2181";
	
	@Before
	public void connectZk(){
		zkManager = ZookeeperManager.getInstance();
		Boolean retBool = zkManager.connect(conString);
		if	(!retBool) 
			fail("Cannot connect to ZooKeeper!!");
	}
	
	@Test
	public void testGetChildren(){
		List<String> children = zkManager.getChildren("/");
		System.out.print(children.toString());
		
		ZKNode zkNode = zkManager.getZKNode("/helloP");
		System.out.println(zkNode.toString());
		try {
			String string = new String(zkNode.getData(), "UTF-8");
			System.out.println(string);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddNode(){
		ZKNode zkNode = new ZKNode("/helloT/djfk", "fd".getBytes(), null);
		zkManager.addNode(zkNode, null, 0);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@After
	public void closeZK(){
		zkManager.close();
	}

}
