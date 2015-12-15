package com.domain;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelDibujo extends JPanel {
	Image img;
	Image imgtemp;
	BufferedImage imgmemoria;
	Graphics2D g2D;
	
	int escalaX = 0;
	int escalaY = 0;
	

	public PanelDibujo(BufferedImage f) {
		// TODO Auto-generated constructor stub
		this.img = f;
		this.imgtemp= f;
		
		this.setSize(f.getWidth(), f.getHeight());
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		if(img!=null){
			imgmemoria = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
			g2D = imgmemoria.createGraphics();
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.drawImage(imgtemp, 0, 0, imgtemp.getWidth(this), imgtemp.getHeight(this), this);
			g2.drawImage(imgmemoria, 0, 0, this);
		}
	}
	
	public void ZoomIn(int zoom){
		escalaX = (int) (imgtemp.getWidth(this)*(zoom/100f));
		escalaY = (int) (imgtemp.getHeight(this)*(zoom/100f));
		this.imgtemp = imgtemp.getScaledInstance((int)(imgtemp.getWidth(this)+escalaX),(int)( imgtemp.getHeight(this)+escalaY), Image.SCALE_AREA_AVERAGING);
		this.setSize(imgtemp.getWidth(this),imgtemp.getHeight(this));
	}
	
	public void ZoomOut(int zoom){
		escalaX = (int) (imgtemp.getWidth(this)*(zoom/100f));
		escalaY = (int) (imgtemp.getHeight(this)*(zoom/100f));
		this.imgtemp = imgtemp.getScaledInstance((int)(imgtemp.getWidth(this)-escalaX),(int)( imgtemp.getHeight(this)-escalaY), Image.SCALE_AREA_AVERAGING);
		this.setSize(imgtemp.getWidth(this),imgtemp.getHeight(this));
	}

}
