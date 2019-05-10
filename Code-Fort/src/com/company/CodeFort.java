package com.company;

/**
 * Classe creatrice du code 2D: creation d'une grille (tableau de tableau) de boolean puis generation grphique de celle ci
 */
public class CodeFort {

    boolean[][] code;//grille du code 2D

    public CodeFort(String message) {

        //transformation du message en byte
        byte[] message_transform = Convertisseur.StringToBytes(message);
        System.out.println("message en byte: "+ message_transform.toString());
        for(byte b: message_transform){
            System.out.print(b+" ");
        }
        System.out.println(" ");

        int taille_message = message_transform.length;

        //ajout du systeme de correction d'erreur
        message_transform = Transformateur.systeme_err(message_transform);//a faire

        //transformation en tableau boolean (0 blance, 1 noire):
        boolean[] message_transform_b = Convertisseur.byteArray2BitArray(message_transform);//byteArray2BitArray convertToBooleanArray
        System.out.println("message transform_b: "+ message_transform_b.toString());
        for(boolean b: message_transform_b){
            System.out.print(b+" ");
        }
        System.out.println(" ");

        //definition des caracteristique spécifique du message (version,taille message,...)
        //a redefinir [...]
        int taille = tailleCode(message_transform_b);
        Double d = Math.ceil(Math.sqrt(message_transform_b.length));//racine carre de la taille du message arrondi a l'entier superieur pour mise en tableau de tableau ('carre')
        int taille_cote_grille = d.intValue();

        boolean[][] grille_info = new boolean[2][];//[1][]information de format et [1][] information de version
        grille_info[0] = info_format(message_transform_b);
        grille_info[1] = info_version(message_transform_b);

        //transformation en grille de boolean (carre):
        System.out.println("taille_cote_grille "+taille_cote_grille);
        boolean[][] grille_boolean = Transformateur.messageEnGrille(message_transform_b,taille_cote_grille);//inscrire dans 2 dimension
        System.out.println("taille grille "+grille_boolean.length);


        //mise en place du masque
        //a continuer
        //grille_boolean = Transformateur.masque(grille_boolean,1);
        //grille_boolean = Transformateur.masque(grille_boolean,2);
        //grille_boolean = Transformateur.masque(grille_boolean,3);
        //grille_boolean = Transformateur.masque(grille_boolean,4);
        //grille_boolean = Transformateur.masque(grille_boolean,5);
        //grille_boolean = Transformateur.masque(grille_boolean,6);
        //grille_boolean = Transformateur.masque(grille_boolean,7);

        //ajout contour (motif fonctionnel) :
        grille_boolean = Transformateur.contour(grille_boolean);
        grille_boolean = Transformateur.ajout_rempart_info(grille_boolean);
        grille_boolean = Transformateur.contour(grille_boolean);

        System.out.println("grille apres transformation: "+ grille_boolean.toString());

        //creation de la grille graphiquement:
        Grille g = new Grille(grille_boolean,grille_info,taille_cote_grille);

        code = grille_boolean;
    }


    /**
     *
     * @param infos
     * @return
     */
    public boolean[] info_format(boolean[] infos){
        //[...]
        return infos;
    }

    /**
     *
     * @param infos
     * @return
     */
    public boolean[] info_version(boolean[] infos){
        //[...]
        return infos;
    }


    /**
     * trouver la taille du QRcode adequate (optimale pour le message)
     * @param b tableau de boolean
     * @return
     */
    public int tailleCode(boolean[] b){
        //[...]
        //b.length
        //faire differentes categories selon la taille [...]
        System.out.println("tailleCode "+ b.length);
        return b.length*10;
    }
}
