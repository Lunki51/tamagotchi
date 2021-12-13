package fr.tama.model;

/**
 * Represent one of the attributes of the tamagotchi
 */
public class Attribute {

    private String name;
    private int value;
    private int max;

    /**
     * Create a new tamagotchi attribute
     * @param name the name of the attribute
     * @param value the default value of the attribute
     */
    public Attribute(String name, int value,int max) {
        this.name = name;
        this.value = value;
        this.max = max;
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

    /**
     * Change the value of the attribute
     * @param value the new value of the attribute
     */
    public void setValue(int value) {
        this.value = value;
    }
}
