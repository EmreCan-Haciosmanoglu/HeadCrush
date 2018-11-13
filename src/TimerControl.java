import java.awt.Point;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TimerControl {
	Queue<HashMap<String, Tile[]>> queue = new LinkedList<HashMap<String, Tile[]>>();
	int sayacLimit = 20;
	int GameLevel;
	HashMap<String,Tile[]> current;
	boolean firststep = true;
	int sayac = 0;
	Tile[] TT;
	int[] offSetArray;
	Tile t1;
	Tile t2;
	int AdjustX;
	int AdjustY;
	Timer myTimer;
	TimerTask gorev;
	boolean durdukmu = true;

	public TimerControl(int gameLevel) {
		GameLevel = gameLevel;
		offSetArray = new int[Level.Form.TM.TMap.length];
	}

	public void StopStart() {
		if (durdukmu) {
			main();
			Level.Form.btnPause.setText("Pause");
			Level.Form.LPaused.setIcon(null);
			Level.Form.LPaused.setBounds(0, 0, 0, 0);
		} else {
			myTimer.cancel();
			durdukmu = true;
			Level.Form.LPaused.setIcon(new ImageIcon(Level.imgPause));
			Level.Form.LPaused.setBounds(0, 0, 1400, 900);
			Level.Form.btnPause.setText("Unpause");
		}
	}

	public void main() {

		myTimer = new Timer();
		gorev = new TimerTask() {

			@Override
			public void run() {
				if (current == null && queue.size() > 0) {
					current = queue.poll();
					firststep = true;
				}
				if (current != null) {
					if (firststep) {
						if (current.containsKey("interchange")) {
							if (Level.Score >= Level.GamesInfos[GameLevel - 1].PointToGetAllStars) {
								Level.FinishTheGame();
							}

							t1 = current.get("interchange")[0];
							t2 = current.get("interchange")[1];
							int i1 = t1.type.getvalue();
							int i2 = t2.type.getvalue();
							t1.setImage(i2);
							t2.setImage(i1);

							JLabel label1 = t1.Jlabel;
							int x1 = label1.getLocation().x;
							int y1 = label1.getLocation().y;

							JLabel label2 = t2.Jlabel;
							int x2 = label2.getLocation().x;
							int y2 = label2.getLocation().y;

							label1.setLocation(new Point(x2, y2));
							label2.setLocation(new Point(x1, y1));

							AdjustX = (x1 - x2) / 20;
							AdjustY = (y1 - y2) / 20;
							for (int i = 0; i < offSetArray.length; i++) {
								offSetArray[i] = 0;
							}
							firststep = false;
						} else if (current.containsKey("fall")) {
							TT = current.get("fall");
							for (int i = 0; i < TT.length; i++) {
								if (TT[i] != null) {
									Level.Form.TM.TMap[TT[i].X][TT[i].Y + offSetArray[i]].setImage(9);
									for (int j = TT[i].Y; j > 0 - offSetArray[i]; j--) {
										int in = Level.Form.TM.TMap[TT[i].X][j - 1 + offSetArray[i]].type.getvalue();
										Level.Form.TM.TMap[TT[i].X][j + offSetArray[i]].setImage(in);

										JLabel label1 = Level.Form.TM.TMap[TT[i].X][j - 1 + offSetArray[i]].Jlabel;
										int x1 = label1.getLocation().x;
										int y1 = label1.getLocation().y;
										JLabel label2 = Level.Form.TM.TMap[TT[i].X][j + offSetArray[i]].Jlabel;
										label2.setLocation(new Point(x1, y1));
									}
									AdjustY = ImgSize.h / (-20);
									Level.Form.TM.TMap[TT[i].X][0]
											.setImage((new SecureRandom()).nextInt(Level.Form.TM.TileRange));
									JLabel l = Level.Form.TM.TMap[TT[i].X][0].Jlabel;
									l.setLocation(new Point(l.getLocation().x, l.getLocation().y - ImgSize.h));
									offSetArray[i]++;
								}
							}
							firststep = false;
						}
					}
					if (current.keySet().toArray()[0].equals("interchange")) {
						if (t1 != null && t2 != null) {
							JLabel label1 = t1.Jlabel;
							label1.setLocation(
									new Point(label1.getLocation().x + AdjustX, label1.getLocation().y + AdjustY));
							JLabel label2 = t2.Jlabel;
							label2.setLocation(
									new Point(label2.getLocation().x - AdjustX, label2.getLocation().y - AdjustY));
						}
					} else if (current.keySet().toArray()[0].equals("fall")) {
						for (int i = 0; i < TT.length; i++) {
							if (TT[i] != null)
								for (int j = TT[i].Y + offSetArray[i] - 1; j >= 0; j--) {
									JLabel label1 = Level.Form.TM.TMap[TT[i].X][j].Jlabel;
									label1.setLocation(
											new Point(label1.getLocation().x, label1.getLocation().y - AdjustY));
								}
						}

					}
					sayac++;
				} else
					sayac = 0;

				if (sayac == sayacLimit) {
					if (current != null && current.keySet().toArray()[0].equals("interchange")) {
						Level.Form.TM.PatPat();
						for (int i = 0; i < offSetArray.length; i++) {
							offSetArray[i] = 0;
						}
					} else if ((queue.peek() != null && queue.peek().keySet().toArray()[0].equals("interchange"))
							|| (queue.peek() == null)) {
						Level.Form.TM.PatPat();
						for (int i = 0; i < offSetArray.length; i++) {
							offSetArray[i] = 0;
						}
					}
					current = null;
					t1 = null;
					t2 = null;
					sayac = 0;
					TT = null;
				} else if (sayac == 0) {
					if ((Level.MovesLeft < 1 && queue.peek() == null)
							|| (Level.Score >= Level.GamesInfos[GameLevel - 1].PointToGetAllStars)) {
						Level.FinishTheGame();
						myTimer.cancel();
					}
				}
			}
		};

		myTimer.schedule(gorev, 0, 40);
		durdukmu = false;
	}
}