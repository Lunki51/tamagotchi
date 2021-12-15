package fr.tama.model;

public class Chien extends Tamagotchi{


    public Chien(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        super(mood, shape, current,sex,name,level);
    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void play() {

    }

    @Override
    public void toilet() {

    }

    @Override
    public void wash() {

    }

    @Override
    public void update() {
        super.update();
        this.getAttribute("hunger").decrease(2);
    }
}
