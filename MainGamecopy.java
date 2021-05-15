import java.util.Scanner;
public class MainGamecopy {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        initPlateau(plateau, 40);
        plateau.cases[0][0] = null;
        //System.out.println(plateau.cases[0][0]);
        System.out.println (plateau);
        //les blancs commencent
        System.out.println("Veuillez indiquer la position abscisse du pion que vous voulez déplacer. Exemple : 10");
        Scanner xJoueur = new Scanner(System.in);
        System.out.println("Veuillez indiquer la position abscisse du pion que vous voulez déplacer. Exemple : 8");
        Scanner yJoueur = new Scanner(System.in);
        
    }

    public static void initPlateau(Plateau plateau, int nbPions) {
        int compteur = 0;
        for (int j = 0; j < (plateau.taille / 2 - 1); j++) {
            for(int i = j % 2; i < plateau.taille; i += 2 ) {
                if (compteur < nbPions / 2){
                    plateau.majPlateau(new Pions (i, j, true));
                    compteur++;
                }
            }
        }
        for (int j = plateau.taille - 1; j >= plateau.taille / 2 + 1 ; j--) {
            for(int i = 1 - (j % 2); i < plateau.taille; i += 2) {
                if (compteur < nbPions){
                    plateau.majPlateau(new Pions (i, j, false));
                    compteur++;
                }
            }
        }
        
    }


}