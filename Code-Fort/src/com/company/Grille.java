package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *  creation de la grille graphiquement (swing : JFrame)
 */
public class Grille extends JFrame
{

    /**
     *
     * @param message
     * @param info
     */
    public Grille(boolean[][] message, boolean[][] info, int taille)
    {
        System.out.println("Grille JFrame");
        //creation de la fenetre
        JFrame f = new JFrame("Code-Fort");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 550);

        System.out.println("taille message"+taille);
        System.out.println("message length "+message.length+"  message[0] length "+message[0].length);

        //creation du message
        JPanel grille = new Code2D(10,taille,message,info);//taille/
        f.add(grille);

        JButton button = new JButton("Générer Image");
        //ajout bouton pour générer l'image correspondante
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /* init du filechooser */
                JFileChooser fc = new JFileChooser();
                /* affichage du dialog et test si le bouton ok est pressé */
                if (fc.showDialog(f,"générer") == JFileChooser.APPROVE_OPTION) {
                    try {
                        /* demande au système d'ouvrir le fichier précédemment séléctionné */
                        Desktop.getDesktop().open(fc.getCurrentDirectory());
                        //generation automatique de l'image
                        BufferedImage img = new BufferedImage(grille.getWidth(), grille.getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = img.createGraphics();
                        grille.printAll(g2d);
                        g2d.dispose();
                        try {
                            ImageIO.write(img, "png", new File(fc.getSelectedFile()+".png"));
                        }catch (Exception ee){
                            System.err.println("PROBLEME GENERATION IMAGE");
                            return;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        f.add(button, BorderLayout.SOUTH);

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
    boolean[][] info;

    public Code2D(int code_SIZE, int cote_SIZE, boolean[][] code, boolean[][] info) {
        Code_SIZE = code_SIZE;
        Cote_SIZE = cote_SIZE;
        this.code = code;
        this.info = info;
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