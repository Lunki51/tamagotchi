package fr.tama.model;

import fr.tama.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameSaveTest {

    static GameSave save;

    @Before
    public void init(){
        int slot = 0;
        Tamagotchi tamagotchi = new Chien(Status.GOOD,Status.GOOD,Current.AWAKE,true,"Waf",Level.EGG);
        Location location = Location.getDefaultLocation();
        save = GameSave.createSave(slot,tamagotchi,location);
    }

    @After
    public void destroy(){
        save.delete();
        save = null;
    }

    @Test
    public void testCreateSave(){
        assertEquals(0,save.getSlot());
        assertEquals(Location.getDefaultLocation(),save.getLocation());
        assertEquals(Status.GOOD,save.getTamagotchi().getMood());
        assertEquals(Status.GOOD,save.getTamagotchi().getShape());
        assertEquals("Waf",save.getTamagotchi().getName());
        assertTrue(save.getTamagotchi().isSex());
        assertEquals(Current.AWAKE,save.getTamagotchi().getCurrent());
    }

    @Test
    public void testSave(){
        assertEquals(save.getTamagotchi().getName(),"Waf");
        save.getTamagotchi().setName("Second");
        save.save();
        GameSave save2 = GameSave.loadSave(save.getSlot());
        assertEquals(save.getTamagotchi().getName(),save2.getTamagotchi().getName());
    }

    @Test
    public void testDelete(){
        save.delete();
        assertNull(GameSave.loadSave(save.getSlot()));
    }

    @Test
    public void testLoadSave(){

        GameSave save2 = GameSave.loadSave(save.getSlot());
        assertEquals(save, save2);
    }





}
