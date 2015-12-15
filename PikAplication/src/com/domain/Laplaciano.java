package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Laplaciano {

	public BufferedImage aplicarLaplaciano (BufferedImage imgsel){
		BufferedImage bisuavizada = null;
		BufferedImage bi = null;
		
		Suavizar blur = new Suavizar();
		
		float[] edgeKernel = {
				0.0f, -1.0f, 0.0f,
				-1.0f, 4.0f, -1.0f,
				0.0f, -1.0f, 0.0f
		}; 
		
		if(imgsel != null){
			bisuavizada = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			
			bisuavizada = blur.aplicarSuavizar(imgsel);
			
			bi = new BufferedImage(bisuavizada.getWidth(), bisuavizada.getHeight(), bisuavizada.getType());
			ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgeKernel));
			op.filter(bisuavizada, bi);
			
		}
		
		return bi;
	}
}
