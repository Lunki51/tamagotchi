package fr.tama.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttributeTest {
    static Attribute attribut;

    @Before
    public void init()
    {
        attribut = new Attribute("test", 13);
    }

    @After
    public void destroy()
    {
        attribut = null;
    }

    @Test
    public void testInit()
    {
        assertEquals(attribut.getName(), "test");
        assertEquals(attribut.getValue(), 13);
    }

    @Test
    public void testIncrease()
    {
        int i = attribut.getValue();
        attribut.increase();
        assertTrue(attribut.getValue() > i);
    }

    @Test
    public void testDecrease()
    {
        int i = attribut.getValue();
        attribut.decrease();
        assertTrue(attribut.getValue() < i);
    }

    //NEED MODIFICATION
    @Test
    public void testToString(){
        //toString() should not always return ""
        fail();
    }

    @Test
    public void testGetName()
    {
        assertEquals(attribut.getName(), "test");
    }

    @Test
    public void testSetName(){
        String s = "iel";
        attribut.setName(s);
        assertEquals(attribut.getName(), s); // ref of <s> should be different than new name of <attribut> ?
    }

    @Test
    public void testGetValue()
    {
        assertEquals(attribut.getValue(), 13);
    }

    @Test
    public void testSetValue(){
        int i = 42;
        attribut.setValue(i);
        assertEquals(attribut.getValue(), i);
    }
}
