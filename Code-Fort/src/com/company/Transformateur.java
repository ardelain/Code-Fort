package com.company;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Transformateur {
    /**
     * permet de transfromer un tableau de bloolean en tableau de tableau carre ()
     * @param mess
     * @return
     */
    public static boolean[][] messageEnGrille(boolean[] mess, int taille){
        System.out.println("messageEnGrille");
        int y = 0;
        boolean[][] grille = new boolean[taille][taille];
        for(int i = 0; i < taille; i++) { //(pour noter de gauche a droite du haut vers le bas au lieu de l'inverse : inverser i et j )
            for(int j = 0; j < taille; j++){
                if(y < mess.length){
                    grille[i][j] = mess[y];
                    y++;
                }else{
                    break;
                    //grille[i][j] = false;
                }
                System.out.print(grille[i][j]+" ");
            }
            System.out.print("\n");
        }
        return grille;
    }


    /**
     *
     * @param b
     * @param b_info
     * @return
     */
    public static boolean[][] ajout_infos(boolean[][] b,boolean[][] b_info){//,boolean[] b_info, int racine_sup
        System.out.println("contour ajout_r_info ");
        System.out.println("b_info "+b_info.length);
        System.out.println("b "+b.length);
        int y = 0;
        //ajout des bords
        for(int i = 0;i<b.length;i++){
            System.out.println("i "+i +" y "+y);
            if(i>= 7 && i<=b.length-7 ){
                //haut
                if(y < b_info[0].length){
                    //b[i][4] = true;
                    b[i][4] = b_info[0][y];
                }
                //bas
                if(y < b_info[1].length){
                   //b[i][b.length-5] = true;
                    b[i][b.length-5] = b_info[1][y];
                }


                //gauche
                if(y < b_info[0].length){
                    b[4][i] = b_info[0][y];//ligne noire
                }

                //droite
                if(y < b_info[1].length){
                    b[b.length-5][i] = b_info[1][y];//ligne noire
                }


                y++;
            }
        }

        return b;
    }

    /**
     * contourne le datagramme d'une ligne blacnhe et noire
     * @param by
     * @return
     */
    public static boolean[][] contour(boolean[][] by){
        System.out.println("contour ");

        boolean[][] nb = new boolean[by.length+4][by.length+4];
        System.out.println("taille grille: "+by.length);
        System.out.println("taille nv grille: "+nb.length);

        //transfert du contenu de la grille
        for(int i = 0;i<by.length;i++){
            for(int j = 0;j<by.length;j++){
                nb[i+2][j+2] = by[i][j];
            }
        }

        //ajout des bords
        for(int i = 0;i<nb.length;i++){
            //haut
            nb[i][0] = true;
            //bas
            nb[i][nb.length-1] = true;
            //gauche
            nb[0][i] = true;//ligne noire
            if(i == 0 || i+1 == nb.length){//pour 'finir' le contour
                nb[1][i] = true;//ligne blanche
            }else{
                nb[1][i] = false;//ligne blanche
            }
            //droite
            nb[nb.length-1][i] = true;//ligne noire
            if(i == 0 || i+1 == nb.length){ //pour 'finir' le contour (ligne blanche entouré de noir)
                nb[nb.length-2][i] = true;
            }else{
                nb[nb.length-2][i] = false;//ligne blanche
            }
        }

        return nb;
    }

    public static boolean[][] ajout_rempart_info(boolean[][] b){//,boolean[] b_info, int racine_sup
        System.out.println("contour ajout_r_info ");

        boolean[][] nb = new boolean[b.length+6][b.length+6];

        //transfert du contenu de la grille
        for(int i = 0;i<b.length;i++){
            for(int j = 0;j<b.length;j++){
                nb[i+3][j+3] = b[i][j];
            }
        }
        //ajout des bords
        for(int i = 0;i<nb.length;i++){
            //haut
            nb[i][0] = true;
            //bas
            nb[i][nb.length-1] = true;
            //gauche
            nb[0][i] = true;//ligne noire
            //droite
            nb[nb.length-1][i] = true;//ligne noire
        }

        return nb;
    }

    /**
     * permet d'ajouter le systeme d'erreur sur un message
     * @param b
     * @return
     */
    public static byte[] systeme_err(byte[] b){
        //[...]
        return b;
    }

    /**
     * permet de passer un masque de 'repartition" pour la grille du message cree
     * @param b
     * @return
     */
    public static boolean[][] masque(boolean[][] b,int masque){
        //[...]
        for(int i = 0; i< b.length; i++){
            for(int j = 0; j< b.length; j++){
                if(b[i][j]){

                }
                //int x = i-1;
                if (getDataMaskBit(masque, i, j)) {
                    b[i][j] = !b[i][j];
                }

            }
        }
        return b;
    }

    /**
     * Copyright 2008 ZXing authors
     * Return the mask bit for "getMaskPattern" at "x" and "y". See 8.8 of JISX0510:2004 for mask
     * pattern conditions.
     */
    static boolean getDataMaskBit(int maskPattern, int x, int y) {
        int intermediate;
        int temp;
        switch (maskPattern) {
            case 0:
                intermediate = (y + x) & 0x1;
                break;
            case 1:
                intermediate = y & 0x1;
                break;
            case 2:
                intermediate = x % 3;
                break;
            case 3:
                intermediate = (y + x) % 3;
                break;
            case 4:
                intermediate = ((y / 2) + (x / 3)) & 0x1;
                break;
            case 5:
                temp = y * x;
                intermediate = (temp & 0x1) + (temp % 3);
                break;
            case 6:
                temp = y * x;
                intermediate = ((temp & 0x1) + (temp % 3)) & 0x1;
                break;
            case 7:
                temp = y * x;
                intermediate = ((temp % 3) + ((y + x) & 0x1)) & 0x1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + maskPattern);
        }
        return intermediate == 0;
    }

}
