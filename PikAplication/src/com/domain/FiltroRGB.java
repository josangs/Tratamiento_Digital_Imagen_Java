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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class FiltroRGB extends JFrame {
	
	ColocarMascara cm = new ColocarMascara();
	
	ImageIcon imagenSeleccionada;
	JLabel limgOriginal, limgGrises;
	JTextField txtNombreImg;
	BufferedImage imgsel;
	JCheckBox cambiar;
	String extension = "JPG";

	public FiltroRGB() {
		// TODO Auto-generated constructor stub
		JPanel pprincipal = new JPanel();
		pprincipal.setLayout(new BorderLayout());
		
		JPanel ptitulo = new JPanel();
		JLabel ltitulo = new JLabel("Cambiar RBG o GBR");
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
				if(r== JFileChooser.APPROVE_OPTION){
					try{
						imgsel = ImageIO.read(selector.getSelectedFile());
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
		
		JPanel pcambiar = new JPanel();
		pcambiar.setLayout(new BoxLayout(pcambiar, BoxLayout.Y_AXIS));
		
		cambiar = new JCheckBox("Cambiar Color");
		cambiar.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				BufferedImage b = aplicarFiltro();
				if(b != null){
					limgGrises.setIcon(new ImageIcon(b));
				}
				
			}
		});;
		
		pcambiar.add(cambiar);
	
		pprincipal.add(ptitulo, BorderLayout.NORTH);
		pprincipal.add(pimagenes, BorderLayout.CENTER);
		pprincipal.add(pcambiar, BorderLayout.EAST);
		pprincipal.add(pselector,BorderLayout.SOUTH);
		
		getContentPane().add(pprincipal);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1280, 768);
		setLocationRelativeTo(null);

	}
	
	public BufferedImage aplicarFiltro(){
		BufferedImage bi = null;
		if(imgsel != null){
			bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
			Color colorImagen = null;
			
			if(cambiar.isSelected()){
				for (int x = 0; x < imgsel.getWidth(); x++) {
					for (int y = 0; y < imgsel.getHeight(); y++) {
						colorImagen = new Color(imgsel.getRGB(x, y));
						int r1 = colorImagen.getRed();
						int g1 = colorImagen.getGreen();
						int b1 = colorImagen.getBlue();
						bi.setRGB(x, y, new Color(b1,r1,g1).getRGB());
					}
				}
			}else{
				for (int x = 0; x < imgsel.getWidth(); x++) {
					for (int y = 0; y < imgsel.getHeight(); y++) {
						colorImagen = new Color(imgsel.getRGB(x, y));
						int r = colorImagen.getRed();
						int g = colorImagen.getGreen();
						int b = colorImagen.getBlue();
						bi.setRGB(x, y, new Color(r,b,g).getRGB());
					}
				}
			}
		}
		return bi;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		FiltroRGB rgb = new FiltroRGB();

	}

}
