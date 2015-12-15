package com.domain;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AbrirImagen {
	JPanel pimagenoriginal, pimagengrises;
	JLabel limgOriginal, limgGrises;
	JTextField txtNombreImg;
	BufferedImage aplicarFiltro;
	

	public void abririmagen(JPanel pimagenoriginal,JPanel pimagengrises, JLabel limgOriginal, JLabel limgGrises, JTextField txtNombreImg, BufferedImage aplicarFiltro){
		JFileChooser selector = new JFileChooser();
		int r = selector.showOpenDialog(null);
		if(r == JFileChooser.APPROVE_OPTION){
			try {
				BufferedImage imgsel = ImageIO.read(selector.getSelectedFile());
				ImageIcon imagenSeleccionada = new ImageIcon(imgsel);
				Image img = imagenSeleccionada.getImage();
				Image otraimg = img.getScaledInstance(pimagenoriginal.getWidth(), pimagenoriginal.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imagenSeleccionadaAjustada = new ImageIcon(otraimg);
				limgOriginal.setIcon(imagenSeleccionadaAjustada);
				ImageIcon imagenFiltrada = new ImageIcon(aplicarFiltro);
				Image imgFilt = imagenFiltrada.getImage();
				Image otraimgFilt = imgFilt.getScaledInstance(pimagengrises.getWidth(), pimagengrises.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imagenFiltradaAjustada = new ImageIcon(otraimgFilt);
				limgGrises.setIcon(imagenFiltradaAjustada);
				txtNombreImg.setText(selector.getSelectedFile().getName());						
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}

}
