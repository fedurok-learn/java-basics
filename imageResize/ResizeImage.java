import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImage {

    public static void main(String args[]) throws IOException {
        System.out.println("Start scaling");
        System.out.println("\n...\n");

        String imgName = args[1];
        int newWidth = Integer.parseInt(args[2]);

        File input = new File(imgName);
        BufferedImage image = ImageIO.read(input);

        BufferedImage resized = resizeToWidth(image, newWidth);

        File output = new File(imgName.substring(0, imgName.length() - 4) + "_scaled.png");
        ImageIO.write(resized, "png", output);

        System.out.println("Scaling completed");
    }

    private static BufferedImage resizeToWidth(BufferedImage img, int width) {
        int height = (int) ((1.0 * img.getHeight()) / img.getWidth() * width);
        return resize(img, height, width);
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}