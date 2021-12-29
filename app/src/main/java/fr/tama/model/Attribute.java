package fr.tama.model;

/**
 * Represent one of the attributes of the tamagotchi
 */
public class Attribute {

    private String name;
    private int value;
    private final int max;
    private int coolDown;
    private final int maxCoolDown;

    /**
     * Create a new tamagotchi attribute
     * @param name the name of the attribute
     * @param value the default value of the attribute
     */
    public Attribute(String name, int value,int max,int coolDown) {
        this.name = name;
        this.value = value;
        this.max = max;
        this.coolDown = 0;
        this.maxCoolDown = coolDown;
    }

    /**
     * Increase the attribute value
     */
    public void increase(int value){
        this.value+=value;
        if(this.value>max)this.value=max;
    }

    /**
     * Decrease the attribute value
     */
    public void decrease(int value){
        this.value-=value;
        if(this.value<0)this.value=0;
    }

    /**
     * Format the attribute to a string
     * @return a string that contain the attribute data
     */
    public String toString(){
        return "";
    }

    /**
     * Return the name of the attribute
     * @return the name of the attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Return the value of the attribute
     * @return the value of the attribute
     */
    public int getValue() {
        return value;
    }

    /**
     * Change the name of the attribute
     * @param name the new name of the attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public boolean isMax(){
        return this.value==this.max;
    }

    /**
     * Change the value of the attribute
     * @param value the new value of the attribute
     */
    public void setValue(int value) {
        this.value = value;
    }

    public void reduceCD(){
        if(this.coolDown!=0)this.coolDown--;
    }

    public void resetCD(){
        this.coolDown = maxCoolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }
}
