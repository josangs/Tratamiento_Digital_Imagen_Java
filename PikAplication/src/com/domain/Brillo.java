package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JSlider;

public class Brillo{
	
	/**
	 * Filtro de control de contraste, aumentamos el nivel de brillo desde 0 a 255.
	 * 
	 * @param imgsel
	 * @param spromedio
	 * @return bi
	 */
	
	
	public BufferedImage aplicarBrillo(BufferedImage imgsel, JSlider spromedio){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					Color c = new Color(imgsel.getRGB(x, y));
					int r = c.getRed();
					int g = c.getGreen();
					int b = c.getBlue();
					r+= spromedio.getValue();
					g+= spromedio.getValue();
					b+= spromedio.getValue();
					if (r>255) {
						r=255;
					}
					if(g>255){
						g=255;
					}
					if(b>255){
						b=255;
					}
					if(r<0){
						r=0;
					}
					if(g<0){
						g=0;
					}
					if(b<0){
						b=0;
					}
					
					bi.setRGB(x, y, new Color(r,g,b).getRGB());
				}
			}
		}
		return bi;
	}

}
