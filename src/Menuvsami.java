import java.util.Scanner;

public class Menuvsami {
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean retour = false;
        while(!retour){

            System.out.println("=================================");
            System.out.println("        MENU PRINCIPAL           ");
            System.out.println("=================================");
            System.out.println("1.Mode facile");
            System.out.println("2.Mode normal");
            System.out.println("3.Retour en arrière");
            System.out.print("Choisissez une option entre 1 et 2 : ");
            choix = sc.nextInt();
            switch (choix){
                case 1:
                    partieNiveauFacile.main();
                    break;
                case 2:
                    partieNiveauNormal.main();
                    break;
                case 3:
                    retour = true;
                    System.out.println("Retour en arrière.");
                    break;
                default:
                    System.out.println("Veuillez choisir un option.");
                    break;
            }
        }
    }
}
