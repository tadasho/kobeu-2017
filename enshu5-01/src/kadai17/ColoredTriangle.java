package kadai17;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class ColoredTriangle {
    /**
     * 三角形の色
     */
    public final Color color;
    /**
     * 頂点の座標
     */
    public List<Point> points;
    /**
     * 三角形の面積
     */
    public double area;
    /**
     * area の値をとるための getter method
     * @return
     */
    public Color getColor() {
		return color;
	}

    /**
     * points 取得用の getter method
     * @return
     */
	public List<Point> getPoints() {
		return points;
	}

	/**
     * AWT 用途の Polygon
     */
    Polygon poly;

    /**
     * コンストラクタ
     * @param color
     * @param a
     * @param b
     * @param c
     */
    public ColoredTriangle(Color color, Point a, Point b, Point c) {
        this.color = color;
        points = new ArrayList<Point>(3);
        points.add(a); points.add(b); points.add(c);
        poly = new Polygon();
        for(Point cp : points) poly.addPoint(cp.x, cp.y);
        this.area = calcArea(points);
    }

    /**
     * 三角形の面積を求めるメソッド
     * @param a
     * @param b
     * @param c
     * @return area
     */
    private static double calcArea(List<Point> ps) {
    	Point2D point0 = new Point2D(ps.get(0).getX(), ps.get(0).getY());
    	Point2D point1 = new Point2D(ps.get(1).getX(), ps.get(1).getY());
        Line2D.Double line = new Line2D.Double(ps.get(0).getX(),ps.get(0).getY(), ps.get(1).getX(), ps.get(1).getY());
        return (int)(line.ptLineDist(ps.get(2).getX(), ps.get(2).getY()) * point0.distance(point1) / 2);  
    }
    /**
     * toString() 再定義
     */
    @Override
    public String toString() {
    	return "area: " + area + "," + getColor() + "," + points.toString();
    }
    /**
     * area を返すメソッド
     * @return
     */
    public double getArea() {
        return area;
    }

    /**
     * 表示用メソッド
     * @param g
     * @param selected この三角形が選択状態にあるか否か
     */
    void paint(Graphics g, boolean selected) {
        g.setColor(selected? ColoredPoint.brightColor(color): color);
        g.fillPolygon(poly);
    }

    public boolean overlapWith(ColoredTriangle target) {
        Area areaObj = new Area(poly);
        areaObj.intersect(new Area(target.poly));
        return !areaObj.isEmpty();
    }

}
