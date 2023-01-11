package Exe.Ex4.geo;

import static Exe.Ex4.geo.Ex4Utils.readPointsString;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a 2D polygon, as in
 * https://en.wikipedia.org/wiki/Polygon This polygon can be assumed to be
 * simple in terms of area and contains.
 * 
 * You should update this class!
 * 
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable {
	private Point2D[] _points;

	public Polygon2D(ArrayList<Point2D> points) {
		Object[] arr = points.toArray();
		this._points = new Point2D[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this._points[i] = (Point2D) arr[i];
		}
	}

	public Polygon2D(Point2D[] points) {
		if(points != null) {
			Point2D[] arr = new Point2D[points.length];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = new Point2D(points[i]);
			}
			this._points = arr;
		}
	}

	public Polygon2D(String s) {
		String[] vals = s.split(",");
		Point2D[] points = readPointsString(vals, 0);
		if (points != null) {
			this._points = points;
		}
	}

	public void add(Point2D p) {
		int new_length = this._points.length + 1;
		Point2D[] arr = java.util.Arrays.copyOf(this._points, new_length);
		arr[new_length - 1] = p;
	}

	public void remove() {
		int new_length = this._points.length - 1;
		Point2D[] arr = java.util.Arrays.copyOfRange(this._points, 0, new_length);
		this._points = arr;
	}

	public String toString() {
		StringBuilder rep = null;
		if (this._points != null) {
			rep = new StringBuilder();
			rep.append(this._points[0].toString());
			for (int i = 1; i < this._points.length; i++) {
				rep.append(",");
				rep.append(this._points[i].toString());
			}
		}
		return rep.toString();
	}

	@Override
	public boolean contains(Point2D ot) {
		boolean ans = Ex4Utils.containsRe(this._points, ot);
		return ans;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		double total = 0;
		for (int i = 0; i < _points.length; i++) {
			int j = (i + 1) % _points.length;
			total += (_points[i].x() * _points[j].y()) - (_points[j].x() * _points[i].y());
		}
		if (total < 0)
			total = (-1) * total;
		return total / 2;
	}

	@Override
	public double perimeter() {
		double ans = 0;
		for (int i = 1; i < _points.length; i++) {
			ans += _points[i - 1].distance(_points[i]);
		}
		ans += _points[_points.length - 1].distance(_points[0]);
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		Polygon2D pol = new Polygon2D(this._points);
		return pol;
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < _points.length; i++) {
			this._points[i].rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		return this._points;
	}

	public double[] getPointsXs() {
		double[] arr = new double[this._points.length];
		for (int i = 0; i < this._points.length; i++) {
			arr[i] = this._points[i].x();
		}
		return arr;
	}

	public double[] getPointsYs() {
		double[] arr = new double[this._points.length];
		for (int i = 0; i < this._points.length; i++) {
			arr[i] = this._points[i].y();
		}
		return arr;
	}

}
