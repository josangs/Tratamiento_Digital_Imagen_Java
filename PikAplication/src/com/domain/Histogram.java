/**
 * @author Jose Antonio Garrido Siles
 * @version 1.0
 * @since 2015-11-9
 * https://github.com/superjosan/Pikapp
 */


package com.domain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

@SuppressWarnings("serial")
public class Histogram extends JFrame {
	public Histogram() {
	}
	
	private static final int BINS = 256;
	public BufferedImage sourceImage;
	DesviacionEstandar desviacion = new DesviacionEstandar();
	
	
	public BufferedImage getSourceImage(){
		return sourceImage;
	}
	
	public void setSourceImage(BufferedImage image){
		sourceImage = image;
		
	}
	
	
	private ChartPanel createChartPanel(){
		//Calculo los datos del Histograma con la metodo Raster.
		HistogramDataset dataset = new HistogramDataset();
		Raster raster = sourceImage.getRaster();
		final int w = sourceImage.getWidth();
		final int h = sourceImage.getHeight();
		double[] r = new double[w * h];
		r = raster.getSamples(0, 0, w, h, 0, r);
		dataset.addSeries("Red", r, BINS);
		r = raster.getSamples(0, 0, w, h, 1, r);
		dataset.addSeries("Green", r, BINS);
		r = raster.getSamples(0, 0, w, h, 2, r);
		dataset.addSeries("Blue", r, BINS);
		
		//Dibujamos la Gráfica.
		JFreeChart chart = ChartFactory.createHistogram("Histograma", "Valor", "Pixel", dataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardXYBarPainter());
		//Gráficas translucidad rojo, verde y azul.
		Paint[] paintArray = {
				new Color(0x80ff0000, true),
				new Color(0x8000ff00, true),
				new Color(0x800000ff, true)
		};
		plot.setDrawingSupplier(new DefaultDrawingSupplier(paintArray,
				DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
		ChartPanel panelHistograma = new ChartPanel(chart);
		panelHistograma.setMouseWheelEnabled(true);
		return panelHistograma;
	}
	
	
	public void display(){
		
		
		JFrame f = new JFrame("Histograma");
		desviacion.setSourceImage(sourceImage);
		
		
		f.getContentPane().add(createChartPanel(),BorderLayout.CENTER);
		f.getContentPane().add(new JLabel(new ImageIcon(sourceImage)), BorderLayout.WEST);
		f.getContentPane().add(desviacion, BorderLayout.SOUTH);
		f.repaint();
		
		f.getContentPane();
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Histogram h = new Histogram();
		h.display();
	}
	
	

}
