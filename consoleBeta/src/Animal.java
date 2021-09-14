import java.util.ArrayList;

public class Animal {

    private String name;
    private int type;

    private Statistic[] stats;
    private State psychoState;
    private State bodyState;
    private int personality;
    private ArrayList<State> stateHistory;


    public Animal(String name, int type) {
        this.name = name;
        this.type = type;
        this.stats = new Statistic[Variables.STATS_NUMBER];
        String[] statsName = {"Tristesse","Besoins","Saleté","Fatigue","Faim", "Age"};
        for (int i = 0; i < Variables.STATS_NUMBER; i++) {
            stats[i] = new Statistic(statsName[i], 0);
        }
        this.psychoState = new State("est heureux", 0,stats);//État psychologique
        this.bodyState = new State("est en forme", 1,stats);//État physique
        this.personality = 1;
        this.stateHistory = new ArrayList<>();
    }

    public Statistic[] getStats() {
        return stats;
    }

    public State getPsychoState() {
        return psychoState;
    }

    public State getBodyState() {
        return bodyState;
    }

    public String getName() {
        return name;
    }

    public void play() {
        stats[0].dec();
        stats[3].inc();
    }

    public void eat() {
        stats[4].dec();
    }

    public void sleep(){
        stats[3].dec();
    }

    public void toilet(){
        stats[1].dec();
    }

    public void wash(){
        stats[2].dec();
    }
    protected int triggerStats(){
        for (int i = 0 ; i < Variables.STATS_NUMBER-1; i++) {
            if(Math.random() <= 0.4){stats[i].inc();}
            if(Math.random() <= 0.2){stats[i].inc();}
            if(stats[i].getValue() > 20){
                return 2;
            }
        }
        stats[Variables.STATS_NUMBER-1].setValue(stats[Variables.STATS_NUMBER-1].getValue()+1);
        if (stats[5].getValue() % 10 == 0){
            Variables.DECR = Variables.DECR+1;

        }
        if(stats[Variables.STATS_NUMBER-1].getValue() >= 50){
            return 1;
        }

        this.bodyState.update();
        this.psychoState.update();
        return 0;

    }
}
