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

    public boolean getestDame() {
        return this.estDame;
    }

    public boolean getestVivant() {
        return this.estVivant;
    }

    public boolean getestBlanc() {
        return this.estBlanc;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setestVivant() {
        estVivant = false;
    }

    public void setestDame() {
        estDame = true;
    }

    public boolean mange(Pions cible, Plateau plateau) {

    }



    public int[] posManger(Pions cible){
        int j;
        int i;
        if(this.y > cible.y){
            j = -1;
        } else {
            j = 1;
        }
        if(this.x > cible.x){
            i = -1;
        } else {
            i = 1;
        }
        if(cible.y + j < 0 || cible.y + j > 9 || cible.x + i < 0 || cible.x + i >0){
            int[] pos = {0, 1};
            return pos;
        } else {
            int[] pos = {cible.y + j, cible.x + i};
            return pos;
        }

    }

    public boolean cheminLibre(Pions cible) {
        int k;
        int l;
        if(this.y > cible.y){
            k = -1;
        } else {
            k = 1;
        }
        if(this.x > cible.x){
            l = -1;
        } else {
            l = 1;
        }
        int[][] condition = {{1, 1},{1, -1},{-1, 1},{-1, -1}};
        int[] cas = {k, l};
        int numcas;
        
        for(int i = 0; i < condition.length; i++) {
            if (cas[0] == condition[i][0] && cas[1] == condition[i][1]) {
                numcas = i;
            }
        }
        if (numcas == 0) {
            int[] depart = {this.x, this.y};
            int[] arrivee = {cible.x, cible.y};
        } else if(numcas == 1) {
            int[] depart = {cible.x, this.y};
            int[] arrivee = {this.x, cible.y};
        } else if(numcas == 2) {
            int[] depart = {this.x, cible.y};
            int[] arrivee = {cible.x, this.y};
        } else {
            int[] depart = {cible.x, cible.y};
            int[] arrivee = {this.x, this.y};
        }
        int distx;
        int disty;

        for(int j = depart[1]; j < arrivee[1]; j++) {
            for(int i = depart[0]; i < arivee[0]; i++) {
                disty = (int) (Math.abs(j-this.y));
                distx = (int) (Math.abs(i-this.x));
                if(disty == distx && plateau.getCases(i, j).estVivant) {
                    return(false);
                }
            }
        }
        return true;
    }
} 


