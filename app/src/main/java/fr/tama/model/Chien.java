package fr.tama.model;

public class Chien extends Tamagotchi{


    public Chien(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        super(mood, shape, current,sex,name,level);
    }

    //TODO COOLDOWN

    @Override
    public void eat() {
        this.getAttribute("hungry").increase(1250);
        if(this.statusCD[0]==0 && this.getAttribute("hungry").isMax()){
            this.setShape(this.getShape().getPlus());
            this.statusCD[0]=144;
        }

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

        if(this.statusCD[1]==0 && this.getAttribute("happiness").isMax()){
            this.setMood(this.getMood().getPlus());
            this.statusCD[1]=144;
        }
    }

    @Override
    public void toilet() {
        this.getAttribute("toilet").increase(1100);
        if(this.statusCD[1]==0 && this.getAttribute("happiness").isMax()){
            this.setMood(this.getMood().getPlus());
            this.statusCD[1]=144;
        }
    }

    @Override
    public void wash() {
        this.getAttribute("cleanliness").increase(2000);
        if(this.getAttribute("cleanliness").isMax()){
            if(this.statusCD[0]==0 ){
                this.setShape(this.getMood().getPlus());
                this.statusCD[0]=144;
            }else if(this.statusCD[1]==0 ){
                this.setMood(this.getMood().getPlus());
                this.statusCD[1]=144;
            }
        }

    }
    @Override
    public void update() {
        super.update();
        this.getAttribute("tiredness").increase(1);
        this.getAttribute("toilet").decrease(1);
        this.getAttribute("happiness").decrease(5);
    }

    @Override
    public String toString() {
        return "Chien";
    }

    public boolean is(String name){
        return name.equals("Chien");
    }
}
