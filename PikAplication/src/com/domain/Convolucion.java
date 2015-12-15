package com.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Convolucion {
	//En los bordes que se salen de la imagen no se aplica el filtro
	public static final int SIN_BORDES = ConvolveOp.EDGE_NO_OP;
	//En bordes que se salen de la imagen se evaluan como si tuviesen
	//valor 0.
	public static final int BORDES_0 = ConvolveOp.EDGE_ZERO_FILL;
	
	/**
	 * Aplica una operacion de convoluci�n sobre la imagen pasada como 
	 * argumento, utilizando la matriz tambi�n pasada como argumento.
	 * 
	 * @param filtro
	 * @param imagen
	 * @param tratBordes
	 * @return imagen resultante de aplicar el filtro, null en caso de fallo
	 * o argumentos incorrectos.
	 */

	public Convolucion() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static BufferedImage aplicarFiltro(float filtro[][], BufferedImage imagen, int tratBordes ){
		BufferedImage res = null;
		if (imagen == null) throw new IllegalArgumentException("La imagen no puede ser nula");
	    if (filtro == null || filtro.length==0) throw new IllegalArgumentException("Debe pasarse alg�n filtro v�lido");
	    if (tratBordes !=SIN_BORDES || tratBordes !=BORDES_0){
	    	tratBordes = SIN_BORDES;
	    }
	    int width = filtro.length;
	    int height = filtro[0].length;
	    int tam = width * height;
	    float filtroK[] = new float[tam];
	    
	    //Creamos el filtro
	    for (int i = 0; i < width; i++) {
	    	for (int j = 0; j < height; j++) {
	    		filtroK[i*width + j]= filtro[i][j];
			}
		}
	    //Creamos la operacion de convolucion
	    Kernel kernel = new Kernel(width, height, filtroK);
	    ConvolveOp cop = new ConvolveOp(kernel, tratBordes, null);
	    
	    //Creamos la imagen nueva a semejanza de la antigua
	    res = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
	    
	    //Aplicamos el filtro
	    cop.filter(imagen, res);
	    
	    return res;
	    
	}

}
