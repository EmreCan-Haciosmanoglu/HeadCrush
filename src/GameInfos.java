
public class GameInfos{
	int Moves;
	int TileRange;
	int width;
	int height;
	int EarnedStar;
	boolean isLocked;
	int HighScore;
	int PointToGetAllStars;
	
	public GameInfos(int moves, int tileRange, int width, int height, int earnedStar, boolean isLocked, int highScore,
			int pointToGetAllStars) {
		super();
		Moves = moves;
		TileRange = tileRange;
		this.width = width;
		this.height = height;
		EarnedStar = earnedStar;
		this.isLocked = isLocked;
		HighScore = highScore;
		PointToGetAllStars = pointToGetAllStars;
	}
}
