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
    private String info;
    private Current current;
    private Level level;
    private Attribute[] attributes;
    private final int difficulty;
    /**
     * Create a new tamagotchi object
     * @param mood the mood of the tamagotchi
     * @param shape the shape of the tamagotchi
     * @param current the current status of the tamagotchi
     * @param name the name of the tamagotchi
     */
    public Tamagotchi(Status mood, Status shape, Current current,boolean sex,String name,Level level,int difficulty) {
        this.mood = mood;
        this.difficulty=difficulty;
        this.shape = shape;
        this.current = current;
        this.name=name;
        this.level=level;
        this.sex=sex;
        this.info = "Dites bonjour à votre nouvel ami !";
        setupDefaultAttributes();
    }

    /**
     * Return the sex of the tamagotchi
     * @return the sex of the tamagotchi
     */
    public boolean isSex() {
        return sex;
    }

    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Setup the default attributes of the tamagotchi
     */
    public void setupDefaultAttributes(){
        this.attributes = new Attribute[]{
                new Attribute("hunger", 1000,2000,75),
                new Attribute("toilet",1000,2000,75),
                new Attribute("tiredness",500,1000,75),
                new Attribute("cleanliness",2000,4000,75),
                new Attribute("happiness",1000,2000,75),

                new Attribute("health",20,20,200),

                new Attribute("lifeCD",0,0,288),
                new Attribute("evolCD",0,0,500),
                new Attribute("moodCD",0,0,144),
                new Attribute("shapeCD",0,0,144)

        };
        for(int i=0;i<5;i++){
            attributes[i].setCoolDown(0);
        }
    }

    /**
     * Set an attribute of the tamagotchi by it's name
     * @param name the name of the attribute to set
     * @param value the new value of the attribute
     */
    public void setAttribute(String name,int value){
        for(Attribute attr : this.attributes){
            if(attr.getName().equals(name)){
                attr.setValue(value);
                break;
            }
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
        return this.attributes[0]; //SHOULD REALLY THROW AN EXCEPTION INSTEAD OF RETURNING FIRST ATTRIBUTE
    }

    /**
     * Return the name of the tamagotchi
     * @return the name of the tamaogotchi
     */
    public String getName() {
        return name;
    }

    /**
     * @return the current information about the tamagotchi
     */
    public String getInfo() { return info;}

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

    /**
     * Set a new name to the tamagotchi
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set a new sex to the tamagotchi
     * @param sex new sex
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setMood(Status mood) {
        this.mood = mood;
    }

    /**
     * Set a shape Status to the tamagotchi
     * @param shape Status to set
     */
    public void setShape(Status shape) {
        this.shape = shape;
    }

    /**
     * Set what the tamagotchi is doing
     * @param current Action that it's doing
     */
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

    /**
     * Ask tamagotchi for eating
     */
    public abstract void eat();
    /**
     * Ask tamagotchi for sleeping
     */
    public abstract void sleep();
    /**
     * Ask tamagotchi for playing
     */
    public abstract void play();
    /**
     * Ask tamagotchi for taking out the sausage guillotine
     */
    public abstract void toilet();
    /**
     * Ask tamagotchi for washing
     */
    public abstract void wash();

    public void update(){
        Attribute evolCD = this.getAttribute("evolCD");
        if(this.level == Level.EGG){
            this.levelUp();
            evolCD.resetCD();
            return;
        }

        if(evolCD.getCoolDown()==0){
            if(this.getShape().isGood() && this.getMood().isGood()){
                this.levelUp();
            }
            evolCD.resetCD();
        }else{
            evolCD.reduceCD();
        }

        for(int i=0;i<6;i++){

            attributes[i].reduceCD();
        }
        //ATTRIBUTES DEFAULT DECREASE
        this.getAttribute("hunger").decrease(7);
        this.getAttribute("toilet").decrease(7);
        this.getAttribute("cleanliness").decrease(7);
        this.getAttribute("happiness").decrease(7);
        if(this.current==Current.AWAKE){
            this.getAttribute("tiredness").decrease(7);
        }else if(this.current==Current.ASLEEP){
            this.getAttribute("tiredness").increase(7);
        }

        Attribute shapeCD = this.getAttribute("shapeCD");
        if(this.getAttribute("tiredness").isMax()){
            if(shapeCD.getCoolDown()==0){
                this.shape = this.shape.getPlus();
                shapeCD.resetCD();
            }
        }

        //STATUS DECREASE
        if(shapeCD.getCoolDown()==0 ){
            if(this.getAttribute("tiredness").getValue()==0 || this.getAttribute("hunger").getValue()==0
                    || this.getAttribute("cleanliness").getValue()==0){
                this.shape = this.shape.getMinus();
                this.info = "Son état de santé baisse ...";
                shapeCD.resetCD();
            }
        }else shapeCD.reduceCD();

        Attribute moodCD = this.getAttribute("moodCD");
        if(moodCD.getCoolDown()==0){
            if(this.getAttribute("toilet").getValue()==0 || this.getAttribute("happiness").getValue()==0
                || this.getAttribute("cleanliness").getValue()==0) {
                this.mood = this.mood.getMinus();
                this.info = "Son état mental n'est pas au plus haut ...";
                moodCD.resetCD();
            }
        }else moodCD.reduceCD();

        //LIFE DECREASE
        Attribute lifeCD = this.getAttribute("lifeCD");
        if(lifeCD.getCoolDown()==0){
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
            lifeCD.resetCD();
        }else{
            lifeCD.reduceCD();
        }
    }

    /**
     * Return if the tamagotchi is dead or not
     * @return true if the tamagotchi is dead else false
     */
    public boolean isDead(){
        return this.getAttribute("health").getValue()==0;
    }
}
