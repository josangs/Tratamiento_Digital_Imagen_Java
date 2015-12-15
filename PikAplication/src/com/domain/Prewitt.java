package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JCheckBox;

public class Prewitt {
	
	public BufferedImage aplicarPrewitt(BufferedImage imgsel, JCheckBox cambiarBarrido){
		BufferedImage bi= null;
		
		float[] edgePrewittV= {
				-1.0f, -1.0f, -1.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f
		};
		float[] edgePrewittH={
			-1.0f, 0.0f, 1.0f,
			-1.0f, 0.0f, 1.0f,
			-1.0f, 0.0f, 1.0f
		};
		
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			
			if(cambiarBarrido.isSelected()){
				ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgePrewittV));
				op.filter(imgsel, bi);
			}else{
				ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgePrewittH));
				op.filter(imgsel, bi);
			}
		}
		return bi;
	}

}
