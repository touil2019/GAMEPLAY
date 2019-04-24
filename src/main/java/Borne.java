public class Borne {

    int minValue;

    int maxValue;

    public Borne() {
        this.minValue = 0;
        this.maxValue = 10;
    }

    /*Creation d'un tableau de borne afin de faire évoluer le réponse de l'IA
      pour les modes Defenseurs et Duel.
     */
    public static Borne[] initialiserLesBornes(int tailleTableau) {
        Borne[] tableauDeBorne = new Borne[tailleTableau];

        for (int index = 0; index < tailleTableau; index++) {
            tableauDeBorne[index] = new Borne();
        }
        return tableauDeBorne;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
