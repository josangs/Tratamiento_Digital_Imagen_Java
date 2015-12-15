package com.domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GuardarImagen extends JFrame {
	BufferedImage imagen;
	ImageIcon img = null;
	JPanel panelprincipal;
	String extension = "JPG";
	JLabel limg;

	public GuardarImagen() {
		// TODO Auto-generated constructor stub
		panelprincipal = new JPanel();
		limg = new JLabel();
		panelprincipal.add(limg);
		JMenuBar barra = new JMenuBar();
		JMenu menuarchivo = new JMenu("Archivo");
		JMenuItem cargar = new JMenuItem("Gargar Imagen");
		cargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFileChooser selector = new JFileChooser();
				int r = selector.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION){
					try {
						imagen = ImageIO.read(selector.getSelectedFile());
						ImageIcon imagenSeleccionada = new ImageIcon(imagen);
						limg.setIcon(imagenSeleccionada);
					}catch (IOException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			
			}
		});
		
		JMenuItem guardar = new JMenuItem("Guardar Imagen");
		guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc= new JFileChooser();
				int r = fc.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					File archivo = fc.getSelectedFile();
					try {
						ImageIO.write(imagen, extension, archivo);
						
					} catch (IOException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				
			}
		});
		
		menuarchivo.add(cargar);
		menuarchivo.add(guardar);
		barra.add(menuarchivo);
		setJMenuBar(barra);
		getContentPane().add(panelprincipal);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GuardarImagen gi = new GuardarImagen();
		gi.setBounds(0, 0, 400, 400);
		gi.setLocationRelativeTo(null);
		gi.setVisible(true);
		gi.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
