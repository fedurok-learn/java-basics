import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

public class ResizeImage {

    public static void main(String args[]) throws IOException {
        System.out.println("Start scaling");
        System.out.println("\n...\n");

        String imgURL = args[1];
        String imgName = args[2];
        String imgExt = args[1].substring(args[1].lastIndexOf('.') + 1);
        if (imgExt == "jpeg") imgExt = "jpg";
        System.out.println(imgExt);

        int newWidth = Integer.parseInt(args[3]);
        
        try {
            BufferedImage image = imageFromURL(imgURL);
            BufferedImage resized = resizeToWidth(image, newWidth);

            File output = new File(imgName + "_scaled." + imgExt);
            ImageIO.write(resized, imgExt, output);

            System.out.println("Scaling completed");
        } catch(MalformedInputException e) {
            System.out.println("Error, URL is invalid");
            System.exit(1);
        }
    }

    private static BufferedImage imageFromURL(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        return ImageIO.read(url);
    }

    private static BufferedImage resizeToWidth(BufferedImage img, int width) {
        int height = (int) ((1.0 * img.getHeight()) / img.getWidth() * width);
        return resize(img, height, width);
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}