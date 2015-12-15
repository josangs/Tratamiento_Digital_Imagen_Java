package com.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CreceRegion extends JFrame{

	public CreceRegion() {
		// TODO Auto-generated constructor stub
		super("Clustering");
		setBounds(10,10,600,450);
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		setContentPane(new panel());
		setVisible(true);
	}
	
	public class panel extends JPanel{
		BufferedImage image;
		
		public panel(){
			image = new BufferedImage(500, 400, BufferedImage.TYPE_INT_ARGB);
			Graphics gr = image.getGraphics();
			for (int x = 0; x < 50; x++) {
				for (int y = 0; y < 50; y++) {
					int c = (int)(Math.random()*100)+100;
					Color color = new Color(c,c,c);
					gr.setColor(color);
					gr.fillRect(x*10, y*10, 10, 10);
				}
				addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent m){
						int x = m.getX();
						int y = m.getY();
						take_it(x,y,image.getRGB(x, y));
						repaint();
					}
				});
				
			}
		}
			
			private void take_it(int x, int y, int rgb){
				if(x < 0 || y < 0) return;
				if(x >= image.getWidth(this)) return;
				if(y >= image.getHeight(this)) return;
				if (image.getRGB(x, y)/1000000 == rgb/1000000) {
					image.setRGB(x, y, Color.red.getRGB());
					take_it(x+1, y, rgb);
					take_it(x-1, y, rgb);
					take_it(x, y+1, rgb);
					take_it(x, y-1, rgb);
					
				}
			}
			
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(image, 1, 1, null);
				g.drawRect(0, 0, 501, 401);
			}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CreceRegion cr = new CreceRegion();

	}

}
