package fr.tama.model;

import fr.tama.controller.LangFile;
import org.w3c.dom.Attr;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represent a tamagotchi in the game
 */
public abstract class Tamagotchi {

    private String name;
    private boolean sex;
    private Status mood;
    private Status shape;
    private Current current;
    private Level level;
    private Attribute[] attributes;

    /**
     * Create a new tamagotchi object
     * @param mood the mood of the tamagotchi
     * @param shape the shape of the tamagotchi
     * @param current the current status of the tamagotchi
     * @param name the name of the tamagotchi
     */
    public Tamagotchi(Status mood, Status shape, Current current,boolean sex,String name,Level level) {
        this.mood = mood;
        this.shape = shape;
        this.current = current;
        this.name=name;
        this.level=level;
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
                new Attribute("happiness",0),
                // NON AFFICHE
                new Attribute("health",0)
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

    public void levelUp(){
        this.level = this.level.next();
    }

    public Level getLevel(){
        return this.level;
    }

    /**
     * Return the raw array of attributes of the tamagotchi
     * @return the attribute array of tamagotchi
     */
    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setMood(Status mood) {
        this.mood = mood;
    }

    public void setShape(Status shape) {
        this.shape = shape;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tamagotchi that = (Tamagotchi) o;
        boolean valid = true;
        for(int i=0;i<attributes.length;i++){
            if (attributes[i].equals(that.attributes[i])) {
                valid = false;
                break;
            }
        }
        System.out.println(valid);
        return level == that.level && sex == that.sex && name.equals(that.name) && mood == that.mood && shape == that.shape && current == that.current && valid;
    }

    @Override
    public String toString() {
        return "Tamagotchi{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", mood=" + mood +
                ", shape=" + shape +
                ", current=" + current +
                ", level="+level+
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }

    public abstract void eat();
    public abstract void sleep();
    public abstract void play();
    public abstract void toilet();
    public abstract void wash();
    public void update(){
        //LES TRUCS QUE TOUTS LES TAMA FONT
        //FAIM -= 7;

        //LES TRUC QUE QUE LUI UPDATE
        //attribute();
    }
}
