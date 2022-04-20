package main;

import java.awt.EventQueue;

import control.Controller;
import view.Window;


public class RunListaCompra {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Window w = new Window();
				
				Controller ctrl = new Controller(w);
				
				w.setControl(ctrl);
				
				w.hacerVisible();
				
			}
		});

	}

}

