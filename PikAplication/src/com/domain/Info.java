package com.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Info extends JPanel {
	
	private JTextField B;
	private JTextField G;
	private JTextField R;
	private JTextField X;
	private JTextField Y;
	private JLabel color;
	private JLabel seleccionado;
		
	public Info() {
		// TODO Auto-generated constructor stub
		GridBagConstraints gridBagConstraints; 
		JPanel jPanel1 = new JPanel();
		JLabel jLabel1 = new JLabel();
		JLabel jLabel2 = new JLabel();
		JLabel jLabel3 = new JLabel();
		
		R = new JTextField();
		G = new JTextField();
		B = new JTextField();
		
		X = new JTextField();
		Y = new JTextField();
		color = new JLabel();
		seleccionado = new JLabel();
		
		JLabel jLabel4 = new JLabel();
		JLabel jLabel5 = new JLabel();
		JPanel jPanel2 = new JPanel();
		
		jPanel1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		jPanel1.setLayout(new GridBagLayout());
		
		jLabel1.setText("R: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(jLabel1, gridBagConstraints);
		
		jLabel2.setText("G");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx= 0;
		gridBagConstraints.gridy= 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(jLabel2, gridBagConstraints);
		
		jLabel3.setText("B");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(jLabel3, gridBagConstraints);
		
		R.setEditable(false);
		R.setText("0");
		R.setPreferredSize(new Dimension(50,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(R, gridBagConstraints);
		
		G.setEditable(false);
		G.setText("0");
		G.setPreferredSize(new Dimension(50,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(G, gridBagConstraints);
		
		B.setEditable(false);
		B.setText("0");
		B.setPreferredSize(new Dimension(50,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(B, gridBagConstraints);
		
		X.setEditable(false);
		X.setText("0");
		X.setPreferredSize(new Dimension(50,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(4,5,4,4);
		jPanel1.add(X, gridBagConstraints);
		
		Y.setEditable(false);
		Y.setText("0");
		Y.setPreferredSize(new Dimension(50,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(Y, gridBagConstraints);
		
		jLabel4.setText("X:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(jLabel4, gridBagConstraints);
		
		jLabel5.setText("Y");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx= 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(4, 5, 4, 4);
		jPanel1.add(jLabel5, gridBagConstraints);
		
		jPanel2.setPreferredSize(new Dimension(81,35));
		jPanel2.setLayout(new GridBagLayout());
		
		color.setBackground(new Color(255,255,255));
		color.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		color.setOpaque(true);
		color.setPreferredSize(new Dimension(25,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		jPanel2.add(color, gridBagConstraints);
		
		seleccionado.setBackground(new Color(255, 255, 255));
		seleccionado.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		seleccionado.setOpaque(true);
		seleccionado.setPreferredSize(new Dimension(25,25));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		jPanel2.add(seleccionado, gridBagConstraints);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	      }
	public void setX(int x){
		X.setText(String.valueOf(x));
	}
	public void setY(int y){
		Y.setText(String.valueOf(y));
	}
	public void setR(int r){
		R.setText(String.valueOf(r));
	}
	public void setG(int g){
		G.setText(String.valueOf(g));
	}
	public void setB(int b){
		B.setText(String.valueOf(b));
	}
	public void setColor(Color c){
		this.color.setBackground(c);
	}
	public void setColorSeleccion(Color c){
		this.seleccionado.setBackground(c);
	}
	
	

}
