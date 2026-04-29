import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPartieNiveauNormal {

    @Test
    public void testVerifierLigne(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . . . . . . .
        //  3 . . X B B N . .
        //  4 . . . . . . . .
        //  5 . . . . . . . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je veux vérifier que si je place mon pion 'N' en (3,2) je peux capturer vers la droite dans la direction (0,1).
        // Resultat attendu : Vrai.

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[3][3] = 'B';
        plateau[3][4] = 'B';
        plateau[3][5] = 'N';

        boolean resultat = partieNiveauNormal.verifierLigne(plateau,3,2,'N','B', 0, 1);
        assertTrue(resultat,"Coup Valide");
    }

    @Test
    public void testVerifierLigne2(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . . . . . . .
        //  3 . . X B B . . .
        //  4 . . . . . . . .
        //  5 . . . . . . . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je veux vérifier que si je place mon pion 'N' en (3,2) je peux capturer vers la droite dans la direction (0,1)
        // Resultat attendu : Faux.

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[3][3] = 'B';
        plateau[3][4] = 'B';

        boolean resultat = partieNiveauNormal.verifierLigne(plateau,3,2,'N','B', 0, 1);
        assertFalse(resultat,"Coup Invalide car pas de pion 'N' après le pion blanc");
    }

    @Test
    public void testVerifierLigneDiagonale(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . X . . . . .
        //  3 . . . B . . . .
        //  4 . . . . B . . .
        //  5 . . . . . N . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je veux vérifier que si je place mon pion 'N' en (2,2) je peux capturer vers en diagonale bas-droite dans la direction (1,1)
        // Resultat attendu : Vrai.

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[3][3] = 'B';
        plateau[4][4] = 'B';
        plateau[5][5] = 'N';

        boolean resultat = partieNiveauNormal.verifierLigne(plateau,2,2,'N','B', 1, 1);
        assertTrue(resultat,"Coup Valide");
    }

    @Test
    public void testCoupPossibleHorizontal(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . . . . . . .
        //  3 . N X B B N . .
        //  4 . . . . . . . .
        //  5 . . . . . . . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je veux savoir si, en jouant mon pion 'N' en (3,2), mon coup permet de prendre des pions adversaires.
        // Resultat attendu : Vrai.

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[3][1] = 'N';
        plateau[3][3] = 'B';
        plateau[3][4] = 'B';
        plateau[3][5] = 'N';

        boolean resultat = partieNiveauNormal.coupPossible(plateau,3,2,'N');
        assertTrue(resultat, "Le coup est possible.");
    }

    @Test
    public void testCoupPossibleDiagonal(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . X . . . . .
        //  3 . . . B . . . .
        //  4 . . . . B . . .
        //  5 . . . . . N . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je veux savoir si, en jouant mon pion 'N' en (2,2), mon coup permet de prendre des pions adversaires.
        // Resultat attendu : Vrai.

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[3][3] = 'B';
        plateau[4][4] = 'B';
        plateau[5][5] = 'N';

        boolean resultat = partieNiveauNormal.coupPossible(plateau,2,2,'N');
        assertTrue(resultat, "Le coup est possible.");
    }

    @Test
    public void testRetournerPionsVertical(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . N . . . . . .
        //  2 . B . . . . . .
        //  3 . B . . . . . .
        //  4 . B . . . . . .
        //  5 . X . . . . . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        // Je souhaite placer mon pion 'N' en (5,1) et prendre les pions blancs entre mes deux pions 'N'

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[1][1] = 'N';
        plateau[2][1] = 'B';
        plateau[3][1] = 'B';
        plateau[4][1] = 'B';
        plateau[5][1] = 'N';

        partieNiveauNormal.retournerPions(plateau,5,1,'N');
        assertEquals('N', plateau[2][1],"Le pion en (2,1) devient 'N'");
        assertEquals('N', plateau[3][1],"Le pion en (3,1) devient 'N'");
        assertEquals('N', plateau[4][1],"Le pion en (4,1) devient 'N'");

    }

    @Test
    public void testRetournerPionsDiagonal(){
        // Configuration Test :
        //    0 1 2 3 4 5 6 7
        //  0 . . . . . . . .
        //  1 . . . . . . . .
        //  2 . . X . . . . .
        //  3 . . . B . . . .
        //  4 . . . . B . . .
        //  5 . . . . . N . .
        //  6 . . . . . . . .
        //  7 . . . . . . . .
        //Je souhaite placer mon pion 'N' en (2,2) et prendre les pions blancs entre mes deux pions 'N'

        char[][] plateau = new char[8][8];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.';
            }
        }
        plateau[2][2] = 'N';
        plateau[3][3] = 'B';
        plateau[4][4] = 'B';
        plateau[5][5] = 'N';

        partieNiveauNormal.retournerPions(plateau,2,2,'N');
        assertEquals('N', plateau[3][3],"Le pion en (3,3) devient 'N'");
        assertEquals('N', plateau[4][4],"Le pion en (4,4) devient 'N'");
    }
}
