package fr.tama.view.utils;

/**
 * Allows to have a abstraction of the screens (panels)
 * and update the panel independently of what panel is
 */
public interface Updatable {

    /**
     * Allows to update the panel attribute
     * specifically because of the differents languages
     */
    void updatePanel();
}
