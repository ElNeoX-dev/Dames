import java.util.Scanner;
public class Plateau {
    Scanner choix = new Scanner(System.in);
    int taille;
    private Pions[][] cases;
    private Pions[][] stockPions = new Pions [2][20];

    //Initialisation du plateau
    public Plateau() {
        this.taille = 10;
        cases = new Pions [taille][taille];

    }

    //Permet d'afficher le plateau
    public void afficher() {
        System.out.println(" ─┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        //On vérifie le statut de chaques pions du plateau et on leur attribut un caractère
        for(int j = 9; j >= 0; j--){
            System.out.print(j + " | ");
            for(int i = 0; i < 10; i++){
                if(!cases[j][i].getestVivant()){
                    System.out.print("  | ");
                }
                else if (cases[j][i].getestBlanc()) {
                    if (cases[j][i].getestDame()) {
                        System.out.print("B | ");
                    } else {
                        System.out.print("o | ");
                    }
                } else {
                    if (cases[j][i].getestDame()) {
                        System.out.print("N | ");
                    } else {
                        System.out.print("x | ");
                    }
                }                       
            }
            System.out.println("");
            System.out.println(" ─├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
        }
        System.out.println("    A   B   C   D   E   F   G   H   I   J  ");

    }

    //Renvoie le pion contenu dans une case précise
    public Pions getCases (int x, int y){
        return this.cases[y][x];
    }

    // On vérifie les bornes des cases à scanner pour éviter de sortir du plateau 
    public int[] borneAnalyse(Pions pion) {
        int[]resultat = new int[4];
        int x = pion.getX();
        int y = pion.getY();
        if(x < 1) {
            resultat[0] = x; //i = colonne
        } else {
            resultat[0] = x - 1;
        }
        if(y < 1) {
            resultat[2] = y; //j = ligne
        } else {
            resultat[2] = y - 1;
        }
        if(x > 8) {
            resultat[1] = x;
        } else {
            resultat[1] = x + 1;
        }
        if(y > 8) {
            resultat[3] = y;
        } else {
            resultat[3] = y + 1;
        }
        return resultat;
    }
   
    //Vérifie si un pion peut manger
    public boolean peutManger(Pions pion) {
        if(!pion.getestDame() && pion.getestVivant()){ //Si le pion n'est pas une dame
            int[] bornes = borneAnalyse(pion);
            //On vérifie que la cible est vivante et que la case derrière le pion est libre
            for(int j = bornes[2]; j <= bornes[3]; j++){
               for(int i = bornes[0]; i <= bornes[1]; i++){              
                   if(cases[j][i].getestVivant() && (pion.getestBlanc() != cases[j][i].getestBlanc())){
                       int[] posCible = pion.posManger(cases[j][i]); //Détermination de la position derrière le pion
                       if(!cases[posCible[0]][posCible[1]].getestVivant() && (posCible[0] != 0 && posCible[1] != 1) ) {
                           return(true); //On renvoie true si toutes les conditions son vérifiées
                       }
                    }
                }
            }
            return false;

        } else if (pion.getestVivant()){ // Si le pion est une dame
            int disty;
            int distx;
            Pions cible;
            //On scna tout le plateau
            for(int j = 1; j < this.taille - 2; j++) {
                for(int i = 1; i < this.taille - 2; i++){
                    cible = cases[j][i];
                    disty = (int) (Math.abs(j-pion.getY()));
                    distx = (int) (Math.abs(i-pion.getX()));
                    //On regarde unisuement les cases en diagnonales, que la cible est vivante, dans l'autre équipe et que le chemin d'accès est libre
                    if((distx == disty) && cible.getestVivant() && (pion.getestBlanc() != cible.getestBlanc()) && pion.cheminLibre(cible, this)) {
                        int[] posCible = pion.posManger(cible); //Détermination de la position derrière le pion
                            return (!cases[posCible[0]][posCible[1]].getestVivant() && (posCible[0] != 0 && posCible[1] != 1));
                    }
                }
            }
            return false;
        }
        return false; //Si le pion choisi est mort
    }

    //Permet de créer un stocks de pions dans un tableau 2D avec la ligne 0 les blancs et la ligne 1 les noirs
    public void stocksPionPlateau(Pions pion, int compteur, int nbPions) {
        cases[pion.getY()][pion.getX()] = pion;
        if(pion.getestBlanc()) {
            this.stockPions[0][compteur] = pion;
        } else {
            this.stockPions[1][compteur] = pion;
        }
    }

    //Permet de remplir toutes les cases vides du Plateau
    public void remplissagePlateau() {
        for(int j = 0; j < this.taille; j++){
            for(int i = 0; i < this.taille; i++){
                if(cases[j][i] == null) { //Si la case n'a pas de pion
                    cases[j][i] = new Pions(j, i, true, false); //on ajoute à cette case un pion mort
                }

            }
        }
    }

    //Permet de déplacer un pion
    public boolean bouger(Pions pion, Pions cible) {
        int a = pion.getY();
        int b = pion.getX();
        int [] posManger = pion.posManger(cible);
        //Si la distance est valide et que le pion peut le manger
        if(peutManger(pion) && pion.distanceValide(cible) && (posManger[0] != 0 && posManger[1] != 1)) {
            pion.setPos(posManger[1], posManger[0]); //On met  à jour la position du pion
            update(a, b, pion); //On met à jour le plateau
            cible.setestVivant(); //On tue la cible
            afficher(); //On affiche le plateau
            if(peutManger(pion)) {
                aDejaManger(pion); //Si le pion peut encore manger, il doit manger ce pion
                return true;
            }
            return true;

        // SI le pion ne peut pas manger, que la case et libre et que la distance est valide
        } else if(!cible.getestVivant() && !peutManger(pion) && pion.distanceValide(cible)) {
            pion.setPos(cible.getX(), cible.getY());
            update(a, b, pion);
            afficher();
            return true;

        //Si aucune des conditions n'est respecter, le coup n'est pas valide
        } else {
            afficher();
            if (peutManger(pion)) {
                System.out.println("Vous êtes obligés de manger");
                return false;
            }
            System.out.println("Vous n'avez pas choisi une cible valide :'(");
            return false;
        }
    }

    //On vérifie que le choix du pion est valide
    //Si le joeuur peut manger, il doit manger
    //Le pion doit être de la couleur du joueur et ne doit pas être bloqué
    public boolean choixPionValide(Pions pion, int nbTours) {
        if((nbTours % 2 == 0) == pion.getestBlanc() && peutManger(pion) && pion.getestVivant()) { //Il peut manger
            return true;
        } else if (pion.getestVivant() && (nbTours % 2 == 0) == pion.getestBlanc() && !pion.estBloque(this)){
            int ligne;
            if(pion.getestBlanc()){
                ligne = 0;
            } else {
                ligne = 1;
            }
            //On vérifie que d'autres pions ne peuvent pas manger
            for(int i = 0; i < stockPions[ligne].length; i++) {
                if(peutManger(stockPions[ligne][i])) {
                    System.out.println("Un de vos pions peut en manger un autre !");
                    return false;
                }
            }
            return true; 
        }
        if(pion.estBloque(this)) {
            System.out.println("Vous avez choisi un pion bloqué !");
            return false;
        }
        System.out.println("Vous avez choisi une case vide !"); //Si la case choisi est vide
        return false;
    }

    //On vérifie si tous les pions d'une équipe sont morts
    public boolean estFini() {
        for(int j = 0; j < 2; j++) {
            for(int i = 0; i < 20; i++) {
                if(stockPions[j][i].getestVivant() == true) {
                    return false;
                }
            }
        }
        return true;
    }

    //Permet de mettre à jour le plateau
    //Le pion prends la place de l'ancien et un pion mort est créer à son ancienne position
    public void update(int a, int b, Pions pion) {
        cases[pion.getY()][pion.getX()] = pion;
        cases[a][b] = new Pions(a, b, true, false);
        if (pion.getY() == 9 && pion.getestBlanc()){ //On regarde si le pion peut se transformer en dame
            pion.setestDame();
        } else if (pion.getY() == 0 && !pion.getestBlanc()) {
            pion.setestDame();
        }

    }

    //Permet de tranformer les lettres majuscules en nombres
    public static int conversionLettreChiffre() {
        Scanner lettre = new Scanner(System.in);        
        return(lettre.next().charAt(0) - 65);
    }

    //Permet de manger un autre pion lorsque cela est possible
    //On redemande la position de notre nouvelle cible
    public void aDejaManger(Pions pion) {
        System.out.println("Vous pouvez manger un autre pion");
        int xCible;
        int yCible;
        do {
            System.out.println("Choisissez votre colone de A à J de la case cible");  
            xCible = conversionLettreChiffre();
            System.out.println("Choisissez votre ligne de 0 à 9 de la case cible");
            yCible = choix.nextInt();
        } while(xCible < 0 || yCible > 9 || xCible > 9 || yCible < 0 || !bouger(pion, cases[yCible][xCible])); //On vérifie que la case choisie est valide  
    }
}