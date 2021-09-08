public class Statistic {
    private String name;
    private int value;

    public Statistic(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void inc(){
        value++;
    }

    public void dec(){
        if(Math.random() > 0.5) {
            value-=Variables.DECR;
        } else{
            value-=Variables.DECR+1;
        }
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
