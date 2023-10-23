import java.io.IOException;
import java.util.*;


public class Main {
    static Scanner in = new Scanner(System.in); // System.in és global


    // Main
    public static void main(String[] args) throws InterruptedException, IOException {
        boolean running = true;
        while (running) {
            String menu = "Escull una opció:";
            menu = menu + "\n 0) PR130mainPersonesHashmap";
            menu = menu + "\n 1) PR131mainEscriu, PR131mainLlegeix";
            menu = menu + "\n 2) PR132main";
            menu = menu + "\n 3) PR133mainTreballadors";
            // Adapta aquí les altres classes de l’exercici (PR122cat…)
            menu = menu + "\n 100) Sortir";
            System.out.println(menu);


            int opcio = Integer.valueOf(llegirLinia("Opció:"));
            try {
                switch (opcio) {
                    case 0: PR130mainPersonesHashmap.main(args); break;
                    case 1: PR131mainEscriu.main(args);
                            PR131mainLlegeix.main(args);
                    break;
                    case 2: PR132main.main(args); break;
                    case 3: PR133mainTreballadors.main(args); break;
                    
                    // Adapta aquí les altres classes de l’exercici (PR122cat…)
                    case 100: running = false; break;
                    default: break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        in.close();
    }


    static public String llegirLinia (String text) {
        System.out.print(text);
        return in.nextLine();
    }
}
