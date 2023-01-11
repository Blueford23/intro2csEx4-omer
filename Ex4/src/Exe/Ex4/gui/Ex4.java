package Exe.Ex4.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Ex4Utils;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.Compartors.*;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the StdDraw
 * with the Map class. Written for 101 java course it uses simple static
 * functions to allow a "Singleton-like" implementation.
 * 
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private ShapeCollectionable _shapes = new ShapeCollection();
	private GUI_Shapeable _gs;
	private Color _color = Color.blue;
	private boolean _fill = false;
	private String _mode = "";
	// added by student
	private Point2D _p1, _p2;
	private ArrayList<Point2D> _points;
	private String[] sorts = { "ByArea", "ByPerimeter", "ByToString", "ByTag", "ByAntiArea", "ByAntiPerimeter",
			"ByAntiToString", "ByAntiTag" };
	private String _directory = "C:\\Users\\omero\\לימוד\\שנה ראשונה סמסטר א\\מבוא לחישוב";
	private final String file_name = "g";

	private static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(ShapeCollectionable s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s.copy();
		}
		_gs = null;
//		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_p1 = null;
		_points = new ArrayList<Point2D>();
		_directory = System.getProperty("user.dir");
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle2D) {
			Circle2D c = (Circle2D) gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if (gs instanceof Rect2D) {
			Rect2D c = (Rect2D) gs;
			double[] x_arr = c.getPointsXs();
			double[] y_arr = c.getPointsYs();
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x_arr, y_arr);
			} else {
				StdDraw_Ex4.polygon(x_arr, y_arr);
			}
		}
		if (gs instanceof Segment2D) {
			Segment2D c = (Segment2D) gs;
			Point2D[] points = c.getPoints();
			StdDraw_Ex4.line(points[0].x(), points[0].y(), points[1].x(), points[1].y());
		}
		if (gs instanceof Triangle2D) {
			Triangle2D c = (Triangle2D) gs;
			Point2D[] points = c.getPoints();
			double[] xs = { points[0].x(), points[1].x(), points[2].x() };
			double[] ys = { points[0].y(), points[1].y(), points[2].y() };
			if (isFill) {
				StdDraw_Ex4.filledPolygon(xs, ys);
			} else {
				StdDraw_Ex4.polygon(xs, ys);
			}
		}
		if (gs instanceof Polygon2D) {
			Polygon2D c = (Polygon2D) gs;
			double[] x_arr = c.getPointsXs();
			double[] y_arr = c.getPointsYs();
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x_arr, y_arr);
			} else {
				StdDraw_Ex4.polygon(x_arr, y_arr);
			}
		}
	}

	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
		}

		if (p.equals("None")) {
			select_none();
		}
		if (p.equals("All")) {
			select_all();
		}
		if (p.equals("Anti")) {
			select_anti();
		}
		if (p.equals("Remove")) {
			removeSelected();
		}

		for (int i = 0; i < sorts.length; i++) {
			if (sorts[i] == p) {
				sort();
			}
		}

		if (p.equals("Save")) {
			save();
		}
		if (p.equals("Load")) {
			load();
		}
		if (p.equals("Copy")) {
			copy();
		}

		drawShapes();

	}

	public void mouseClicked(Point2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		if (_mode.equals("Circle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Segment")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if (_mode.equals("Triangle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				if (_p2 == null)
					_p2 = new Point2D(p);
				else {
					_gs.setColor(_color);
					_gs.setFilled(_fill);
					_shapes.add(_gs);
					_gs = null;
					_p1 = null;
					_p2 = null;
				}
			}
		}

		if (_mode.equals("Polygon")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
				_points.add(_p1);
			} else {
				_points.add(new Point2D(p));
			}
		}

		if (_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				move();
				_p1 = null;
			}
		}
		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				rotate(p);
				_p1 = null;
			}
		}
		if (_mode.equals("Scale_90%")) {
			_p1 = p;
			scale90();
			_p1 = null;
		}
		if (_mode.equals("Scale_110%")) {
			_p1 = p;
			scale110();
			_p1 = null;
		}

		if (_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}

	private void select(Point2D p) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null && g.contains(p)) {
				s.setSelected(!s.isSelected());
//				i = _shapes.size();
			}
		}
	}

	private void move() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.move(_p1);
			}
		}
	}

	private void rotate(Point2D p) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				double angle_degrees = Ex4Utils.angleFromPointRounded(_p1, p);
				g.rotate(_p1, angle_degrees);
			}
		}
	}

	private void scale90() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.scale(_p1, 0.9);
			}
		}
	}

	private void scale110() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.scale(_p1, 1.1);
			}
		}
	}

	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		if (_mode.equals("Polygon") && _p1 != null) {
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			// if points of the new polygon(_points) only
			// contains less then 2 points don't add the point.
			_gs.setShape(_points.size() > 1 ? new Polygon2D(_points) : null);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			_points.clear();
			drawShapes();
		}

	}

	public void mouseMoved(MouseEvent e) {
		if (_p1 != null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			// System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1, y1);
			if (_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1, r);
			}
			if (_mode.equals("Rect")) {
				gs = new Rect2D(_p1, p);
			}
			if (_mode.equals("Segment")) {
				gs = new Segment2D(_p1, p);
			}
			if (_mode.equals("Triangle")) {
				if (_p2 != null) {
					gs = new Triangle2D(_p1, _p2, p);
				} else
					gs = new Triangle2D(_p1, p, p);
			}
			if (_mode.equals("Polygon") && _points.size() > 0) {
				_points.add(p);
				Polygon2D poly = new Polygon2D(_points);
				gs = poly;
				_points.remove(_points.size() - 1);
			}
			_gs = new GUIShape(gs, false, Color.pink, 0);
			drawShapes();
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}

	@Override
	public void show() {
		show(Ex4_Const.DIM_SIZE);
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans += s.toString() + "\n";
		}
		return ans;
	}

	public void sort() {
		Comparator<GUI_Shapeable> comp = compareBy(_mode);
		this._shapes.sort(comp);
	}

	public void save() {
		if (this._shapes != null) {
			String path = _directory + java.io.File.separator + file_name;
			this._shapes.save(path);
		}
	}

	public void load() {
		if (this._shapes != null) {
			String path = _directory + java.io.File.separator + file_name;
			this._shapes.load(path);
		}
	}

	public void select_none() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			s.setSelected(false);
		}
	}

	public void select_all() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			s.setSelected(true);
		}
	}

	public void select_anti() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			s.setSelected(!s.isSelected());
		}
	}

	public void removeSelected() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s != null && s.isSelected()) {
				_shapes.removeElementAt(i);
			}
		}
	}

	public void copy() {
		GUI_Shapeable temp;
		int size = _shapes.size();
		for (int i = 0; i < size; i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s != null && s.isSelected()) {
				temp = s.copy();
				temp.setSelected(true);
				_shapes.add(temp);
				s.setSelected(false);
			}
		}
	}

	/**
	 * return a function to compare {@link GUI_Shapeable}, Decided by key.
	 * 
	 * @param key value to decide which sort function to activate
	 * @return Comparator by key:<br>
	 *         <li>default = {@link CompareByArea}</li>
	 *         <li>"ByPerimeter" = {@link CompareByPerimeter}</li>
	 *         <li>"ByString" = {@link CompareByString}</li>
	 *         <li>"ByTag" = {@link CompareByTag}</li>
	 *         <li>"ByAntiArea" = {@link CompareByAntiArea}</li>
	 *         <li>"ByAntiPerimeter"= {@link CompareByAntiPerimeter}</li>
	 *         <li>"ByAntiString" = {@link CompareByAntiString}</li>
	 *         <li>"ByAntiTag" = {@link CompareByAntiTag}</li>
	 */
	private Comparator<GUI_Shapeable> compareBy(String key) {
		Comparator<GUI_Shapeable> comp;

		if (key == "ByPerimeter")
			comp = new CompareByPerimeter();
		else if (key == "ByToString")
			comp = new CompareByString();
		else if (key == "ByTag")
			comp = new CompareByTag();
		else if (key == "ByAntiArea")
			comp = new CompareByAntiArea();
		else if (key == "ByAntiPerimeter")
			comp = new CompareByAntiPerimeter();
		else if (key == "ByAntiToString")
			comp = new CompareByAntiString();
		else if (key == "ByAntiTag")
			comp = new CompareByAntiTag();
		else
			comp = new CompareByArea();

		return comp;
	}
}
