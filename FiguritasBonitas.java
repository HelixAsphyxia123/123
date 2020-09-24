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

public class FiguritasBonitas extends JPanel {

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
    private int coordenada_x, coordenada_y, tiempofinal = 5;

    //declarar hilo
    private Thread hilo;

    public FiguritasBonitas() {
        //inicializar la ventana
        ventana = new JFrame("Dibujando figurita VLCA");
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
            grphcs.setColor(Color.cyan);
        }
    }

    public void dibujar() {
        try {
            int contador = 0;
            int opc;
            while (contador < 10) {

                opc = (int) (Math.random() * 4);
                switch (opc) {
                    case 1:
                        ABAJO();
                        contador += 1;
                        System.out.println("Se va a romper el vidrio! " + contador);
                        break;
                    case 2:
                        ARRIBA();
                        contador += 1;
                        System.out.println("Se va a romper el vidrio! " + contador);
                        break;
                    case 3:
                        DERECHA();
                        contador += 1;
                        System.out.println("Se va a romper el vidrio! " + contador);
                        break;
                    case 4:
                        IZQUIERDA();
                        contador += 1;
                        System.out.println("Se va a romper el vidrio! " + contador);
                        break;
                    default:
                }
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }
//Metodo que recorre hacia abajo
    public void ABAJO() {
        coordenada_x = (int) (Math.random() * 500);
        coordenada_y = (int) (Math.random() * 500);
        for (int i = coordenada_x + 60; i < AltoVentana; i++) {
            try {

                coordenada_x = coordenada_x + 1;
                hilo.sleep(tiempofinal);
                paint(getGraphics());

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Se recorrieron = " + i + " hacia abajo");
        }        
    }

    //Metodo que recorre hacia arriba
    public void ARRIBA() {
        this.coordenada_x = (int) (Math.random() * 500);
        this.coordenada_y = (int) (Math.random() * 500);
        for (int i = coordenada_x; i > 0; i--) {
            try {

                coordenada_x = this.coordenada_x - 1;
                hilo.sleep(tiempofinal);
                paint(getGraphics());

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Se recorrio = " + i + "hacia arriba");
        }
    }

    //Metodo que recorre a la derecha
    public void DERECHA() {
        coordenada_x = (int) (Math.random() * 500);
        coordenada_y = (int) (Math.random() * 500);
        for (int i = coordenada_y + 30; i < AnchoVentana; i++) {
            try {
                this.coordenada_y = coordenada_y + 1;
                this.hilo.sleep(tiempofinal);
                paint(getGraphics());

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Se recorrio = " + i + "hacia la derecha");
        }

    }

    //Metodo que recorre a la izquierda
    public void IZQUIERDA() {
        coordenada_x = (int) (Math.random() * 500);
        coordenada_y = (int) (Math.random() * 500);
        for (int i = coordenada_y; i > 0; i--) {
            try {
                this.coordenada_y = coordenada_y - 1;
                this.hilo.sleep(tiempofinal);
                paint(getGraphics());

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Se recorrio = " + i + "hacia la izquierda");
        }
    }

    public static void main(String[] args) {
        FiguritasBonitas animacion = new FiguritasBonitas();
        animacion.dibujar();
    }
}
