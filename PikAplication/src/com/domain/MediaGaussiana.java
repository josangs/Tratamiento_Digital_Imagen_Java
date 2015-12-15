package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class MediaGaussiana {
	
	public BufferedImage aplicarMedia(BufferedImage imgsel){
		BufferedImage bi = null;
		//BufferedImage gris = null;
		
		float[] edgeMedia = {
				1/273f, 4/273f , 7/273f, 4/273f, 1/273f,
				4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
				7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
				4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
				1/273f, 4/273f, 7/273f, 4/273f, 1/173f
		};
		
		if (imgsel!=null) {
			//gris = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			//NivelesdeGris ng = new NivelesdeGris();
			//gris = ng.aplicarNivelesGris(imgsel);
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			ConvolveOp op = new ConvolveOp(new Kernel(5, 5, edgeMedia));
			op.filter(imgsel, bi);
		}
		return bi;
	}
}
