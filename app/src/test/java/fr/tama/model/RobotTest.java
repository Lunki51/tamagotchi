package fr.tama.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RobotTest {

    static Robot robot;
    static final Status MOOD = Status.VERY_GOOD;
    static final Status SHAPE = Status.VERY_GOOD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = false;
    static final String NAME = "Bender";
    static final Level LEVEL = Level.EGG;

    @Before
    public void init()
    {
        robot = new Robot(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL);
    }

    @After
    public void destroy()
    {
        //Le coup du robot
        robot.setCurrent(Current.DEAD);
    }

    @Test
    public void testInit()
    {
        assertEquals(robot.getMood(), MOOD);
        assertEquals(robot.getShape(), SHAPE);
        assertEquals(robot.getCurrent(), CURRENT);
        assertEquals(robot.isSex(), SEX);
        assertEquals(robot.getName(), NAME);
    }

    @Test
    public void testEat()
    {
        //Need more information about action
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        int stat = robot.getAttribute("hunger").getValue();
        robot.eat();
        assertTrue(stat > robot.getAttribute("hunger").getValue());
    }

    @Test
    public void testSleep()
    {
        //Need more information about action
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        int stat = robot.getAttribute("tiredness").getValue();
        robot.sleep();
        assertTrue(stat > robot.getAttribute("tiredness").getValue());
    }

    @Test
    public void testPlay()
    {
        //Need more information about action
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        int stat = robot.getAttribute("hapiness").getValue();
        robot.play();
        assertTrue(stat < robot.getAttribute("hapiness").getValue());
    }

    @Test
    public void testToilet()
    {
        //Need more information about action
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        int stat = robot.getAttribute("toilet").getValue();
        robot.toilet();
        assertTrue(stat > robot.getAttribute("toilet").getValue());
    }

    @Test
    public void testWash()
    {
        //Need more information about action
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        assertNotEquals(robot.getCurrent(), Current.DEAD);
        int stat = robot.getAttribute("cleanliness").getValue();
        robot.wash();
        assertTrue(stat < robot.getAttribute("cleanliness").getValue());
    }

    @Test
    public void testUpdate() //TODO
    {
        //Need more information about action
    }
}
