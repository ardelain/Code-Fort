package com.company;

import java.util.Arrays;

public class Outils {

    /**
     * @param t1 premier tableau
     * @param t2 restes des tableau
     * @return touveau tableau
     */
    public static boolean[] concatener_tab_bool(boolean[] t1,boolean[] ... t2){
        int taille =  t1.length;
        for(boolean[] t: t2){
            taille += t.length;
        }
        boolean[] tab = Arrays.copyOf(t1,taille);
        int top = t1.length;
        for(boolean[] t: t2){
            System.arraycopy(t,0,tab,top,t.length);
            top +=t.length;
        }
        return tab;
    }

}
