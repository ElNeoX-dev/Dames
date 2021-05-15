public class Plateau {
    int taille;
    private Pions[][] cases;

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
        if(!pion.getDame()){
            int x = pion.getX();
            int y = pion.getY();
            for(int j = y - 1; j <= y+1; j += 2){
               for(int i = x-1; i <= x+1; i += 2){              
                   if(cases[j][i].getVivant() && (pion.getBlanc() != cases[j][i].getBlanc())){
                       int[] posCible = pion.posManger(cases[j][i], this);
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
            for(int j = 0; j < this.taille - 1; j++) {
                for(int i = 0; i < this.taille -1; i++){
                    cible = cases[j][i];
                    disty = (int) (Math.abs(j-pion.getY()));
                    distx = (int) (Math.abs(i-pion.getX()));
                    if(distx == disty && cible.getVivant() && (pion.getBlanc() != cible.getBlanc()) && pion.cheminLibre(cible)) {
                        int[] posCible = pion.posManger(cible, this);
                        if(!cases[posCible[0]][posCible[1]].estVivant) {
                            return true;
                        }
                    }
                }


            }
            return false;
        }
    }

    public void majPlateau(Pions pion) {
        cases [pion.getY()][pion.getX()] = pion;
    }

    public void remplissagePlateau() {
        for(int j = 0; j < this.taille / 2; j++){
            for(int i = 0; i < this.taille; i++){
                if(cases[j][i] == null) {
                    cases[j][i] = new Pions(j, i, true, false);
                }

            }
        }
        for(int j = this.taille - 1; j >= this.taille / 2; j--){
            for(int i = 0; i < this.taille; i++){
                if(cases[j][i] == null) {
                    cases[j][i] = new Pions(j, i, false, false);
                }

            }
        }
        
    }

    public boolean bouger(Pions pion) {
        if(peutManger(pion)) {

        } else {
            
        }
    }

}
