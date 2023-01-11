package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;

import static Exe.Ex4.geo.Ex4Utils.*;

class Circle2DTest {
	
	Circle2D cir1,cir2,cir3,cir4;
	
	@Test
	void testContains() {
		cir1 = new Circle2D(p2D(20, 20), 20);
		Point2D p1,p2,p3;
		p1 = p2D(20, 30);
		assertTrue(cir1.contains(p1));
		
		p2 = p2D(19, 38);
		assertTrue(cir1.contains(p2));
		
		p3 = p2D(20, 40.5);
		assertFalse(cir1.contains(p3));
	}

	@Test
	void testGetPoints() {
		fail("Not yet implemented");
	}

	@Test
	void testScale() {
		cir1 = new Circle2D(p2D(20, 20), 20);
		cir2 = (Circle2D) cir1.copy();
		cir2.scale(p2D(20, 20), 0.9);
		assertEquals(cir1.getRadius()*0.9, cir2.getRadius(),0.01);
	}

	@Test
	void testRotate() {
		fail("Not yet implemented");
	}

}
