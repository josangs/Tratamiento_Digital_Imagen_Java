package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;
import javax.swing.JSlider;


public class DeteccionBordes{
	
	public BufferedImage aplicarBordePorFrontera(BufferedImage imgsel, JCheckBox cambiarBarrido, JSlider spromedio){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			if(cambiarBarrido.isSelected()){
				for (int x = 0; x < imgsel.getWidth(); x++) {
					for (int y = imgsel.getHeight() - 1 ; y > 0; y--) {
						int diferencia1 = new Color(imgsel.getRGB(x, y)).getRed() - 
								new Color(imgsel.getRGB(x, y-1)).getRed();
						int diferencia2 = new Color(imgsel.getRGB(x, y)).getGreen() -
								new Color(imgsel.getRGB(x, y-1)).getGreen();
						int diferencia3 = new Color(imgsel.getRGB(x, y)).getBlue() -
								new Color(imgsel.getRGB(x, y-1)).getBlue();
						
						if(diferencia1 < 0){
							diferencia1 *= -1;
						}
						if(diferencia2 < 0){
							diferencia2 *= -1;
						}
						if(diferencia3 < 0){
							diferencia3 *= -1;
						}
						
						int cantidad = spromedio.getValue();
						if(diferencia1 < cantidad && diferencia2 < cantidad && diferencia3 < cantidad){
							bi.setRGB(x, y, new Color(255,255,255).getRGB());
						}else{
							bi.setRGB(x, y, imgsel.getRGB(x, y));
						}
					}
				}
				for (int x = 0; x < imgsel.getWidth(); x++){
					for(int y = 0; y < imgsel.getHeight() - 1; y++){
					int diferencia1 = new Color(imgsel.getRGB(x, y)).getRed() - 
							new Color(imgsel.getRGB(x, y + 1)).getRed();
					int diferencia2 = new Color(imgsel.getRGB(x, y)).getGreen() -
							new Color(imgsel.getRGB(x, y + 1)).getGreen();
					int diferencia3 = new Color(imgsel.getRGB(x, y)).getBlue() -
							new Color(imgsel.getRGB(x, y + 1)).getBlue();
					
					if(diferencia1 < 0){
						diferencia1 *= -1;
					}
					if(diferencia2 < 0){
						diferencia2 *= -1;
					}
					if(diferencia3 < 0){
						diferencia3 *= -1;
					}
					
					int cantidad = spromedio.getValue();
					if(diferencia1 < cantidad && diferencia2 < cantidad && diferencia3 < cantidad){
						bi.setRGB(x, y, new Color(255,255,255).getRGB());
					}else{
						bi.setRGB(x, y, imgsel.getRGB(x, y));
					}
				}
			}
		}else{
			for (int x = imgsel.getWidth() - 1; x > 0; x--){
				for(int y = 0; y < imgsel.getHeight(); y++){
				int diferencia1 = new Color(imgsel.getRGB(x, y)).getRed() - 
						new Color(imgsel.getRGB(x - 1, y)).getRed();
				int diferencia2 = new Color(imgsel.getRGB(x, y)).getGreen() -
						new Color(imgsel.getRGB(x - 1, y)).getGreen();
				int diferencia3 = new Color(imgsel.getRGB(x, y)).getBlue() -
						new Color(imgsel.getRGB(x - 1, y)).getBlue();
				
				if(diferencia1 < 0){
					diferencia1 *= -1;
				}
				if(diferencia2 < 0){
					diferencia2 *= -1;
				}
				if(diferencia3 < 0){
					diferencia3 *= -1;
				}
				
				int cantidad = spromedio.getValue();
				if(diferencia1 < cantidad && diferencia2 < cantidad && diferencia3 < cantidad){
					bi.setRGB(x, y, new Color(255,255,255).getRGB());
				}else{
					bi.setRGB(x, y, imgsel.getRGB(x, y));
				}
			}
		}
			for (int x = 0; x < imgsel.getWidth() - 1; x++){
				for(int y = 0; y < imgsel.getHeight(); y++){
				int diferencia1 = new Color(imgsel.getRGB(x, y)).getRed() - 
						new Color(imgsel.getRGB(x + 1, y)).getRed();
				int diferencia2 = new Color(imgsel.getRGB(x, y)).getGreen() -
						new Color(imgsel.getRGB(x + 1, y)).getGreen();
				int diferencia3 = new Color(imgsel.getRGB(x, y)).getBlue() -
						new Color(imgsel.getRGB(x + 1, y)).getBlue();
				
				if(diferencia1 < 0){
					diferencia1 *= -1;
				}
				if(diferencia2 < 0){
					diferencia2 *= -1;
				}
				if(diferencia3 < 0){
					diferencia3 *= -1;
				}
				
				int cantidad = spromedio.getValue();
				if(diferencia1 < cantidad && diferencia2 < cantidad && diferencia3 < cantidad){
					bi.setRGB(x, y, new Color(255,255,255).getRGB());
				}else{
					bi.setRGB(x, y, imgsel.getRGB(x, y));
				}
			}
		}
	}
	}
	return bi;
	}

	
	
}
