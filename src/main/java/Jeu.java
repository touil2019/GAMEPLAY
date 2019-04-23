import java.awt.*;

public class Jeu {

    public void lancerLeJeu(int operation) {
        switch (operation) {
            case 1:
                System.out.println("Vous avez choisi le mode 1: challenger");

                Challenger modeChallenger = new Challenger();
                modeChallenger.jouer();
                break;

            case 2:
                System.out.println("Vous avez choisi le mode 2: defenseur");

                Defenseur modeDefenseur = new Defenseur();
                modeDefenseur.jouer();
                break;
            case 3:
                System.out.println("Vous avez choisi le mode 3: duel");

                Duel modeDuel = new Duel();
                modeDuel.jouer();
                break;
        }
    }
}
