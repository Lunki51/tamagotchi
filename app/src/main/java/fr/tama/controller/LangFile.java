package fr.tama.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LangFile {

    private static final HashMap<String,LangTuple> langs;

    static
    {
        langs = new HashMap<>();
        langs.put("fr", new LangTuple("Français", Locale.FRENCH));
        langs.put("en", new LangTuple("English", Locale.ENGLISH));
    }

    ResourceBundle bundle;
    public static String lang;

    private LangFile(ResourceBundle bundle){
        this.bundle = bundle;
    }

    /**
     * Set in database current language
    */
    public static void saveLang()
    {
        DBConfig.setString("lang", lang);
    }

    /**
     * Return the value of a property in current language
     * @param property Property to get in language file
     * @return Value of the specified property
     */
    public String getString (String property) {
        return bundle.getString(property);
    }

    /**
     * Function that return language name from code ("fr" and "en" are codes associated to language name "Français" and "English")
     * @param code Code from which retrieve language name
     * @return Language name
     */
    public static String getName(String code)
    {
        if(langs.containsKey(code))
            return langs.get(code).getName();
        throw new RuntimeException("Requested language undefined: '" + code + "'");
    }

    public static void switchLang(String l){
        if(langs.containsKey(l))
            lang = l;
        else
        {
            for(String s : langs.keySet())
                if(l.equals(langs.get(s).getName()))
                {
                    lang = s;
                    return;
                }
            throw new RuntimeException("Requested language undefined: '" + l + "'");
        }
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
        if(lang == null)
            lang = DBConfig.getString("lang");
            
        LangFile file = new LangFile(null);
        file.bundle = ResourceBundle.getBundle("lang",langs.get(lang).getLocale());

        if(file.bundle==null)
            System.err.println("Error bundle is null");

        return file;
    }

}
