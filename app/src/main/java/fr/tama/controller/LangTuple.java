package fr.tama.controller;
import java.util.Locale;

/**
 * Class that shall link Locale and language name in its own language
 */
public class LangTuple
{
    private String name;
    private Locale loc;
    LangTuple(String name, Locale loc)
    {
        this.loc = loc;
        this.name = name;
    }

    public Locale getLocale(){ return loc; }

    public String getName(){ return name; }
}