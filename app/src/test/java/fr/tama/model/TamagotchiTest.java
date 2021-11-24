package fr.tama.model;

import fr.tama.controller.LangFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Objects;

public class TamagotchiTest {

    static Tamagotchi[] tamas;
    static final Status MOOD = Status.VERY_BAD;
    static final Status SHAPE = Status.VERY_BAD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = true;
    static final String NAME = "PasseParTrou";
    static final Level LEVEL = Level.EGG;

    @Before
    public void init() // Test each kind of Tamagotchi, they should have similar behavior according to their methods
    {
        tamas = new Tamagotchi[]
        { 
            new Chat( MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL),
            new Chien(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL),
            new Lapin(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL),
            new Robot(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL)
        };
    }

    @After
    public void destroy()
    {
        for(Tamagotchi t: tamas)
            t.setCurrent(Current.DEAD);
    }


    //Getters
    @Test
    public void testIsSex()
    {
        for(Tamagotchi t: tamas)
            assertEquals(t.isSex(), SEX);
    }

    @Test
    public void testGetName()
    {
        for(Tamagotchi t: tamas)
            assertEquals(t.getName(), NAME);
    }

    @Test
    public void testGetMood()
    {
        for(Tamagotchi t: tamas)
            assertEquals(t.getMood(), MOOD);
    }

    @Test
    public void testGetShape()
    {
        for(Tamagotchi t: tamas)
            assertEquals(t.getShape(), SHAPE);
    }

    @Test
    public void testGetCurrent()
    {
        for(Tamagotchi t: tamas)
            assertEquals(t.getCurrent(), CURRENT);
    }

    //Setters
    @Test
    public void testSetSex()
    {
        boolean s = false;
        for(Tamagotchi t: tamas)
        {
            assertNotEquals(s, t.isSex());
            t.setSex(s);
            assertEquals(t.isSex(), s);
        }
    }

    @Test
    public void testSetName()
    {
        String s = "Ohayou";
        for(Tamagotchi t: tamas)
        {
            assertNotEquals(t.getName(), s);
            t.setName(s);
            assertEquals(t.getName(), s);
        }
    }

    @Test
    public void testSetMood()
    {
        Status s = Status.GOOD;
        for(Tamagotchi t: tamas)
        {
            assertNotEquals(t.getMood(), s);
            t.setMood(s);
            assertEquals(t.getMood(), s);
        }
    }

    @Test
    public void testSetShape()
    {
        Status s = Status.BAD;
        for(Tamagotchi t: tamas)
        {
            assertNotEquals(t.getShape(), s);
            t.setShape(s);
            assertEquals(t.getShape(), s);
        }
    }

    @Test
    public void testSetCurrent()
    {
        Current s = Current.ASLEEP;
        for(Tamagotchi t: tamas)
        {
            assertNotEquals(t.getCurrent(), s);
            t.setCurrent(s);
            assertEquals(t.getCurrent(), s);
        }
    }

    //Other methods
    @Test
    public void testSetupDefaultAttributes()
    {
        for(Tamagotchi t: tamas)
        {
            t.setupDefaultAttributes();

            for(Attribute a: t.getAttributes())
                assertEquals(a.getValue(), 0);
        }
    }

    @Test
    public void testEquals()
    {
        for(int i = 0; i < tamas.length; i++)
        {
            for(int j = 0; j < tamas.length; j++)
                if(i == j)
                    assertEquals(tamas[i], tamas[j]);
                else //If 2 tamagotchis are equals, ensure that their actual classes are the same
                    assertFalse(tamas[i].equals(tamas[j]) && tamas[i].getClass() != tamas[j].getClass());

            assertNotEquals(tamas[i], null);
        }
    }
}
