package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

/**
 * Classe Fonction regroupe des fonctions permettant le fonctionnement du jeu
 *
 * @author MUSTAPHA TOUIL
 * @version
 */

public class Fonction {

    private static final Logger LOGGER = LogManager.getLogger(Fonction.class.getName());

    /**
     * creation d'un nombre aleatoire
     *
     * @param longueurC longueur de la combinaison
     * @return tab1
     */

    public static int[] random(int longueurC) {

        Random rnd = new Random();
        int tab1[] = new int[longueurC];
        for (int loop = 0; loop < longueurC; loop++) {
            int mystere = rnd.nextInt(10);
            tab1[loop] = mystere;
        }
        return tab1;
    }

    /**
     * creation d'une fonction random avec en parametre trois tableaux pour permettre de faire évoluer la reponse de l'IA
     *
     * @param reponse
     * @param borneDuRandom
     * @param tableauDeVerification
     */

    public static void random(int reponse[], Borne[] borneDuRandom, char[] tableauDeVerification){
        Random r = new Random();
        /*Boucle avec condition permettant d'enregistrer la proposition de l'IA
        afin de remplir un tableau de verification suite à la saisie des reponses par l'utilisateur
        dans le but d'orienter et faire évoluer la reponse de l'IA
       */
        for (int index = 0; index < reponse.length; index++) {
            if ('=' == tableauDeVerification[index]) {
                continue;
            } else if ('+' == tableauDeVerification[index]) {
                borneDuRandom[index].setMinValue(reponse[index]);
            } else if ('-' == tableauDeVerification[index]) {
                borneDuRandom[index].setMaxValue(reponse[index]);
            }

            int minValue = borneDuRandom[index].getMinValue();
            int maxValue = borneDuRandom[index].getMaxValue();

            int mystere = r.nextInt(maxValue - minValue) + minValue;
            reponse[index] = mystere;
        }
    }

    /**
     * prise en compte da la saisie utilisateur
     *
     * @param longueurC
     * @param saisiejoueur
     * @return tab2 Tableau de la saisie du joueur en caractere convertie en entier
     */

    public static int[] player(int longueurC,String saisiejoueur){

        int tab2[] = new int[longueurC];
        for (int loop = 0; loop < longueurC; loop++) {
            int conversion = Integer.parseInt(String.valueOf(saisiejoueur.charAt(loop)));
            tab2[loop]= conversion;
    }
       return tab2;
    }

    /**
     *Fonction permettant l'affichage avec une sortie des indices alignes à la suite
     *
     * @param longueurC
     * @param propositionIa
     * @param tabtentative
     * @return resultat
     */
    public static String verifierPropositionJoueur(int propositionIa[], int tabtentative[], int longueurC){

        String resultat= "";

        for (int index = 0; index < longueurC; index++) {
            if (propositionIa[index] < tabtentative[index]) {
                resultat= resultat +"-";
            } else if (propositionIa[index] > tabtentative[index]) {
                resultat= resultat +"+";
            } else {
                resultat= resultat +"=";
            }
        }

        return resultat;
    }


    /**
     *Fonction permettant de bloquer la saisie du joueur et catch des exceptions liees à la saisie.
     *
     * @param longueurC
     * @param scanner input
     * @return nombreChoisiParJoueur permet de retourner la saisie attendue
     */
    public static int[] recupererPropositionJoueur(int longueurC, Scanner input) {
        String saisieJoueur;
        int nombreChoisiParJoueur[] = new int[0];
        do {
            try{
                LOGGER.info(" Saisir un nombre à "+longueurC+" chiffres ");
                 saisieJoueur = input.nextLine();
                 nombreChoisiParJoueur = Fonction.player(longueurC, saisieJoueur);
            } catch(NumberFormatException e){
                LOGGER.error(" Vous ne pouvez pas saisir de lettre ");
                saisieJoueur = "";
            } catch (StringIndexOutOfBoundsException e){
                LOGGER.error(" Respecter le nombre de chiffres ");
                saisieJoueur = "";
            }
        }while (saisieJoueur.length() != longueurC);

        return nombreChoisiParJoueur;
    }
}
