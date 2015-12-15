package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Negativo{
	
	public BufferedImage aplicarNegativo(BufferedImage imgsel){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					Color c = new Color(imgsel.getRGB(x, y));
					int r = c.getRed();
					int g = c.getGreen();
					int b = c.getBlue();
					r = 255-r;
					g = 255-g;
					b = 255-b;
					bi.setRGB(x, y, new Color(r,g,b).getRGB());
				}
				
			}
		}
		return bi;
	}
	
	
}
