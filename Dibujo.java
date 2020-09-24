/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Icono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dibujo extends JPanel {

    // ventana
    private JFrame ventana;
    //contenedor
    private Container contenedor;
    private int AnchoVentana = 800, AltoVentana = 600;

    //Hexadecimal de la figura
    private final int[] FIGURA = {
        
        0x0FFFFFF0,
0x0FFFFFF0,
0x00018000,
0x00018000,
0x00018000,
0x00018000,
0x0FFFFFF0,
0x0FFFFFF0,
0x0FFFFFF0,
0x00018000,
0x00018000,
0x00018000,
0x00018000,
0x0FFFFFF0,
0x0FFFFFF0

       
    };

    //mascara
    private final int MASCARA = 0x0000001;

    //ancho de bits
    private final int ANCHO_BITS = 32;

    //coordenadas
    private int coordenada_x, coordenada_y;

    //declarar hilo
    private Thread hilo;

    public Dibujo() {//esto no lo sabia crtl + espacio
        //inicializar la ventana
        ventana = new JFrame("Dibujando icono");
        //definir tamaño a ventana

        ventana.setSize(AnchoVentana, AltoVentana);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        //Configurando contenedor
        contenedor = ventana.getContentPane();
        contenedor.setSize(AnchoVentana, AltoVentana);

        //agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);

        //definir hilo
        hilo = new Thread();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
    
        for (int i = 0; i < this.FIGURA.length; i++) {
            //iterador para el ancho en bits de la figura
            for (int j = 0; j < this.ANCHO_BITS; j++) {
                int temp = this.FIGURA[i] & (this.MASCARA << j);
                if (temp != 0) {
                    grphcs.drawLine(this.coordenada_y + j,
                            this.coordenada_x + i,
                            this.coordenada_y + j,
                            this.coordenada_x + i);
                }
            }
        }
            
    }

    public void dibujar() {       

       try {
            int bandera = 0;
            int banderax = 0;
            int banderay = 0;
            int contador = 0;
            int opc;
            while (contador<10) {                                
                   if (banderax==0) {
                       coordenada_x+=10;
                   }else{
                       coordenada_x-=10;
                  }
                   if (banderay==0) {                       coordenada_y+=10;
                   }else{
                       coordenada_y-=10;
                  }
                   
                   if (coordenada_x+60==AltoVentana) {
                       banderax = 1;
                        contador+=1;                        
                    }
                   if (coordenada_x==0) { 
                    banderax = 0;
                      contador+=1;
                  }
                    if(coordenada_y+30==AnchoVentana){
                        banderay = 1;
                        contador+=1;
                   }
                   if (coordenada_y==0) {
                       banderay = 0;                       contador+=1;
                   }
                
                   this.hilo.sleep(50);
                   paint(getGraphics()); 
                   System.out.println("OUCH "+contador);

do {
                 opc = (int) (Math.random()*3);
                switch (opc) {
                    case 1:  
                        if (banderax==0) {
                        coordenada_x+=10;
                    }
                        else{
                       coordenada_x-=10;
                   }
                        if (coordenada_x+60==AltoVentana) {
                        banderax = 1;
                        contador+=1;                        
                    }
                    if (coordenada_x==0) { 
                        banderax = 0;
                        contador+=1;
                    }
                        this.hilo.sleep(200);
                        paint(getGraphics());
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:                        
                }
            
        } while (opc!=3);
            }
//            
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
            
        
    }

    public static void main(String[] args) {
        Dibujo d = new Dibujo();
        d.dibujar();

    }
}
