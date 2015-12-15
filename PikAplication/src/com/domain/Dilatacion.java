package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Dilatacion {
	
	public BufferedImage aplicarDilation(BufferedImage imgsel){
		BufferedImage bi= null;
		
		float[] edgeDilation= {
				0.0f,1.0f,0.0f,
				1.0f,1.0f,1.0f,
				0.0f,1.0f,0.0f
		};
		
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgeDilation));
			op.filter(imgsel, bi);
			
		}
		return bi;
	}


}
