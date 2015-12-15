package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JCheckBox;

public class Sobel {

	public BufferedImage aplicarSobel (BufferedImage imgsel, JCheckBox cambiarBarrido){
		BufferedImage bi = null;
		
		float[] edgeSobelV = {
				-1.0f, -2.0f, -1.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 2.0f, 1.0f
		};
		float[] edgeSobelH = {
				-1.0f, 0.0f, 1.0f,
				-2.0f, 0.0f, 2.0f,
				-1.0f, 0.0f, 1.0f
		};
		
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			if(cambiarBarrido.isSelected()){
				ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgeSobelV), ConvolveOp.EDGE_ZERO_FILL,null);
				op.filter(imgsel, bi);
			}else{
				ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edgeSobelH), ConvolveOp.EDGE_ZERO_FILL, null);
				op.filter(imgsel, bi);
			}
		}
		return bi;
	}
	

}
