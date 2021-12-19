package fr.tama.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static fr.tama.model.Level.*;
import static org.junit.Assert.assertEquals;

public class LevelTest {

    static Level LEVEL = null;

    @Before
    public void init()
    {
        LEVEL = EGG;
    }

    @After
    public void destroy()
    {
        LEVEL=null;
    }

    @Test
    public void testInit(){
        assertEquals(LEVEL,EGG);
    }

    @Test
    public void testGetValue()
    {
        assertEquals(EGG.getValue(),0);
        assertEquals(BLOB.getValue(),1);
        assertEquals(HEAD.getValue(),2);
        assertEquals(CHILD.getValue(),3);
        assertEquals(ADULT.getValue(),4);
    }

    @Test
    public void testNext(){
        assertEquals(EGG.next(),BLOB);
        assertEquals(BLOB.next(),HEAD);
        assertEquals(HEAD.next(),CHILD);
        assertEquals(CHILD.next(),ADULT);
    }

    @Test
    public void testGetLevel(){
        assertEquals(getLevel(0),EGG);
        assertEquals(getLevel(1),BLOB);
        assertEquals(getLevel(2),HEAD);
        assertEquals(getLevel(3),CHILD);
        assertEquals(getLevel(4),ADULT);
    }

}
