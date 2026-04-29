import java.util.Scanner;

public class partieNiveauNormalContreOrdinateur {
    static Scanner sc = new Scanner(System.in);
    static char couleurJoueur = 'N';
    static char couleurOrdinateur = 'B';

    public static void main() {

        char[][] plateau;
        plateau = new char[8][8];
        String joueur;
        int ligne, col;

        partieNiveauNormal.initialiserPlateau(plateau);
        System.out.println("Nom du joueur (Pion Noir) : ");
        joueur = sc.nextLine();
        System.out.println("L'ordinateur joue avec les pions blancs.");

        boolean tourJoueur = true;
        int toursPassesConsecutifs = 0;

        while (!partieNiveauNormal.verificationPlateauRemplie(plateau) && toursPassesConsecutifs < 2 ) {
            if (tourJoueur) {
                partieNiveauNormal.afficherPlateau(plateau);
                if (partieNiveauNormal.passeTonTour(plateau, couleurJoueur)) {
                    System.out.println(joueur + " n'a aucun coup possible. Tour passé !");
                    toursPassesConsecutifs ++;
                } else {
                    System.out.println(joueur + " ou voulez-vous jouer ? Choix de la ligne : ");
                    ligne = sc.nextInt();
                    System.out.println(joueur + " ou voulez-vous jouer ? Choix de la colonne : ");
                    col = sc.nextInt();
                    partieNiveauNormal.placerPion(plateau, ligne, col, couleurJoueur);
                    partieNiveauNormal.afficherPlateau(plateau);
                    toursPassesConsecutifs = 0;
                }
            }
            else {
                if (partieNiveauNormal.passeTonTour(plateau, couleurOrdinateur)) {
                    System.out.println("L'ordinateur n'a aucun coup possible. Tour passé !");
                    toursPassesConsecutifs ++;
                }
                else {
                    CoupBot(plateau, couleurOrdinateur);
                    toursPassesConsecutifs = 0;
                }
            }
            tourJoueur = !tourJoueur;
        }
        partieNiveauNormal.afficherPlateau(plateau);
        System.out.println("Partie Terminée");

        if (toursPassesConsecutifs >= 2){
            System.out.println("Aucun joueur ne peut jouer.");
        } else {
            System.out.println("Le plateau est rempli !");
        }

        afficherGagnant(plateau, joueur);
    }

    public static void CoupBot (char[][] plateau, char couleurOrdinateur){
        int meilleureCoupLigne = -1; //Si on met 0, le bot pourrait quand même jouer en (0,0) donc créer un problème.
        int meilleureCoupCol = -1;
        int maxPionsRetournes = 0;

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[ligne].length; col++) {
                if (partieNiveauNormal.coupPossible(plateau, ligne, col, couleurOrdinateur)) {
                    int pionsRetournes = compterPionsRetournes(plateau, ligne, col, couleurOrdinateur);
                    if (pionsRetournes > maxPionsRetournes) {
                        maxPionsRetournes = pionsRetournes;
                        meilleureCoupLigne = ligne;
                        meilleureCoupCol = col;
                    }

                }
            }
        }

        System.out.println("L'ordinateur joue en " + meilleureCoupLigne + " , " + meilleureCoupCol);
        partieNiveauNormal.placerPion(plateau, meilleureCoupLigne, meilleureCoupCol, couleurOrdinateur);
    }

    public static int compterPionsRetournes (char[][] plateau, int ligne, int col, char couleurOrdinateur){
        char couleurAdverse = 'N';
        int compteur = 0;

        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, 0, 1);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, 1, 0);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, 1, 1);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, 0, -1);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, 1, -1);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, -1, -1);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, -1, 0);
        compteur = compteur + compterDirection (plateau, ligne, col, couleurOrdinateur, couleurAdverse, -1, 1);

        return compteur;
    }

    public static int compterDirection (char[][] plateau, int ligne, int col, char CouleurOrdinateur, char couleurAdverse, int directionLigne, int directionCol){
        int l = ligne + directionLigne;
        int c = col + directionCol;
        int compteur = 0;

        while (l >= 0 && l < plateau.length && c >= 0 && c < plateau.length){
            char caseActuelle = plateau[l][c];
            if (caseActuelle == couleurAdverse) {
                compteur++;
                l = l + directionLigne;
                c = c + directionCol;
            }
            else {
                if (caseActuelle == CouleurOrdinateur) {
                    return compteur;
                }
                else {
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void afficherGagnant (char[][] plateau, String joueur){
        int pointJoueur = partieNiveauNormal.compter(plateau, couleurJoueur);
        int pointBot = partieNiveauNormal.compter(plateau, couleurOrdinateur);

        System.out.println("Résultat");
        System.out.println(joueur + " : " + pointJoueur + " pions");
        System.out.println("Ordinateur : " + pointBot + " pions");

        if (pointJoueur > pointBot) {
            System.out.println(joueur + " a gagné !");
        } else if (pointBot > pointJoueur) {
            System.out.println("L'ordinateur a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }

}
