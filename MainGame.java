public class MainGame {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        initPlateau(plateau, 40);
        System.out.println("Bienvenue sur le jeu de dames");
        System.out.println("Les règles sont celles des dames classiques");
        System.out.println("Lorsque vous pouvez manger un pion, vous êtes obligés de le faire");
        System.out.println("Pour manger un pions, vous devez sélectionner la case du pion que vous voulez manger");
        int nbTours = 0;
        while(!plateau.estFini()) {
            plateau.afficher();
            
        }

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