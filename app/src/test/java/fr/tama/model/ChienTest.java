package fr.tama.model;

import static fr.tama.model.Level.EGG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChienTest {

    static Chien chien;
    static final Status MOOD = Status.VERY_GOOD;
    static final Status SHAPE = Status.VERY_GOOD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = false;
    static final String NAME = "Bouboule";
    static final Level LEVEL = EGG;

    @Before
    public void init()
    {
        chien = new Chien(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL);
    }

    @After
    public void destroy()
    {
        //Le coup du chien
        chien.setCurrent(Current.DEAD);
    }

    @Test
    public void testInit()
    {
        assertEquals(chien.getMood(), MOOD);
        assertEquals(chien.getShape(), SHAPE);
        assertEquals(chien.getCurrent(), CURRENT);
        assertEquals(chien.isSex(), SEX);
        assertEquals(chien.getName(), NAME);
    }

    @Test
    public void testEat()
    {
        //Need more information about action
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        int stat = chien.getAttribute("hunger").getValue();
        chien.eat();
        assertTrue(stat > chien.getAttribute("hunger").getValue());
    }

    @Test
    public void testSleep()
    {
        //Need more information about action
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        int stat = chien.getAttribute("tiredness").getValue();
        chien.sleep();
        assertTrue(stat > chien.getAttribute("tiredness").getValue());
    }

    @Test
    public void testPlay()
    {
        //Need more information about action
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        int stat = chien.getAttribute("hapiness").getValue();
        chien.play();
        assertTrue(stat < chien.getAttribute("hapiness").getValue());
    }

    @Test
    public void testToilet()
    {
        //Need more information about action
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        int stat = chien.getAttribute("toilet").getValue();
        chien.toilet();
        assertTrue(stat > chien.getAttribute("toilet").getValue());
    }

    @Test
    public void testWash()
    {
        //Need more information about action
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        assertNotEquals(chien.getCurrent(), Current.DEAD);
        int stat = chien.getAttribute("cleanliness").getValue();
        chien.wash();
        assertTrue(stat < chien.getAttribute("cleanliness").getValue());
    }

    @Test
    public void testUpdate() //TODO
    {
        //Need more information about action
    }
}
