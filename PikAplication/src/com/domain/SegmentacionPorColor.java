package com.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SegmentacionPorColor extends JFrame {
	ImageIcon imagenSeleccionada;
	JLabel limgOriginal, limgGrises;
	JTextField txtNombreImg;
	BufferedImage imgsel;
	JSlider srojo,sverde,sazul,stolerancia;

	public SegmentacionPorColor() {
		// TODO Auto-generated constructor stub
		JPanel pprincipal = new JPanel();
		pprincipal.setLayout(new BorderLayout());
		
		JPanel ptitulo = new JPanel();
		JLabel ltitulo = new JLabel("Segmentacion por Colores");
		ptitulo.add(ltitulo);
		
		JPanel primagenes = new JPanel(new BorderLayout());
		JSplitPane split = new JSplitPane();
		split.setResizeWeight(0.5);
		JPanel pimagneoriginal = new JPanel();
		limgOriginal = new JLabel();
		pimagneoriginal.add(limgOriginal);
		JScrollPane spimgoriginal = new JScrollPane(pimagneoriginal);
		split.setLeftComponent(spimgoriginal);
		JPanel pimagengrises = new JPanel();
		limgGrises = new JLabel();
		pimagengrises.add(limgGrises);
		JScrollPane spimggrises = new JScrollPane(pimagengrises);
		split.setRightComponent(spimggrises);
		
		primagenes.add(split);
		
		JPanel pselector = new JPanel();
		txtNombreImg = new JTextField(30);
		JButton btn = new JButton("Seleccionar");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser selector = new JFileChooser();
				int r = selector.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION){
					try {
						imgsel = ImageIO.read(selector.getSelectedFile());
						imagenSeleccionada = new ImageIcon(imgsel);
						limgOriginal.setIcon(imagenSeleccionada);
						limgGrises.setIcon(new ImageIcon(aplicarFiltro()));
						txtNombreImg.setText(selector.getSelectedFile().getName());						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		});
		pselector.add(txtNombreImg);
		pselector.add(btn);
		
		JPanel pescalar = new JPanel();
		pescalar.setLayout(new BoxLayout(pescalar, BoxLayout.Y_AXIS));
		srojo = new JSlider(0,255);
		srojo.setValue(3);
		srojo.setOrientation(SwingConstants.VERTICAL);
		srojo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sverde = new JSlider(0,255);
		sverde.setValue(3);
		sverde.setOrientation(SwingConstants.VERTICAL);
		sverde.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		sazul = new JSlider(0,255);
		sazul.setValue(3);
		sazul.setOrientation(SwingConstants.VERTICAL);
		sazul.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		stolerancia = new JSlider(0,100);
		stolerancia.setValue(80);
		stolerancia.setOrientation(SwingConstants.VERTICAL);
		stolerancia.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				BufferedImage b = aplicarFiltro();
				if(b!=null){
					limgGrises.setIcon(new ImageIcon(b));
				}
			}
		});
		
		pescalar.add(new JLabel ("R"));
		pescalar.add(srojo);
		pescalar.add(new JLabel("G"));
		pescalar.add(sverde);
		pescalar.add(new JLabel("B"));
		pescalar.add(sazul);
		pescalar.add(new JLabel("Tolerancia"));
		pescalar.add(stolerancia);
		
		pprincipal.add(ptitulo, BorderLayout.NORTH);
		pprincipal.add(primagenes, BorderLayout.CENTER);
		pprincipal.add(pselector, BorderLayout.SOUTH);
		pprincipal.add(pescalar, BorderLayout.EAST);
		
		getContentPane().add(pprincipal);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1280, 768);
		setLocationRelativeTo(null);
	}
	
	public BufferedImage aplicarFiltro(){
		BufferedImage bi= null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			Color colorImagen = null;
			int red = srojo.getValue(), green = sverde.getValue(), blue = sazul.getValue(), 
					tolerancia = stolerancia.getValue();
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
				colorImagen = new Color(imgsel.getRGB(x, y));
				if((Math.abs(red - colorImagen.getRed()) > tolerancia) 
					|| (Math.abs(green - colorImagen.getGreen()) > tolerancia)
					|| (Math.abs(blue - colorImagen.getBlue()) > tolerancia)){
					colorImagen = new Color(0,0,0);
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
		SegmentacionPorColor spc = new SegmentacionPorColor();

	}

}
