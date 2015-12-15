package com.domain;


import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;



public class FiltroMediana {
	
	
	public BufferedImage aplicarMediana(BufferedImage imgsel){
		BufferedImage bi = null;
		
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			
			//Recuperamos imagen de entrada
			Raster raster = imgsel.getRaster(); //declara e instancia objeto para raster lectura
			WritableRaster wraster = bi.getRaster(); //declara e instancia objeto para raster escritura
			
			//Procesa valores de imagen de entrada y los armacena en la de salida
			
			double valornr, valorng, valornb;
			int[] v = new int[9];
			
			for (int y = 1; y < imgsel.getHeight()-1; y++) {
				for (int x=1; x <imgsel.getWidth()-1; x++) {
					
					//Aplica filtro de mediana 3x3
					filtrotres(raster,v,x,y,0);
					valornr = CalcMediana(9,v);
					
					filtrotres(raster,v,x,y,1);
					valorng = CalcMediana(9,v);
					
					filtrotres(raster,v,x,y,2);
					valornb = CalcMediana(9,v);
					
					wraster.setSample(x, y,0, (int)(valornr+0.5));
					wraster.setSample(x, y, 1, (int)(valorng+0.5));
					wraster.setSample(x,y,2,(int)(valornb+0.5));
				}
			}
		}
		return bi;
	}
	
	//Método para leer una ventana o máscara 3x3 de una banda de una imagen de entrada
	//en un vector v.
	//(x,y) representa la columna y fila en el centro de la ventana
	
	public void filtrotres(Raster raster, int[]v, int x, int y, int banda){
		v[0] = raster.getSample(x-1, y-1, banda);
		v[1] = raster.getSample(x, y-1, banda);
		v[2] = raster.getSample(x+1, y-1, banda);
		v[3] = raster.getSample(x-1, y, banda);
		v[4] = raster.getSample(x, y, banda);
		v[5] = raster.getSample(x+1, y, banda);
		v[6] = raster.getSample(x-1, y+1, banda);
		v[7] = raster.getSample(x, y+1, banda);
		v[8] = raster.getSample(x+1, y+1, banda);
		
	}
	
	//Método para Calcular la Mediana de un vector de npts puntos
	
	public double CalcMediana(int npts, int[]v){
		int aux;
		
		//Ordena en orden creciente los elementos del vector
		
		for (int i = 0; i < npts-1; i++) {
			for(int j = i+1; j< npts; j++){
				if (v[i] > v[j]) {
					aux = v[i];
					v[i]= v[j];
					v[j]= aux;
				}
			}
		}
		//Define el valor de la mediana
		if((npts%2)==0){
			return ((double)v[npts/2]);
		}else{
			return((double)((v[npts/2]+v[npts/2+1])/2.0));
		}
	}
}
