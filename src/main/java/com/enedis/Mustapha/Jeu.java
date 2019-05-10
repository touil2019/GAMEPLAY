package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {

    private static final Logger LOGGER = LogManager.getLogger(Jeu.class.getName());

    public void lancerLeJeu(int operation) {

        switch (operation) {
            case 1:
                LOGGER.info("Vous avez choisi le mode 1: challenger");

                Challenger modeChallenger = new Challenger(this);
                modeChallenger.jouer();
                break;

            case 2:
                LOGGER.info("Vous avez choisi le mode 2: defenseur");

                Defenseur modeDefenseur = new Defenseur(this);
                modeDefenseur.jouer();
                break;
            case 3:
                LOGGER.info("Vous avez choisi le mode 3: duel");

                Duel modeDuel = new Duel(this);
                modeDuel.jouer();
                break;

            case 4:
                LOGGER.info("Vous avez choisi de quitter le jeu");
                break;
        }

    }

    public void menuPrincipal(){





        int operation = saisirModeJeu();
        lancerLeJeu(operation);
    }

    int saisirModeJeu (){

        Scanner sc = new Scanner(System.in);

        int resultat=0;

        boolean isValide;
        do{
            try {


                System.out.println("Selectionner votre mode de jeu en tapant *1 challenger* ou *2 defenseur* ou *3 duel* ou *4 quitter*");

                isValide = true;

                resultat = sc.nextInt();


                if (resultat > 4 || resultat < 1) {

                    isValide = false;
                    LOGGER.info("Votre saisie est incorrecte");
                }
            }catch (InputMismatchException e){

                sc.nextLine();
                isValide = false;
                LOGGER.info("Votre saisie est incorrecte");
            }

        }while(isValide==false);

        return resultat;
    }





   }
