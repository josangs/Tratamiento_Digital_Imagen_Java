package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JSlider;


public class FiltroSepia{
	
	public BufferedImage aplicarSepia(BufferedImage imgsel, JSlider spromedio) {
		// TODO Auto-generated method stub
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			//Definimos la profundidad del tono sepia, 20 esta bien
			//0 nos produce una imagen en blanco y negro
			int sepiaDepth = 20;
			
			int w = imgsel.getWidth();
			int h = imgsel.getHeight();
			
			@SuppressWarnings("unused")
			WritableRaster raster = bi.getRaster();
			
			//Necesitamos 3 enteros r,g,b para los pixeles.
			int[] pixels = new int[w*h*3];
			imgsel.getRaster().getPixels(0, 0, w, h, pixels);
			
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					
					int rgb = imgsel.getRGB(x, y);
					Color color = new Color(rgb,true);
					int r = color.getRed();
					int g = color.getGreen();
					int b = color.getBlue();
					int gry = (r+g+b)/3;
					
					r = g = b = gry;
					r = r + (sepiaDepth * 2);
					g = g + sepiaDepth;
					
					if (r > 255){
						r = 255;
					}
					if (g > 255){
						g = 255;
					}
					if (b > 255){
						b = 255;
					}
					
					//Darken blue color to increase sepia effect
					b -= spromedio.getValue();
					
					//normalize if out of bounds
					if(b < 0){
						b = 0;
					}
					if (b > 255){
						b = 255;
					}
					
					color = new Color(r,g,b, color.getAlpha());
					bi.setRGB(x, y, color.getRGB());
					
				}
			}
		}
		return bi;
	}
	
	
}
