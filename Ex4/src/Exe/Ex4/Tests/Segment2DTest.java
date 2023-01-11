package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;
import static Exe.Ex4.geo.Ex4Utils.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Segment2D;

class Segment2DTest {

	@Test
	void testContains() {
		Segment2D sg1 = new Segment2D(p2D(7,8),p2D(9, 10));
		Point2D check1,check2,check3;
		check1 = p2D(8.7, 9.7);
		assertTrue(sg1.contains(check1));
		
		check2 =p2D(8, 8.8);
		assertFalse(sg1.contains(check2));
		check3 =p2D(8, 8.999);
		assertTrue(sg1.contains(check3));
		
	}

	@Test
	void testMove() {
		fail("Not yet implemented");
	}

	@Test
	void testScale() {
		fail("Not yet implemented");
	}

	@Test
	void testRotate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPoints() {
		fail("Not yet implemented");
	}
}
