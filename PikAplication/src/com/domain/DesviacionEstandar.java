package com.domain;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DesviacionEstandar extends JPanel {
	
	private JTextField tfDimension;
	private JTextField tfMedia;
	private JTextField tfVarianza;
	private JTextField tfDesviacion;
	private int ancho = 0;
	private int alto = 0;
	private int num = 0;
	float media = 0;
	double varianza, desviaciontipica = 0.0;
	private BufferedImage sourceImage;
	
	
	
	public BufferedImage getSourceImage(){
		return sourceImage;
	}
	
	public void setSourceImage(BufferedImage image){
		sourceImage = image;
		
	}
	
	public  DesviacionEstandar(){
		setLayout(null);
		
		JLabel lblDimension = new JLabel("Dimensión:");
		lblDimension.setBounds(10, 11, 63, 37);
		add(lblDimension);
		
		tfDimension = new JTextField();
		tfDimension.setEditable(false);
		tfDimension.setText("0");
		tfDimension.setBounds(68, 19, 86, 20);
		add(tfDimension);
		tfDimension.setColumns(10);
		
		JLabel lblMedia = new JLabel("Media:");
		lblMedia.setBounds(172, 10, 42, 38);
		add(lblMedia);
		
		tfMedia = new JTextField();
		tfMedia.setEditable(false);
		tfMedia.setText("0");
		tfMedia.setBounds(210, 19, 86, 20);
		add(tfMedia);
		tfMedia.setColumns(10);
		
		JLabel lblVarianza = new JLabel("Varianza:");
		lblVarianza.setBounds(326, 11, 159, 37);
		add(lblVarianza);
		
		tfVarianza = new JTextField();
		tfVarianza.setEditable(false);
		tfVarianza.setText("0");
		tfVarianza.setBounds(383, 19, 86, 20);
		add(tfVarianza);
		tfVarianza.setColumns(10);
		
		JLabel lblDesviacionTipica = new JLabel("Desviación Típica:");
		lblDesviacionTipica.setBounds(497, 11, 106, 37);
		add(lblDesviacionTipica);
		
		tfDesviacion = new JTextField();
		tfDesviacion.setEditable(false);
		tfDesviacion.setText("0");
		tfDesviacion.setBounds(588, 19, 86, 20);
		add(tfDesviacion);
		tfDesviacion.setColumns(10);
		
	}
	
	public float calculoMedia(){
    	
    	ancho = sourceImage.getWidth();
    	alto = sourceImage.getHeight();
    	num = ancho * alto;
    	
		int valor[][] = new int[ancho][alto];
		int sumatorio = 0;
		
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				sumatorio = sumatorio + valor[x][y];
				media = sumatorio/ (num*1.0f);
			}
		}
		
		return media;
	}
	
	public double calculoVarianza(){
		
		ancho = sourceImage.getWidth();
    	alto = sourceImage.getHeight();
		num = ancho * alto;
	
		int valor[][] = new int[ancho][alto];
		double rango = 0.0;
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				rango = Math.pow(valor[x][y]- media, 2f);
				varianza = varianza + rango;
				
			}
		} 
		varianza = varianza/(num-1);
		
		return varianza;
	}
	
	public double calculoDesviacion(){
		
		desviaciontipica = Math.sqrt(calculoVarianza());
		
		return desviaciontipica;
	}
	
	
	public void setTamaño (int t){
		tfDimension.setText(String.valueOf(t));
	}
	public void setMedia (float m){
		tfMedia.setText(String.valueOf(m));
	}
	public void setVarianza (double v){
		tfVarianza.setText(String.valueOf(v));
	}
	public void setDesviacion (double d){
		tfDesviacion.setText(String.valueOf(d));
	}
	
	public static void main (String args[]){
		DesviacionEstandar desv = new DesviacionEstandar();
		//desv.setTamaño();
		desv.setMedia(desv.calculoMedia());
		desv.setVarianza(desv.calculoVarianza());
		desv.setDesviacion(desv.calculoDesviacion());
		
		desv.setVisible(true);
	}
}
