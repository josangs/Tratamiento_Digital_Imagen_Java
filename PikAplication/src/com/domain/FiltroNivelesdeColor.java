package com.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
public class FiltroNivelesdeColor extends JFrame {
	
	ColocarMascara cm = new ColocarMascara();
	
	ImageIcon imagenSeleccionada;
	JLabel limgOriginal, limgGrises;
	JTextField txtNombreImg;
	BufferedImage imgsel;
	JSlider srojo, sverde,sazul;
	String extension = "JPG";

	public FiltroNivelesdeColor() {
		// TODO Auto-generated constructor stub
		JPanel pprincipal = new JPanel();
		pprincipal.setLayout(new BorderLayout());
		
		JPanel ptitulo = new JPanel();
		JLabel ltitulo = new JLabel("Niveles de Color");
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
		
		JButton btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc= new JFileChooser();
				int r = fc.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					File archivo = fc.getSelectedFile();
					try {
						ImageIO.write(aplicarFiltro(), extension, archivo);
						
					} catch (IOException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				
			}
		});
		
		pselector.add(txtNombreImg);
		pselector.add(btn);
		pselector.add(btnguardar);
		
		JPanel pescalar = new JPanel();
		pescalar.setLayout(new BoxLayout(pescalar, BoxLayout.Y_AXIS));
		srojo = new JSlider(-50,50);
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
		sverde = new JSlider(-50,50);
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
		sazul = new JSlider(-50,50);
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
		
		pescalar.add(new JLabel ("R"));
		pescalar.add(srojo);
		pescalar.add(new JLabel("G"));
		pescalar.add(sverde);
		pescalar.add(new JLabel("B"));
		pescalar.add(sazul);
		
		
		pprincipal.add(ptitulo, BorderLayout.NORTH);
		pprincipal.add(primagenes, BorderLayout.CENTER);
		pprincipal.add(pselector, BorderLayout.SOUTH);
		pprincipal.add(pescalar, BorderLayout.EAST);
		
		getContentPane().add(pprincipal);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 768);
		setLocationRelativeTo(null);
	}
	
	public BufferedImage aplicarFiltro(){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			for (int x = 0; x < imgsel.getWidth(); x++) {
				for (int y = 0; y < imgsel.getHeight(); y++) {
					Color c = new Color(imgsel.getRGB(x, y));
					int r = c.getRed();
					int g = c.getGreen();
					int b = c.getBlue();
					r = (int)r+((r*srojo.getValue()/100));
					g = (int)g+((g*sverde.getValue()/100));
					b = (int)b+((b*sazul.getValue()/100));
					
					if(r > 255){
						r = 255;
					}
					if(g > 255){
						g = 255;
					}
					if(b > 255){
						b = 255;
					}
					if(r < 0){
						r = 0;
					}
					if(g < 0){
						g = 0;
					}
					if(b < 0){
						b = 0;
					}
					
					bi.setRGB(x, y, new Color(r,g,b).getRGB());
				}
			}
		}
		
		return bi;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		FiltroNivelesdeColor fnc = new FiltroNivelesdeColor();

	}

}
