package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Intensificar {

	public BufferedImage aplicarIntensificar (BufferedImage imgsel){
		BufferedImage bi = null;
		
		float[] sharpKernel = {
				0.0f, -1.0f, 0.0f,
				-1.0f, 5.0f, -1.0f,
				0.0f, -1.0f, 0.0f
		};
		
		if (imgsel != null) {
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			ConvolveOp op = new ConvolveOp(new Kernel(3, 3, sharpKernel));
			op.filter(imgsel, bi);
			
		}
		
		return bi;
	}
}
