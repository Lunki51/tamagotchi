package fr.tama.controller;
import java.util.Locale;


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