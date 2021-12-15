package fr.tama.model;

public class Chien extends Tamagotchi{


    public Chien(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        super(mood, shape, current,sex,name,level);
    }

    @Override
    public void eat() {
        this.getAttribute("hungry").increase(1250);
    }

    @Override
    public void sleep() {
        if(this.getCurrent() == Current.AWAKE)
            this.setCurrent(Current.ASLEEP);
    }

    @Override
    public void play() {
        this.getAttribute("tiredness").decrease(50);
        this.getAttribute("cleanliness").decrease(50);
        this.getAttribute("happiness").increase(1000);
    }

    @Override
    public void toilet() {
        this.getAttribute("toilet").increase(1100);

    }

    @Override
    public void wash() {
        this.getAttribute("cleanliness").increase(2000);
        //TODO CoolDown
    }

    @Override
    public void update() {
        super.update();
        this.getAttribute("tiredness").increase(1);
        this.getAttribute("toilet").decrease(1);
        this.getAttribute("happiness").decrease(5);
    }
}
