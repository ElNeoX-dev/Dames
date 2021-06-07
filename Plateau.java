public class Plateau {
    int taille;
    private Pions[][] cases;
    private Pions[][] stockPions = new Pions [2][20];

    public Plateau() {
        this.taille = 10;
        cases = new Pions [taille][taille];

    }

    public void afficher() {
        System.out.println(" ─┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        for(int j = 9; j >= 0; j--){
            System.out.print(j + " | ");
            for(int i = 0; i < 10; i++){
                if(!cases[j][i].estVivant){
                    System.out.print("  | ");
                }
                else if (cases[j][i].estBlanc) {
                    if (cases[j][i].estDame) {
                        System.out.print("O | ");
                    } else {
                        System.out.print("o | ");
                    }
                } else {
                    if (cases[j][i].estDame) {
                        System.out.print("X | ");
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

    public Pions getCases (int x, int y){
        return this.cases[y][x];
    }
   
    public boolean peutManger(Pions pion) {
        if(!pion.getestDame()){
            int x = pion.getX();
            int y = pion.getY();
            int iMin;
            int iMax;
            int jMin;
            int jMax;

            if(x < 2) {
                iMin = x;
            } else {
                iMin = x - 1;
            }
            if(y < 2) {
                jMin = y;
            } else {
                jMin = y - 1;
            }
            if(x > 7) {
                iMax = x;
            } else {
                iMax = x + 1;
            }
            if(y > 7) {
                jMax = y;
            } else {
                jMax = y + 1;
            }

            for(int j = jMin; j <= jMax; j++){
               for(int i = iMin; i <= iMax; i++){              
                   if(cases[j][i].getestVivant() && (pion.getestBlanc() != cases[j][i].getestBlanc())){
                       int[] posCible = pion.posManger(cases[j][i]);
                       if(!cases[posCible[0]][posCible[1]].estVivant) {
                           return(true);
                       }
                    }
                }
            }
            return (false);
        } else {
            int disty;
            int distx;
            Pions cible;
            for(int j = 1; j < this.taille - 2; j++) {
                for(int i = 1; i < this.taille - 2; i++){
                    cible = cases[j][i];
                    disty = (int) (Math.abs(j-pion.getY()));
                    distx = (int) (Math.abs(i-pion.getX()));
                    if(distx == disty && cible.getestVivant() && (pion.getestBlanc() != cible.getestBlanc()) && pion.cheminLibre(cible, this)) {
                        int[] posCible = pion.posManger(cible);
                        if(!cases[posCible[0]][posCible[1]].estVivant && posCible[0] != 0 && posCible[1]!= 1) {
                            return true;
                        }
                    }
                }


            }
            return false;
        }
    }

    public void stocksPionPlateau(Pions pion, int compteur, int nbPions) {
        cases[pion.getY()][pion.getX()] = pion;
        if(pion.getestBlanc()) {
            this.stockPions[0][compteur] = pion;
        } else {
            this.stockPions[1][compteur] = pion;
        }
    }

    public void remplissagePlateau() {
        for(int j = 0; j < this.taille; j++){
            for(int i = 0; i < this.taille; i++){
                if(cases[j][i] == null) {
                    cases[j][i] = new Pions(j, i, true, false);
                }

            }
        }
    }

    public boolean bouger(Pions pion, Pions cible) {
        int a = pion.getY();
        int b = pion.getX();
        int [] posManger = pion.posManger(cible);
        if(peutManger(pion) && (cible).getestVivant() && posManger[0] != 0 && posManger[1] != 1 && !cases[posManger[0]][posManger[1]].estVivant && pion.distanceValide(cible)) {
            pion.setPos(posManger[1], posManger[0]);
            update(a, b, pion);
            cible.setestVivant();
            afficher();
            return true;
        } else if(!cible.estVivant && !peutManger(pion) && pion.distanceValide(cible)) {
            pion.setPos(cible.getX(), cible.getY());
            update(a, b, pion);
            afficher();
            return true;
        } else {
            afficher();
            return false;
        }
    }

    public boolean choixPionValide(Pions pion, int nbTours) {
        if((nbTours % 2 == 0) == pion.getestBlanc() && peutManger(pion)) {
            return true;
        } else {
            int ligne;
            if(pion.getestBlanc()){
                ligne = 0;
            } else {
                ligne = 1;
            }
            for(int i = 0; i < stockPions[ligne].length; i++) {
                if(peutManger(stockPions[ligne][i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean estFini() {
        for(int j = 0; j < 2; j++) {
            for(int i = 0; i < 20; i++) {
                if(stockPions[j][i].estVivant == true) {
                    return false;
                }
            }
        }
        return true;
    }

    public void update(int a, int b, Pions pion) {
        cases[pion.getY()][pion.getX()] = pion;
        cases[a][b] = new Pions(a, b, true, false);

    }

}