package kadai17;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class KadaiPane extends JPanel {


    private List<ColoredPoint> points;
    private List<ColoredTriangle> triangles;
    private Collection<ColoredPoint> selectedPoints = Collections.<ColoredPoint>emptySet();

    private Collection<ColoredTriangle> selectedTriangles = Collections.<ColoredTriangle>emptySet();


    public List<ColoredPoint> getPoints() {
        return points;
    }
    public void setPoints(List<ColoredPoint> points) {
        this.points = points;
    }

    public List<ColoredTriangle> getTriangles() {
        return triangles;
    }
    public void setTriangles(List<ColoredTriangle> triangles) {
        this.triangles = triangles;
    }
    public Collection<ColoredPoint> getSelectedPoints() {
        return selectedPoints;
    }
    public void setSelectedPoints(Collection<ColoredPoint> selectedPoints) {
        this.selectedPoints = selectedPoints;
    }
    public Collection<ColoredTriangle> getSelectedTriangles() {
        return selectedTriangles;
    }
    public void setSelectedTriangles(Collection<ColoredTriangle> selectedTriangles) {
        this.selectedTriangles = selectedTriangles;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if(triangles!=null) {
            for(ColoredTriangle t: triangles) {
                t.paint(g, selectedTriangles.contains(t));
            }
        }
        if(points!= null) {
            for(ColoredPoint p: points) {
                p.paint(g, selectedPoints.contains(p));
            }
        }
    }

}
