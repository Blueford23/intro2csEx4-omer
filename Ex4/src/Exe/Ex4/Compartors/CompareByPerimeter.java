package Exe.Ex4.Compartors;

import java.util.Comparator;

import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.geo.GeoShapeable;

public class CompareByPerimeter implements Comparator<GUI_Shapeable> {

	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans = 0;
		GeoShapeable shape1,shape2;
		shape1 = o1.getShape();
		shape2 = o2.getShape();
		if(shape1 != null && shape2 != null) {
			ans = Double.compare(shape1.perimeter(), shape2.perimeter());
		}else {
			if(shape1 == null)
				ans = ans - 1;
			if(shape2 == null)
				ans = ans + 1;
		}
		return ans;
	}

}
