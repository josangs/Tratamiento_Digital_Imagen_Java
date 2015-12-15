/**
 * Algoritmo basado en http://www.tomgibara.com
 */

package com.domain;

import java.awt.image.BufferedImage;
import java.util.Arrays;


public class CannyEdgeDetector {

	//Constantes
	
	private final static float GAUSSIAN_CUT_OFF = 0.005f;
	private final static float MAGNITUDE_SCALE = 100F;
	private final static float MAGNITUDE_LIMIT = 1000F;
	private final static int MAGNITUDE_MAX = (int) (MAGNITUDE_SCALE * MAGNITUDE_LIMIT);

	//Variables
	
	private int height;
	private int width;
	private int picsize;
	private int[] data;
	private int[] magnitude;
	private BufferedImage sourceImage;
	private BufferedImage edgesImage;
	
	private float gaussianKernelRadius;
	private float lowThreshold;
	private float highThreshold;
	private int gaussianKernelWidth;
	private boolean contrastNormalized;

	private float[] xConv;
	private float[] yConv;
	private float[] xGradient;
	private float[] yGradient;
	
	//Constructors
	
	/**
	 * Pasamos los parametros por defecto al constructor.
	 */
	
	public CannyEdgeDetector() {
		lowThreshold = 2.5f;
		highThreshold = 7.5f;
		gaussianKernelRadius = 2f;
		gaussianKernelWidth = 16;
		contrastNormalized = false;
	}
	
	/**
	 * Conseguimos la Imagen.
	 * 
	 * @return fuente de la imagen, or null
	 */
	
	public BufferedImage getSourceImage() {
		return sourceImage;
	}
	
	/**
	 * Cargamos la imagen.
	 *  
	 * @param imagen fuente.
	 */
	
	public void setSourceImage(BufferedImage image) {
		sourceImage = image;
	}

	/**
	 * Obtiene una imagen conteniendo los bordes detectados durante la ultima llamada
	 * a el metodo process. El BufferedImage es una imagen opaca de tipo
	 * BufferedImage.TYPE_INT_ARGB donde los pixeles del borde son blancos y 
	 * todos los otros pixieles negros.
	 * 
	 * @return una imagen conteniendo los bordes detectados, or null si el metodo 
	 * process no ha sido todavía llamado.
	 */
	
	public BufferedImage getEdgesImage() {
		return edgesImage;
	}
 
	/**
	 * Cargamos los bordes de la imagen. La llamada a este metodo no cambiara la operacion 
	 * de deteccion de bordes de ninguna forma. 
	 *  
	 * @param edgesImage esperados (aunque no es requerido) to be null
	 */
	
	public void setEdgesImage(BufferedImage edgesImage) {
		this.edgesImage = edgesImage;
	}

	/**
	 * El low threshold para la histeresis. El valor por defecto es 2.5.
	 * 
	 * @return umbral minimo histeresis.
	 */
	
	public float getLowThreshold() {
		return lowThreshold;
	}
	
	/**
	 * Carga el valor.
	 * 
	 * @param threshold un umbral minimo de histeresis.
	 */
	
	public void setLowThreshold(float threshold) {
		if (threshold < 0) throw new IllegalArgumentException();
		lowThreshold = threshold;
	}
 
	/**
	 * El high threshold para la histeresis. El valor por defecto es 7.5.
	 * 
	 * @return el maximo umbral de la histeresis
	 */
	
	public float getHighThreshold() {
		return highThreshold;
	}
	
	/**
	 * Carga el valor máximo para la histeresis.
	 * 
	 * @param threshold un valor del umbral maximo.
	 */
	
	public void setHighThreshold(float threshold) {
		if (threshold < 0) throw new IllegalArgumentException();
		highThreshold = threshold;
	}

	/**
	 * El numero de pixeles a travez del cual el Gaussian Kernel es aplicado.
	 * El valor por defecto es 16.
	 * 
	 * @return el radio de la operación de convolucion en pixeles.
	 */
	
	public int getGaussianKernelWidth() {
		return gaussianKernelWidth;
	}
	
	/**
	 * El numero de pixeles a travez de los cuales el Gaussian Kernel es aplicado.
	 * Esta implementacion reduce el radio si la contribucion de los valores de los pixeles
	 * es estimada erroneamente, este es un raido maximo..
	 * 
	 * @param gaussianKernelWidth un radio para la operacion de convolucion es
	 * pixeles, al menos 2.
	 */
	
	public void setGaussianKernelWidth(int gaussianKernelWidth) {
		if (gaussianKernelWidth < 2) throw new IllegalArgumentException();
		this.gaussianKernelWidth = gaussianKernelWidth;
	}

	/**
	 * El radio de el kernel de la convolución Gaussiana usado para suavizar la imagen
	 * fuente antes del calculo del gradiente. El valor por defecto es 16.
	 * 
	 * @return Gaussian radio del kernel en pixeles
	 */
	
	public float getGaussianKernelRadius() {
		return gaussianKernelRadius;
	}
	
	/**
	 * Cargar el radio del kernel de la convolucion Gaussiana es usado para suavizar 
	 * la imagen fuente antes del calculo del gradiente.
	 * 
	 * @return Gaussian kernel radius en pixeles, no debe ser superior a 0.1f.
	 */
	
	public void setGaussianKernelRadius(float gaussianKernelRadius) {
		if (gaussianKernelRadius < 0.1f) throw new IllegalArgumentException();
		this.gaussianKernelRadius = gaussianKernelRadius;
	}
	
	/**
	 * Los datos de la luminancia extraidos desde la imagen fuente son 
	 * normalizados aplicandoles el histograma antes de la extraccion de los bordes.
	 * El valor por defecto es falso false.
	 * 
	 * @return si el contraste es normalizado
	 */
	
	public boolean isContrastNormalized() {
		return contrastNormalized;
	}
	
	/**
	 * Gargar el contraste normalizado
	 * @param contrastNormalized true si el contraste debe ser normalizado,
	 * false para lo demas.
	 */
	
	public void setContrastNormalized(boolean contrastNormalized) {
		this.contrastNormalized = contrastNormalized;
	}
	
	// Metodos
	
	public void process() {
		width = sourceImage.getWidth();
		height = sourceImage.getHeight();
		picsize = width * height;
		initArrays();
		readLuminance();
		if (contrastNormalized) normalizeContrast();
		computeGradients(gaussianKernelRadius, gaussianKernelWidth);
		int low = Math.round(lowThreshold * MAGNITUDE_SCALE);
		int high = Math.round( highThreshold * MAGNITUDE_SCALE);
		performHysteresis(low, high);
		thresholdEdges();
		writeEdges(data);
	}
 
	// Metodos privados que dan la funcionalidad al metodo process
	
	private void initArrays() {
		if (data == null || picsize != data.length) {
			data = new int[picsize];
			magnitude = new int[picsize];

			xConv = new float[picsize];
			yConv = new float[picsize];
			xGradient = new float[picsize];
			yGradient = new float[picsize];
		}
	}
	
	private void computeGradients(float kernelRadius, int kernelWidth) {
		
		//Genera la mascara para la convolucion
		float kernel[] = new float[kernelWidth];
		float diffKernel[] = new float[kernelWidth];
		int kwidth;
		for (kwidth = 0; kwidth < kernelWidth; kwidth++) {
			float g1 = gaussian(kwidth, kernelRadius);
			if (g1 <= GAUSSIAN_CUT_OFF && kwidth >= 2) break;
			float g2 = gaussian(kwidth - 0.5f, kernelRadius);
			float g3 = gaussian(kwidth + 0.5f, kernelRadius);
			kernel[kwidth] = (g1 + g2 + g3) / 3f / (2f * (float) Math.PI * kernelRadius * kernelRadius);
			diffKernel[kwidth] = g3 - g2;
		}

		int initX = kwidth - 1;
		int maxX = width - (kwidth - 1);
		int initY = width * (kwidth - 1);
		int maxY = width * (height - (kwidth - 1));
		
		//Actua la convolucion en x e y direcciones.
		for (int x = initX; x < maxX; x++) {
			for (int y = initY; y < maxY; y += width) {
				int index = x + y;
				float sumX = data[index] * kernel[0];
				float sumY = sumX;
				int xOffset = 1;
				int yOffset = width;
				for(; xOffset < kwidth ;) {
					sumY += kernel[xOffset] * (data[index - yOffset] + data[index + yOffset]);
					sumX += kernel[xOffset] * (data[index - xOffset] + data[index + xOffset]);
					yOffset += width;
					xOffset++;
				}
				
				yConv[index] = sumY;
				xConv[index] = sumX;
			}
 
		}
 
		for (int x = initX; x < maxX; x++) {
			for (int y = initY; y < maxY; y += width) {
				float sum = 0f;
				int index = x + y;
				for (int i = 1; i < kwidth; i++)
					sum += diffKernel[i] * (yConv[index - i] - yConv[index + i]);
 
				xGradient[index] = sum;
			}
 
		}

		for (int x = kwidth; x < width - kwidth; x++) {
			for (int y = initY; y < maxY; y += width) {
				float sum = 0.0f;
				int index = x + y;
				int yOffset = width;
				for (int i = 1; i < kwidth; i++) {
					sum += diffKernel[i] * (xConv[index - yOffset] - xConv[index + yOffset]);
					yOffset += width;
				}
 
				yGradient[index] = sum;
			}
 
		}
 
		initX = kwidth;
		maxX = width - kwidth;
		initY = width * kwidth;
		maxY = width * (height - kwidth);
		for (int x = initX; x < maxX; x++) {
			for (int y = initY; y < maxY; y += width) {
				int index = x + y;
				int indexN = index - width;
				int indexS = index + width;
				int indexW = index - 1;
				int indexE = index + 1;
				int indexNW = indexN - 1;
				int indexNE = indexN + 1;
				int indexSW = indexS - 1;
				int indexSE = indexS + 1;
				
				float xGrad = xGradient[index];
				float yGrad = yGradient[index];
				float gradMag = hypot(xGrad, yGrad);

				//Aplicamos non-maximal supression
				float nMag = hypot(xGradient[indexN], yGradient[indexN]);
				float sMag = hypot(xGradient[indexS], yGradient[indexS]);
				float wMag = hypot(xGradient[indexW], yGradient[indexW]);
				float eMag = hypot(xGradient[indexE], yGradient[indexE]);
				float neMag = hypot(xGradient[indexNE], yGradient[indexNE]);
				float seMag = hypot(xGradient[indexSE], yGradient[indexSE]);
				float swMag = hypot(xGradient[indexSW], yGradient[indexSW]);
				float nwMag = hypot(xGradient[indexNW], yGradient[indexNW]);
				float tmp;
				/*
				 * Una breve explicacion de lo que sucede aqui es:
				 * Esta actuacion "non-maximal
				 * supression" fase de la deteccion de bordes de Canny en la cual nosotros
				 * necesitamos comparar la magnitud del gradiente en la direccion de este;
				 * solo si el valor es un maximo local nosotros consideraremos el punto
				 * como un candidato a borde.
				 * 
				 * Nosotros necesitamos romper la comparacion entre un numero de diferentes
				 * casos dependiendo de la direccion del gradiente ademas del
				 * valor apropiado pueda ser usado. Para evitar calcular la
				 * direccion del gradiente, nosotros utilizamos dos simples comparaciones:
				 * Primero comporabamos que la derivada parcial tiene el mismo signo (1)
				 * y luego comprobamos cual es el mayor (2). Consecuentemente, 
				 * nosotros hemos reducido el problema para uno de los cuatro casos identicos
				 * que cada test de la magnitud central del gradiente contra el valor
				 * 2 puntos con 'identical support'; lo que significa qe es 
				 * la geometria requerida para interpolar con exactitud la magnitud
				 * de la funcion del gradiente en cuyos puntos tienen una geometria
				 * identica.
				 * 
				 * Cuando comparamos el valor del gradiente central con los dos 
				 * valores interpolados, nosotros evitamos tener que actuar cualquiere division, 
				 * multiplicando ambos lados de cada disparidad por el mayor de las 2 derivadas
				 * parciales. El valor comparado es almacenado en una variable temporal
				 * (3) y reutilizado en el paso (4).
				 * 
				 */
				if (xGrad * yGrad <= (float) 0 /*(1)*/
					? Math.abs(xGrad) >= Math.abs(yGrad) /*(2)*/
						? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * neMag - (xGrad + yGrad) * eMag) /*(3)*/
							&& tmp > Math.abs(yGrad * swMag - (xGrad + yGrad) * wMag) /*(4)*/
						: (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * neMag - (yGrad + xGrad) * nMag) /*(3)*/
							&& tmp > Math.abs(xGrad * swMag - (yGrad + xGrad) * sMag) /*(4)*/
					: Math.abs(xGrad) >= Math.abs(yGrad) /*(2)*/
						? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * seMag + (xGrad - yGrad) * eMag) /*(3)*/
							&& tmp > Math.abs(yGrad * nwMag + (xGrad - yGrad) * wMag) /*(4)*/
						: (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * seMag + (yGrad - xGrad) * sMag) /*(3)*/
							&& tmp > Math.abs(xGrad * nwMag + (yGrad - xGrad) * nMag) /*(4)*/
					) {
					magnitude[index] = gradMag >= MAGNITUDE_LIMIT ? MAGNITUDE_MAX : (int) (MAGNITUDE_SCALE * gradMag);
					//NOTE: The orientation of the edge is not employed by this
					//implementation. It is a simple matter to compute it at
					//this point as: Math.atan2(yGrad, xGrad);
				} else {
					magnitude[index] = 0;
				}
			}
		}
	}
 
	//NOTE: It is quite feasible to replace the implementation of this method
	//with one which only loosely approximates the hypot function. I've tested
	//simple approximations such as Math.abs(x) + Math.abs(y) and they work fine.
	private float hypot(float x, float y) {
		return (float) Math.hypot(x, y);
	}
 
	private float gaussian(float x, float sigma) {
		return (float) Math.exp(-(x * x) / (2f * sigma * sigma));
	}
 
	private void performHysteresis(int low, int high) {
		//Esta implementacion reutiliza el array de datos almacenado junto
		//a los datos de luminancia de la imagen,y los bordes de intensidad desde el procesamiento.
		//Esto se hace para que la memoria sea mas eficiente,otra implementacion podria
		//separar estas funciones.
		Arrays.fill(data, 0);
 
		int offset = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (data[offset] == 0 && magnitude[offset] >= high) {
					follow(x, y, offset, low);
				}
				offset++;
			}
		}
 	}
 
	private void follow(int x1, int y1, int i1, int threshold) {
		int x0 = x1 == 0 ? x1 : x1 - 1;
		int x2 = x1 == width - 1 ? x1 : x1 + 1;
		int y0 = y1 == 0 ? y1 : y1 - 1;
		int y2 = y1 == height -1 ? y1 : y1 + 1;
		
		data[i1] = magnitude[i1];
		for (int x = x0; x <= x2; x++) {
			for (int y = y0; y <= y2; y++) {
				int i2 = x + y * width;
				if ((y != y1 || x != x1)
					&& data[i2] == 0 
					&& magnitude[i2] >= threshold) {
					follow(x, y, i2, threshold);
					return;
				}
			}
		}
	}

	private void thresholdEdges() {
		for (int i = 0; i < picsize; i++) {
			data[i] = data[i] > 0 ? -1 : 0xff000000;
		}
	}
	
	private int luminance(float r, float g, float b) {
		return Math.round(0.299f * r + 0.587f * g + 0.114f * b);
	}
	
	private void readLuminance() {
		int type = sourceImage.getType();
		if (type == BufferedImage.TYPE_INT_RGB || type == BufferedImage.TYPE_INT_ARGB) {
			int[] pixels = (int[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
			for (int i = 0; i < picsize; i++) {
				int p = pixels[i];
				int r = (p & 0xff0000) >> 16;
				int g = (p & 0xff00) >> 8;
				int b = p & 0xff;
				data[i] = luminance(r, g, b);
			}
		} else if (type == BufferedImage.TYPE_BYTE_GRAY) {
			byte[] pixels = (byte[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
			for (int i = 0; i < picsize; i++) {
				data[i] = (pixels[i] & 0xff);
			}
		}else {
			throw new IllegalArgumentException("Unsupported image type: " + type);
		}
	}
 
	private void normalizeContrast() {
		int[] histogram = new int[256];
		for (int i = 0; i < data.length; i++) {
			histogram[data[i]]++;
		}
		int[] remap = new int[256];
		int sum = 0;
		int j = 0;
		for (int i = 0; i < histogram.length; i++) {
			sum += histogram[i];
			int target = sum*255/picsize;
			for (int k = j+1; k <=target; k++) {
				remap[k] = i;
			}
			j = target;
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = remap[data[i]];
		}
	}
	
	private void writeEdges(int pixels[]) {
		//No es un mecanismo para obtener los datos de los bordes.
		//Solo es viable para INT_ARGB.
		if (edgesImage == null) {
			edgesImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}
		edgesImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, pixels);
	}
 
}