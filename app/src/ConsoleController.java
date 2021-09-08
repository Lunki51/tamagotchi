import java.util.Scanner;

public class ConsoleController {

    private Game game;

    public ConsoleController(Game game){
        this.game = game;
    }
    public void gameloop(){
        Scanner sc;
        String str;
        while(true) {
            System.out.println(game.getStatsString());
            System.out.println("Quelle action a réaliser ? (m = manger, l = le laver, d = dormir, j = jouer, t = toilette)");
            System.out.println(game.getAnimal().getName() + " " + game.getAnimal().getBodyState().getName() + " et " + game.getAnimal().getName() + " " + game.getAnimal().getPsychoState().getName());
            System.out.print("Action : ");
            sc = new Scanner(System.in);
            str = sc.nextLine();
            switch (str) {
                case "m":
                    game.getAnimal().eat();
                    System.out.println("Vous lui donnez à manger");
                    break;
                case "l":
                    game.getAnimal().wash();
                    System.out.println("Vous le lavez");
                    break;
                case "d":
                    game.getAnimal().sleep();
                    System.out.println("Vous le couchez");
                    break;
                case "j":
                    game.getAnimal().play();
                    System.out.println("Vous jouez avec lui");
                    break;
                case "t":
                    game.getAnimal().toilet();
                    System.out.println("Vous l'aidez à faire ces besoins");
                    break;
            }

            System.out.println();
            if (game.getAnimal().triggerStats() == 1) {
                System.out.println("Votre animal est mort en paix, bravo bon travail !");
                return;
            } else if (game.getAnimal().triggerStats() == 2) {
                System.out.println("Votre animal est mort car vous ne vous êtes pas bien occupé de lui.");
                return;
            }
        }
    }
}
