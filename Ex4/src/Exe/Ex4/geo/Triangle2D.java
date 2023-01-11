package Exe.Ex4.geo;

import static Exe.Ex4.geo.Ex4Utils.readPointsString;

/**
 * This class represents a 2D Triangle in the plane. Ex4: you should implement
 * this class!
 * 
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable {
	private Point2D[] _points;

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this._points = new Point2D[3];
		this._points[0] = p1;
		this._points[1] = p2;
		this._points[2] = p3;
	}

	public Triangle2D(Point2D[] points) {
		this._points = new Point2D[3];
		this._points[0] = new Point2D(points[0]);
		this._points[1] = new Point2D(points[1]);
		this._points[2] = new Point2D(points[2]);
	}

	public Triangle2D(String s) {
		String[] vals = s.split(",");
		Point2D[] points = readPointsString(vals, 6);
		if (points != null) {
			this._points = points;
		}
	}

	@Override
	public boolean contains(Point2D ot) {
		boolean ans = Ex4Utils.containsRe(this._points, ot);
		return ans;
	}

	@Override
	public double area() {
		double shetach;
		Point2D kodkod = new Point2D(this._points[1]);
		Point2D midpoint = new Point2D(this._points[0]);
		midpoint.scale(this._points[2], 0.5);
		shetach = kodkod.distance(midpoint) * this._points[0].distance(this._points[2]);
		shetach = shetach / 2;
		return shetach;
	}

	@Override
	public double perimeter() {
		double sum = 0;
		sum = sum + this._points[0].distance(this._points[1]);
		sum = sum + this._points[1].distance(this._points[2]);
		sum = sum + this._points[2].distance(this._points[0]);
		return sum;
	}

	@Override
	public void move(Point2D vec) {
		this._points[0].move(vec);
		this._points[1].move(vec);
		this._points[2].move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(_points);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._points[0].scale(center, ratio);
		this._points[1].scale(center, ratio);
		this._points[2].scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._points[0].rotate(center, angleDegrees);
		this._points[1].rotate(center, angleDegrees);
		this._points[2].rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] pnts = new Point2D[3];
		pnts[0] = this._points[0];
		pnts[1] = this._points[1];
		pnts[2] = this._points[2];
		return pnts;
	}
	
	public String toString() {
		String rep = null;
		if (this._points != null) {
			rep = "";
			for (int i = 0; i < this._points.length; i++) {
				rep += this._points[i].toString();
				rep += ",";
			}
		}
		return rep;
	}

}
