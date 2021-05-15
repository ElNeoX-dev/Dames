public class MainGame {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        initPlateau(plateau, 40);
        plateau.afficher();
    }

    public static void initPlateau(Plateau plateau, int nbPions) {
        int compteur = 0;
        Pions[][] stockPions = new Pions[2][nbPions];
        for (int j = 0; j < (plateau.taille / 2 - 1); j++) {
            for(int i = j % 2; i < plateau.taille; i += 2 ) {
                if (compteur < nbPions){
                    stockPions[0][compteur] = new Pions (i, j, true, true);
                    plateau.majPlateau(stockPions[0][compteur]);
                    compteur++;
                }
            }
        }
        compteur = 0;
        for (int j = plateau.taille - 1; j >= plateau.taille / 2 + 1; j--) {
            for(int i = 1 - (j % 2); i < plateau.taille; i += 2) {
                if (compteur < nbPions){
                    stockPions[1][compteur] = new Pions (i, j, false, true);
                    plateau.majPlateau(stockPions[1][compteur]);
                    compteur++;
                }
            }
        }
        plateau.remplissagePlateau();
    }
}