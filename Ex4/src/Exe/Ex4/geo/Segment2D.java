package Exe.Ex4.geo;

import static Exe.Ex4.geo.Ex4Utils.*;

/**
 * This class represents a 2D segment on the plane, Ex4: you should implement
 * this class!
 * 
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable {

	private Point2D _p1, _p2;

	public Segment2D() {
		this._p1 = p2D(0, 0);
		this._p2 = p2D(10, 10);
	}

	public Segment2D(Point2D p1, Point2D p2) {
		this._p1 = p1;
		this._p2 = p2;
	}

	public Segment2D(Segment2D seg) {
		this._p1 = new Point2D(seg.p1());
		this._p2 = new Point2D(seg.p2());
	}

	public Segment2D(String s) {
		String[] vals = s.split(",");
		Point2D[] points = readPointsString(vals, 4);
		if (points != null) {
			this._p1 = points[0];
			this._p2 = points[1];
		}
	}

	@Override
	public String toString() {
		String rep = "";
		rep = rep + this._p1.toString() + ",";
		rep = rep + this._p2.toString();
		return rep;
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		boolean answer = Ex4Utils.isIntersecting(this, ot);
		return answer;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return 2 * this._p1.distance(this._p2);
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		this._p1.move(vec);
		this._p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(this);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] getter = { this._p1, this._p2 };
		return getter;
	}

	public Point2D p1() {
		return _p1;
	}

	public Point2D p2() {
		return _p2;
	}

}