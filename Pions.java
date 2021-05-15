public class Pions {
    private int x;
    private int y;
    boolean estBlanc;
    boolean estDame;
    boolean estVivant;


    public Pions(int x, int y, boolean estBlanc, boolean estVivant) {
        this.x = x;
        this.y = y;
        this.estBlanc = estBlanc;
        estDame = false;
        this.estVivant = estVivant;
    }


    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getDame() {

    }

    public boolean getVivant() {
        return this.estVivant;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean setMort() {
        estVivant = false;
    }

    public boolean mange(Pions cible, Plateau plateau) {

    }

    public boolean coupValide(Pion cible, Plateau plateau){
        
    }


} 


