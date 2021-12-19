package fr.tama.model;

public class Chat extends Tamagotchi{
    public Chat(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        super(mood, shape, current,sex,name,level);
    }

    //TODO COOLDOWN
    //TODO CUSTOM UPDATE

    @Override
    public void eat() {
        this.getAttribute("hungry").increase(1250);
    }

    @Override
    public void sleep() {
        if(this.getCurrent() == Current.AWAKE){
            this.setCurrent(Current.ASLEEP);
        }else{
            this.setCurrent(Current.AWAKE);
        }

    }

    @Override
    public void play() {
        this.getAttribute("tiredness").decrease(50);
        this.getAttribute("toilet").decrease(50);
        this.getAttribute("happiness").increase(1000);
    }

    @Override
    public void toilet() {
        this.getAttribute("toilet").increase(1100);

    }

    @Override
    public void wash() {
        this.getAttribute("cleanliness").increase(2000);
    }

    @Override
    public void update() {
        super.update();
    }
}
