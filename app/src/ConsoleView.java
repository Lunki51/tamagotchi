import com.sun.tools.jconsole.JConsoleContext;

import java.util.Scanner;

public class ConsoleView {
    public void showConsoleView() {
        System.out.println("Lancement du jeu");
        System.out.println("Nom de votre animal :");
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();
        System.out.print("Dites bonjour à "+str+ " !");
        Game game = new Game(str, 3);
        ConsoleController ctrl = new ConsoleController(game);
        ctrl.gameloop();

    }
}
