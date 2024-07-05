package common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class ScreenshotUtils {
    private static String screenshotFileName = "screenshotActual";

    private static Optional<Path> getLatestScreenshotPath() throws IOException {
        try (Stream<Path> files = Files.list(Paths.get("screenshots"))) {
            return files
                    .filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
        }
    }

    private static File getBaseImg() {
        File baseImg = Paths.get("screenshots/screenshotBase.png").toFile();
        return baseImg;
    }

    static void saveScreenshot(File file, boolean isBaseScreenshot) {
        try {
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            String now = LocalDateTime.now().format(formatDateTime);
            screenshotFileName += "-" + now;
            if (isBaseScreenshot)
                screenshotFileName = "screenshotBase";
            new File("screenshots/").mkdirs();  //mappa létrehozása, amennyiben még nem létezik
            Files.copy(file.toPath(), Paths.get("screenshots/" + screenshotFileName + ".png"), StandardCopyOption.REPLACE_EXISTING).toFile();
        } catch (IOException e) {
            System.err.println("File copy was not successful! (" + e.getMessage() + ")");
        }
    }

    /**
     * Compare two images with a specified tolerance level for pixel differences.
     *
     * @return true if the images are considered identical within the tolerance, false otherwise
     */
    static boolean compareLatestImages() {
        final int TOLERANCE = 10; // The tolerance value is a between 0 and 255 for the RGB values
        final double DIFFERENCE_THRESHOLD = 0.01; // Allowable percentage of different pixels (e.g.,0.01 means 1%)

        try {
            // Load images
            File fileA = getBaseImg();
            File fileB = getLatestScreenshotPath().get().toFile();
            BufferedImage imgA = ImageIO.read(fileA);
            BufferedImage imgB = ImageIO.read(fileB);

            // Check if images have the same dimensions
            if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
                return false;
            }

            int width = imgA.getWidth();
            int height = imgA.getHeight();
            int totalPixels = width * height;
            int differingPixels = 0;

            // Compare pixels with tolerance
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);

                    int rA = (rgbA >> 16) & 0xff;
                    int gA = (rgbA >> 8) & 0xff;
                    int bA = rgbA & 0xff;

                    int rB = (rgbB >> 16) & 0xff;
                    int gB = (rgbB >> 8) & 0xff;
                    int bB = rgbB & 0xff;

                    if (Math.abs(rA - rB) > TOLERANCE ||
                            Math.abs(gA - gB) > TOLERANCE ||
                            Math.abs(bA - bB) > TOLERANCE) {
                        differingPixels++;
                    }
                }
            }
            double percentageDifference = (double) differingPixels / totalPixels;
            return percentageDifference <= DIFFERENCE_THRESHOLD;
        } catch (Exception e) {
            System.err.println("Image compare was not successful! (" + e.getMessage() + ")");
            return false;
        }
    }
}
