package fr.tama.model;

import fr.tama.controller.LangFile;
import org.w3c.dom.Attr;

/**
 * Represent a tamagotchi in the game
 */
public abstract class Tamagotchi {

    private String name;
    private boolean sex;
    private Status mood;
    private Status shape;
    private Current current;
    private Attribute[] attributes;

    /**
     * Create a new tamagotchi object
     * @param mood the mood of the tamagotchi
     * @param shape the shape of the tamagotchi
     * @param current the current status of the tamagotchi
     * @param name the name of the tamagotchi
     */
    public Tamagotchi(Status mood, Status shape, Current current,boolean sex,String name) {
        this.mood = mood;
        this.shape = shape;
        this.current = current;
        this.name=name;
        this.sex=sex;
        setupDefaultAttributes();
    }

    /**
     * Return the sex of the tamagotchi
     * @return the sex of the tamagotchi
     */
    public boolean isSex() {
        return sex;
    }

    /**
     * Setup the default attributes of the tamagotchi
     */
    public void setupDefaultAttributes(){
        this.attributes = new Attribute[]{
                new Attribute("hunger", 0),
                new Attribute("toilet",0),
                new Attribute("tiredness",0),
                new Attribute("cleanliness",0),
                new Attribute("hapiness",0)
        };
    }

    /**
     * Set an attribute of the tamagotchi by it's name
     * @param name the name of the attribute to set
     * @param value the new value of the attribute
     */
    public void setAttribute(String name,int value){
        for(Attribute attr : this.attributes){
            if(attr.getName().equals(name))attr.setValue(value);
        }
    }

    /**
     * Search for an attribute by it's name and return it
     * @param name the name of the attribute to search
     * @return the attribute found if possible
     */
    public Attribute getAttribute(String name){
        for(Attribute attr : this.attributes){
            if(attr.getName().equals(name))return attr;
        }
        return this.attributes[0];
    }

    /**
     * Return the name of the tamagotchi
     * @return the name of the tamaogotchi
     */
    public String getName() {
        return name;
    }

    /**
     * Return the current mood of the tamagotchi
     * @return the mood of the tamagotchi
     */
    public Status getMood() {
        return mood;
    }

    /**
     * Return the current shape of the tamagotchi
     * @return the current shape of the tamagotchi
     */
    public Status getShape() {
        return shape;
    }

    /**
     * Return the current status of the tamagotchi
     * @return the current status of the tamagotchi
     */
    public Current getCurrent() {
        return current;
    }

    /**
     * Return the raw array of attributes of the tamagotchi
     * @return the attribute array of tamagotchi
     */
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
