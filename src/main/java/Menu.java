import com.company.Challenger;
import com.company.Jeu;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choississez le mode de jeu en tapant *1 challenger*ou *2 defenseur* ou *3 duel*");
        int operation = sc.nextInt();

        new Jeu().lancerLeJeu(operation);
    }
}
