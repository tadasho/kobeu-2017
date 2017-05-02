package kadai17;

import java.util.Comparator;

public class AreaComp implements Comparator<ColoredTriangle> {
	public int compare(ColoredTriangle a, ColoredTriangle b) {
		return Double.compare(a.getArea(), b.getArea());
	}
}