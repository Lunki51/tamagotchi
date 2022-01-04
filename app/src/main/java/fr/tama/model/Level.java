package fr.tama.model;

public enum Level {
    EGG(0),
    BLOB(1),
    HEAD(2),
    CHILD(3),
    ADULT(4);

    private final int level;

    Level(int level){
        this.level = level;
    }

    /**
     * Return current Level value as integer
     * @return current level value as integer
     */
    public int getValue(){
        return this.level;
    }

    /**
     * Return next Level value
     * @return next level value
     */
    public Level next(){
        if(this.level==0)return BLOB;
        if(this.level==1)return HEAD;
        if(this.level==2)return CHILD;
        if(this.level==3)return ADULT;
        return this;
    }

    /**
     * Transform an integer into a Level value
     * @param value integer to compare
     * @return corresponding Level value
     */
    public static Level getLevel(int value){
        switch (value){
            case 0: return EGG;
            case 1: return BLOB;
            case 2: return HEAD;
            case 3: return CHILD;
            case 4: return ADULT;
        }
        return EGG;
    }
}
