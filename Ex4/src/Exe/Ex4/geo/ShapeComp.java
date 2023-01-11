package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - as a linear order over
 * GUI_Shapes. Ex4: you should implement this class!
 * 
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable> {
	////////// add your code below ///////////

	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);




	private int _flag;

	public ShapeComp(int flag) {
		_flag = flag;

	}

	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans = 0;
		if (_flag == Ex4_Const.Sort_By_toString) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = o1.toString().compareTo(o2.toString());
			} else {
				if (shape1 == null)
					ans = ans - 1;
				if (shape2 == null)
					ans = ans + 1;
			}
		}
		////////// add your code below ///////////
		if (_flag == Ex4_Const.Sort_By_Area) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = Double.compare(shape1.area(), shape2.area());
			} else {
				if (shape1 == null)
					ans = ans - 1;
				if (shape2 == null)
					ans = ans + 1;
			}
		}
		if (_flag == Ex4_Const.Sort_By_Perimeter) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = Double.compare(shape1.perimeter(), shape2.perimeter());
			} else {
				if (shape1 == null)
					ans = ans - 1;
				if (shape2 == null)
					ans = ans + 1;
			}
		}
		if (_flag == Ex4_Const.Sort_By_Tag) {
			int tag1 = o1.getTag();
			int tag2 = o2.getTag();
			ans = Integer.compare(tag1, tag2);
		}
		if (_flag == Ex4_Const.Sort_By_Anti_toString) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = o2.toString().compareTo(o1.toString());
			} else {
				if (shape1 == null)
					ans = ans + 1;
				if (shape2 == null)
					ans = ans - 1;
			}
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Area) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = Double.compare(shape2.area(), shape1.area());
			} else {
				if (shape1 == null)
					ans = ans + 1;
				if (shape2 == null)
					ans = ans - 1;
			}
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			GeoShapeable shape1, shape2;
			shape1 = o1.getShape();
			shape2 = o2.getShape();
			if (shape1 != null && shape2 != null) {
				ans = Double.compare(shape2.perimeter(), shape1.perimeter());
			} else {
				if (shape1 == null)
					ans = ans + 1;
				if (shape2 == null)
					ans = ans - 1;
			}
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
			int tag1 = o1.getTag();
			int tag2 = o2.getTag();
			ans = Integer.compare(tag2, tag1);
		}
		//////////////////////////////////////////
		return ans;
	}

}
