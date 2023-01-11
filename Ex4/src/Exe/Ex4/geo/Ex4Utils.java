package Exe.Ex4.geo;


import Exe.Ex4.Ex4_Const;

public class Ex4Utils {

	public static double roundDbl(double num) {
		return Math.round(num * 100) / 100.0;
	}

	public static Point2D kefelPoint2D(Point2D point, double num) {
		return new Point2D(point.x() * num, point.y() * num);
	}

	public static Point2D p2D(double x, double y) {
		return new Point2D(x, y);
	}

	/**
	 * 
	 * returns the angle Point b is from Point center
	 * 
	 * @param center
	 * @param b
	 * @return the angle in degrees
	 */
	public static double angleFromPoint(Point2D center, Point2D b) {
		double ans = Math.atan2(b.y() - center.y(), b.x() - center.x());
		ans = roundDbl(Math.toDegrees(ans));
		return ans;
	}

	public static double angleFromPointRounded(Point2D center, Point2D b) {
		double ans = angleFromPoint(center, b);
		return ans > 0 ? ans : ans + 360;
	}

	/**
	 * checks if a point intersects with a segment
	 * 
	 * @param segment
	 * @param point
	 * @return true if the point intersects with the segment otherwise false
	 */
	public static boolean isIntersecting(Segment2D segment, Point2D point) {
		return isIntersecting(segment.p1(), segment.p2(), point);
	}

	/**
	 * checks if a point intersects with a segment of two endpoints
	 * 
	 * @param endpoint1
	 * @param endpoint2
	 * @param point
	 * @return true if the point intersects with the segment otherwise false
	 */
	public static boolean isIntersecting(Point2D endpoint1, Point2D endpoint2, Point2D check) {
		boolean ans = false;
		double min_x, max_x, min_y, max_y;
		min_x = Math.min(endpoint1.x(), endpoint2.x());
		max_x = Math.max(endpoint1.x(), endpoint2.x());
		min_y = Math.min(endpoint1.y(), endpoint2.y());
		max_y = Math.max(endpoint1.y(), endpoint2.y());
		if (check.x() <= max_x && check.x() >= min_x && min_y <= check.y() && check.y() <= max_y) {
			if (endpoint1.x() == endpoint2.x()) {
				// in case of a vertical line if point x,y is between segment endpoints
				// x,y then point x = endpoints x.
				ans = true;
			} else {
				double slope = slopeOf2Points(endpoint1, endpoint2);
				double b = endpoint1.y() - slope * endpoint1.x();
				double yexpected = slope * check.x() + b;
				ans = (Math.abs(check.y() - yexpected) < Ex4_Const.EPS1);
			}
		}
		return ans;
	}

	/**
	 * given a segment and a point, "cast" a ray from point to the right (<point.x),
	 * check if ray intersects with segment and return segments slope alignment
	 * 
	 * @param endpoint1 First point of segment.
	 * @param endpoint2 Second point of segment.
	 * @param check     base point for the ray
	 * @return <b>1</b> - if ray does intersect and segment slope > 0. <br>
	 *         <b>-1</b> - if ray does intersect and segment slope < 0. <br>
	 *         <b>0</b> - if ray doesn't intersect
	 */
	public static int WindNumber(Point2D endpoint1, Point2D endpoint2, Point2D check) {
		int ans = 0;
		double slope;
		double x_of_ray, b;
		boolean isbetween;
		if (endpoint1.x() == endpoint2.x()) {
			// handle vertical line
			if (endpoint1.y() >= endpoint2.y()) {
				isbetween = (endpoint1.y() >= check.y() && endpoint2.y() <= check.y());
				ans = isbetween && endpoint1.x() >= check.x() ? -1 : 0;
			} else {
				isbetween = (endpoint1.y() <= check.y() && endpoint2.y() >= check.y());
				ans = isbetween && endpoint1.x() >= check.x() ? 1 : 0;
			}

		} else {
			slope = slopeOf2Points(endpoint1, endpoint2);
			b = endpoint1.y() - slope * endpoint1.x();
			x_of_ray = (check.y() - b) / slope;
			if (endpoint1.x() >= endpoint2.x())
				isbetween = (endpoint1.x() >= x_of_ray && endpoint2.x() <= x_of_ray);
			else
				isbetween = (endpoint1.x() <= x_of_ray && endpoint2.x() >= x_of_ray);
			if (isbetween && x_of_ray >= check.x()) {
				ans = slope > 0 ? 1 : -1;
			}
		}
		return ans;
	}

	/**
	 * given a point and a geoShapble shape edges, check if the point is inside the
	 * shape (or on the border) using winding numbers algorithm. <br>
	 * * doesn't work with complicated polygons
	 * 
	 * @param shape_points edge points of a shape ordered by connection segments
	 * @param ot           the point
	 * @return if point is contained inside the shape
	 */
	public static boolean contains(Point2D[] shape_points, Point2D ot) {
		double overall = 0;
		double curr;
		Point2D p, pnext;
		int length = shape_points.length;
		for (int i = 0; i < length; i++) {
			p = shape_points[i];
			pnext = shape_points[(i + 1) % length];
			if (ot.equals(p) || isIntersecting(p, pnext, ot)) {
				// if at border return stop iteration and return true
				curr = 0;
				overall = 360;
				i = length;
			} else {
				curr = AngleBetween3Points(ot, p, pnext);
			}
			overall += curr;
		}
		return Math.abs(overall) >= 360;
	}

	public static boolean containsRe(Point2D[] shape_points, Point2D ot) {
		double overall = 0;
		double curr;
		Point2D p, pnext;
		int length = shape_points.length;
		for (int i = 0; i < length; i++) {
			p = shape_points[i];
			pnext = shape_points[(i + 1) % length];
			if (isIntersecting(p, pnext, ot)) {
				overall = 1;
				curr = 0;
				i = length;
			} else {
				curr = WindNumber(p, pnext, ot);
			}
			overall += curr;
		}
		return overall != 0;
	}

	private static double AngleBetween3Points(Point2D Origin, Point2D A, Point2D B) {
		double ans = 0;
		double a, b;
		a = angleFromPointRounded(Origin, A);
		b = angleFromPointRounded(Origin, B);
		boolean rightside = A.x() > Origin.x() && B.x() > Origin.x();
		if (rightside && (A.y() > Origin.y() && B.y() < Origin.y()))
			a += 360;

		if (rightside && (A.y() < Origin.y() && B.y() > Origin.y()))
			b += 360;
		ans = a - b;
		return ans;
	}

	private static double slopeOf2Points(Point2D A, Point2D B) {
		return (A.y() - B.y()) / (A.x() - B.x());
	}

	public static Point2D[] readPointsString(String[] strings, int expected_size) {
		try {
			if ((expected_size == 0 && strings.length % 2 != 0)
					|| (expected_size != 0) && strings.length != expected_size) {
				throw new IllegalArgumentException("Rect2D error:Amount of numbers is not " + expected_size
						+ ", values does not represent this Geoshape");
			} else {
				Point2D[] points = new Point2D[strings.length / 2];
				for (int i = 0, j = 0; i < strings.length; i = i + 2, j++) {
					points[j] = new Point2D(strings[i] + "," + strings[i + 1]);
				}
				return points;
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
