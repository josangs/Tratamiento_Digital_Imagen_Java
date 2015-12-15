package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class LoG {

	public BufferedImage aplicarLoG (BufferedImage imgsel){
		BufferedImage bi = null;
		
		float[] edgeLoG = {
				0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
				0.0f, -1.0f, -2.0f, -1.0f, 0.0f,
				-1.0f, -2.0f, 16.0f, -2.0f, -1.0f,
				0.0f, -1.0f, -2.0f, -1.0f, 0.0f,
				0.0f, 0.0f, -1.0f, 0.0f, 0.0f
		};
		
		//para theta = 1.4
		//Log(x,y) = -(1/pi*theta^4)*(1-((x^2+y^2)/2*theta^2))*e^(-(x^2+y^2)/2*theta^2);
		
		float[] edgeLoG14 = {
				0.0f, 0.0f, 3.0f, 2.0f, 2.0f, 2.0f, 3.0f, 0.0f, 0.0f,
				0.0f, 2.0f, 3.0f, 5.0f, 5.0f, 5.0f, 3.0f, 2.0f, 0.0f,
				3.0f, 3.0f, 5.0f, 3.0f, 0.0f, 3.0f, 5.0f, 3.0f, 3.0f,
				2.0f, 5.0f, 3.0f, -12.0f, -23.0f, -12.0f, 3.0f, 5.0f, 2.0f,
				2.0f, 5.0f, 0.0f, -23.0f, -40.0f, -23.0f, 0.0f, 5.0f, 2.0f,
				2.0f, 5.0f, 3.0f, -12.0f, -23.0f, -12.0f, 3.0f, 5.0f, 2.0f,
				3.0f, 3.0f, 5.0f, 3.0f, 0.0f, 3.0f, 5.0f, 3.0f, 3.0f,
				0.0f, 2.0f, 3.0f, 5.0f, 5.0f, 5.0f, 3.0f, 2.0f, 0.0f,
				0.0f, 0.0f, 3.0f, 2.0f, 2.0f, 2.0f, 3.0f, 0.0f, 0.0f
		};
		
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			
			ConvolveOp op = new ConvolveOp(new Kernel(9, 9, edgeLoG14));
			op.filter(imgsel, bi);
		}
		
		return bi;
	}
}
