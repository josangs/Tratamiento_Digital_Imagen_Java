package com.domain;

import java.awt.image.BufferedImage;

public class Erosion {
	
	public BufferedImage aplicarErode(BufferedImage imgsel){
		
		BufferedImage bi = null;
		
		bi = new BufferedImage( imgsel.getWidth(),imgsel.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		
		final int BLACK = 0xFF000000;
		final int WHITE = 0xFFFFFFFF;
		
		for (int x = 1; x < imgsel.getWidth() - 1 ; x++) {
			for (int y = 1; y < imgsel.getHeight() - 1 ; y++) {
				if (imgsel.getRGB(x - 1, y) != WHITE 
						|| imgsel.getRGB(x + 1, y) != WHITE 
						|| imgsel.getRGB(x, y - 1) != WHITE
						|| imgsel.getRGB(x, y + 1) != WHITE) {
					//Si uno de los píxeles vecinos es negro, pone este negro también 
					bi.setRGB(x, y, BLACK);
				}else{
					bi.setRGB(x, y, WHITE);
				}
			}
		}
	return bi;
	}
}
