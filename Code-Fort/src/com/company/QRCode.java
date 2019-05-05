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

        //transformation en tableau bollean (0 blance, 1 noire)
        boolean[] message_transform_b = Convertisseur.byteArray2BitArray(message_transform);//byteArray2BitArray convertToBooleanArray

        //transformation du message
        int taille = tailleCode(message_transform_b);
        //transformation en grille de boolean (0 blance, 1 noire)
        Double d = Math.sqrt(message_transform_b.length);//prend la racine de la taille du message pour  minimiser la taille/categorie a inscrire dans 2 dimension
        boolean[][] grille_boolean = Transformateur.messageEnGrille(message_transform_b,d.intValue());
        System.out.println("grille apres transformation: "+ grille_boolean.toString());

        //creation de la grille graphiquement
        Grille g = new Grille(grille_boolean,taille);

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
