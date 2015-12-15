package com.domain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class EscalarImagen extends JFrame {
	JPanel panelPrincipal;
	PanelDibujo pd;
	boolean sw = false;
	
	public EscalarImagen() {
		// TODO Auto-generated constructor stub
		colocarSkin();
		setLayout(new BorderLayout());
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		JToolBar barra = new JToolBar();
		JMenuItem abrir = new JMenuItem("Abrir");
		abrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser selector = new JFileChooser();
				int resultado = selector.showOpenDialog(null);
				if(resultado == JFileChooser.APPROVE_OPTION){
					try {
						pd = new PanelDibujo(ImageIO.read(selector.getSelectedFile()));
						pd.repaint();
						repaint();
						panelPrincipal.add(pd);
						sw = true;
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
						sw = false;
					}
				}
			}
		});
		
		JMenuItem aumentar = new JMenuItem("Zoom In (+)");
		aumentar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sw){
					pd.ZoomIn(15);
				}
			}
		});
		
		JMenuItem disminuir = new JMenuItem("Zoom Out (-)");
		disminuir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sw){
					pd.ZoomOut(15);
				}
			}
		});
		
		barra.add(abrir);
		barra.add(aumentar);
		barra.add(disminuir);
		add(barra, BorderLayout.NORTH);
		add(panelPrincipal);
	}
	
	public void colocarSkin(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EscalarImagen ei = new EscalarImagen();
		ei.setVisible(true);
		ei.setBounds(0, 0, 800, 600);
		ei.setLocationRelativeTo(null);
		ei.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
