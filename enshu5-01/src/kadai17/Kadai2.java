package kadai17;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;

public class Kadai2 extends JFrame {

    private JPanel contentPane;
    private JTextField positionField;
    KadaiPane kadaiPane;


    /******************************************
     * setter, getter 群
     * Eclipse の「ソース」→「以上メソッド」で作成
     *******************************************/

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

    public Collection<ColoredPoint> getSelectedPoints() {
        return kadaiPane.getSelectedPoints();
    }

    public void setSelectedPoints(Collection<ColoredPoint> selectedPoints) {
        kadaiPane.setSelectedPoints(selectedPoints);
    }

    public Collection<ColoredTriangle> getSelectedTriangles() {
        return kadaiPane.getSelectedTriangles();
    }

    public void setSelectedTriangles(Collection<ColoredTriangle> selectedTriangles) {
        kadaiPane.setSelectedTriangles(selectedTriangles);
    }


    /**
     *  点の選択状態をリセット。再描画もおこなう。実装済み。参考にしてください。
     *  setSelectedPoints()側で再描画をするのが筋という考え方もある。
     */
    public void resetSelectedPoints() {
        setSelectedPoints(new ArrayList<ColoredPoint>());
        kadaiPane.repaint();
    }

    /**
     * 指定された（クリックされた）場所の ColoredPoint を探す。
     * 座標が完全一致しなくても、十分近い（表示円の中とか）場合は選択されたと考えましょう。
     * @param p
     * @return
     */
    public ColoredPoint searchPoint(Point p) {
    	Iterator<ColoredPoint> iterator = kadaiPane.getPoints().iterator();
    	while(iterator.hasNext()) {
    	    ColoredPoint cp = (ColoredPoint)iterator.next();
    	    if(cp.contain(p)) {
    	        return cp;
    	    }
    	}
    	return null;
    }

    /**
     * kadaiPane をクリックされたときの挙動を定義しましょう。
     *
     * @param p kadaiPane 内のクリック座標
     */
    public void processClick(Point p) {
    	ColoredPoint foundPoint = searchPoint(p);
    	if (foundPoint != null) {
    		kadaiPane.getSelectedPoints().add(foundPoint);
    	}
    	kadaiPane.repaint();
    }
    /**
     * 三角形を選択したときの挙動を書きましょう
     * @return
     */
    public boolean makeTriangle() {
        if (((List<ColoredPoint>)kadaiPane.getSelectedPoints()).size() == 3
        		&& ((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(0).getColor().equals(((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(1).getColor())
        		&& ((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(1).getColor().equals(((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(2).getColor())) {
        	return true;
        } else {
        	return false;
        }
        	
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kadai2 frame = new Kadai2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * ちなみに、ほとんどの部分はDesign ツールをつかって作成
     */
    public Kadai2() {
        setTitle("Kadai2 2017");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 550, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        kadaiPane = new KadaiPane();
        kadaiPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		processClick(e.getPoint());
        	}
        });
        kadaiPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                positionField.setText(e.getPoint().toString());
            }
        });
        contentPane.add(kadaiPane, BorderLayout.CENTER);

        JPanel operationPanel = new JPanel();
        contentPane.add(operationPanel, BorderLayout.SOUTH);

        positionField = new JTextField();
        positionField.setHorizontalAlignment(SwingConstants.RIGHT);
        positionField.setEditable(false);
        operationPanel.add(positionField);
        positionField.setColumns(16);

        JButton btnMakeTriangle = new JButton("make triangle");
        btnMakeTriangle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (makeTriangle()) {
        			//System.out.println("success");
        			ColoredTriangle tri = new ColoredTriangle(((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(0).getColor(),
        					((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(0),
        					((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(1),
        					((List<ColoredPoint>) kadaiPane.getSelectedPoints()).get(2)); 
        			kadaiPane.getTriangles().add(tri);
        		} else {
        			//System.out.println("failed");
        		}
        		resetSelectedPoints();
        	}
        });
        operationPanel.add(btnMakeTriangle);

        JButton btnResetPoints = new JButton("reset points");
        btnResetPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetSelectedPoints();
            }
        });
        operationPanel.add(btnResetPoints);

        problemSetup();
    }
    /**
     * 問題のセットアップや、点や三角形のリストの準備
     */
    public void problemSetup() {
        List<ColoredPoint> points = Kadai1Solver.genSampleProblem(new Random(2017));
        setPoints(points);
        setTriangles(new ArrayList<ColoredTriangle>());
        kadaiPane.setSelectedPoints(new ArrayList<ColoredPoint>());
        kadaiPane.setSelectedTriangles(new ArrayList<ColoredTriangle>());
    }

}
