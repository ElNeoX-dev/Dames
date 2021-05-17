public class MainGame {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        initPlateau(plateau, 40);
        System.out.println("Bienvenue sur le jeu de dames")
        plateau.afficher();
    }

    public static void initPlateau(Plateau plateau, int nbPions) {
        int compteur = 0;
        for (int j = 0; j < (plateau.taille / 2 - 1); j++) {
            for(int i = j % 2; i < plateau.taille; i += 2 ) {
                if (compteur < nbPions){
                    plateau.stocksPionPlateau(new Pions (i, j, true, true), compteur, nbPions);
                    compteur++;
                }
            }
        }
        compteur = 0;
        for (int j = plateau.taille - 1; j >= plateau.taille / 2 + 1; j--) {
            for(int i = (j % 2); i < plateau.taille; i += 2) {
                if (compteur < nbPions){
                    plateau.stocksPionPlateau(new Pions (i, j, false, true), compteur, nbPions);
                    compteur++;
                }
            }
        }
        plateau.remplissagePlateau();
    }
}