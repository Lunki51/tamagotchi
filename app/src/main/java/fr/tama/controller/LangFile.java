package fr.tama.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LangFile {

    private static final HashMap<String,LangTuple> langs = new HashMap<>();
    ResourceBundle bundle;
    public static String lang;

    private LangFile(ResourceBundle bundle){
        this.bundle = bundle;
    }

    public static void setLang(String name){
        DBConfig.setString("lang", lang = name);
    }

    public String getString (String string) {
        return bundle.getString(string);
    }

    public static String getName(String sigle)
    {
        if(langs.containsKey(sigle))
            return langs.get(sigle).getName();
        throw new RuntimeException("Requested language undefined: " + sigle);
    }

    public static void switchLang(String l){
        if(langs.containsKey(l))
            setLang(l);
        else
        {
            for(String s : langs.keySet())
                if(l.equals(langs.get(s).getName()))
                {
                    setLang(s);
                    return;
                }
        }
        throw new RuntimeException("Requested language undefined: " + l);
    }

    public static HashMap<String, LangTuple> getLangs()
    {
        return langs;
    }

    public static void addLang(String sigle, String name,Locale locale){
        langs.put(sigle,new LangTuple(name, locale));
    }

    public static LangFile getLangFile()
    {
        //-----------LANGUAGES-----------
        if(langs.size() == 0)
        {
            langs.put("fr", new LangTuple("Français", Locale.FRENCH));
            langs.put("en", new LangTuple("English", Locale.ENGLISH));
        }
        //-------------------------------

        if(lang == null)
            lang = DBConfig.getString("lang");
            
        LangFile file = new LangFile(null);
        file.bundle = ResourceBundle.getBundle("lang",langs.get(lang).getLocale());

        if(file.bundle==null)
            System.err.println("Error bundle is null");

        return file;
    }

}
