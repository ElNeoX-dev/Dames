public class Plateau {
    int taille;
    Pions[][] cases;

    public Plateau() {
        this.taille = 10;
        cases = new Pions [taille][taille];

    }

    public void afficher() {
        System.out.println("  ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
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
            System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
        }
        System.out.println("  | A | B | C | D | E | F | G | H | I | J |");
        System.out.println("  └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘");


        //System.out.println("1 | o |   | x |   | O |   | X |   | o |   |").
        //System.out.println("  ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
    }
    
    public boolean peutManger(Pions pion) {
        
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

}
