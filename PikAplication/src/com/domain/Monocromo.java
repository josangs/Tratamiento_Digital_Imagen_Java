package com.domain;


import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JSlider;


public class Monocromo{
	
	public BufferedImage aplicarMonocromo(BufferedImage imgsel, JSlider spromedio){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(),imgsel.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					Color c = new Color(imgsel.getRGB(x, y));
					int r = c.getRed();
					int g = c.getGreen();
					int b = c.getBlue();
					if(r<spromedio.getValue()){
						r=0;
					}else{
						r=255;
					}
					if(g<spromedio.getValue()){
						g=0;
					}else{
						g=255;
					}
					if(b<spromedio.getValue()){
						b=0;
					}else{
						b=255;
					}
					bi.setRGB(x, y, new Color(r,g,b).getRGB());
				}
			}
		}
		return bi;
	}
	
}