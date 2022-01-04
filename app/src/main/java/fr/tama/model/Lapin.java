package fr.tama.model;

public class Lapin extends Tamagotchi{
    public Lapin(Status mood, Status shape, Current current,boolean sex,String name,Level level,int difficulty) {
        super(mood, shape, current,sex,name,level,difficulty);
    }

    @Override
    public void eat() {
        Attribute food = this.getAttribute("hungry");
        if(food.getCoolDown()==0){
            if(this.getDifficulty()==0){
                food.increase(1750);
            }else{
                food.increase(1500);
            }

            Attribute shapeCD = this.getAttribute("shapeCD");
            if(shapeCD.getCoolDown()==0 && food.isMax()){
                this.setShape(this.getShape().getPlus());
                shapeCD.resetCD();
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
            if(this.getDifficulty()==0){
                this.getAttribute("tiredness").decrease(25);
                this.getAttribute("toilet").decrease(25);
                attrib.increase(950);
            }else{
                this.getAttribute("tiredness").decrease(50);
                this.getAttribute("toilet").decrease(50);
                attrib.increase(600);
            }


            Attribute moodCD = this.getAttribute("moodCD");
            if(moodCD.getCoolDown()==0 && attrib.isMax()){
                this.setMood(this.getMood().getPlus());
                moodCD.resetCD();
            }
            attrib.resetCD();
        }

    }

    @Override
    public void toilet() {
        Attribute attrib = this.getAttribute("toilet");
        if(attrib.getCoolDown()==0){
            if(this.getDifficulty()==0){
                this.getAttribute("toilet").increase(1300);
            }else{
                this.getAttribute("toilet").increase(1100);
            }


            Attribute moodCD = this.getAttribute("moodCD");
            if(moodCD.getCoolDown()==0 && this.getAttribute("happiness").isMax()){
                this.setMood(this.getMood().getPlus());
                moodCD.resetCD();
            }
            attrib.resetCD();
        }

    }

    @Override
    public void wash() {
        Attribute attrib = this.getAttribute("cleanliness");
        if(attrib.getCoolDown()==0){
            if(this.getDifficulty()==0){
                attrib.increase(2500);
            }else{
                attrib.increase(2000);
            }

            if(this.getAttribute("cleanliness").isMax()){
                Attribute moodCD = this.getAttribute("moodCD");
                Attribute shapeCD = this.getAttribute("shapeCD");
                if(shapeCD.getCoolDown()==0 ){
                    this.setShape(this.getShape().getPlus());
                    shapeCD.resetCD();
                }else if(moodCD.getCoolDown()==0 ){
                    this.setMood(this.getMood().getPlus());
                    moodCD.resetCD();
                }
            }
            attrib.resetCD();
        }
    }

    @Override
    public void update() {
        super.update();
        this.getAttribute("hungry").decrease(5);
    }

    @Override
    public String toString() {
        return "Lapin";
    }

    public boolean is(String name){
        return name.equals("Lapin");
    }
}
