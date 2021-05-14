public class MainGame {
    public static void main(String[] args) {
        
    }

    public static Pions[] InitPlateau(Plateau plateau, int nbPions) {
        int compteur = 0;
        Pions stockPions[] = new Pions[(2 * nbPions)];
        for (int j = 0; j < plateau.taille; j+= 2) {
            for(int i = 0; i < plateau.taille; i+= 2) {
                if (compteur < nbPions){
                    stockPions[compteur] = new Pions (i, j, true);
                    compteur++;
                }
            }
        }
        for (int j = plateau.taille - 1; j >= 0 ; j-= 2) {
            for(int i = plateau.taille; i >= 0 ; i-= 2) {
                if (compteur < nbPions){
                    stockPions[compteur] = new Pions (i, j, false);
                    compteur++;
                }
            }
        }
    }
}