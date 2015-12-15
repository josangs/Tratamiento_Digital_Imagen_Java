package com.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class ReemplazoColor extends JFrame {

	ImageIcon imagenSeleccionada;
	JLabel limgOriginal, limgGrises;
	JTextField txtNombreImg;
	BufferedImage imgsel;
	JSlider srojo1,sverde1,sazul1,srojo2,sverde2,sazul2,stolerancia;

	
	public ReemplazoColor() {
		// TODO Auto-generated constructor stub
		
		JPanel pprincipal = new JPanel();
		pprincipal.setLayout(new BorderLayout());
			
		JPanel ptitulo = new JPanel();
		JLabel ltitulo = new JLabel("Reemplazar color");
		ptitulo.add(ltitulo);
			
		JPanel pimagenes = new JPanel(new BorderLayout());
		JSplitPane split = new JSplitPane();
		split.setResizeWeight(0.5);
		JPanel primagenoriginal = new JPanel();
		limgOriginal = new JLabel();
		primagenoriginal.add(limgOriginal);
		JScrollPane sprimgoriginal = new JScrollPane(primagenoriginal);
		split.setLeftComponent(sprimgoriginal);
		JPanel primagengrises = new JPanel();
		limgGrises = new JLabel();
		primagengrises.add(limgGrises);
		JScrollPane spimggrises = new JScrollPane(primagengrises);
		split.setRightComponent(spimggrises);
			
		pimagenes.add(split);
			
		JPanel pselector = new JPanel();
		txtNombreImg = new JTextField(30);
		JButton btn = new JButton("Seleccionar");
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selector = new JFileChooser();
				int r = selector.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION){
					try{
						imgsel= ImageIO.read(selector.getSelectedFile());
						imagenSeleccionada = new ImageIcon(imgsel);
						limgOriginal.setIcon(imagenSeleccionada);
						limgGrises.setIcon(new ImageIcon(aplicarFiltro()));
						txtNombreImg.setText(selector.getSelectedFile().getName());
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		});
		
			
		pselector.add(txtNombreImg);
		pselector.add(btn);
			
		JPanel pescalar = new JPanel();
		pescalar.setLayout(new BoxLayout(pescalar,BoxLayout.Y_AXIS));
		srojo1 = new JSlider(0,255);
		srojo1.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b != null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		srojo1.setValue(3);
		srojo1.setOrientation(SwingConstants.VERTICAL);
		
		
		sverde1 = new JSlider(0,255);
		sverde1.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sverde1.setValue(3);
		sverde1.setOrientation(SwingConstants.VERTICAL);
		
		sazul1 = new JSlider(0,255);
		sazul1.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sazul1.setValue(3);
		sazul1.setOrientation(SwingConstants.VERTICAL);
		
		stolerancia = new JSlider(0,100);
		stolerancia.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		stolerancia.setValue(80);
		stolerancia.setOrientation(SwingConstants.VERTICAL);
		
		pescalar.add(new JLabel("R"));
		pescalar.add(srojo1);
		pescalar.add(new JLabel("G"));
		pescalar.add(sverde1);
		pescalar.add(new JLabel("B"));
		pescalar.add(sazul1);
		pescalar.add(new JLabel("Tolerancia"));
		pescalar.add(stolerancia);
		
		JPanel premplazo = new JPanel();
		premplazo.setLayout(new BoxLayout(premplazo, BoxLayout.Y_AXIS));
		srojo2 = new JSlider(0,255);
		srojo2.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		srojo2.setValue(3);
		srojo2.setOrientation(SwingConstants.VERTICAL);
		
		
		sverde2 = new JSlider(0,255);
		sverde2.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sverde2.setValue(3);
		sverde2.setOrientation(SwingConstants.VERTICAL);
		
		sazul2 = new JSlider(0,255);
		sazul2.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sazul2.setValue(3);
		sazul2.setOrientation(SwingConstants.VERTICAL);
		
		premplazo.add(new JLabel("R"));
		premplazo.add(srojo2);
		premplazo.add(new JLabel("G"));
		premplazo.add(sverde2);
		premplazo.add(new JLabel("B"));
		premplazo.add(sazul2);
		
		
		
		pprincipal.add(ptitulo, BorderLayout.NORTH);
		pprincipal.add(pimagenes, BorderLayout.CENTER);
		pprincipal.add(pselector,BorderLayout.SOUTH);
		pprincipal.add(pescalar, BorderLayout.WEST);
		pprincipal.add(premplazo,BorderLayout.EAST);
		
			
		getContentPane().add(pprincipal);
			
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null);
	}
	
	public  BufferedImage aplicarFiltro(){
		BufferedImage bi= null;
		if(imgsel!=null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			Color colorImagen = null;
			int red = srojo1.getValue(), green = sverde1.getValue(), blue = sazul1.getValue(), tolerancia = stolerancia.getValue();
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					colorImagen = new Color(imgsel.getRGB(x, y));
					if(Math.pow(red - colorImagen.getRed(), 2)+ Math.pow(green - colorImagen.getGreen(), 2) + Math.pow(blue - colorImagen.getBlue(), 2) <= Math.pow(tolerancia, 2) ){
						colorImagen = new Color(srojo2.getValue(),sverde2.getValue(),sazul2.getValue());
					}
					bi.setRGB(x, y, colorImagen.getRGB());
				}
			}
		}
		return bi;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		ReemplazoColor rc = new ReemplazoColor();

	}

}
