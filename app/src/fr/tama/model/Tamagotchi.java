package fr.tama.model;

public abstract class Tamagotchi {

    private String name;
    private Status mood;
    private Status shape;
    private Current current;
    private Attribute[] attributes;

    public Tamagotchi(Status mood, Status shape, Current current,String name) {
        this.mood = mood;
        this.shape = shape;
        this.current = current;
        this.name=name;
        setupDefaultAttributes();
    }

    public void setupDefaultAttributes(){
        this.attributes = new Attribute[]{
                new Attribute("faim",0),
                new Attribute("toilette",0),
                new Attribute("fatigue",0),
                new Attribute("proprete",0),
                new Attribute("bonheur",0)
        };
    }

    public void setAttribute(String name,int value){
        for(Attribute attr : this.attributes){
            if(attr.getName().equals(name))attr.setValue(value);
        }
    }

    public Attribute getAttribute(String name){
        for(Attribute attr : this.attributes){
            if(attr.getName().equals(name))return attr;
        }
        return this.attributes[0];
    }

    public String getName() {
        return name;
    }

    public Status getMood() {
        return mood;
    }

    public Status getShape() {
        return shape;
    }

    public Current getCurrent() {
        return current;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public abstract void eat();
    public abstract void sleep();
    public abstract void play();
    public abstract void toilet();
    public abstract void wash();
    public abstract void update();
}
