public class Plateau {
    int taille;
    Pions[][] cases;

    public Plateau() {
        this.taille = 10;
        cases = new Pions [taille][taille];

    }

    public void afficherPlateau() {

    }
    
    public boolean PeutManger(Pions pion) {
        
    }

    public void majPlateau(Pions pion) {
        cases [pion.getY()][pion.getX()] = pion;
    }

}
