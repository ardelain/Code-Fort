package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;

/**
 *  creation de la grille graphiquement (swing : JFrame)
 */
public class Grille extends JFrame
{
    /**
     *
     * @param b
     * @param taille
     */
    public Grille(boolean[][] b, int taille)
    {
        System.out.println("Grille");
        //creation de la fenetre
        JFrame f = new JFrame("Affichage QR CODE en Java");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("taille "+taille);
        System.out.println("b "+b.length+"  b[0] "+b[0].length);
        f.setSize(500, 500);

        //creation du message
        JPanel grille = new Code2D(10,taille,b);//taille/
        f.add(grille);

        //rendre visible
        f.setVisible(true);
    }


    /*public static void main(String argv[])
    {
        Runnable showMyGUI = new Runnable() {
            public void run() {
                new Grille();
            }
        };
        SwingUtilities.invokeLater(showMyGUI);
    }*/
}

/**
 * Generation/remplissage visuel du code barre 2D
 */
class Code2D extends JPanel
{
    int Code_SIZE;
    int Cote_SIZE;

    boolean[][] code;

    public Code2D(int code_SIZE, int cote_SIZE, boolean[][] code) {
        Code_SIZE = code_SIZE;
        Cote_SIZE = cote_SIZE;
        this.code = code;
    }

    public void paintComponent(Graphics g)
    {
        System.out.println("paintComponent");
        //remplissage en blanc
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        //remplissage des cases noires selon la grille des booleean
        g.setColor(Color.BLACK);
        int i=0,x=0,y=0,row=0;
        for(boolean bs[] : code){
            y=0;
            row=0;
            for(boolean b : bs) {
                System.out.print(b);
                System.out.print(" ");
                x =  (i + Code_SIZE /2);
                if(b){
                    g.fillRect(x, y, Code_SIZE, Code_SIZE );
                }

                y+=Code_SIZE;
                row++;
            }
            System.out.println("");
            i+=Code_SIZE;
        }
        /* for (int stripeX = 0; stripeX < Cote_SIZE; stripeX += Code_SIZE) {
            for (int y = 0, row = 0; y < Cote_SIZE; y += Code_SIZE /2, ++row) {
                int x = (row % 2 == 0) ? stripeX : (stripeX + Code_SIZE /2);
                g.fillRect(x, y, Code_SIZE /2, Code_SIZE /2);
            }
        }*/
    }
}