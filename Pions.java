public class Pions {
    private int x;
    private int y;
    boolean estBlanc;
    boolean estDame;
    boolean estVivant;


    public Pions(int y, int x, boolean estBlanc, boolean estVivant) {
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
        if(cible.y + j < 0 || cible.y + j > 9 || cible.x + i < 0 || cible.x + i > 9){
            int[] pos = {0, 1};
            return pos;
        } else {
            int[] pos = {cible.y + j, cible.x + i};
            return pos;
        }

    }

    public boolean cheminLibre(Pions cible, Plateau plateau) {
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
        int numcas = 5;
        
        for(int i = 0; i < condition.length; i++) {
            if (cas[0] == condition[i][0] && cas[1] == condition[i][1]) {
                numcas = i;
            }
        }
        int[] arrivee = new int [2];
        int[] depart = new int [2];
        if (numcas == 0) {
            depart[0] = this.x;
            depart[1] = this.y;
            arrivee[0] = cible.x;
            arrivee[1] = cible.y;
        } else if(numcas == 1) {
            depart[0] = cible.x;
            depart[1] = this.y;
            arrivee[0] = this.x;
            arrivee[1] = cible.y;
        } else if(numcas == 2) {
            depart[0] = this.x;
            depart[1] = cible.y;
            arrivee[0] = cible.x;
            arrivee[1] = this.y;

        } else {
            depart[0] = cible.x;
            depart[1] = cible.y;
            arrivee[0] = this.x;
            arrivee[1] = this.y;
        }
        int distx;
        int disty;

        for(int j = depart[1]; j < arrivee[1]; j++) {
            for(int i = depart[0]; i < arrivee[0]; i++) {
                disty = (int) (Math.abs(j-this.y));
                distx = (int) (Math.abs(i-this.x));
                if(disty == distx && plateau.getCases(i, j).estVivant) {
                    return(false);
                }
            }
        }
        return true;
    }

    public int dist(Pions cible) {
        return (int) ( (Math.abs(this.x-cible.x) + Math.abs(this.y-cible.y)  ) );
    }
} 


