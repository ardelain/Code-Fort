package com.company;

/**
 *
 */
public class Transformateur {
    /**
     * permet de transfromer un messsage en byte pour l'integrer au gr code (transformation en grille)
     * @param mess
     * @return
     */
    public static boolean[][] messageEnGrille(boolean[] mess, int taille){
        System.out.println("messageEnGrille");
        int y = 0;
        boolean[][] grille = new boolean[taille][taille];
        for(int i = 0; i < taille; i++) {
            for(int j = 0; j < taille; j++){
                if(y < mess.length){
                    grille[i][j] = mess[y];
                    y++;
                }else{
                    grille[i][j] = false;
                }
                System.out.print(grille[i][j]+" ");
            }
            System.out.print("\n");
        }

        grille = masque(grille);//a faire
        grille = systeme_err(grille);//a faire
        grille = contour(grille);


        return grille;
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

    /**
     * permet d'ajouter le systeme d'erreur sur un message
     * @param b
     * @return
     */
    public static boolean[][] systeme_err(boolean[][] b){
        //[...]
        return b;
    }

    /**
     * permet de passer un masque de 'repartition" pour la grille du message cree
     * @param b
     * @return
     */
    public static boolean[][] masque(boolean[][] b){
        //[...]
        return b;
    }
}