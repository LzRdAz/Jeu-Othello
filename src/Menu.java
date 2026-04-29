import java.util.Scanner;

public class Menu {
    public static void afficherMenuPrincipal(){
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean quitter = false;
        while(!quitter){

            System.out.println("=================================");
            System.out.println("        MENU PRINCIPAL           ");
            System.out.println("=================================");
            System.out.println("1.Jouer contre ami");
            System.out.println("2.Jouer contre bot");
            System.out.println("3.Les règles du jeu Othelo ou aussi appelé Reversi");
            System.out.println("4.Quitter");
            System.out.print("Choisissez une option entre 1 et 4: ");
            choix = sc.nextInt();

            switch (choix){
                case 1:
                    Menuvsami.menu();
                    break;
                case 2:
                    partieNiveauNormalContreOrdinateur.main();
                    break;
                case 3:
                    Regles.menuRegle();
                    break;
                case 4:
                    quitter = true;
                    System.out.println("Vous avez quittez, au revoir");
                    break;
                default:
                    System.out.println("Saisie incorrect!");
                    break;
            }
        }
    }
}


