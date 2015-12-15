package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Suavizar {

	public BufferedImage aplicarSuavizar (BufferedImage imgsel){
		BufferedImage bi = null;
		
		float ninth = 1.0f/9.0f;
		
		float[] blurKernel = {
				ninth, ninth, ninth,
				ninth, ninth, ninth,
				ninth, ninth, ninth,
		};
		
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			ConvolveOp op = new ConvolveOp(new Kernel(3, 3, blurKernel));
			op.filter(imgsel, bi);
		}
		
		return bi;
	}
}
