import java.util.ArrayList;

public class State {
    private String name;
    private int value;
    private Statistic[] listStats;

    public State(String name, int value, Statistic[] listStats) {
        this.name = name;
        this.value = value;
        this.listStats = listStats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void update(){
        int stateMax = 0;
        int max = 0;
        for (int i = 0; i < listStats.length; i++) {
            stateMax = 0;
            if(max < listStats[i].getValue()) {
                max = listStats[i].getValue();
                stateMax = i;
            }
        }
        if(value == 0) {
            switch (stateMax) {
                case 0:
                    name = "est crispé, il pleurt.";
                    break;
                case 1:
                    name = "sent très mauvais, il y a pleins d'excréments sur le sol";
                    break;
                case 2:
                    name = "est très sale, il n'a plus la même couleur qu'au début...";
                    break;
                case 3:
                    name = "est fatigué";
                    break;
                case 4:
                    name = "maigrit à vue d'oeil tellement il a faim.";
                    break;
            }
        } else {
            switch (stateMax) {
                case 0:
                    name = "est très triste";
                    break;
                case 1:
                    name = "est très enervé";
                    break;
                case 2:
                    name = "a vraiment besoin d'attention";
                    break;
                case 3:
                    name = "veux vraiment dormir";
                    break;
                case 4:
                    name = "vous suit partout car il a très faim";
                    break;
            }
        }
    }
}
