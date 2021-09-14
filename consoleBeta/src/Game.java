import java.util.Arrays;

public class Game {
    private Animal animal;

    public Game(String animalName, int type){
        this.animal = new Animal(animalName, type);

    }

    public Animal getAnimal(){
        return animal;
    }

    public String getStatsString() {
        return Arrays.toString(animal.getStats());
    }
}
