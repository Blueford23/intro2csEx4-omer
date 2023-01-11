package Exe.Ex4.Compartors;

import java.util.Comparator;

import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.geo.GeoShapeable;

public class CompareByTag implements Comparator<GUI_Shapeable> {

	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans = 0;
		int tag1 = o1.getTag();
		int tag2 = o2.getTag();
		ans = Integer.compare(tag1, tag2);
		return ans;
	}

}
