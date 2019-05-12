package com.company;

/**
 * Classe creatrice du code 2D: creation d'une grille (tableau de tableau) de boolean puis generation grphique de celle ci
 */
public class CodeFort {

    boolean[][] code;//grille du code 2D

    public CodeFort(String message) {

        //Creation du HashCode sur 32 bits
        int hash = message.hashCode();

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
        int taille_version = 32;//tailleCode(message_transform_b); //version

        if(taille_version*taille_version < message_transform_b.length){
            System.err.println("MESSAGE TROP LONG");
            return;
        }
        boolean[][] grille_info = new boolean[2][];//[1][]information de format/version et [1][] information de hash
        //info general:
        // masque 3 par default, 0 pour pas de code correcteur et pas de format particulier (ASCII - 8 bit)
        grille_info[0] = info_complementaire(3,0,taille_message,0,taille_version);
        //hash:
        grille_info[1] = Convertisseur.byteArray2BitArray(Convertisseur.intToByte(hash)) ;
        System.out.println("hash  ");
        for(boolean b: grille_info[1] ){
            System.out.print(b+" ");
        }
        System.out.println(" ");

        //transformation en grille de boolean (carre):
        System.out.println("taille_cote_grille "+taille_version);
        boolean[][] grille_boolean = Transformateur.messageEnGrille(message_transform_b,taille_version);//inscrire dans 2 dimension
        System.out.println("taille grille "+grille_boolean.length);



        //mise en place du masque
        //a continuer
        //grille_boolean = Transformateur.masque(grille_boolean,1);
        //grille_boolean = Transformateur.masque(grille_boolean,2);
        grille_boolean = Transformateur.masque(grille_boolean,3);
        //grille_boolean = Transformateur.masque(grille_boolean,4);
        //grille_boolean = Transformateur.masque(grille_boolean,5);
        //grille_boolean = Transformateur.masque(grille_boolean,6);
        //grille_boolean = Transformateur.masque(grille_boolean,7);

        //ajout contour (motif fonctionnel) :
        grille_boolean = Transformateur.contour(grille_boolean);
        grille_boolean = Transformateur.ajout_rempart_info(grille_boolean);
        grille_boolean = Transformateur.contour(grille_boolean);


        //integration des infos complementaire
        grille_boolean = Transformateur.ajout_infos(grille_boolean,grille_info);


        System.out.println("grille apres transformation: "+ grille_boolean.toString());

        //creation de la grille graphiquement:
        Grille g = new Grille(grille_boolean,grille_info,taille_version);

        code = grille_boolean;
    }
//

    /**
     * permet de trouver les information complemataire de format (et dans le future de la version)
     * taille : 32 bits -> 4 bits pour le masque, 4 bits code correcteur, 8 bits pour la taille du message, 8 bits pour le format et 8 bits pour la version
     * @return
     */
    public boolean[] info_complementaire(int masque,int correcteur,int taille, int format, int version){ //et version future
        System.out.println("info_complementaire");
        //ajout du futur format redd salomon avec le taux de correction ainsi que les differents masque utilisé

        boolean[] t_masque = Convertisseur.intToBooleanTab(masque,4);
        boolean[] t_correcteur = Convertisseur.intToBooleanTab(correcteur,4);
        boolean[] t_taille = Convertisseur.intToBooleanTab(taille,8);
        boolean[] t_format = Convertisseur.intToBooleanTab(taille,8);
        boolean[] t_version = Convertisseur.intToBooleanTab(version,8);


        System.out.println("t_masque"+masque+"-->>"+t_masque.length);
        System.out.println("t_correcteur"+correcteur+"-->>"+t_correcteur.length);
        System.out.println("t_taille"+taille+"-->>"+t_taille.length);
        System.out.println("t_format"+format+"-->>"+t_format.length);
        System.out.println("t_version"+version+"-->>"+t_version.length);


        boolean[] t = Outils.concatener_tab_bool(t_masque,t_correcteur,t_taille,t_format,t_version);

        System.out.println("t-->>"+t.length);
        return t;
    }



    /**
     * anciennemant trouver la taille du QRcode adequate (optimale pour le message)
     * @param b tableau de boolean
     * @return
     */
    public int tailleCode(boolean[] b){
        //[...]
        //b.length
        //faire differentes categories selon la taille [...]
        System.out.println("tailleCode "+ b.length);
        Double d = Math.ceil(Math.sqrt(b.length));//racine carre de la taille du message arrondi a l'entier superieur pour mise en tableau de tableau ('carre')
        int taille_cote_grille = d.intValue();
        return taille_cote_grille;//b.length*10;
    }
}
