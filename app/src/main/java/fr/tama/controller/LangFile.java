package fr.tama.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void setLang(String name) {

        try{
            String sql = "UPDATE config SET lang=? WHERE TRUE";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,name);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
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

    public static LangFile getLangFile(){ //TODO: Automatization from database entries

        //-----------LANGUAGES-----------
        if(langs.size() == 0)
        {
            langs.put("fr", new LangTuple("Français", Locale.FRENCH));
            langs.put("en", new LangTuple("English", Locale.ENGLISH));
        }
        //-------------------------------

        String sql = "SELECT * FROM config";
        LangFile file = new LangFile(null);
        try{
            Statement stm = DBConnection.getConnection().createStatement();
            ResultSet st = stm.executeQuery(sql);
            if(st.next()){
                file.bundle = ResourceBundle.getBundle("lang",langs.get(st.getString("lang")).getLocale());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(file.bundle==null){
            System.err.println("Error bundle is null");
        }
        return file;
    }

}
