import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

class Margin {
	static int left = 550;
	static int top = 125;
}

class ImgSize {
	static int w = 80;
	static int h = 80;
}

public class Tile {
	Tile Up;
	Tile Down;
	Tile Right;
	Tile Left;
	int X;
	int Y;
	Type type;
	TileMap GG;
	JLabel Jlabel;

	Tile(int x, int y, Type t, TileMap TM) {
		X = x;
		Y = y;
		type = t;
		GG = TM;
		Jlabel = new JLabel("");
		Jlabel.setBounds(X * ImgSize.w + Margin.left, Y * ImgSize.h + Margin.top, ImgSize.w, ImgSize.h);
		setImage(t.getvalue());
		Jlabel.addMouseListener(new Level.MouseEvent());
		Level.Form.add(Jlabel);

	}

	public Tile(Tile up, Tile down, Tile right, Tile left, int x, int y, Type type, TileMap gG, JLabel jlabel) {
		Up = up;
		Down = down;
		Right = right;
		Left = left;
		X = x;
		Y = y;
		this.type = type;
		GG = gG;
		Jlabel = jlabel;
	}

	@Override
	public String toString() {
		return "Tile [X=" + X + ", Y=" + Y + ", type=" + type + "]";
	}

	void CreateNeighbours() {
		Up = (Y > 0) ? GG.TMap[X][Y - 1] : null;
		Down = (Y < GG.height - 1) ? GG.TMap[X][Y + 1] : null;

		Right = (X < GG.width - 1) ? GG.TMap[X + 1][Y] : null;
		Left = (X > 0) ? GG.TMap[X - 1][Y] : null;

	}

	void setImage(int type) {
		Jlabel.setIcon(Helper.readpng(ImgSize.w, ImgSize.h, "img" + type + ".png"));
		this.type = Type.valueOf(type);
	}

	void ResetImageIconPos() {
		Jlabel.setBounds(X * ImgSize.w + 80, Y * ImgSize.h + 60, ImgSize.w, ImgSize.h);
	}

	public Tile Clone() {
		return new Tile(Up, Down, Right, Left, X, Y, type, GG, Jlabel);
	}
}

@SuppressWarnings("unchecked")
enum Type {
	type0(0), type1(1), type2(2), type3(3), type4(4), type5(5), type6(6), type7(7), type8(8), typeBlank(9);

	private int value;
	@SuppressWarnings("rawtypes")
	private static Map map = new HashMap<>();

	private Type(int value) {
		this.value = value;
	}

	static {
		for (Type pagetype : Type.values()) {
			map.put(pagetype.value, pagetype);
		}
	}

	public static Type valueOf(int type) {
		return (Type) map.get(type);
	}

	public int getvalue() {
		return value;
	}
}
