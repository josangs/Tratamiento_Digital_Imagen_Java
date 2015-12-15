package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JCheckBox;

public class Roberts {
	
	public BufferedImage aplicarRoberts(BufferedImage imgsel, JCheckBox cambiarBarrido){
		BufferedImage bi = null;
		
		float[] edgeRobertsH = {
				-1.0f, 0.0f,
				0.0f, 1.0f
		};
		float[] edgeRobertsV = {
				0.0f, -1.0f,
				1.0f, 0.0f
		};
		
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			
			if(cambiarBarrido.isSelected()){
				ConvolveOp op = new ConvolveOp(new Kernel(2, 2, edgeRobertsV));
				op.filter(imgsel, bi);
			}else{
				ConvolveOp op = new ConvolveOp(new Kernel(2, 2, edgeRobertsH));
				op.filter(imgsel, bi);
			}
		}
		return bi;
	}

}
