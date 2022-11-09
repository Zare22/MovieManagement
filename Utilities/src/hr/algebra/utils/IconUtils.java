package hr.algebra.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Leo
 */
public class IconUtils {

    private IconUtils() {
    }

    public static ImageIcon createIcon(File file, int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        if (bufferedImage != null) {
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }
        return new ImageIcon("../Cinema/src/assets/no_image.png");
    }

}
