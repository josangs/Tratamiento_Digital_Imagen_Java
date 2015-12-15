package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;

public class Aclarado {
	
	/**
	 * Filtro de aclarado aplica una trasformación cuadrática, para que la imagen tenga más brillo.
	 * 
	 * @param  imgsel.
	 * @return bi.
	 */

	public BufferedImage aplicarAclarado (BufferedImage imgsel){
		BufferedImage bi = null;
		
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			//Define una tabla de busqueda para aumentar la brillantez de una imagen usando una
			//transformacion raiz cuadratica.
			short[] rootBrighten = new short[256];
			
			for (int i = 0; i < 256; i++) {
				rootBrighten[i] = (short) (Math.sqrt((double) i / 255.0 )* 255.0);
			}
			
			LookupTable table = new ShortLookupTable(0, rootBrighten);
			
			//crea una operación de busqueda apartir de la tabla 
			LookupOp op = new LookupOp(table, null);
			
			op.filter(imgsel, bi);
			
		}
		
		return bi;
	}
}
