package com.domain;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FiltroMedia {

	
	public BufferedImage  aplicarMedia (BufferedImage imgsel){
		BufferedImage bi = null;
        int ventanaDePixeles[] = new int[9];
        int rgbs[] = new int[3];
        float[] hsbvals = new float[3];
        int mediaR=0;
        int mediaG=0;
        int mediaB=0;
        
        if(imgsel != null){
        	
        	bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
        
            for(int i=1;i<imgsel.getWidth()-1;i++){
                for(int j=1;j<imgsel.getHeight()-1;j++){
                       
                                ventanaDePixeles[0] = imgsel.getRGB(i-1,j-1);
                                ventanaDePixeles[1] = imgsel.getRGB(i-1,j);
                                ventanaDePixeles[2] = imgsel.getRGB(i-1,j+1);
                                ventanaDePixeles[3] = imgsel.getRGB(i,j-1);
                                ventanaDePixeles[4] = 2*imgsel.getRGB(i,j);
                                ventanaDePixeles[5] = imgsel.getRGB(i,j+1);
                                ventanaDePixeles[6] = imgsel.getRGB(i+1,j-1);
                                ventanaDePixeles[7] = imgsel.getRGB(i+1,j);
                                ventanaDePixeles[8] = imgsel.getRGB(i+1,j+1);
                               
                        for(int k=0;k<9;k++){  
                                rgbs = Colores.obtieneRGB(ventanaDePixeles[k]);
                                mediaR +=rgbs[0];
                                mediaG +=rgbs[1];
                                mediaB +=rgbs[2];
                        }
                        mediaR=mediaR/10;
                        mediaG=mediaG/10;
                        mediaB=mediaB/10;
                        Color.RGBtoHSB(mediaR, mediaG, mediaB, hsbvals);
                        bi.setRGB(i,j,Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]));

                }
        }
        }
        return bi;
}


}
