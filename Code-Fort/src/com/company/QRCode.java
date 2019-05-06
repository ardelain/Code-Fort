package com.company;

/**
 *
 */
public class QRCode {

    /**
     *
     * @param message
     */
    public QRCode(String message) {

        //transformation du message en byte     //( possible crytptage ?)
        byte[] message_transform = Convertisseur.StringToBytes(message);
        System.out.println("message en byte: "+ message_transform.toString());
        for(byte b: message_transform){
            System.out.print(b+" ");
        }
        System.out.println(" ");

        int taille_message = message_transform.length;

        message_transform = Transformateur.systeme_err(message_transform);//a faire

        //transformation en tableau bollean (0 blance, 1 noire)
        boolean[] message_transform_b = Convertisseur.byteArray2BitArray(message_transform);//byteArray2BitArray convertToBooleanArray
        System.out.println("message transform_b: "+ message_transform_b.toString());
        for(boolean b: message_transform_b){
            System.out.print(b+" ");
        }
        System.out.println(" ");

        //transformation du message
        int taille = tailleCode(message_transform_b);
        //transformation en grille de boolean (0 blance, 1 noire)

        Double d = Math.ceil(Math.sqrt(message_transform_b.length));//carre de la taille du message arrondi a l'entier superieur pour mise en tableau de tableau ('carre')
        int taille_cote_grille = d.intValue();
        System.out.println("taille_cote_grille "+taille_cote_grille);
        //int cote = message_transform_b.length / 2 ;
        boolean[][] grille_boolean = Transformateur.messageEnGrille(message_transform_b,taille_cote_grille);//inscrire dans 2 dimension
        System.out.println("grille apres transformation: "+ grille_boolean.toString());

        //creation de la grille graphiquement
        Grille g = new Grille(grille_boolean,taille_cote_grille);

    }


    /**
     *trouver la taille du QRcode adequate (optimale pour le message)
     * @return
     */
    public int tailleCode(boolean[] b){
        //[...]
        //b.length
        //faire differentes categories selon la taille ->
        System.out.println("tailleCode "+ b.length);
        return b.length*10;
    }
}
