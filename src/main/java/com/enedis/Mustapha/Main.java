package com.enedis.Mustapha;


import java.io.IOException;

/**
 * Classe permettant le démarrage et le choix du mode de jeu
 *
 * @author MUSTAPHA TOUIL
 * @version
 */

public class Main {



    public static void main(String[] args) throws IOException {

        GetPropertyValues properties = GetPropertyValues.getInstance();

        if (args.length > 0 && args[0].equals("modeDev")){
            properties.modeDev = 1;
        }

         new Jeu().menuPrincipal();



    }


}
