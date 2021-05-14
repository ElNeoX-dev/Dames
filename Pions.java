public class Pions {
    private int x;
    private int y;
    boolean estBlanc;
    boolean estDame;
    boolean estVivant;


    public Pions(int x, int y, boolean estBlanc) {
        this.x = x;
        this.y = y;
        this.estBlanc = estBlanc;
        estDame = false;
        estVivant = true;
    }


    
    public int getX() {

    }

    public int getY() {
        
    }

    public boolean getDame() {

    }

    public boolean getVivant() {

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

    public boolean coupValide(Pion cible){
        
    }


} 


