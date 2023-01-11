package Exe.Ex4;

/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

public class GUIShape implements GUI_Shapeable {
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g != null) {
			_g = g.copy();
		}
		_fill = f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}

	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}

	@Override
	public String toString() {
		String str = GUIShape.class.getSimpleName();
		str += "," + this._color.getRGB();
		str += "," + this.isFilled();
		str += "," + this.getTag();
		GeoShapeable shape = this.getShape();
		str += "," + shape.getClass().getSimpleName();
		str += "," + shape.toString();
		return str;
	}

	private void init(String[] ww) {
	}

	@Override
	public boolean isSelected() {
		return this._isSelected;
	}

	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}

	@Override
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		_g = g;
	}

	public static GUIShape fromString(String str) {
		String[] arr = str.split(",", 6);
		GUIShape nw = null;
		Color c;
		boolean filled;
		int tag;
		GeoShapeable geo;

		if (arr[0].equals(GUIShape.class.getSimpleName())) {
			c = Color.decode(arr[1]);
			filled = Boolean.parseBoolean(arr[2]);
			tag = Integer.parseInt(arr[3]);
			geo = selectedShape(arr[4], arr[5]);
			nw = new GUIShape(geo, filled, c, tag);
		}

		return nw;
	}

	private static GeoShapeable selectedShape(String string_class, String points) {
		GeoShapeable shape = null;
		if (string_class.equals("Segment2D")) {
			shape = new Segment2D(points);
		}else if (string_class.equals("Circle2D")) {
			shape = new Circle2D(points);
		}else if (string_class.equals("Rect2D")) {
			shape = new Rect2D(points);
		}else if (string_class.equals("Triangle2D")) {
			shape = new Triangle2D(points);
		}else if (string_class.equals("Polygon2D")) {
			shape = new Polygon2D(points);
		}else {
			shape = null;
		}
		return shape;
	}

}
