package fr.tama.model;

import java.util.Arrays;

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
    private final int[] statusCD;
    private int lifeCD;

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
        this.statusCD = new int[]{144,144};
        this.lifeCD = 288;
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
                new Attribute("hunger", 500,2000),
                new Attribute("toilet",500,2000),
                new Attribute("tiredness",500,1000),
                new Attribute("cleanliness",500,4000),
                new Attribute("happiness",500,2000),
                // NON AFFICHE
                new Attribute("health",20,20)
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
        this.getAttribute("hunger").decrease(7);
        this.getAttribute("toilet").decrease(7);
        if(this.current==Current.AWAKE){
            this.getAttribute("tiredness").decrease(7);
        }else if(this.current==Current.ASLEEP){
            this.getAttribute("tiredness").increase(7);
        }
        this.getAttribute("cleanliness").decrease(7);
        this.getAttribute("happiness").decrease(7);
        if(this.statusCD[0]==0||this.statusCD[1]==0){
            if(this.getAttribute("tiredness").getValue()==0 || this.getAttribute("hunger").getValue()==0
                    || this.getAttribute("cleanliness").getValue()==0){
                this.shape = this.shape.getMinus();
                this.statusCD[0]=144;
                this.statusCD[1]=144;
            }
            if(this.getAttribute("toilet").getValue()==0 || this.getAttribute("happiness").getValue()==0
                    || this.getAttribute("cleanliness").getValue()==0){
                this.mood = this.mood.getMinus();
                this.statusCD[0]=144;
                this.statusCD[1]=144;
            }
        }else{
            this.statusCD[0]--;
            this.statusCD[1]--;
        }

        if(this.lifeCD==0){
            if(this.mood.isGood()){
                this.getAttribute("health").increase(1);
            }else{
                this.getAttribute("health").decrease(1);
            }
            if(this.shape.isGood()){
                this.getAttribute("health").increase(1);
            }else{
                this.getAttribute("health").decrease(1);
            }
            this.lifeCD=288;
        }else{
            this.lifeCD--;
        }
    }
}
