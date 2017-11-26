package zhangq.zkClient.ui.bean;

import java.util.Arrays;

import org.apache.zookeeper.data.Stat;
import org.omg.CORBA.PRIVATE_MEMBER;

public class ZKNode {
	private String name;
	
	private Boolean expanded = false;
	
	private String path;
	
	private byte[] data;
	
	private Stat stat;
	
	public ZKNode(String path, byte[] data, Stat stat){
		this.path = path;
		this.data = data;
		this.stat = stat;
		resetName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		resetName();
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}
	
	private void resetName(){
		if	(null != path){
			if	(path.equals("/")){
					name = "ZooKeeper";
					return ;
			}
			int index = path.lastIndexOf("/");
			if	(index+1 < path.length())
				name = path.substring(index+1);
		}
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
	
}
