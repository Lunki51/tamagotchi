package fr.tama.model;

import fr.tama.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    static String defaultName;
    static String defaultAction;

    @Before
    public void init(){
        defaultAction = "hunger";
        defaultName = "kitchen";
    }

    @After
    public void destroy(){
    }

    @Test
    public void testGetLocations(){
        Location[] locations = Location.getLocations();
    }

    @Test
    public void testGetName(){
        assertEquals(defaultName, Location.getDefaultLocation().getName());
    }
    @Test
    public void testGetAction(){
        assertEquals(defaultAction, Location.getDefaultLocation().getAction());
    }
    @Test
    public void testGetNext(){
        assertEquals(Location.getLocations()[Location.getLocations().length-1], Location.getDefaultLocation().getNext());
    }
    @Test
    public void testGetPrevious(){
        assertEquals(Location.getLocations()[1], Location.getDefaultLocation().getPrevious());
    }
    @Test
    public void testGetLocation(){
        try{
            assertEquals(Location.getLocation(defaultName).getName(),defaultName);
        }catch (AttributeNotFoundException e){
            fail();
        }
    }
    @Test
    public void testGetDefaultLocation(){
        assertEquals(defaultName, Location.getDefaultLocation().getName());
    }
    @Test
    public void testEquals(){
        assertEquals(Location.getDefaultLocation(), Location.getDefaultLocation());
    }

}
