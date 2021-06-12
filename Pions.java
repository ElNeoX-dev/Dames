public class Pions {
    private int x;
    private int y;
    private boolean estBlanc;
    private boolean estDame;
    private boolean estVivant;

    //Initialisation des pions
    public Pions(int y, int x, boolean estBlanc, boolean estVivant) {
        this.x = x;
        this.y = y;
        this.estBlanc = estBlanc;
        estDame = false;
        this.estVivant = estVivant;
    }

    //renvoie la position X
    public int getX() {
        return this.x;
    }

    //renvoie la position Y
    public int getY() {
        return this.y;
    }
    
    //renvoie si le pion est une dame
    public boolean getestDame() {
        return this.estDame;
    }

    //renvoie si le pion est vivant
    public boolean getestVivant() {
        return this.estVivant;
    }

    //renvoie la couleur du pion
    public boolean getestBlanc() {
        return this.estBlanc;
    }

    //permet de modifier la postion du pion
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //permet de tuer un pion
    public void setestVivant() {
        estVivant = false;
    }

    //permet de transformer un pion en dame
    public void setestDame() {
        estDame = true;
    }

    //Détermine la position de la case ou le pion atterit si il mange un pion
    public int[] posManger(Pions cible){
        int j;
        int i;
        //Détermine la position de la cible par rapport à notre pion
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
        //Si la position finale est hors du tableau, alors la position renvoyée est une case dites "morte"(aucun pion ne peut y aller)
        if(cible.y + j < 0 || cible.y + j > 9 || cible.x + i < 0 || cible.x + i > 9){
            int[] pos = {0, 1};
            return pos;
        } else {
            int[] pos = {cible.y + j, cible.x + i};
            return pos;
        }

    }

    //Permet de déterminer si le chemin vers un pion est libre(aucun pion sur le chemin, uniquement pour les dames)
    public boolean cheminLibre(Pions cible, Plateau plateau) {
        int k;
        int l;
        //Détermine la position de la cible par rapport à notre pion
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
        //On détermine les bornes de départ et d'arrivée pour l'analyse du plateau
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

        //On vérifie que les cases en diagnonales sont libres
        for(int j = depart[1] + 1; j < arrivee[1]; j++) {
            for(int i = depart[0] + 1; i < arrivee[0]; i++) {
                disty = (int) (Math.abs(j-this.y));
                distx = (int) (Math.abs(i-this.x));
                if(disty == distx && plateau.getCases(i, j).estVivant) {
                    return(false);
                }
            }
        }
        return true;
    }

    //Permet de déterminer si la distance entre la case cible et le pion est valide
    public boolean distanceValide(Pions cible) {
        int distx =(int) Math.abs(this.x - cible.x);
        int disty = this.y - cible.y;
        if(!this.estDame && cible.estVivant && (cible.estBlanc != this.estBlanc)) { //Si la cible est vivante alors le pion peut reculer
            return((distx == (int) Math.abs(disty)) && distx == 1);
        } else if(!this.estDame && !this.estBlanc) { //Si le pion est noir, il ne peut que descendre
            return(distx == 1 && disty == 1);
        } else if(!this.estDame && this.estBlanc) { //Si le pion ets blanc, il ne peut que monter
            return (distx == 1 && disty == -1);
        } else {
            return (distx == (int) Math.abs(disty)); //Si c'est une dame, il ne peut que se déplacer en diagnonale
        }

    }

    public boolean estBloque(Plateau plateau) {
        int[] bornes = plateau.borneAnalyse(this);
        for(int j = bornes[2]; j <= bornes[3]; j++) {
            for(int i = bornes[0]; i <= bornes[1]; i++) { 
                if(!plateau.getCases(i, j).estVivant && distanceValide(plateau.getCases(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
} 