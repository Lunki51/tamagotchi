package fr.tama.model;

public class Chat extends Tamagotchi{
    public Chat(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        super(mood, shape, current,sex,name,level);
    }

    @Override
    public void eat() {
        Attribute food = this.getAttribute("hungry");
        if(food.getCoolDown()==0){
            food.increase(1250);
            if(this.statusCD[0]==0 && food.isMax()){
                this.setShape(this.getShape().getPlus());
                this.statusCD[0]=144;
            }
            food.resetCD();
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
        Attribute attrib = this.getAttribute("happiness");
        if(attrib.getCoolDown()==0){
            this.getAttribute("tiredness").decrease(50);
            this.getAttribute("toilet").decrease(50);
            attrib.increase(1000);

            if(this.statusCD[1]==0 && attrib.isMax()){
                this.setMood(this.getMood().getPlus());
                this.statusCD[1]=144;
            }
            attrib.resetCD();
        }

    }

    @Override
    public void toilet() {
        Attribute attrib = this.getAttribute("toilet");
        if(attrib.getCoolDown()==0){
            this.getAttribute("toilet").increase(1100);
            if(this.statusCD[1]==0 && this.getAttribute("happiness").isMax()){
                this.setMood(this.getMood().getPlus());
                this.statusCD[1]=144;
            }
            attrib.resetCD();
        }

    }

    @Override
    public void wash() {
        Attribute attrib = this.getAttribute("cleanliness");
        if(attrib.getCoolDown()==0){
            attrib.increase(2000);
            if(this.getAttribute("cleanliness").isMax()){
                if(this.statusCD[0]==0 ){
                    this.setShape(this.getMood().getPlus());
                    this.statusCD[0]=144;
                }else if(this.statusCD[1]==0 ){
                    this.setMood(this.getMood().getPlus());
                    this.statusCD[1]=144;
                }
            }
            attrib.resetCD();
        }


    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public String toString() {
        return "Chat";
    }

    public boolean is(String name){
        return name.equals("Chat");
    }
}
