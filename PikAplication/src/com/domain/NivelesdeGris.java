package com.domain;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;


public class NivelesdeGris{
	
	public BufferedImage aplicarNivelesGris(BufferedImage imgsel){
		BufferedImage bi = null;
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			op.filter(imgsel, bi);
		}
		return bi;
	}
	
}
