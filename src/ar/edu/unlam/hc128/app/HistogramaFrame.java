package ar.edu.unlam.hc128.app;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ar.edu.unlam.hc128.utils.*;
import ar.edu.unlam.hc128.visual.NuevoJPanel;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HistogramaFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    private NuevoJPanel jPanel1;
    private javax.swing.JPanel jPanel_alfa;
    private javax.swing.JPanel jPanel_azul;
    private javax.swing.JPanel jPanel_rojo;
    private javax.swing.JPanel jPanel_verde;
    private File imagen;
    private String titulo;
    // End of variables declaration//GEN-END:variables

	public HistogramaFrame(File imagen, String titulo) throws HeadlessException, IOException {
		this.imagen = imagen;
		this.titulo = titulo;
		initComponents();
		dibujar(imagen);
	}
	
	private void initComponents() {
		jPanel1 = new NuevoJPanel();
        jPanel_rojo = new javax.swing.JPanel();
        jPanel_verde = new javax.swing.JPanel();
        jPanel_alfa = new javax.swing.JPanel();
        jPanel_azul = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle(this.titulo);
        setVisible(true);

        jPanel_rojo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel_rojoLayout = new javax.swing.GroupLayout(jPanel_rojo);
        jPanel_rojo.setLayout(jPanel_rojoLayout);
        jPanel_rojoLayout.setHorizontalGroup(
            jPanel_rojoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_rojoLayout.setVerticalGroup(
            jPanel_rojoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );

        jPanel_verde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel_verdeLayout = new javax.swing.GroupLayout(jPanel_verde);
        jPanel_verde.setLayout(jPanel_verdeLayout);
        jPanel_verdeLayout.setHorizontalGroup(
            jPanel_verdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );
        jPanel_verdeLayout.setVerticalGroup(
            jPanel_verdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
        
        jPanel_alfa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel_alfaLayout = new javax.swing.GroupLayout(jPanel_alfa);
        jPanel_alfa.setLayout(jPanel_alfaLayout);
        jPanel_alfaLayout.setHorizontalGroup(
            jPanel_alfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_alfaLayout.setVerticalGroup(
            jPanel_alfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        jPanel_azul.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel_azulLayout = new javax.swing.GroupLayout(jPanel_azul);
        jPanel_azul.setLayout(jPanel_azulLayout);
        jPanel_azulLayout.setHorizontalGroup(
            jPanel_azulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        jPanel_azulLayout.setVerticalGroup(
            jPanel_azulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        		jPanel1Layout.createSequentialGroup()
        		.addComponent(jPanel_azul, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        		.addGap(32)
				.addComponent(jPanel_rojo, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
				.addGap(32)
				.addComponent(jPanel_verde, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
        		jPanel1Layout.createParallelGroup()
        		.addComponent(jPanel_azul, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        		.addGap(32)
				.addComponent(jPanel_rojo, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
				.addGap(32)
				.addComponent(jPanel_verde, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE));
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
	}
	
	private void dibujar(File Image) throws IOException {
		Image imagen=ImageIO.read(Image);
    	Histograma ObjHistograma=new Histograma();
        int[][] histograma=ObjHistograma.histograma((BufferedImage)imagen);
        
      //DIBUJAMOS LOS HISTOGRAMAS
        DibujoGrafico ObjDibujaHisto=new DibujoGrafico();
        for (int i = 0; i < 5; i++) {
            //extraemos un canal del histograma 
            int[] histogramaCanal=new int[256];
            System.arraycopy(histograma[i], 0, histogramaCanal, 0, histograma[i].length);
            //Dibujamos en el panel
            switch(i){
                case 0:
                    ObjDibujaHisto.crearHistograma(histogramaCanal, jPanel_rojo, Color.red);
                    break;
                case 1:
                    ObjDibujaHisto.crearHistograma(histogramaCanal, jPanel_verde, Color.green);
                    break;
                case 2:
                    ObjDibujaHisto.crearHistograma(histogramaCanal, jPanel_azul, Color.blue);
                    break;
                case 3:
                    ObjDibujaHisto.crearHistograma(histogramaCanal, jPanel_alfa, Color.black);
                    break;
            }
        }
	}

}
