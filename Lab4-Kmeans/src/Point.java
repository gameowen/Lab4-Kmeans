public class Point {
	double x;
	double y;
	boolean isInitCentroid;
	public Point belonged;

	public boolean equals(Point p2) {

		return (this.x == p2.x) && (this.y == p2.y);
	}
}
