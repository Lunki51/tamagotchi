package fr.tama.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TamagotchiTest {

    static Tamagotchi[] tamas;
    static final Status MOOD = Status.VERY_BAD;
    static final Status SHAPE = Status.VERY_BAD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = true;
    static final String NAME = "Maïté";
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

    
    @Test
    public void testEat()
    {
        for(Tamagotchi t : tamas)
        {
            assertNotEquals(t.getCurrent(), Current.DEAD);
            int stat = t.getAttribute("hunger").getValue();
            t.eat();
            assertTrue(stat < t.getAttribute("hunger").getValue());
        }
    }

    @Test
    public void testSleep()
    {
        for(Tamagotchi t : tamas)
        {
            assertNotEquals(t.getCurrent(), Current.DEAD);
            int stat = t.getAttribute("tiredness").getValue();
            t.sleep();
            assertTrue(stat < t.getAttribute("tiredness").getValue());
        }
    }

    @Test
    public void testPlay()
    {
        for(Tamagotchi t : tamas)
        {
            assertNotEquals(t.getCurrent(), Current.DEAD);
            int stat = t.getAttribute("happiness").getValue();
            t.play();
            assertTrue(stat < t.getAttribute("happiness").getValue());
        }
    }

    @Test
    public void testToilet()
    {
        for(Tamagotchi t : tamas)
        {
            assertNotEquals(t.getCurrent(), Current.DEAD);
            assertNotEquals(t.getCurrent(), Current.DEAD);
            int stat = t.getAttribute("toilet").getValue();
            t.toilet();
            assertTrue(stat < t.getAttribute("toilet").getValue());
        }
    }

    @Test
    public void testWash()
    {
        for(Tamagotchi t : tamas)
        {
            assertNotEquals(t.getCurrent(), Current.DEAD);
            assertNotEquals(t.getCurrent(), Current.DEAD);
            int stat = t.getAttribute("cleanliness").getValue();
            t.wash();
            assertTrue(stat < t.getAttribute("cleanliness").getValue());
        }
    }

    @Test
    public void testUpdate()
    {
        for(Tamagotchi t: tamas)
        {
            Attribute[] attributes = t.getAttributes();
            Attribute[] oldAttributes = new Attribute[attributes.length];
            for(int i = 0; i < attributes.length; i++)
                oldAttributes[i] = new Attribute(attributes[i].getName(), attributes[i].getValue(),500);
            t.update();
            
            for(int i = 0; i < attributes.length; i++)
                if(!attributes[i].getName().equals("health"))
                    assertTrue(attributes[i].getValue() + 7 == oldAttributes[i].getValue() || oldAttributes[i].getValue() < 7);
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
                if(!a.getName().equals("health"))
                    assertEquals(a.getValue(), 0); //Subject to change [2000 for 24h, ...]
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
