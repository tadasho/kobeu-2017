package kadai17;

import java.util.List;
import java.io.Serializable;

class StoreData implements Serializable {
	List<ColoredPoint> points;
	List<ColoredTriangle> triangles;
	public StoreData(List<ColoredPoint> dog, List<ColoredTriangle> cat) {
		points = dog;
		triangles = cat;
	}
	/**
	 * @return points
	 */
	public List<ColoredPoint> getPoints() {
		return points;
	}
	/**
	 * @param points セットする points
	 */
	public void setPoints(List<ColoredPoint> points) {
		this.points = points;
	}
	/**
	 * @return triangles
	 */
	public List<ColoredTriangle> getTriangles() {
		return triangles;
	}
	/**
	 * @param triangles セットする triangles
	 */
	public void setTriangles(List<ColoredTriangle> triangles) {
		this.triangles = triangles;
	}
	
}
