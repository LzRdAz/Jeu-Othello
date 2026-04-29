import java.util.Scanner;

public class Regles {
    public static void menuRegle() {
        Scanner sc = new Scanner(System.in);
        int saisie;
        System.out.println("=================================");
        System.out.println("           LES REGLES           ");
        System.out.println("=================================");
        System.out.println("Matériel");
        System.out.println("- Plateau 8x8");
        System.out.println("- 64 pions bicolores (noir/blanc)\n");

        System.out.println("But du jeu");
        System.out.println("- Avoir le plus de pions de sa couleur à la fin.\n");

        System.out.println("Mise en place");
        System.out.println("- Cases centrales :");
        System.out.println("  D5, E4 : pions noirs");
        System.out.println("  D4, E5 : pions blancs\n");

        System.out.println("Déroulement d'un tour");
        System.out.println("1. Placer un pion de sa couleur sur une case vide.");
        System.out.println("2. Le pion posé doit encadrer au moins un pion adverse");
        System.out.println("   entre lui et un autre pion de sa couleur (sur une même ligne,");
        System.out.println("   colonne ou diagonale).");
        System.out.println("3. Tous les pions adverses encadrés sont retournés.");
        System.out.println("4. Si aucun coup n'est possible, le joueur passe son tour.\n");

        System.out.println("Règles importantes");
        System.out.println("- Un coup est obligatoire s'il permet d'encadrer un pion.");
        System.out.println("- On peut retourner des pions dans plusieurs directions.");
        System.out.println("- La partie s'arrête si le plateau est plein ou si");
        System.out.println("  aucun joueur ne peut jouer.\n");

        System.out.println("Victoire");
        System.out.println("- Le joueur avec le plus de pions de sa couleur gagne.\n");

        System.out.println("================================\n");
        System.out.print("Taper sur 1 pour quitter et revenir au menu:");
        saisie = sc.nextInt();
        if(saisie == 1)
            Menu.afficherMenuPrincipal();
        sc.close();

    }


}
