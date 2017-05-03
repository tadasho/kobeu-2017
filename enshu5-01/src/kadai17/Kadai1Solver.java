package kadai17;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Kadai1Solver {

	static final int problemWidth = 100;
	static final int problemHeight = 100;
	static final int grid = 4;
	static final Color[] colors = new Color[] { Color.BLUE, Color.RED, Color.GREEN };
	static final int numPoints = 30;
	/**
	 * 与えられた ColoredPoint のリスト
	 */
	private List<ColoredPoint> points;
	/**
	 * 三角形の候補すべて
	 */
	private List<ColoredTriangle> triangles = new ArrayList<ColoredTriangle>();
	/**
	 * 課題１で求める「色が異なり、重なりがなく、面積の和を最大化する三角形のペア」
	 */
	private List<ColoredTriangle> answer = new ArrayList<ColoredTriangle>();
	/**
	 * 三角形ペアの面積の和の最大値を格納
	 */
	private double maxSum = 0.0;

	/**
	 * コンストラクタ
	 * 
	 * @param points
	 *            与えられた ColoredPoint のリスト
	 */
	public Kadai1Solver(List<ColoredPoint> points) {
		this.points = points;
	}

	/**
	 * 問題を解くようのメソッド。といっても、個々のメソッドを呼ぶだけ。
	 */
	private void solve() {
		// 問題情報表示(全部だすのもきついので、最大３つ分だけ表示)
		List<ColoredPoint> points3 = points.subList(0, Math.min(points.size(), 3));
		System.out.println("Points(first 3):" + points3);

		long start = System.currentTimeMillis();

		// 三角形生成＆ソート
		genTriangles();
		// 前から３つ分表示してみる,ソートが終わっているなら、同じ結果がでるはず。
		System.out.println("Triangles(first 3): " + triangles.subList(0, Math.min(triangles.size(), 3)));

		// 三角形の和を最大化しよう
		searchTrianglePair();
		long end = System.currentTimeMillis();

		// 結果を表示
		System.out.println("Sum: " + maxSum);
		for (ColoredTriangle t : answer) {
			System.out.println("Triangle: " + t);
		}
		System.out.println("solved in " + (end - start) + "msec");
	}

	/**
	 * ソート済みの三角形のリストをみながら、色が異なる＆重ならない三角形のペアを探す。 ２重ループ
	 */
	/* Memo
	 * for statementの
	 * i >= 0 を i >= triangles.size() - 110
	 * j >= 0 を j >= triangles.size() - 110
	 * とすることにより同じ結果で実行時間を160[ms]ほどまで短縮できる。
	*/
	private void searchTrianglePair() {
		for (int i = triangles.size() - 1; i >= 0; i--) {
			outside: for (int j = triangles.size() - 1; j >= 0; j--) {
				// maxSum判定
				double tmp = triangles.get(i).getArea() + triangles.get(j).getArea();
				if (maxSum > tmp) {
					continue outside;
				}
				// 色判定
				if (triangles.get(i).getColor() == triangles.get(j).getColor()) {
					continue outside;
				}
				
				// 重なり判定
				if (triangles.get(i).overlapWith(triangles.get(j))) {
					continue outside;
				}

				// 条件に適するものをanswerへ代入
				maxSum = tmp;
				answer.clear();
				answer.add(triangles.get(i));
				answer.add(triangles.get(j));
			}
		}
	}

	/**
	 * 与えられた色と、点のリスト(ps)から、可能性のある三角形をすべて生成し、triangles に格納すること
	 * 
	 * @param color
	 *            三角形の色
	 * @param ps
	 *            Point もしくは「そのサブクラス」を要素に持つリスト
	 */
	private void genTriangles1(Color color, List<? extends Point> ps) {
		// 同じ色の座標リスト ps から、三角形候補をすべて生成
		for (int i = 0; i < ps.size(); i++) {
			for (int j = i; j < ps.size(); j++) {
				for (int k = j; k < ps.size(); k++) {
					ColoredTriangle tmp = new ColoredTriangle(color, ps.get(i), ps.get(j), ps.get(k));
					triangles.add(tmp);
				}
			}
		}
	}

	/**
	 * 三角形を生成してから、大きさ順にソートしよう。
	 *
	 */
	private void genTriangles() {
		// points を色ごとに分けて、genTriangles(Color, List<? extends Point>)を呼ぶ
		// それが終わったら、Collections.sort をつかってソート
		List<ColoredPoint> pointsOfBlue = new ArrayList<>();
		List<ColoredPoint> pointsOfRed = new ArrayList<>();
		List<ColoredPoint> pointsOfGreen = new ArrayList<>();

		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).color == Color.BLUE) {
				pointsOfBlue.add(points.get(i));
			}
			if (points.get(i).color == Color.RED) {
				pointsOfRed.add(points.get(i));
			}
			if (points.get(i).color == Color.GREEN) {
				pointsOfGreen.add(points.get(i));
			}
		}
		genTriangles1(Color.BLUE, pointsOfBlue);
		genTriangles1(Color.RED, pointsOfRed);
		genTriangles1(Color.GREEN, pointsOfGreen);

		Collections.sort(triangles, new AreaComp());
		
		// ソートできているか確認
		/* 
		for (ColoredTriangle t : triangles) {
			System.out.println("Triangles: " + t);
		}
		*/
	}

	/**
	 * メインメソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<ColoredPoint> points = genSampleProblem(new Random(2017));
		Kadai1Solver solver = new Kadai1Solver(points);
		solver.solve();
		solver.display();
	}
	/**
	 * 表示用メソッド
	 */
	private void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kadai1Viewer frame = new Kadai1Viewer();
					frame.setPoints(points);
					frame.setTriangles(answer);
					frame.setBounds(0, 0, grid * problemWidth + 50, grid * problemHeight + 100);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * サンプル問題作成用メソッド
	 * 
	 * @param rand
	 *            ランダム生成用オブジェクト
	 * @return ColoredPoint のリストを返す（同じ座標のポイントは含まれないものとする）
	 */

	public static List<ColoredPoint> genSampleProblem(Random rand) {

		HashSet<Point> points0 = new HashSet<Point>();
		while (points0.size() < numPoints * colors.length) {
			Point p = new Point(grid * (1 + rand.nextInt(problemWidth - 1)),
					grid * (1 + rand.nextInt(problemHeight - 1)));
			if (points0.contains(p))
				continue;
			points0.add(p);
		}
		ArrayList<ColoredPoint> result = new ArrayList<ColoredPoint>(points0.size());
		int cindex = 0;
		for (Point p : points0) {
			Color c = colors[cindex++];
			if (cindex == colors.length)
				cindex = 0;
			result.add(new ColoredPoint(c, p));
		}
		return result;
	}

}
