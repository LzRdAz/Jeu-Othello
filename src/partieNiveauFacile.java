import java.util.Scanner;
public class partieNiveauFacile {

    static Scanner sc = new Scanner(System.in);
    static char couleurJoueur1 = 'N';
    static char couleurJoueur2= 'B';

    public static void main() {

        char[][] plateau;
        plateau = new char[8][8];
        String joueur1, joueur2;
        int ligne, col;

        initialiserPlateau(plateau);
        System.out.println("Nom du joueur 1 (Pion Noir) : ");
        joueur1 = sc.nextLine();
        System.out.println("Nom du joueur 2 (Pion Blanc) : ");
        joueur2 = sc.nextLine();

        boolean tourJoueur1 = true;
        int toursPassesConsecutifs = 0; //Cette limitation permet d'éviter une boucle infini si les 2 joueurs ne peuvent plus jouer meme si il reste des cases vides.

        while (!verificationPlateauRemplie(plateau) && toursPassesConsecutifs < 2 ) {
            if (tourJoueur1) {
                verificationPion(plateau, couleurJoueur1);
                afficherPlateau(plateau);
                if (passeTonTour(plateau, couleurJoueur1)) {
                    System.out.println(joueur1 + " n'a aucun coup possible. Tour passé !");
                    toursPassesConsecutifs ++;
                } else {
                    System.out.println(joueur1 + " ou voulez-vous jouer ? Choix de la ligne : ");
                    ligne = sc.nextInt();
                    System.out.println(joueur1 + " ou voulez-vous jouer ? Choix de la colonne : ");
                    col = sc.nextInt();
                    placerPion(plateau, ligne, col, couleurJoueur1);
                    toursPassesConsecutifs = 0;
                }
            } else {
                verificationPion(plateau, couleurJoueur2);
                afficherPlateau(plateau);
                if (passeTonTour(plateau, couleurJoueur2)) {
                    System.out.println(joueur2 + " n'a aucun coup possible. Tour passé !");
                    toursPassesConsecutifs ++;
                } else {
                    System.out.println(joueur2 + " ou voulez-vous jouer ? Choix de la ligne : ");
                    ligne = sc.nextInt();
                    System.out.println(joueur2 + " ou voulez-vous jouer ? Choix de la colonne : ");
                    col = sc.nextInt();
                    placerPion(plateau, ligne, col, couleurJoueur2);
                    toursPassesConsecutifs = 0;
                }
            }
            tourJoueur1 = !tourJoueur1;
        }
        afficherPlateau(plateau);
        System.out.println("Partie Terminée");

        if (toursPassesConsecutifs >= 2){
            System.out.println("Aucun joueur ne peut jouer.");
        }

        afficherGagnant(plateau, joueur1, joueur2);

    }

    public static void initialiserPlateau(char[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                plateau[ligne][col] = '.';
            }
        }
        plateau[3][3] = 'B';
        plateau[3][4] = 'N';
        plateau[4][4] = 'B';
        plateau[4][3] = 'N';
    }

    public static void afficherPlateau(char[][] plateau) {
        System.out.println("\nPlateau de Jeu Othello\n");

        System.out.print("  ");
        for (int col = 0; col < plateau.length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print(ligne + " ");
            for (int col = 0; col < plateau[ligne].length; col++) {
                System.out.print(plateau[ligne][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void placerPion(char[][] plateau, int ligne, int col, char couleurJoueur){
        while (ligne < 0 || ligne >= plateau.length || col < 0 || col >= plateau.length || plateau[ligne][col] != '*'){
            System.out.println("Position Incorrect ! Retapez la ligne et la colonne ");
            System.out.println("Ligne : ");
            ligne = sc.nextInt();
            System.out.println("Colonne : ");
            col = sc.nextInt();
        }
        plateau[ligne][col] = couleurJoueur;
        retournerPions(plateau, ligne, col, couleurJoueur);
    }

    public static void verificationPion (char[][] plateau, char couleurJoueur){
        for (int ligne = 0 ; ligne < plateau.length; ligne ++){
            for (int col = 0 ; col < plateau[ligne].length; col++){
                if(plateau[ligne][col] == '*'){
                    plateau[ligne][col] = '.';
                }
            }
        }

        for (int ligne = 0 ; ligne < plateau.length; ligne ++){
            for (int col = 0 ; col < plateau[ligne].length; col++){
                if(plateau[ligne][col] == '.' && coupPossible(plateau, ligne, col, couleurJoueur)){
                    plateau[ligne][col] = '*';
                }
            }
        }
    }

    public static char couleurAdverse (char[][] plateau, char couleurJoueur){
        if (couleurJoueur == 'N'){
            return 'B';
        }
        else {
            return 'N';
        }
    }

    public static boolean coupPossible (char[][] plateau, int ligne, int col, char couleurJoueur){
        // Schema réflexion pour les directions
        //   (-1,-1)     (-1,0)      (-1,1)
        // Haut Gauche    Haut    Haut Droite
        //
        //   (0,-1)      (0,0)       (0,1)
        //   Gauche       Pion       Droite
        //
        //   (1,-1)      (1,0)      (1,1)
        // Bas Gauche     Bas     Bas Droite


        if (plateau[ligne][col] != '.' && plateau[ligne][col] != '*'){
            return false;
        }

        char couleurAdverse = couleurAdverse(plateau, couleurJoueur);

        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,0,1)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,0)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,1)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,0,-1)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,-1)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,-1)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,0)){
            return true;
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,1)){
            return true;
        }
        return false;
    }

    public static boolean verifierLigne (char[][] plateau, int ligne, int col, char couleurJoueur, char couleurAdverse, int directionLigne, int directionCol){
        int l = ligne + directionLigne;
        int c = col + directionCol;

        boolean pionAdverse = false;

        while (l >= 0 && l < plateau.length && c >= 0 && c < plateau.length) {
            if (plateau[l][c] == couleurAdverse) {
                pionAdverse = true;
                l = l + directionLigne;
                c = c + directionCol;
            } else {
                if (plateau[l][c] == couleurJoueur) {
                    return pionAdverse;
                }
                else {
                    return false;
                }
            }
        }
        return false; //sortie du tableau
    }

    public static void retournerPions (char[][] plateau, int ligne, int col, char couleurJoueur){
        char couleurAdverse = couleurAdverse(plateau, couleurJoueur);

        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,0,1)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, 0, 1);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,0)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, 1, 0);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,1)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, 1, 1);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,0,-1)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, 0, -1);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,1,-1)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, 1, -1);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,-1)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, -1, -1);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,0)) {
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse, -1, 0);
        }
        if(verifierLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,1)){
            retournerLigne(plateau, ligne, col, couleurJoueur, couleurAdverse,-1,1);
        }
    }

    public static void retournerLigne (char[][] plateau, int ligne, int col, char couleurJoueur, char couleurAdverse, int directionLigne, int directionCol){
        int l = ligne + directionLigne;
        int c = col + directionCol;

        while (l >= 0 && l < plateau.length && c >= 0 && c < plateau.length && plateau[l][c] == couleurAdverse) {
            plateau[l][c] = couleurJoueur;
            l = l + directionLigne;
            c = c + directionCol;
        }
    }

    public static boolean passeTonTour (char[][] plateau, char couleurJoueur){
        for (int ligne = 0; ligne < plateau.length; ligne ++){
            for (int col = 0; col < plateau[ligne].length; col++){
                if (coupPossible(plateau, ligne, col, couleurJoueur)){
                    return false;
                }
            }
        }
        return true;
    }

    // methode pour le while pour verifier si le tableau est remplie avec le !gagnant
    public static boolean verificationPlateauRemplie(char[][] plateau){
        boolean remplie = false;
        int compter = 0;
        for (int l = 0; l < plateau.length ; l++){
            for(int c = 0; c < plateau[l].length ; c++){
                if(plateau[l][c] == '.' || plateau[l][c] == '*'){
                    return false;
                }
                compter++;
            }
        }
        if(compter == 64){
            remplie = true;
        }
        return remplie;
    }

    public static int compter (char[][] plateau, char couleurJoueur){
        int cpt = 0;
        for (int ligne = 0; ligne < plateau.length; ligne++){
            for(int col = 0; col < plateau[ligne].length ; col++){
                if(plateau[ligne][col] == couleurJoueur){
                    cpt++;
                }
            }
        }
        return cpt;
    }

    public static void afficherGagnant (char[][] plateau, String joueur1, String joueur2){
        int pointJoueur1 = compter(plateau,'N');
        int pointJoueur2 = compter(plateau, 'B');

        System.out.println("Résultat");
        System.out.println(joueur1 + " : " + pointJoueur1 + " pions");
        System.out.println(joueur2 + " : " + pointJoueur2 + " pions");

        if (pointJoueur1 > pointJoueur2) {
            System.out.println(joueur1 + " a gagné !");
        } else if (pointJoueur2 > pointJoueur1) {
            System.out.println(joueur2 + " a gagné !");
        } else {
            System.out.println("Match nul !");
        }

    }
}