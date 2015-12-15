package com.domain;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ColocarMascara {

	public ColocarMascara() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
		
	}
}


