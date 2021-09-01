package ru.stqa.pft.java.main;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {
    @Test
    public void testDistance() {
        Point p1 = new Point (3,5);
        Point p2 = new Point (6,8);
        Assert.assertEquals(p1.distanceInPoint(p2) , 4.242640687119285);
    }

    @Test
    public void testDistanceDouble() {
        Point p1 = new Point (0.5,0.5);
        Point p2 = new Point (1,1);
        Assert.assertEquals(p1.distanceInPoint(p2) , 0.7071067811865476);
    }




}