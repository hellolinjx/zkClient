package zhangq.zkClient.ui.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import zhangq.zkClient.ui.zkManager.ZookeeperManager;

public class OnFrameWindowListener implements WindowListener{

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		ZookeeperManager.getInstance().close();
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
