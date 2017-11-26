package zhangq.zkClient.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;

import zhangq.zkClient.ui.swing.AddNodeDialog;

public class OnAddItemMouseListener implements MouseListener{
	JTree tree;
	public OnAddItemMouseListener(JTree tree){
		this.tree = tree;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		AddNodeDialog.main(tree);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
