package com.domain;

import java.awt.image.BufferedImage;

public class Histograma {
	
	BufferedImage sourceImage;
	int width;
	int height;
	int picsize;
	int[] data;
	
	
	public Histograma() {
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage getSourceImage() {
		return sourceImage;
	}
	
		
	public void setSourceImage(BufferedImage image) {
		sourceImage = image;
	}

	
	private int luminance(float r, float g, float b) {
		return Math.round(0.299f * r + 0.587f * g + 0.114f * b);
	}
	
	private void readLuminance() {
		int type = sourceImage.getType();
		width = sourceImage.getWidth();
		height = sourceImage.getHeight();
		picsize = width * height;
		data = new int[picsize];
		
		
		/**if (type == BufferedImage.TYPE_INT_RGB || type == BufferedImage.TYPE_INT_ARGB) {
			int[] pixels = (int[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
			for (int i = 0; i < picsize; i++) {
				int p = pixels[i];
				int r = (p & 0xff0000) >> 16;
				int g = (p & 0xff00) >> 8;
				int b = p & 0xff;
				data[i] = luminance(r, g, b);
			}
		}*/
		 if (type == BufferedImage.TYPE_BYTE_GRAY) {
			byte[] pixels = (byte[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
			for (int i = 0; i < picsize; i++) {
				data[i] = (pixels[i] & 0xff);
			}
		} else {
			throw new IllegalArgumentException("Unsupported image type: " + type);
		}
	}
 
	private void normalizeContrast() {
		int[] histogram = new int[256];
		readLuminance();
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
	
	public BufferedImage ecualizacion (){
		BufferedImage bi = null;
		normalizeContrast();
		int[] eq = new int[picsize];
		
		bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for (int i = 0; i < data.length; i++) {
			eq[i]+= data[i];
		}
		
		
		
		
		return bi;
	}

	
	
}
