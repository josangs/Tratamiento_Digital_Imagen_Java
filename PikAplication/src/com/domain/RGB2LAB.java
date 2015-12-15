package com.domain;

public class RGB2LAB {
	public void rgb2lab(int R, int G, int B, int L, int a, int b){
		
		float var_r,var_g,var_b, X,Y,Z, var_x, var_y, var_z ;
		
		
		float ref_x = 95.047f; //Illuminant = D65
		float ref_y = 100.000f;
		float ref_z = 108.883f;
		
		//RGB a XYZ
		
		var_r= R/255.0f;
		var_g= G/255.0f;
		var_b= B/255.0f;
		
		//Soponemos sRGB (D65)
		if(var_r > 0.04045){
			var_r = (float) Math.pow((var_r+0.055f)/1.055f, 2.4f); 
		}else{
			var_r = var_r/12.92f;
		}
		if(var_g > 0.04045){
			var_g = (float) Math.pow((var_g+0.055f)/1.055f, 2.4f);
		}else{
			var_g = var_g/12.92f;
		}
		if(var_b > 0.04045){
			var_b = (float) Math.pow((var_b+0.055f)/1.055f, 2.4f);
		}else{
			var_b = var_b/12.92f;
		}
		
		var_r = var_r*100;
		var_g = var_g*100;
		var_b = var_b*100;
		
		//observer = 2º, Illuminant= D65
		X= var_r * 0.4124f + var_g * 0.3576f + var_b * 0.1805f;
		Y= var_r * 0.2126f + var_g * 0.7152f + var_b * 0.0722f;
		Z= var_r * 0.0193f + var_g * 0.1192f + var_b * 0.9505f;
		
		//XYZ to CIE-L*ab
		
		var_x = X / ref_x;
		var_y = Y / ref_y;
		var_z = Z / ref_z;
		
		if(var_x > 0.008856){
			var_x = (float) Math.pow(var_x, 0.33333333f);
		}else{
			var_x = (7.787f*var_x)+(0.137931f);
		}
		if(var_y > 0.008856){
			var_y = (float) Math.pow(var_y, 0.33333333f);
		}else{
			var_y = (7.787f*var_y)+(0.137931f);
		}
		if(var_z > 0.008856){
			var_z = (float) Math.pow(var_z, 0.33333333f);
		}else{
			var_z = (7.787f*var_z)+(0.137931f);
		}
		
		L = (int) ((116 * var_y) - 16);
		a = (int) (500 * (var_x - var_y));
		b = (int) (200 * (var_y - var_z));
	}

}
