package fr.tama.model;

/**
 * Represent status that can take mental or physical state of the tamagotchi
 */
public enum Status {
    VERY_BAD,
    BAD,
    GOOD,
    VERY_GOOD;

    Status getMinus(){
        if(this==VERY_GOOD)return GOOD;
        if(this==GOOD)return BAD;
        return VERY_BAD;
    }

    Status getPlus(){
        if(this==VERY_BAD)return  BAD;
        if(this==BAD)return GOOD;
        return VERY_GOOD;
    }

    boolean isGood(){
        return this == GOOD || this == VERY_GOOD;
    }
}
