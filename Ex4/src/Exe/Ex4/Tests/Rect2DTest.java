package Exe.Ex4.Tests;
import static Exe.Ex4.geo.Ex4Utils.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

class Rect2DTest {
	
	Rect2D rctdefault,rct2,rct3,rct4;
	@Test
	void testHeightandWidth(){
		rctdefault = new Rect2D();
		assertEquals(10,rctdefault.getWidth());
		assertEquals(10,rctdefault.getHeight());
		
		rctdefault.rotate(rctdefault.center(), 120);
		assertEquals(10,roundDbl(rctdefault.getWidth()));
		assertEquals(10,roundDbl(rctdefault.getHeight()));
	}
	
	@Test
	void testStringConstructor() {
		try {
			String s = "0.2140625,0.5078125,0.2140625,0.728125,0.3015625,0.728125,0.3015625,0.5078125";
			Rect2D rct = new Rect2D(s);
			System.out.println(rct);
			
			String snotgoodlist = "0.21406250.50781250.2140625,0.728125,0.3015625,0.728125,0.3015625,0.5078125";
			Rect2D rct1 = new Rect2D(snotgoodlist);
			System.out.println(rct1);
		} catch (Exception e) {
			assert(true);
			System.out.println(e.getMessage());
		}
	}

	@Test
	void testContains() {
		rctdefault = new Rect2D();
		assertTrue(rctdefault.contains(p2D(2,8)));
		assertTrue(rctdefault.contains(p2D(3,2)));
		assertTrue(rctdefault.contains(p2D(9.4,7)));
		assertTrue(rctdefault.contains(p2D(7,10)));
		assertFalse(rctdefault.contains(p2D(5,11)));
		assertFalse(rctdefault.contains(p2D(0,20)));
	}

	@Test
	void testMove() {
		fail("Not yet implemented");
	}

	@Test
	void testScale() {
		rctdefault = new Rect2D();
		rctdefault.scale(p2D(10, 20), 0.9);
		Point2D[] expected1 = {p2D(1, 2),p2D(10, 11)};
		assertArrayEquals(expected1,rctdefault.getPoints());
		
		Point2D[] coords2 = {p2D(0, 0),p2D(0, 0),p2D(0, 0),p2D(0, 0)};
		Rect2D rct2 = new Rect2D(coords2);
		
		Point2D[] coords3 = {p2D(0, 0),p2D(0, 0),p2D(0, 0),p2D(0, 0)};
		Rect2D rct3 = new Rect2D(coords3);
	}

	@Test
	void testRotate() {
		//TODO test edge case when rotated points exceeds the boundaries example:(0,0) rotate with center(10,10)
		// Check if points can exceed the board boundaries.
		rctdefault = new Rect2D();
		rctdefault.rotate(rctdefault.center(), 45);
//		Point2D[] expected1 = {p2D(1, 2),p2D(10, 11)};
//		assertArrayEquals(expected1,rctdef.getPoints());
		
		Point2D[] coords2 = {p2D(0, 0),p2D(0, 5),p2D(5, 5),p2D(5, 0)};
		Rect2D rct2 = new Rect2D(coords2);
		rct2.rotate(p2D(10, 10), 45);
		
		Point2D[] coords3 = {p2D(0, 0),p2D(0, 0),p2D(0, 0),p2D(0, 0)};
		Rect2D rct3 = new Rect2D(coords3);
	}
}
