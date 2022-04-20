package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



import view.Window;

public class Controller implements ActionListener {

	
	Window window;
	
	
	
	
	public Controller(Window window) {
		this.window = window;
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton)e.getSource();
			if (btn == window.getBtnAdd()) {
				window.addProducts();
			}else if (btn == window.getBtnDlt()) {
				window.DeleteProducts();
			}else if(btn == window.getBtnBuy()) {
				window.showBuy();
			}
		}
		
	}

}
