package fr.tama.controller;

import fr.tama.model.AttributeNotFoundException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LangFile {

    private static final HashMap<String,Locale> langs = new HashMap<>();
    ResourceBundle bundle;
    public static boolean lang;

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

    public static void switchLang(){
        String name;
        if(!lang) {
            name = "fr";
            lang = true;
        }
        else {
            name = "en";
            lang = false;
        }
        setLang(name);
    }

    public static void addLang(String name,Locale locale){
        langs.put(name,locale);
    }

    public static LangFile getLangFile(){
        if(langs.size()!=2){
            langs.clear();
            langs.put("fr",Locale.FRENCH);
            langs.put("en",Locale.ENGLISH);
        }
        String sql = "SELECT * FROM config";
        LangFile file = new LangFile(null);
        try{
            Statement stm = DBConnection.getConnection().createStatement();
            ResultSet st = stm.executeQuery(sql);
            if(st.next()){
                file.bundle = ResourceBundle.getBundle("lang",langs.get(st.getString("lang")));
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
