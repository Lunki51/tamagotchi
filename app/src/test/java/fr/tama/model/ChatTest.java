package fr.tama.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChatTest {

    static Chat chat;
    static final Status MOOD = Status.VERY_GOOD;
    static final Status SHAPE = Status.VERY_GOOD;
    static final Current CURRENT = Current.AWAKE;
    static final boolean SEX = false;
    static final String NAME = "Nyan-nyan";
    static final Level LEVEL = Level.EGG;

    @Before
    public void init()
    {
        chat = new Chat(MOOD, SHAPE, CURRENT, SEX, NAME,LEVEL);
    }

    @After
    public void destroy()
    {
        //Le coup du chat
        chat.setCurrent(Current.DEAD);
    }

    @Test
    public void testInit()
    {
        assertEquals(chat.getMood(), MOOD);
        assertEquals(chat.getShape(), SHAPE);
        assertEquals(chat.getCurrent(), CURRENT);
        assertEquals(chat.isSex(), SEX);
        assertEquals(chat.getName(), NAME);
    }

    @Test
    public void testEat()
    {
        //Need more information about action
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        int stat = chat.getAttribute("hunger").getValue();
        chat.eat();
        assertTrue(stat > chat.getAttribute("hunger").getValue());
    }

    @Test
    public void testSleep()
    {
        //Need more information about action
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        int stat = chat.getAttribute("tiredness").getValue();
        chat.sleep();
        assertTrue(stat > chat.getAttribute("tiredness").getValue());
    }

    @Test
    public void testPlay()
    {
        //Need more information about action
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        int stat = chat.getAttribute("hapiness").getValue();
        chat.play();
        assertTrue(stat < chat.getAttribute("hapiness").getValue());
    }

    @Test
    public void testToilet()
    {
        //Need more information about action
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        int stat = chat.getAttribute("toilet").getValue();
        chat.toilet();
        assertTrue(stat > chat.getAttribute("toilet").getValue());
    }

    @Test
    public void testWash()
    {
        //Need more information about action
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        assertNotEquals(chat.getCurrent(), Current.DEAD);
        int stat = chat.getAttribute("cleanliness").getValue();
        chat.wash();
        assertTrue(stat < chat.getAttribute("cleanliness").getValue());
    }

    @Test
    public void testUpdate() //TODO
    {
        //Need more information about action
    }
}
