package Exe.Ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import Exe.Ex4.geo.Rect2D;

/**
 * This class represents a collection of GUI_Shape. Ex4: you should implement
 * this class!
 * 
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable {
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}

	public ShapeCollection(ArrayList<GUI_Shapeable> shapes) {
		this._shapes = new ArrayList<GUI_Shapeable>();
		for (GUI_Shapeable gui_shape : shapes) {
			this._shapes.add(gui_shape.copy());
		}
	}

	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		////////// add your code below ///////////
		int size = this._shapes != null ? this._shapes.size() : 0;
		if (i > -1 && i < size)
			return _shapes.remove(i);
		else
			return null;
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		////////// add your code below ///////////
		if (s != null && s.getShape() != null) {
			_shapes.add(i, s);
		}

		//////////////////////////////////////////
	}

	@Override
	public void add(GUI_Shapeable s) {
		if (s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	@Override
	public ShapeCollectionable copy() {
		////////// add your code below ///////////

		ShapeCollection col = new ShapeCollection(this._shapes);
		return col;
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		////////// add your code below ///////////

		this._shapes.sort(comp);

		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {
		////////// add your code below ///////////

		this._shapes.clear();

		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		////////// add your code below ///////////
		// TODO
		try {

			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(fw);
			int length = _shapes.size();
			GUI_Shapeable shape;
			String txt_shape = "";
			for (int i = 0; i < length; i++) {
				shape = _shapes.get(i);
				txt_shape = shape.toString();
				outs.println(txt_shape);
			}
			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		try {
			this._shapes.clear();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			GUI_Shapeable shape;
			for (int i = 1; str != null; i = i + 1) {
				str = br.readLine();
				if (str != null) {
					shape = GUIShape.fromString(str);
					this._shapes.add(shape);
				}
			}
			br.close();
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
		}
		//////////////////////////////////////////
	}

	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		////////// add your code below ///////////
		// TODO

		//////////////////////////////////////////
		return ans;
	}

	@Override
	public String toString() {
		String ans = "";
		GUIShape shape;
		for (int i = 0; i < size(); i = i + 1) {
			shape = (GUIShape) this.get(i);
			ans += shape.toString();
		}
		return ans;
	}

}
