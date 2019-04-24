import java.util.Random;

public class Fonction {

    //creation d'un nombre aleatoire

    public static int[] random(int longueurC) {

        Random rnd = new Random();
        int tab1[] = new int[longueurC];
        for (int loop = 0; loop < longueurC; loop++) {
            int mystere = rnd.nextInt(10);
            tab1[loop] = mystere;
        }
        return tab1;
    }

    public static void random(int reponse[], Borne[] borneDuRandom, char[] tableauDeVerification){
        Random r = new Random();
        for (int index = 0; index < reponse.length; index++) {
            if (!"=".equals(tableauDeVerification[index])){
                System.out.println("minValue = " + borneDuRandom[index].minValue);
                System.out.println("maxValue = " + borneDuRandom[index].maxValue);
                int minValue = borneDuRandom[index].getMinValue();
                int maxValue = borneDuRandom[index].getMaxValue();

                int mystere = r.nextInt(maxValue - minValue) + minValue;
                reponse[index] = mystere;
            }
        }
    }
    // prise en compte da la saisie utilisateur

    public static int[] player(int longueurC,String saisiejoueur){

        int tab2[] = new int[longueurC];
        for (int loop = 0; loop < longueurC; loop++) {
            int conversion = Integer.parseInt(String.valueOf(saisiejoueur.charAt(loop)));
            tab2[loop]= conversion;
    }
       return tab2;
    }
}
