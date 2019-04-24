

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Menu {private static final Logger LOGGER = LogManager.getLogger(Menu.class.getName());



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LOGGER.debug("Selectionner votre mode de jeu en tapant *1 challenger* ou *2 defenseur* ou *3 duel*");
        int operation = sc.nextInt();

        new Jeu().lancerLeJeu(operation);
    }
}
