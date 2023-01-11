package Exe.Ex4.geo;

import static Exe.Ex4.geo.Ex4Utils.*;


/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this
 * shape can be rotated!) Ex4: you should implement this class!
 * 
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	/*
	 * _points[1] = _leftUp; _points[2] = _RightUp; ***************************
	 * _points[0] = _leftDown;_points[3] = _RightDown;
	 */
	private Point2D[] _points;

	/**
	 * creates a simple Rect2D with coordinates (from the bottom left point
	 * clockwise): (0,0) (0,10) (10,10) (10,0)
	 */
	public Rect2D() {
		this._points = new Point2D[4];
		this._points[0] = new Point2D(0, 0);
		this._points[1] = new Point2D(0, 10);
		this._points[2] = new Point2D(10, 10);
		this._points[3] = new Point2D(10, 0);
	}

	/**
	 * 
	 * creates a simple Rect2D with coordinates from the parameters. assumes that
	 * each point is not null and a part of a rectangle.
	 * 
	 * @param left_down  The bottom left point of the Rect2D
	 * @param left_up    The top left point of the Rect2D
	 * @param right_up   The top right point of the Rect2D
	 * @param right_down The bottom right point of the Rect2D
	 */
	public Rect2D(Point2D left_down, Point2D left_up, Point2D right_up, Point2D right_down) {
		this._points = new Point2D[4];
		this._points[0] = new Point2D(left_down);
		this._points[1] = new Point2D(left_up);
		this._points[2] = new Point2D(right_up);
		this._points[3] = new Point2D(right_down);
	}

	public Rect2D(Point2D[] po) {
		if (po != null) {
			_points = new Point2D[4];
			this._points[0] = new Point2D(po[0]);
			this._points[1] = new Point2D(po[1]);
			this._points[2] = new Point2D(po[2]);
			this._points[3] = new Point2D(po[3]);
		}
	}

	public Rect2D(Rect2D o) {
		_points = new Point2D[4];
		this._points[0] = new Point2D(o.leftDown());
		this._points[1] = new Point2D(o.leftUp());
		this._points[2] = new Point2D(o.rightUp());
		this._points[3] = new Point2D(o.rightDown());
	}

	public Rect2D(String s) throws IllegalArgumentException{
			String[] vals = s.split(",");
			  Point2D[] points = readPointsString(vals,8);
			  if(points != null) {
				  this._points = points;
			}
	}

	public Rect2D(Point2D p1, Point2D p2) {
		_points = new Point2D[4];
		Point2D vec = p1.vector(p2);
		double lftdwnx = (vec.x() > 0 ? p1.x() : p2.x());
		double lftdwny = (vec.y() > 0 ? p1.y() : p2.y());
		this._points[0] = new Point2D(lftdwnx, lftdwny);
		this._points[1] = new Point2D(lftdwnx, lftdwny + Math.abs(vec.y()));
		this._points[2] = new Point2D(lftdwnx + Math.abs(vec.x()), lftdwny + Math.abs(vec.y()));
		this._points[3] = new Point2D(lftdwnx + Math.abs(vec.x()), lftdwny);
	}

	public double getHeight() {
		return this._points[0].distance(this._points[1]);
	}

	public double getWidth() {
		return this._points[0].distance(this._points[3]);
	}

	public Point2D center() {
		Point2D mid = new Point2D(this._points[0]);
		mid.scale(this._points[2], 0.5);
		return mid;
	}

	@Override
	public boolean contains(Point2D ot) {
		boolean ans = Ex4Utils.containsRe(this._points, ot);
		return ans;
	}

	@Override
	public double area() {
		double ans = this.getHeight() * this.getWidth();
		return Math.abs(ans);
	}

	@Override
	public double perimeter() {
		double ans = getHeight() * 2 + getWidth() * 2;
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		this._points[0].move(vec);
		this._points[1].move(vec);
		this._points[2].move(vec);
		this._points[3].move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(this);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		this._points[0].scale(center, ratio);
		this._points[1].scale(center, ratio);
		this._points[3].scale(center, ratio);
		this._points[2].scale(center, ratio);

	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		this._points[0].rotate(center, angleDegrees);
		this._points[1].rotate(center, angleDegrees);
		this._points[3].rotate(center, angleDegrees);
		this._points[2].rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = { _points[0], _points[2] };
		return ans;
	}

	@Override
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
	
	public double[] getPointsXs(){
		double[] arr = new double[4];
		for (int i = 0; i < this._points.length; i++) {
			arr[i] = this._points[i].x();
		}
		return arr;
	}
	public double[] getPointsYs() {
		double[] arr = new double[4];
		for (int i = 0; i < this._points.length; i++) {
			arr[i] = this._points[i].y();
		}
		return arr;
	}

	private Point2D leftDown() {
		return this._points[0];
	}

	private Point2D leftUp() {
		return this._points[1];
	}

	private Point2D rightUp() {
		return this._points[2];
	}

	private Point2D rightDown() {
		return this._points[3];
	}
}
