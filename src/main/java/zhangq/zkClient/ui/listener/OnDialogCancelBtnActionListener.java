package zhangq.zkClient.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;


public class OnDialogCancelBtnActionListener implements ActionListener{
	public JDialog dialog;
	
	public OnDialogCancelBtnActionListener(JDialog dialog){
		this.dialog = dialog;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dialog.dispose();
	}

}
