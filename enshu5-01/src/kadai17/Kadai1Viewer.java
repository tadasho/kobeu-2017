package kadai17;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Kadai1Viewer extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    KadaiPane kadaiPane;


    public List<ColoredPoint> getPoints() {
        return kadaiPane.getPoints();
    }

    public void setPoints(List<ColoredPoint> points) {
        kadaiPane.setPoints(points);
    }

    public List<ColoredTriangle> getTriangles() {
        return kadaiPane.getTriangles();
    }

    public void setTriangles(List<ColoredTriangle> triangles) {
        kadaiPane.setTriangles(triangles);
    }

    /**
     * Create the frame.
     */
    public Kadai1Viewer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        textField = new JTextField();
        contentPane.add(textField, BorderLayout.SOUTH);
        textField.setColumns(10);

        kadaiPane = new KadaiPane();
        kadaiPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                textField.setText(e.getPoint().toString());
            }
        });
        contentPane.add(kadaiPane, BorderLayout.CENTER);
    }

}
