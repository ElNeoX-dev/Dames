public class Plateau {
    private int taille;
    private Pions[][] cases;

    public Plateau() {
        this.taille = 10;
        cases = new Pions [taille][taille];

    }

    public void afficherPlateau() {

    }

    public Pions[][] getCases (){
        return this.cases;
    }
    
    public boolean peutManger(Pions pion) {
        int x = pion.getX;
        int y = pion.getY;
        return (cases [x+1][y+1].getVivant || cases [x+1][y-1].getVivant || cases [x-1][y+1].getVivant || cases [x-1][y-1].getVivant);
    }

    public void majPlateau(Pions pion) {
        cases [pion.getY()][pion.getX()] = pion;
    }

}
