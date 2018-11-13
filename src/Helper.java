import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Helper {
	static float Map(int value, int initMin, int initMax, int finalMin, int finalMax) {
		// (value - initMin) / (initMax -initMin) = (result - finalMin) / (finalMax -
		// finalMin)
		return (((value - initMin) / (float) (initMax - initMin)) * (finalMax - finalMin)) + finalMin;
	}
	static ImageIcon readpng(int w,int h,String path ) {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (img == null)
			return null;
		Image dimg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
		
	}
}
