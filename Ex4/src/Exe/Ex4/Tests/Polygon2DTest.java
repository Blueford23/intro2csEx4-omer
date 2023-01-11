package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;

import static Exe.Ex4.geo.Ex4Utils.*;

class Polygon2DTest {
	Point2D[] rectpoints = {p2D(0, 0),p2D(0, 10),p2D(10, 10),p2D(10, 0)};

	@Test
	void testContains() {
		fail("Not yet implemented");
	}

	@Test
	void testArea() {
		Rect2D rct = new Rect2D(rectpoints);
		Polygon2D poly = new Polygon2D(rectpoints);
		assertEquals(rct.area(), poly.area());
	}

	@Test
	void testPerimeter() {
		Rect2D rct = new Rect2D(rectpoints);
		Polygon2D poly = new Polygon2D(rectpoints);
		assertEquals(rct.perimeter(), poly.perimeter());
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
