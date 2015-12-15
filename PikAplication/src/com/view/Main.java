/**
 * @author Jose Antonio Garrido Siles
 * @version 1.0
 * @since 2015-11-9
 * https://github.com/superjosan/Pikapp
 */

package com.view;


import java.awt.BorderLayout;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.domain.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class Main extends JFrame {
	
ColocarMascara cm = new ColocarMascara();
	
	ImageIcon imagenSeleccionada, imagenSeleccionadaAjustada, imagenFiltrada, imagenFiltradaAjustada;
	JPanel pimagenoriginal, pimagenmodificada;
	JLabel limgOriginal, limgModificada;
	BufferedImage imgsel;
	JSlider spromedio;
	JCheckBox cambiarBarrido;
	String extension = "PNG";
	boolean brillo = false, aclarado = false, monocromo = false, gris = false , negativo = false, sepia = false, invertir = false, suavizar = false, 
			media = false, mediana = false, intensificar = false, laplaciano = false , log = false, bordeporfrontera = false, sobel = false, prewitt = false,
			roberts= false, canny = false, dilatacion = false, erosion= false, clustering = false, selector= false;
	BufferedImage imagentratada = null;
	

	public Main() {
		// TODO Auto-generated constructor stub
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		mntmAbrir.setIcon(new ImageIcon(getClass().getResource("/imagen/folder.png")));
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser selector = new JFileChooser();
				int r = selector.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION){
					try {
						brillo = false; aclarado = false; monocromo = false; gris = false; negativo = false; sepia = false; invertir = false; suavizar = false; 
						media = false; mediana = false; intensificar = false; laplaciano = false; log = false; bordeporfrontera = false; sobel = false; 
						prewitt = false; roberts= false; canny = false; dilatacion = false; erosion = false; clustering = false;
						
						imgsel = ImageIO.read(selector.getSelectedFile());
						imagenSeleccionada = new ImageIcon(imgsel);
						Image img = imagenSeleccionada.getImage();
						Image otraimg = img.getScaledInstance(pimagenoriginal.getWidth(), pimagenoriginal.getHeight(), Image.SCALE_SMOOTH);
						imagenSeleccionadaAjustada = new ImageIcon(otraimg);
						limgOriginal.setIcon(imagenSeleccionadaAjustada);
						limgModificada.setIcon(new ImageIcon(this.getClass().getResource("/imagen/fondo.jpg")));
						repaint();
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		});
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mntmGuardar);
		mntmGuardar.setIcon(new ImageIcon(getClass().getResource("/imagen/disk.png")));
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc= new JFileChooser();
				int r = fc.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					File archivo = fc.getSelectedFile();
					
					try {
						ImageIO.write(selectordeFiltro(selector), extension, archivo);
						
					} catch (IOException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				
			}
		});
		
		JMenu mnColor = new JMenu("Color");
		menuBar.add(mnColor);
		
		JMenuItem mntmCambiaColor = new JMenuItem("Cambia el Color");
		mnColor.add(mntmCambiaColor);
		mntmCambiaColor.setIcon(new ImageIcon(getClass().getResource("/imagen/color.png")));
		mntmCambiaColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		mntmCambiaColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Principal principal = new Principal();
				principal.setVisible(true);
				
			}
		});
		
		JMenu mnGrafica = new JMenu("Grafica");
		menuBar.add(mnGrafica);
		
		JMenuItem mntmHistograma = new JMenuItem("Histograma Original");
		mnGrafica.add(mntmHistograma);
		mntmHistograma.setIcon(new ImageIcon(getClass().getResource("/imagen/histo.PNG")));
		mntmHistograma.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		mntmHistograma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Histogram hist = new Histogram();
				hist.setSourceImage(imgsel);
				hist.display();
				
			}
		});
		
		JMenuItem mntmHistogramaFiltrada = new JMenuItem("Histograma Filtrada");
		mnGrafica.add(mntmHistogramaFiltrada);
		mntmHistogramaFiltrada.setIcon(new ImageIcon(getClass().getResource("/imagen/histofilt.png")));
		mntmHistogramaFiltrada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		mntmHistogramaFiltrada.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				representarHistogramaFiltrado();
			}
		});
		
		JMenu mnDibuja = new JMenu("Dibuja");
		menuBar.add(mnDibuja);
		
		JMenuItem mntmDibuja = new JMenuItem("Paint");
		mnDibuja.add(mntmDibuja);
		mntmDibuja.setIcon(new ImageIcon(getClass().getResource("/imagen/draw.png")));
		mntmDibuja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		mntmDibuja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BasicPaint paint = new BasicPaint();
				paint.setVisible(true);
			}
		} );
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAyuda = new JMenuItem("About PikApp");
		mnAyuda.add(mntmAyuda);
		mntmAyuda.setIcon(new ImageIcon(getClass().getResource("/imagen/help.PNG")));
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		mntmAyuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		JPanel pprincipal = new JPanel();
		pprincipal.setLayout(new BorderLayout());
		
		JPanel primagenes = new JPanel(new BorderLayout());
		JSplitPane split = new JSplitPane();
		split.setResizeWeight(0.5);
		pimagenoriginal = new JPanel();
		limgOriginal = new JLabel();
		pimagenoriginal.add(limgOriginal);
		JScrollPane spimgoriginal = new JScrollPane(pimagenoriginal);
		split.setLeftComponent(spimgoriginal);
		pimagenmodificada = new JPanel();
		limgModificada = new JLabel();
		pimagenmodificada.add(limgModificada);
		JScrollPane spimgmodificada = new JScrollPane(pimagenmodificada);
		split.setRightComponent(spimgmodificada);
		
		primagenes.add(split);
		
		JPanel pescalar = new JPanel();
		pescalar.setLayout(new BoxLayout(pescalar, BoxLayout.Y_AXIS));
		pescalar.setBorder(BorderFactory.createTitledBorder("Op"));
		spromedio = new JSlider(0,255);
		spromedio.setValue(3);
		spromedio.setOrientation(SwingConstants.VERTICAL);		
		spromedio.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				// TODO Auto-generated method stub
				
				BufferedImage b = selectordeFiltro(selector);
				if(b!=null){
					imagenFiltrada = new ImageIcon(b);
					Image imgFilt = imagenFiltrada.getImage();
					Image otraimgFilt = imgFilt.getScaledInstance(limgModificada.getWidth(),limgModificada.getHeight(), Image.SCALE_DEFAULT);
					imagenFiltradaAjustada = new ImageIcon(otraimgFilt);
					limgModificada.setIcon(imagenFiltradaAjustada);
				}	
			}
		});
		
		cambiarBarrido = new JCheckBox();
		cambiarBarrido.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				BufferedImage b = selectordeFiltro(selector);
				if(b!=null){
					imagenFiltrada = new ImageIcon(b);
					Image imgFilt = imagenFiltrada.getImage();
					Image otraimgFilt = imgFilt.getScaledInstance(limgModificada.getWidth(),limgModificada.getHeight(), Image.SCALE_DEFAULT);
					imagenFiltradaAjustada = new ImageIcon(otraimgFilt);
					limgModificada.setIcon(imagenFiltradaAjustada);
				}
				
			}
		});
		pescalar.add(cambiarBarrido);
		pescalar.add(new JLabel(""));
		pescalar.add(spromedio);
		pprincipal.add(primagenes, BorderLayout.CENTER);
		pprincipal.add(pescalar, BorderLayout.EAST);
		
		getContentPane().add(pprincipal);
		
		JPanel panelBotones = new JPanel();
		pprincipal.add(panelBotones, BorderLayout.WEST);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.setBorder(BorderFactory.createTitledBorder("Filtros Imagen"));
		
		JButton btnBrillo = new JButton("Brillo");
		btnBrillo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				brillo = true;
				imagentratada = selectordeFiltro(brillo);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnBrillo);
		
		JButton btnAclarado = new JButton("Aclarado");
		btnAclarado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				aclarado = true;
				imagentratada = selectordeFiltro(aclarado);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnAclarado);
		
		
		JButton btnMonocromo = new JButton("Monocromo");
		btnMonocromo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				monocromo = true;
				imagentratada = selectordeFiltro(monocromo);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnMonocromo);
		
		JButton btnNivelesGris = new JButton("Grises");
		btnNivelesGris.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gris = true;
				imagentratada = selectordeFiltro(gris);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnNivelesGris);
		
		JButton btnNegativo = new JButton("Negativo");
		btnNegativo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				negativo = true;
				imagentratada = selectordeFiltro(negativo);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnNegativo);
		
		JButton btnSepia = new JButton("Sepia");
		btnSepia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sepia = true;
				imagentratada = selectordeFiltro(sepia);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnSepia);
		
		JButton btnInvertir = new JButton("Y/X");
		btnInvertir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				invertir = true;
				imagentratada = selectordeFiltro(invertir);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnInvertir);
		
		JButton btnSuavizar = new JButton("Blurring");
		btnSuavizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				suavizar = true;
				imagentratada = selectordeFiltro(suavizar);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnSuavizar);
		
		JButton btnFiltroMedia = new JButton("Media");
		btnFiltroMedia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				media = true;
				imagentratada = selectordeFiltro(media);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnFiltroMedia);
		
		JButton btnFiltroMediana = new JButton("Mediana");
		btnFiltroMediana.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mediana = true;
				imagentratada = selectordeFiltro(mediana);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnFiltroMediana);
		
		JButton btnIntensificar = new JButton("Sharpening");
		btnIntensificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				intensificar = true;
				imagentratada = selectordeFiltro(intensificar);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnIntensificar);
		
		JButton btnLaplaciano = new JButton("Laplace");
		btnLaplaciano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				laplaciano = true;
				imagentratada = selectordeFiltro(laplaciano);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnLaplaciano);
		
		JButton btnLoG = new JButton("LoG");
		btnLoG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				log = true;
				imagentratada = selectordeFiltro(log);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnLoG);
		
		JButton btnBordePorFrontera = new JButton("SobelW");
		btnBordePorFrontera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bordeporfrontera = true;
				imagentratada = selectordeFiltro(bordeporfrontera);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnBordePorFrontera);
		
		JButton btnFiltroSobel = new JButton("Sobel");
		btnFiltroSobel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sobel = true;
				imagentratada = selectordeFiltro(sobel);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnFiltroSobel);
		
		JButton btnFiltroPrewitt = new JButton("Prewitt");
		btnFiltroPrewitt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				prewitt = true;
				imagentratada = selectordeFiltro(prewitt);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnFiltroPrewitt);
		
		JButton btnFiltroRoberts = new JButton("Roberts");
		btnFiltroRoberts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				roberts = true;
				imagentratada = selectordeFiltro(roberts);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnFiltroRoberts);
		
		JButton btnCanny = new JButton("Canny");
		btnCanny.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				canny = true;
				imagentratada = selectordeFiltro(canny);
				representarFiltro(imagentratada);
				
			}
		});
		panelBotones.add(btnCanny);
		
		JButton btnDilation = new JButton("Dilatacion");
		btnDilation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dilatacion = true;
				imagentratada = selectordeFiltro(dilatacion);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnDilation);
		
		JButton btnErosion = new JButton("Erosion");
		btnErosion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				erosion = true;
				imagentratada = selectordeFiltro(erosion);
				representarFiltro(imagentratada);
			}
		});
		panelBotones.add(btnErosion);
		
		JButton btnClustering = new JButton("Clustering");
		btnErosion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clustering = true;
				//CreceRegion growingregion = new CreceRegion();
			}
		});
		panelBotones.add(btnClustering);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1280, 768);
		setLocationRelativeTo(null);
		
	}
	
	
	@SuppressWarnings("unused")
	public static void main (String args[]){
		Main m = new Main();
	}
	
		
	public BufferedImage selectordeFiltro(boolean selector){
		BufferedImage bi = null;
		
		if(brillo == true){
			Brillo shine = new Brillo();
			bi = shine.aplicarBrillo(imgsel,spromedio);
		}
		
		if (aclarado == true) {
			Aclarado brighten = new Aclarado();
			bi = brighten.aplicarAclarado(imgsel);
			
		}
		
		if(monocromo == true){
			Monocromo monochrome = new Monocromo();
			bi = monochrome.aplicarMonocromo(imgsel, spromedio);
		}
		if(gris == true){
			NivelesdeGris graylevel = new NivelesdeGris();
			bi = graylevel.aplicarNivelesGris(imgsel);
			
		}
		if(negativo == true){
			Negativo negative = new Negativo();
			bi = negative.aplicarNegativo(imgsel);
		}
        if(sepia == true){
        	FiltroSepia sepiacolour = new FiltroSepia();
        	bi = sepiacolour.aplicarSepia(imgsel, spromedio);
		}
		
		if(invertir == true){
			InvertirY flip = new InvertirY();
			bi = flip.aplicarInvertir(imgsel, cambiarBarrido);
		}
		
		if(suavizar == true){
			Suavizar blur = new Suavizar();
			bi = blur.aplicarSuavizar(imgsel);
		}
		
		if(media == true){
			MediaGaussiana meanfilter = new MediaGaussiana();
			bi = meanfilter.aplicarMedia(imgsel);
		}
		
		if(mediana == true){
			FiltroMediana medianfilter = new FiltroMediana();
			bi = medianfilter.aplicarMediana(imgsel);
		}
		
		if(intensificar == true) {
			Intensificar sharp = new Intensificar();
			bi = sharp.aplicarIntensificar(imgsel);
			
		}
		
		if(laplaciano == true){
			Laplaciano laplaedge = new Laplaciano();
			bi = laplaedge.aplicarLaplaciano(imgsel);
		}
		
		if(log == true){
			LoG laplaceofgauss = new LoG();
			bi = laplaceofgauss.aplicarLoG(imgsel);
		}
		
		if(bordeporfrontera == true){ 
			DeteccionBordes edge = new DeteccionBordes();
			bi = edge.aplicarBordePorFrontera(imgsel, cambiarBarrido, spromedio);
		}
		
		if(sobel == true){
			
			Sobel sobelfilter = new Sobel();
			bi = sobelfilter.aplicarSobel(imgsel, cambiarBarrido); 
		}
		
		if(prewitt == true){
			
			Prewitt prewittfilter = new Prewitt();
			bi = prewittfilter.aplicarPrewitt(imgsel, cambiarBarrido);
		}
		
		if (roberts == true) {
			
			Roberts robertsfilter = new Roberts();
			bi = robertsfilter.aplicarRoberts(imgsel, cambiarBarrido);
		}
		
		if(canny == true){
			CannyEdgeDetector detector = new CannyEdgeDetector();
			//Puedes ajustar estos parametros como se desee.
			detector.setLowThreshold(0.5f);
			detector.setHighThreshold(1f);
			//Aplica los cambios a la imagen.
			detector.setSourceImage(imgsel);
			detector.process();
			bi = detector.getEdgesImage();
		}
		
		if (dilatacion == true) {
			Dilatacion dilation = new Dilatacion();
			bi = dilation.aplicarDilation(imgsel);
		}
		
		if (erosion == true) {
			Erosion erode = new Erosion();
			bi= erode.aplicarErode(imgsel);
		}
		
		return bi;
	}
	
	
	public void representarHistogramaFiltrado(){
		Histogram histfiltrado = new Histogram();
		histfiltrado.setSourceImage(selectordeFiltro(selector));
		histfiltrado.display();
	}
	
	
	public void representarFiltro(BufferedImage bi){
		
		imagenFiltrada = new ImageIcon(bi);
		Image imgFilt = imagenFiltrada.getImage();
		Image otraimgFilt = imgFilt.getScaledInstance(pimagenoriginal.getWidth(), pimagenoriginal.getHeight(), Image.SCALE_DEFAULT);
		imagenFiltradaAjustada = new ImageIcon(otraimgFilt);
		limgModificada.setIcon(imagenFiltradaAjustada);
		repaint();
		
	}
	

	
}
