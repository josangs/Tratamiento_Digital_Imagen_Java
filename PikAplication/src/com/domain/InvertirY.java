package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;


public class InvertirY {
	
	public BufferedImage aplicarInvertir(BufferedImage imgsel, JCheckBox cambiarBarrido){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			Color colorImagen = null;
			
			if(cambiarBarrido.isSelected()){
				for (int x = 0; x < imgsel.getWidth(); x++) {
					for (int y = 0; y < imgsel.getHeight(); y++) {
						colorImagen = new Color(imgsel.getRGB(x, y));
						bi.setRGB(x, (imgsel.getHeight()-1) - y, colorImagen.getRGB());
					}
				}
			}else{
				for (int x = 0; x < imgsel.getWidth(); x++) {
					for (int y = 0; y < imgsel.getHeight(); y++) {
						colorImagen = new Color(imgsel.getRGB(x, y));
						bi.setRGB((imgsel.getWidth()-1) - x, y, colorImagen.getRGB());
					}
				}
			}
		}
		return bi;
	}
	
	
}
