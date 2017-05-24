package kadai17;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ColoredPoint extends Point {
    Color color;
    static final int normalSize = 4;
    /**
     * コンストラクタ
     * @param c 色
     * @param p 座標
     */
    public ColoredPoint(Color c, Point p) {
        super(p);
        this.color = c;
    }
    /**
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	/**
     * 点がクリックされたかどうか判定する
     * @return
     */
    public boolean contain(Point p) {
    	if (p.distance(this.getX(), this.getY()) < 8) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * 表示用メソッド
     * @param g
     * @param selected
     */
    void paint(Graphics g, boolean selected) {
        g.setColor(selected? brightColor(color): color);
        g.fillOval(this.x-normalSize, this.y-normalSize, normalSize*2, normalSize*2);
		g.setColor(Color.WHITE);
		g.drawOval(this.x-normalSize, this.y-normalSize, normalSize*2, normalSize*2);

    }

    /**
     * toString() つまりこのクラスを文字表現するときの挙動を再定義
     */
    @Override
    public String toString() {
        return "CP(" + super.toString() +", " + color +")";
    }

    /**
     * 選択時の色生成用メソッド
     * @param c もとの色
     * @return 明るくした色（白との間）
     */
    public static Color brightColor(Color c) {
        return new Color(c.getRed()/2+128, c.getGreen()/2+128, c.getBlue()/2+128);
    }
}
