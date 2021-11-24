package fr.tama.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LapinTest {

    static Lapin lapin;
    static final Status MOOD = Status.VERY_GOOD;
    static final Status SHAPE = Status.VERY_GOOD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = true;
    static final Level LEVEL = Level.EGG;
    static final String NAME = "Carrot";

    @Before
    public void init()
    {
        lapin = new Lapin(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL);
    }

    @After
    public void destroy()
    {
        //Le coup du lapin
        lapin.setCurrent(Current.DEAD);
    }

    @Test
    public void testInit()
    {
        assertEquals(lapin.getMood(), MOOD);
        assertEquals(lapin.getShape(), SHAPE);
        assertEquals(lapin.getCurrent(), CURRENT);
        assertEquals(lapin.isSex(), SEX);
        assertEquals(lapin.getName(), NAME);
    }

    @Test
    public void testEat()
    {
        //Need more information about action
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        int stat = lapin.getAttribute("hunger").getValue();
        lapin.eat();
        assertTrue(stat > lapin.getAttribute("hunger").getValue());
    }

    @Test
    public void testSleep()
    {
        //Need more information about action
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        int stat = lapin.getAttribute("tiredness").getValue();
        lapin.sleep();
        assertTrue(stat > lapin.getAttribute("tiredness").getValue());
    }

    @Test
    public void testPlay()
    {
        //Need more information about action
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        int stat = lapin.getAttribute("hapiness").getValue();
        lapin.play();
        assertTrue(stat < lapin.getAttribute("hapiness").getValue());
    }

    @Test
    public void testToilet()
    {
        //Need more information about action
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        int stat = lapin.getAttribute("toilet").getValue();
        lapin.toilet();
        assertTrue(stat > lapin.getAttribute("toilet").getValue());
    }

    @Test
    public void testWash()
    {
        //Need more information about action
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        assertNotEquals(lapin.getCurrent(), Current.DEAD);
        int stat = lapin.getAttribute("cleanliness").getValue();
        lapin.wash();
        assertTrue(stat < lapin.getAttribute("cleanliness").getValue());
    }

    @Test
    public void testUpdate() //TODO
    {
        //Need more information about action
    }
}
