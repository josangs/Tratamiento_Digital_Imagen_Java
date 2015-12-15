package com.domain;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MiniPhoto extends JPanel implements MouseMotionListener,MouseListener {
	
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen", "jpg","png");
	
	private BufferedImage Imagen_en_memoria;
	//tamaño del contenedor
	private int ancho= 0;
	private int alto = 0;
	//coordenadas del mouse en pantalla
	private int Pos_Mouse_X = 0, Pos_Mouse_Y = 0;
	
	private Color color_elegido;
	//para los valores rgb del color elegido
	private int R,G,B;
	//el valor entre 0-255
	private int tolerancia = 0;
	
	private float d;
	
	private BufferedImage foto;
	//para los valores RGB de la foto
	private int r,g,b;
	
	private Color c;
	Info panel_info = new Info();

	public MiniPhoto() {
		// TODO Auto-generated constructor stub
		try {
			//se carga la imagen default- lienzo blanco
			foto = ImageIO.read(getClass().getResource("/imagen/blanco.jpg"));
			ancho = foto.getWidth();
			alto = foto.getHeight();
			this.setPreferredSize(new Dimension(ancho, alto));
			this.setSize(new Dimension(ancho,alto));
			this.setVisible(true);
			this.repaint();
			
		} catch (IOException e) {
			// TODO: handle exception
			Logger.getLogger(MiniPhoto.class.getName()).log(Level.SEVERE, null, e);
		}
		//se añade eventos del mouse
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		//se da tamaño al jpanel de informacion
		panel_info.setSize(new Dimension(81,202));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
	//se sobreescribe el metodo paint
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		//se crea una imagen en memoria
		Imagen_en_memoria = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = Imagen_en_memoria.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//se dibuja las imagenes en el contenedor
		g2d.drawImage(foto, 0, 0, this);
		//se dibuja toda la imagen
		g2.drawImage(Imagen_en_memoria, 0, 0, this);
	}
	
	public void mouseDragged(MouseEvent e){
		
	}
	public void mouseMoved(MouseEvent e){
		if((e.getPoint().getX() < foto.getWidth()) && (e.getPoint().getY() < foto.getHeight())){
		  Pos_Mouse_X = (int)e.getPoint().getX();
		  Pos_Mouse_Y = (int)e.getPoint().getY();
		  c = new Color(foto.getRGB(Pos_Mouse_X, Pos_Mouse_Y));
		  //se coloca los valores al jpanel de informaciones
		   this.panel_info.setX(Pos_Mouse_X);
		   this.panel_info.setY(Pos_Mouse_Y);
		   this.panel_info.setR(c.getRed());
		   this.panel_info.setG(c.getGreen());
		   this.panel_info.setB(c.getBlue());
		   this.panel_info.setColorSeleccion(c);
		   repaint();
		}
		 
	}
	
	//metodo que recorre pixel a pixel la imagen y
	public void cambiar_color(Color color_cambio){
		//se descompone en RGB el color seleccionado de la imagen
		R= color_elegido.getRed();
		G= color_elegido.getGreen();
		B= color_elegido.getBlue();
		//se recorre toda la imagen pixel a pixel
		for (int i = 0; i < foto.getWidth(); i++) {
			for (int j = 0; j < foto.getHeight(); j++) {
				c= new Color(foto.getRGB(i, j));
				r= c.getRed();
				g= c.getGreen();
				b= c.getBlue();
				d= (float)Math.sqrt(Math.pow(((r-R)), 2) + Math.pow((g-G), 2) + Math.pow((b-B), 2));
				if (d <= this.tolerancia) {
					foto.setRGB(i, j, color_cambio.getRGB());
				}
			}
		}
		this.repaint();
	}
	
	//solo valores entre 0 y 255
	public void setTolerancia(int t){
		if (t>255) {
			this.tolerancia = 255;
		}else if(t<0){
			this.tolerancia=0;
		}else{
			this.tolerancia=t;
		}
	}
	
	//muestra una ventana de dialogo para abrir un archivo de imagen
	public void Open(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		//fileChooser.setCurrentDirectory(new File("c:/"));
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			try {
				//se obtiene la direccion del archivo y se carga imagen en memoria
				foto = ImageIO.read(fileChooser.getSelectedFile());
				//se redimensiona el contenedor
				ancho = foto.getWidth(this);
				alto = foto.getHeight(this);
				this.setPreferredSize(new Dimension(ancho, alto));
				this.setSize(new Dimension(ancho,alto));
				this.repaint();
			} catch (IOException e) {
				// TODO: handle exception
				Logger.getLogger(MiniPhoto.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public void Save(){
		String file = null;
		JFileChooser fileChooser = new JFileChooser();
		//fileChooser.setCurrentDirectory(new File("c:/"));
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile().toString();
			guardar_imagen(file + ".jpg");
			
		}
	}
	
	//metodo que guarda la imagen en directorio
	public void guardar_imagen(String f){
		try {
			//se extrae el formato de la cadena "f" que contiene la direccion
			String formato = (f.endsWith(".jpg")) ? "jpg":"png";
			//se escribe en disco
			ImageIO.write(foto, formato, new File(f));
			JOptionPane.showMessageDialog(null, "La imagen se guardo correctamente...");
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Error: nose pudo guardar la imagen...");
		}
	}
	
	public void mouseClicked(MouseEvent e){
		this.color_elegido = new Color(foto.getRGB(Pos_Mouse_X, Pos_Mouse_Y));
		this.panel_info.setColor(this.color_elegido);
	}
	public void mousePressed(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	public void mouseExit(MouseEvent e){
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
