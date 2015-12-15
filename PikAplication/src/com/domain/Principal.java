/**
 * @author Jose Antonio Garrido Siles
 * @version 1.0
 * @since 2015-11-9
 * https://github.com/superjosan/Pikapp
 */


package com.domain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Principal extends JFrame{
	MiniPhoto mp = new MiniPhoto();
	JColorChooser selectorcolor = new JColorChooser();
	
	JPanel jPanel1, jPanel2, jPanel3;
	JButton jButton1, jButton2, jButton3, jButton4, jButton5;
	JScrollPane jScrollPane1;
	JTextField tolerancia;
	JLabel jLabel1;
	
	public Principal() {
		// TODO Auto-generated constructor stub
		initComponents();
		this.setTitle("Cambio de color de una imagen");
		//Se añade el jpanel al scroll
		jScrollPane1.setViewportView(mp);
		//Se añade el jpanel de informaciones
		jPanel1.add(mp.panel_info);
		mp.panel_info.setBounds(16, 30, mp.panel_info.getSize().width, mp.panel_info.getSize().height);
		jPanel1.repaint();
	}
	
	private void initComponents(){
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		
		jScrollPane1 = new JScrollPane();
		
		jLabel1 = new JLabel();
		
		tolerancia = new JTextField();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setName("herramientas");
		
		jButton1.setIcon(new ImageIcon(getClass().getResource("/imagen/folder.png")));
		jButton1.setText("Abrir");
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButton1ActionPerformed(e);
			}
		});
		
		jPanel1.setBorder(BorderFactory.createTitledBorder(" Informacion "));
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 120, Short.MAX_VALUE)
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 250, Short.MAX_VALUE)
	        );
		
		jPanel3.setBorder(BorderFactory.createEtchedBorder());
		jButton2.setIcon(new ImageIcon(getClass().getResource("/imagen/color.png")));
		jButton2.setText("Color");
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButton2ActionPerformed(e);
				
			}
		});
		
		jLabel1.setBackground(new Color(255,255,255));
		jLabel1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		jLabel1.setOpaque(true);
		
		jButton3.setIcon(new ImageIcon(getClass().getResource("/imagen/paint.png")));
		jButton3.setText("Cambiar");
		jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButton3ActionPerformed(e);
			}
		});
		tolerancia.setHorizontalAlignment(JTextField.CENTER);
		tolerancia.setText("15");
		
		jButton5.setIcon(new ImageIcon(getClass().getResource("/imagen/disk.png")));
		jButton5.setText("Guardar");
		jButton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jButton5ActionPerformed(e);	
			}
		});
		
		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
	                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
	                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
	                    .addComponent(tolerancia, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
	                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jButton2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jButton3)
	                .addGap(18, 18, 18)
	                .addComponent(tolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jButton5)
	                .addContainerGap(63, Short.MAX_VALUE))
	        );
	        
		jButton4.setText("Herramientas");
		jButton4.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
	                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jButton1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jButton4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        
		jScrollPane1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt){
				jScrollPane1MouseEntered(evt);
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
				.addContainerGap())
				);


				layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				
				setBounds(0, 0, 1280, 768);
				setLocationRelativeTo(null);
				//pack();
	}
	
	private void jButton1ActionPerformed(ActionEvent evt){
		mp.Open();
	}
	private void jScrollPane1MouseEntered(MouseEvent evt){
		
	}
	private void jButton2ActionPerformed(ActionEvent evt){
		Color color = JColorChooser.showDialog(null, "Seleccione un Color", Color.WHITE);
		jLabel1.setBackground(color);
	}
	private void jButton3ActionPerformed(ActionEvent evt){
		mp.setTolerancia(Integer.valueOf(tolerancia.getText()));
		mp.cambiar_color(jLabel1.getBackground());
	}
	private void jButton5ActionPerformed(ActionEvent evt){
		mp.Save();
	}
	
	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Principal().setVisible(true);
			}
		});
	}

}
