import java.util.Scanner;
public class LesDames {
    public static void main(String[] args) {
        Plateau plateau = new Plateau(); //Création du plateau
        Scanner choix = new Scanner(System.in);
        initPlateau(plateau, 40); //Remplissage du plateau

        System.out.println("Bienvenue sur le jeu de dames");
        System.out.println("Les règles sont celles des dames classiques");
        System.out.println("Lorsque vous pouvez manger un pion, vous êtes obligés de le faire");
        System.out.println("Pour manger un pions, vous devez sélectionner la case du pion que vous voulez manger");

        plateau.afficher();

        int nbTours = 0;
        String nomCouleur;
        int xJoueur;
        int yJoueur;
        int xCible;
        int yCible;

        while(!plateau.estFini()) { //On regarde si la partie est finie
            if(nbTours % 2 == 0) {
                nomCouleur = "blanc";
            } else {
                nomCouleur = "noir";
            }
            System.out.println("C'est à " + nomCouleur + " de jouer");
            //On demande au joueur le pion qu'il souhaite bouger
            do {
                System.out.println("Choisissez votre colone de A à J du pions que vous souhaitez bouger");  
                xJoueur = conversionLettreChiffre();
                System.out.println("Choisissez votre ligne de 0 à 9 du pions que vous souhaitez bouger");
                yJoueur = choix.nextInt();
            } while(xJoueur < 0 || yJoueur > 9 || xJoueur > 9 || yJoueur < 0 || !plateau.choixPionValide(plateau.getCases(xJoueur, yJoueur), nbTours)); //On vérifie que le pion choisi est valide  
            //On demande au joueur le position cible
            do {
                System.out.println("Choisissez votre colone de A à J de la case cible");  
                xCible = conversionLettreChiffre();
                System.out.println("Choisissez votre ligne de 0 à 9 de la case cible");
                yCible = choix.nextInt();
            } while(xCible < 0 || yCible > 9 || xCible > 9 || yCible < 0 || !plateau.bouger(plateau.getCases(xJoueur, yJoueur), plateau.getCases(xCible, yCible)));  //On vérifie que la case choisie est valide         
            nbTours++;           
        }
        System.out.println("fini");


    }

    //Permet d'initialiser le plateau
    public static void initPlateau(Plateau plateau, int nbPions) {
        int compteur = 0;
        //Création des pions blancs
        for (int j = 0; j < (plateau.taille / 2 - 1); j++) {
            for(int i = j % 2; i < plateau.taille; i += 2 ) {
                if (compteur < nbPions){
                    plateau.stocksPionPlateau(new Pions (j, i, true, true), compteur, nbPions);
                    compteur++;
                }
            }
        }
        compteur = 0;
        //Création des pions noirs
        for (int j = plateau.taille - 1; j >= plateau.taille / 2 + 1; j--) {
            for(int i = (j % 2); i < plateau.taille; i += 2) {
                if (compteur < nbPions){
                    plateau.stocksPionPlateau(new Pions (j, i, false, true), compteur, nbPions);
                    compteur++;
                }
            }
        }
        plateau.remplissagePlateau(); //On remplit les cases vides
    }

    //Permet de tranformer les lettres majuscules en nombres
    public static int conversionLettreChiffre() {
        Scanner lettre = new Scanner(System.in);        
        return(lettre.next().charAt(0) - 65);
    }
}