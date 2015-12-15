package com.domain;

import java.awt.image.BufferedImage;

public class FiltroGaussiano {
	
	/**
	 * Aplica un filtro Gaussiano
	 * 
	 * @param imagen a la que se le aplica el filtro
	 * @param theta
	 * @param tamaño de la imagen
	 * @return la imagen resultante de la aplicacion
	 */

	public FiltroGaussiano() {
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage aplicarGauss(BufferedImage imagen, int theta, int size ){
		BufferedImage res = null;
		float kernel[][] = gKernel(theta, size);
		
		res = Convolucion.aplicarFiltro(kernel, imagen, Convolucion.BORDES_0);
		
		return res;
	}
	
	/**
	 * Genera la matriz de convolución para un filtro Gaussiano a 
	 * partir de theta y el tamaño de la matriz(nxn)
	 * @param theta
	 * @param size
	 * @return
	 */
	
	private float[][] gKernel(float theta, int size){
		float [][] kernel = new float[size][size];
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				kernel[i][j]= gaussianDiscrete2D(theta, i-(size/2), j-(size/2));
			}
		}
		double sum = 0;
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				sum = sum + kernel[i][j];
			}
		}
		return kernel;
	}
	
	
	private float gaussianDiscrete2D(double theta, int x, int y){
		float g = 0;
		
		for (double ySubPixel = y - 0.5; ySubPixel < y + 0.55; ySubPixel +=0.1) {
			for (double xSubPixel = x - 0.5; xSubPixel < x+0.55; xSubPixel +=0.1) {
				g = g +(float)((1/(2*Math.PI*theta*theta))*
						Math.pow(Math.E,-(xSubPixel*xSubPixel+ySubPixel*ySubPixel)/
								(2*theta*theta)));
			}
			
		}
		g= g/121;
		//System.out.println(g);
		return g;
	}
	

}
