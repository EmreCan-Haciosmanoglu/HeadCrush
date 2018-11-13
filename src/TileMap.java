import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TileMap {
	Tile[][] TMap;
	int width;
	int height;
	int TileRange;
	int LevelNum;

	TileMap(int w, int h, int TR, int LN) {
		width = w;
		height = h;
		TileRange = TR;
		LevelNum = LN;
		CreateMap();
	}

	void CreateMap() {
		TMap = new Tile[width][height];
		Random r = new Random();

		for (int x = 0; x < TMap.length; x++) {
			for (int y = 0; y < TMap[x].length; y++) {
				TMap[x][y] = new Tile(x, y, Type.valueOf(r.nextInt(TileRange)), this);
			}
		}
		for (int x = 0; x < TMap.length; x++) {
			for (int y = 0; y < TMap[x].length; y++) {
				TMap[x][y].CreateNeighbours();
			}
		}
	}

	Tile GetTileOverMouse(double posx, double posy) {
		double x = (posx - (Margin.left + 10)) / ImgSize.w;
		double y = (posy - (Margin.top + 32)) / ImgSize.h;
		return TMap[(int) x][(int) y];
	}

	void InterChange(Tile t1, Tile t2) {
		if (t1 == null || t2 == null)
			return;
		Tile[] RTile = { t1, t2 };
		HashMap<String, Tile[]> HshMap = new HashMap<String, Tile[]>();
		HshMap.put("interchange", RTile);
		Level.TC.queue.add(HshMap);
	}

	boolean IsValid(Tile t1, Tile t2) {
		if (t1.X > t2.X) {
			// t1
			if (t1.X > 2 && t1.type == t1.Left.Left.type) {
				if (t1.type == t1.Left.Left.Left.type) {
					return true;
				}
			}
			if (t1.Y > 0 && t1.type == t1.Left.Up.type) {
				if (t1.Y > 1 && t1.type == t1.Left.Up.Up.type) {
					return true;
				}
				if (t1.Y < TMap[0].length - 1 && t1.type == t1.Left.Down.type) {
					return true;
				}
			}
			if (t1.Y < TMap[0].length - 2 && t1.type == t1.Left.Down.type) {
				if (t1.type == t1.Left.Down.Down.type) {
					return true;
				}
			}
			// t2
			if (t2.X < TMap.length - 3 && t2.type == t2.Right.Right.type) {
				if (t2.type == t2.Right.Right.Right.type) {
					return true;
				}
			}
			if (t2.Y > 0 && t2.type == t2.Right.Up.type) {
				if (t2.Y > 1 && t2.type == t2.Right.Up.Up.type) {
					return true;
				}
				if (t2.Y < TMap[0].length - 1 && t2.type == t2.Right.Down.type) {
					return true;
				}
			}
			if (t2.Y < TMap[0].length - 2 && t2.type == t2.Right.Down.type) {
				if (t2.type == t2.Right.Down.Down.type) {
					return true;
				}
			}
		}
		////////////////////////////////////////
		if (t1.Y > t2.Y) {
			// t1
			if (t1.Y > 2 && t1.type == t1.Up.Up.type) {
				if (t1.type == t1.Up.Up.Up.type) {
					return true;
				}
			}
			if (t1.X > 0 && t1.type == t1.Up.Left.type) {
				if (t1.X > 1 && t1.type == t1.Up.Left.Left.type) {
					return true;
				}
				if (t1.X < TMap.length - 1 && t1.type == t1.Up.Right.type) {
					return true;
				}
			}
			if (t1.X < TMap.length - 2 && t1.type == t1.Up.Right.type) {
				if (t1.type == t1.Up.Right.Right.type) {
					return true;
				}
			}
			// t2
			if (t2.Y < TMap[0].length - 3 && t2.type == t2.Down.Down.type) {
				if (t2.type == t2.Down.Down.Down.type) {
					return true;
				}
			}
			if (t2.X > 0 && t2.type == t2.Down.Left.type) {
				if (t2.X > 1 && t2.type == t2.Down.Left.Left.type) {
					return true;
				}
				if (t2.X < TMap.length - 1 && t2.type == t2.Down.Right.type) {
					return true;
				}
			}
			if (t2.X < TMap.length - 2 && t2.type == t2.Down.Right.type) {
				if (t2.type == t2.Down.Right.Right.type) {
					return true;
				}
			}
		}
		return false;
	}

	boolean NoPatPat() {
		boolean done = true;
		for (int i = 0; i < TMap.length; i++) {
			for (int j = 0; j < TMap[i].length; j++) {
				if (i < TMap.length - 2 && TMap[i][j].type.getvalue() == TMap[i + 1][j].type.getvalue()
						&& TMap[i][j].type.getvalue() == TMap[i + 2][j].type.getvalue()) {
					if (TMap[i + 1][j].type.getvalue() < TileRange - 1) {
						TMap[i + 1][j].setImage(TMap[i][j].type.getvalue() + 1);
						done = false;
					} else {
						TMap[i + 1][j].setImage(0);
						done = false;
					}
				}
				if (j < TMap[i].length - 2 && TMap[i][j].type.getvalue() == TMap[i][j + 1].type.getvalue()
						&& TMap[i][j].type.getvalue() == TMap[i][j + 2].type.getvalue()) {
					if (TMap[i][j + 1].type.getvalue() < TileRange - 1) {
						TMap[i][j + 1].setImage(TMap[i][j].type.getvalue() + 1);
						done = false;
					} else {
						TMap[i][j + 1].setImage(0);
						done = false;
					}
				}
			}
		}
		return done;
	}

	boolean PatPat() {
		ArrayList<Tile> Colin = new ArrayList<Tile>();

		for (Tile[] ta : TMap) {
			for (Tile t1 : ta) {
				if (t1.Right != null && t1.type == t1.Right.type)
					if (t1.Right.Right != null && t1.type == t1.Right.Right.type) {
						if (!Colin.contains(t1))
							Colin.add(t1);
						if (!Colin.contains(t1.Right))
							Colin.add(t1.Right);
						if (!Colin.contains(t1.Right.Right))
							Colin.add(t1.Right.Right);
					}

				if (t1.Down != null && t1.type == t1.Down.type)
					if (t1.Down.Down != null && t1.type == t1.Down.Down.type) {
						if (!Colin.contains(t1))
							Colin.add(t1);
						if (!Colin.contains(t1.Down))
							Colin.add(t1.Down);
						if (!Colin.contains(t1.Down.Down))
							Colin.add(t1.Down.Down);
					}
			}
		}
		if (Colin.size() > 0) {
			Tile[][] TMAPCOPY = new Tile[TMap[0].length][TMap.length];
			Level.Score += Math.pow((Colin.size() - 2),1.5) * 100 + 200;

			float width = Helper.Map(Level.Score, 0, Level.GamesInfos[GameProbs.currentLvL-1].PointToGetAllStars, 0, 280);
			
			Level.Form.ProgressBar.setIcon(Helper.readpng((int) width > 0 ? (int) width : 1, 40, "ProgressBar.png"));
			Level.Form.ProgressBar.setBounds(71, 177, (int) width <= 280 ? (int) width : 280, 40);

			Level.Form.LScore.setText("" + Level.Score);
			for (Tile t : Colin) {
				TMAPCOPY[t.Y][t.X] = t;
			}
			for (int i = TMAPCOPY.length - 1; i >= 0; i--) {
				for (int j = 0; j < TMAPCOPY[i].length; j++) {
					if (TMAPCOPY[i][j] != null) {
						Boom(TMAPCOPY[i]);
						break;
					}
				}

			}
		}
		return false;
	}

	void Boom(Tile[] ts) {
		HashMap<String, Tile[]> HshMap = new HashMap<String, Tile[]>();
		HshMap.put("fall", ts);
		Level.TC.queue.add(HshMap);
	}
}