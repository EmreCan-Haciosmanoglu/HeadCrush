import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GameProbs {
	static int w = 1410;
	static int h = 932;
	static String Title = "HeadCrush Alpha v0.1";
	static int currentLvL = 0;
	static Point Position = new Point(0, 0);
}

public class Level extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public static Level Form;
	public static Level PauseMenu = new Level();
	public static GameInfos[] GamesInfos = new GameInfos[3];
	public TileMap TM;
	static TimerControl TC;
	static int Score = 0;
	static int MovesLeft = 0;
	JLabel LScore = new JLabel("" + Level.Score);
	JLabel LMoves = new JLabel("" + Level.MovesLeft);
	JLabel ProgressBar = new JLabel();
	JLabel gameName;
	JLabel LPaused = new JLabel();
	JButton btnPause = new JButton("Pause");

	static Container game;
	static Container pause;

	static BufferedImage imgPause;
	BufferedImage earnedStar;
	
	public static void StartProgram() {
		CreateMainMenu();
		// CreateCredit();
		// CreatePauseMenu();
		CreateGamesInfos();
		// CreateLevels();
		// CreateEND();
		try {
			imgPause = ImageIO.read(new File("PuD.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void createTC(int LM) {
		TC = new TimerControl(LM);
		TC.main();
	}

	public static void CreateGamesInfos() {
		GamesInfos[0] = new GameInfos(3, 5, 5, 5, 0, false, 0, 800);
		GamesInfos[1] = new GameInfos(5, 5, 6, 6, 0, true, 0, 1300);
		GamesInfos[2] = new GameInfos(10, 5, 6, 6, 0, true, 0, 3000);
	}

	public static void CreateMainMenu() {
		if (Form != null) {
			Form.setContentPane(new Container());
		} else {
			Form = new Level();
			Form.setSize(GameProbs.w, GameProbs.h);
			Form.setTitle(GameProbs.Title);
			Form.setLocation(GameProbs.Position);
		}

		JLabel gameName = new JLabel("HeadCrush");
		gameName.setBounds(GameProbs.w / 2 - 200, 50, 400, 200);
		gameName.setFont(new Font("Times New Roman", 1, 80));

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(GameProbs.w / 2 - 75, 250, 150, 50);
		Action aStart = new Action();
		aStart.name = "Start";
		btnStart.addActionListener(aStart);

		JButton btnCredit = new JButton("Credit");
		btnCredit.setBounds(GameProbs.w / 2 - 75, 400, 150, 50);
		Action aCredit = new Action();
		aCredit.name = "Credit";
		btnCredit.addActionListener(aCredit);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(GameProbs.w / 2 - 75, 550, 150, 50);
		Action aExit = new Action();
		aExit.name = "Exit";
		btnExit.addActionListener(aExit);

		JPanel panel = new JPanel();
		Form.add(gameName);
		Form.add(btnExit);
		Form.add(btnCredit);
		Form.add(btnStart);
		Form.getContentPane().add(panel);

		Form.setVisible(true);
		Form.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void CreateLevels() {
		Form.setContentPane(new Container());

		JLabel gameName = new JLabel("Levels");
		gameName.setBounds(GameProbs.w / 2 - 100, 10, 400, 50);
		gameName.setFont(new Font("Times New Roman", 1, 45));

		JButton btnLvl1 = new JButton("1");
		btnLvl1.setBounds(150, 150, 150, 150);
		if (GamesInfos[0].isLocked) {
			btnLvl1.setIcon(Helper.readpng(150, 150, "LLocked.png"));
		} else
			btnLvl1.setIcon(Helper.readpng(150, 150, "L" + GamesInfos[0].EarnedStar + "Star.png"));
		JLabel LblLvL1 = new JLabel("1");
		LblLvL1.setBounds(200, 100, 50, 50);
		LblLvL1.setFont(new Font("Times New Roman", 1, 50));
		Action aLvl1 = new Action();
		aLvl1.name = "Level";
		aLvl1.GameLevel = 1;
		btnLvl1.addActionListener(aLvl1);

		JButton btnLvl2 = new JButton("2");
		btnLvl2.setBounds(350, 150, 150, 150);
		if (GamesInfos[1].isLocked)
			btnLvl2.setIcon(Helper.readpng(150, 150, "LLocked.png"));
		else
			btnLvl2.setIcon(Helper.readpng(150, 150, "L" + GamesInfos[1].EarnedStar + "Star.png"));
		JLabel LblLvL2 = new JLabel("2");
		LblLvL2.setBounds(400, 100, 50, 50);
		;
		LblLvL2.setFont(new Font("Times New Roman", 1, 50));
		Action aLvl2 = new Action();
		aLvl2.name = "Level";
		aLvl2.GameLevel = 2;
		btnLvl2.addActionListener(aLvl2);

		JButton btnLvl3 = new JButton("3");
		btnLvl3.setBounds(550, 150, 150, 150);
		if (GamesInfos[2].isLocked)
			btnLvl3.setIcon(Helper.readpng(150, 150, "LLocked.png"));
		else
			btnLvl3.setIcon(Helper.readpng(150, 150, "L" + GamesInfos[2].EarnedStar + "Star.png"));
		JLabel LblLvL3 = new JLabel("3");
		LblLvL3.setBounds(600, 100, 50, 50);
		LblLvL3.setFont(new Font("Times New Roman", 1, 50));
		Action aLvl3 = new Action();
		aLvl3.name = "Level";
		aLvl3.GameLevel = 3;
		btnLvl3.addActionListener(aLvl3);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(GameProbs.w / 2 - 100, GameProbs.h - 205, 120, 50);
		Action btnBackAction = new Action();
		btnBackAction.name = "Back to MainMenu";
		btnBack.addActionListener(btnBackAction);
		Form.add(btnBack);

		JPanel panel = new JPanel();
		Form.add(gameName);
		Form.add(LblLvL1);
		Form.add(LblLvL2);
		Form.add(LblLvL3);
		Form.add(btnLvl1);
		Form.add(btnLvl2);
		Form.add(btnLvl3);

		Form.getContentPane().add(panel);
		Form.setVisible(true);
		Form.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void CreatePauseMenu() {
		Form.setContentPane(new Container());
		
		Form.dispose();
		PauseMenu = new Level();
		PauseMenu.setSize(GameProbs.w, GameProbs.h);
		PauseMenu.setTitle(GameProbs.Title);
		PauseMenu.setLocation(GameProbs.Position);

		JLabel gameName = new JLabel("GAME PAUSED");
		gameName.setBounds(GameProbs.w / 2 - 200, 50, 400, 100);
		gameName.setFont(new Font("Times New Roman", 1, 50));

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(GameProbs.w / 2 - 100, 150, 120, 50);
		Action btnMainMAction = new Action();
		btnMainMAction.name = "Main Menu";
		btnMainMenu.addActionListener(btnMainMAction);
		PauseMenu.add(btnMainMenu);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(GameProbs.w / 2 - 100, 250, 120, 50);
		Action btnBackAction = new Action();
		btnBackAction.name = "Back to Game";
		btnBack.addActionListener(btnBackAction);
		PauseMenu.add(btnBack);

		JPanel panel = new JPanel();

		PauseMenu.add(gameName);
		PauseMenu.getContentPane().add(panel);
		PauseMenu.setVisible(true);
		PauseMenu.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void CreateCredit() {
		
		Form.setContentPane(new Container());

		JLabel C1 = new JLabel("Emre Can Hacýosmanoðlu");
		C1.setBounds(GameProbs.w / 2 - 250, 50, 500, 100);
		C1.setFont(new Font("Times New Roman", 1, 40));

		JLabel C2 = new JLabel("Muhammed Talha Demir");
		C2.setBounds(GameProbs.w / 2 - 250, 150, 500, 100);
		C2.setFont(new Font("Times New Roman", 1, 40));

		JLabel C3 = new JLabel("Copyright © 2016 - 2018, ECHOO and/or its affiliates.");
		C3.setBounds(GameProbs.w / 2 - 250, GameProbs.h - 200, 500, 100);
		C3.setFont(new Font("Times New Roman", 1, 20));

		JLabel C4 = new JLabel("All rights reserved.");
		C4.setBounds(GameProbs.w / 2 - 250, GameProbs.h - 150, 500, 100);
		C4.setFont(new Font("Times New Roman", 1, 20));

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(GameProbs.w / 2 - 60, GameProbs.h / 2 - 25, 120, 50);
		Action btnBackAction = new Action();
		btnBackAction.name = "Back to MainMenu";
		btnBack.addActionListener(btnBackAction);
		Form.add(btnBack);

		JPanel panel = new JPanel();
		Form.add(C1);
		Form.add(C2);
		Form.add(C3);
		Form.add(C4);
		Form.getContentPane().add(panel);

		Form.setVisible(true);
		Form.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void CreateEND(String A, String B, String C, int D) {
		Form.setContentPane(new Container());
		PauseMenu.dispose();

		JLabel gameName = new JLabel(A);
		gameName.setBounds(GameProbs.w / 2 - 185, 315, 400, 100);
		gameName.setFont(new Font("Times New Roman", 1, 40));
		Form.add(gameName);

		JLabel YourPoint = new JLabel("Yoour Point : " + Level.Score);
		YourPoint.setBounds(GameProbs.w / 2 - 185, 375, 400, 100);
		YourPoint.setFont(new Font("Times New Roman", 1, 40));
		Form.add(YourPoint);

		JLabel HighPoint = new JLabel("Hiigh Point : " + Level.GamesInfos[GameProbs.currentLvL - 1].HighScore);
		HighPoint.setBounds(GameProbs.w / 2 - 185, 435, 400, 100);
		HighPoint.setFont(new Font("Times New Roman", 1, 40));
		Form.add(HighPoint);

		JButton btnMainMenu = new JButton("MM");
		btnMainMenu.setBounds(GameProbs.w / 2 - 10, 700, 70, 70);
		btnMainMenu.setFont(new Font("Times New Roman", 1, 15));
		Action btnMainMAction = new Action();
		btnMainMAction.name = "Main Menu";
		btnMainMenu.addActionListener(btnMainMAction);
		Form.add(btnMainMenu);

		JButton btnNext = new JButton(B);
		btnNext.setBounds(GameProbs.w / 2 - 10, 600, 70, 70);
		btnNext.setFont(new Font("Times New Roman", 1, 15));
		Action btnBackAction = new Action();
		btnBackAction.name = C;
		btnNext.addActionListener(btnBackAction);
		Form.add(btnNext);
		JLabel LEND = new JLabel(Helper.readpng(600, 600, "END"+D+".png"));
		LEND.setBounds(GameProbs.w / 2 - 300, GameProbs.h / 2 - 300, 600, 600);
		Form.add(LEND);

		JPanel panel = new JPanel();

		Form.getContentPane().add(panel);
		Form.setVisible(true);
		Form.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void CreateGame(int lvl) {
		if(game == null);
			game = new Container();
			
		Form.setContentPane(game);

		// PauseMenu
		JButton btnPauseMenu = new JButton("Pause Menu");
		btnPauseMenu.setBounds(60, 100, 120, 40);
		Action btnPauseMAction = new Action();
		btnPauseMAction.name = "Pause Menu";
		btnPauseMenu.addActionListener(btnPauseMAction);
		Form.add(btnPauseMenu);

		// PauseBtn
		Form.btnPause.setBounds(190, 100, 120, 40);
		Action btnPauseAction = new Action();
		btnPauseAction.name = "Pause";
		Form.btnPause.addActionListener(btnPauseAction);
		Form.add(Form.btnPause);

		// Settings
		JButton btnSettings = new JButton();
		btnSettings.setBounds(320, 100, 40, 40);
		btnSettings.setIcon(Helper.readpng(40, 40, "Settings.png"));
		Action btnSettingsAction = new Action();
		btnSettingsAction.name = "Settings";
		btnSettings.addActionListener(btnSettingsAction);
		Form.add(btnSettings);

		// Score
		Form.LScore.setBounds(240, 265, 400, 50);
		Form.add(Form.LScore);
		Form.LScore.setFont(new Font("Bauhaus 93", 1, 30));
		Level.Score = 0;
		Form.LScore.setText(Level.Score + "");

		JLabel LScore2 = new JLabel("SCORE");
		LScore2.setBounds(80, 265, 400, 50);
		LScore2.setFont(new Font("Bauhaus 93", 1, 25));
		Form.add(LScore2);

		// Moves
		Form.LMoves.setBounds(120, 360, 400, 50);
		Form.add(Form.LMoves);
		Form.LMoves.setFont(new Font("Bauhaus 93", 1, 30));
		Level.MovesLeft = GamesInfos[lvl - 1].Moves;
		Form.LMoves.setText(Level.MovesLeft + "");

		JLabel LMoves2 = new JLabel("MOVES LEFT");
		LMoves2.setBounds(215, 360, 400, 50);
		LMoves2.setFont(new Font("Bauhaus 93", 1, 25));
		Form.add(LMoves2);

		// PauseLabel
		Form.LPaused.setBounds(300, 0, 0, 0);
		Form.add(Form.LPaused);

		// Stars
		JLabel Star1 = new JLabel(Helper.readpng(60, 60, "Star.png"));
		JLabel Star2 = new JLabel(Helper.readpng(60, 60, "Star.png"));
		JLabel Star3 = new JLabel(Helper.readpng(60, 60, "Star.png"));
		Star1.setBounds(142, 170, 60, 60);
		Star2.setBounds(247, 170, 60, 60);
		Star3.setBounds(320, 170, 60, 60);
		Form.add(Star1);
		Form.add(Star2);
		Form.add(Star3);

		// ProgressBar
		Form.ProgressBar.setBounds(71, 177, 0, 40);
		Form.add(Form.ProgressBar);

		// GameName
		Form.gameName = new JLabel("LVL" + lvl);

		Form.gameName.setBounds(GameProbs.w / 2 + 110, 40, 400, 50);
		Form.gameName.setFont(new Font("Times New Roman", 1, 60));
		Form.add(Form.gameName);

		// BackGround
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon iconbackground = new ImageIcon(background);
		JLabel LBackground = new JLabel(iconbackground);
		LBackground.setBounds(0, 0, GameProbs.w - 10, GameProbs.h - 32);
		Form.add(LBackground);

		GameProbs.currentLvL = lvl;
		Form.TM = new TileMap(GamesInfos[GameProbs.currentLvL - 1].width, GamesInfos[GameProbs.currentLvL - 1].height,
				GamesInfos[GameProbs.currentLvL - 1].TileRange, GameProbs.currentLvL);

		Level.createTC(lvl);

		while (!Form.TM.NoPatPat())
			;

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseEvent());

		Form.getContentPane().add(panel);
		Form.setVisible(true);
		Form.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	static void FinishTheGame() {
		if (Level.Score >= Level.GamesInfos[GameProbs.currentLvL - 1].PointToGetAllStars) {
			Level.Score += Level.MovesLeft * 600;
			if (Level.Score > GamesInfos[GameProbs.currentLvL - 1].HighScore) {
				GamesInfos[GameProbs.currentLvL - 1].HighScore = Level.Score;
				try {
					GamesInfos[GameProbs.currentLvL].isLocked = false;
				} catch (Exception e) {
				}
			}
			GamesInfos[GameProbs.currentLvL - 1].EarnedStar = 3;
			CreateEND("You Passed", ">>", "Next Game", 3);
		} else if (Level.Score >= Level.GamesInfos[GameProbs.currentLvL - 1].PointToGetAllStars * 2 / 3.0) {
			if (GamesInfos[GameProbs.currentLvL - 1].EarnedStar < 2) {
				GamesInfos[GameProbs.currentLvL - 1].EarnedStar = 2;
			}
			if (Level.Score > GamesInfos[GameProbs.currentLvL - 1].HighScore) {
				GamesInfos[GameProbs.currentLvL - 1].HighScore = Level.Score;
				try {
					GamesInfos[GameProbs.currentLvL].isLocked = false;
				} catch (Exception e) {
				}
			}
			CreateEND("You Passed", ">>", "Next Game", 2);
		} else if (Level.Score >= Level.GamesInfos[GameProbs.currentLvL - 1].PointToGetAllStars * 1 / 3.0) {
			if (GamesInfos[GameProbs.currentLvL - 1].EarnedStar < 1) {
				GamesInfos[GameProbs.currentLvL - 1].EarnedStar = 1;
			}
			if (Level.Score > GamesInfos[GameProbs.currentLvL - 1].HighScore) {
				GamesInfos[GameProbs.currentLvL - 1].HighScore = Level.Score;
				try {
					GamesInfos[GameProbs.currentLvL].isLocked = false;
				} catch (Exception e) {
				}
			}
			CreateEND("You Passed", ">>", "Next Game", 1);
		} else {

			CreateEND("You Failed", "R", "Replay", 0);
		}

	}

	static class Action implements ActionListener {
		String name = "";
		int GameLevel = 0;

		public void actionPerformed(ActionEvent event) {
			if (name.equals("Exit")) {
				System.exit(0);
			} else if (name.equals("Start")) {
				GameProbs.Position = Form.getLocation();
				CreateLevels();
			} else if (name.equals("Credit")) {
				GameProbs.Position = Form.getLocation();
				CreateCredit();
			} else if (name.equals("Level")) {
				GameProbs.Position = Form.getLocation();
				if (!GamesInfos[GameLevel - 1].isLocked)
					CreateGame(GameLevel);
			} else if (name.equals("Pause Menu")) {
				GameProbs.Position = Form.getLocation();
				if (!Level.TC.durdukmu) {
					Level.TC.StopStart();
				}
				CreatePauseMenu();
				PauseMenu.setLocation(Form.getLocation());
			} else if (name.equals("Main Menu")) {
				GameProbs.Position = Form.getLocation();
				CreateMainMenu();
			} else if (name.equals("Pause")) {
				GameProbs.Position = Form.getLocation();
				Level.TC.StopStart();
			} else if (name.equals("Back to Game")) {
				GameProbs.Position = PauseMenu.getLocation();

				Form.setVisible(true);
				Level.TC.StopStart();
				PauseMenu.setVisible(false);
				Form.setLocation(PauseMenu.getLocation());

			} else if (name.equals("Back to MainMenu")) {
				GameProbs.Position = Form.getLocation();
				CreateMainMenu();
			} else if (name.equals("Replay")) {
				GameProbs.Position = Form.getLocation();
				CreateGame(GameProbs.currentLvL);
			} else if (name.equals("Next Game")) {
				GameProbs.Position = Form.getLocation();
				try {
					CreateGame(GameProbs.currentLvL + 1);
				} catch (Exception e) {
					CreateLevels();
				}
			}

		}

	}

	static class MouseEvent implements MouseListener {
		static Tile FirstT;
		static int FirstX;
		static int FirstY;
		static Tile LastT;

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {

		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			try {

				FirstX = e.getLocationOnScreen().x - Form.getLocation().x;
				FirstY = e.getLocationOnScreen().y - Form.getLocation().y;

				FirstT = Form.TM.GetTileOverMouse(FirstX, FirstY);
				Form.setTitle(FirstT.type.getvalue() + "");
			} catch (Exception e2) {
			}
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			if (FirstT == null) {
				return;
			}
			int LastX = e.getLocationOnScreen().x - Form.getLocation().x;
			int LastY = e.getLocationOnScreen().y - Form.getLocation().y;
			if (Math.abs(LastX - FirstX) > Math.abs(LastY - FirstY)) {
				if (LastX - FirstX > 0) {
					LastT = FirstT.Right;
				} else {
					LastT = FirstT.Left;
				}
			} else {
				if (LastY - FirstY > 0) {
					LastT = FirstT.Down;
				} else {
					LastT = FirstT.Up;
				}
			}
			if (!Level.TC.durdukmu && Level.TC.queue.size() == 0 && Level.MovesLeft > 0) {

				Level.MovesLeft--;
				Form.LMoves.setText(Level.MovesLeft + "");
				Form.TM.InterChange(FirstT, LastT);
				if (((FirstT.X != LastT.X) && !Form.TM.IsValid((FirstT.X > LastT.X) ? FirstT : LastT,
						(FirstT.X > LastT.X) ? LastT : FirstT))
						|| ((FirstT.Y != LastT.Y) && !Form.TM.IsValid((FirstT.Y > LastT.Y) ? FirstT : LastT,
								(FirstT.Y > LastT.Y) ? LastT : FirstT))) {
					Form.TM.InterChange(FirstT, LastT);
				} else {
					while (Form.TM.PatPat())
						;
				}
			}

			FirstT = null;
			LastT = null;
		}

	}
}
