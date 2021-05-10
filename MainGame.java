public class MainGame {
    public static void main(String[] args) {
        Pions [] p = new Pions [3];
        Pions [][] plateau = new Pions [10][10];
        for (int i = 0; i<4; i++){
            for (int j = 0; j<5; j++){
                p[5*i+j] = new Pions (2*j, i, true);
                plateau[2*j][i] = p[5*i +j];
            }
        }
        for (int i = 0; i<4; i++){
            for (int j = 0; j<5; j++){
                p[5*i+j] = new Pions (2*j, 10-i, false);
            }
        }
    }
}