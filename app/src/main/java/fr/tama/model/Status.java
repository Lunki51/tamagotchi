package fr.tama.model;

/**
 * Represent the current status of an attribute of the tamagotchi
 */
public enum Status {
    VERY_BAD,
    BAD,
    GOOD,
    VERY_GOOD;

    /**
     * Get lower status level
     * @return lower status level
     */
    Status getMinus(){
        if(this==VERY_GOOD)return GOOD;
        if(this==GOOD)return BAD;
        return VERY_BAD;
    }

    /**
     * Get upper status level
     * @return upper status level
     */
    Status getPlus(){
        if(this==VERY_BAD)return  BAD;
        if(this==BAD)return GOOD;
        return VERY_GOOD;
    }

    /**
     * Check if status is positive
     * @return true if good or verygood else false
     */
    boolean isGood(){
        return this == GOOD || this == VERY_GOOD;
    }
}
