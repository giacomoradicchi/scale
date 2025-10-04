import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cotor {
    private BufferedImage img;

    public Cotor(String name) throws Exception{
        this.img = ImageIO.read(new File("./img/" + name));
    }

    public Cotor scale(double scale_factor) {
        if (scale_factor < 0) {
            throw new IllegalArgumentException("\nNumero negativo " + scale_factor + " duro fra, il cotor è troppo factorio.\n");
        }
        if (scale_factor >= 1) {
            img = upscale(scale_factor);
        } else{
            img = downscale(scale_factor);
        }
        return this;
    }

    private BufferedImage downscale(double scale_factor) {
        if (scale_factor > 1) {
            return null; //only for downscale
        }
        int sourceWidth = img.getWidth();
        int sourceHeight = img.getHeight();
        int sizeMask = (int) (1/scale_factor);
        float[][] mask = getMaskArray(sizeMask);
        

        int outWidth = (int) Math.floor(sourceWidth * scale_factor);
        int outHeight = (int) Math.floor(sourceHeight * scale_factor);
        BufferedImage out = new BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < outHeight; y++) {
            for (int x = 0; x < outWidth; x++) {
                float red = 0;
                float green = 0;
                float blue = 0;
                float alpha = 0;

                // getting rgba values by weighting each pixel 
                for (int i = 0; i < mask.length; i++) {
                    for (int j = 0; j < mask[0].length; j++) {
                        Color c = new Color(img.getRGB(Math.min(x*sizeMask + j, sourceWidth-1), Math.min(y*sizeMask + i, sourceHeight-1)));

                        // weighted mean
                        float weight = mask[i][j];
                        red += c.getRed()/255f * weight;
                        green += c.getGreen()/255f * weight;
                        blue += c.getBlue()/255f * weight;
                        alpha += c.getAlpha()/255f * weight;
                    }
                }

                out.setRGB(x, y, new Color(red, green, blue, alpha).getRGB());
            }
        }
        

        return out;
    }

    private BufferedImage upscale(double scale_factor) {
        if (scale_factor < 1) {
            return null;
        }

        int sourceWidth = img.getWidth();
        int sourceHeight = img.getHeight();

        int outWidth = (int) Math.floor(sourceWidth * scale_factor);
        int outHeight = (int) Math.floor(sourceHeight * scale_factor);
        BufferedImage out = new BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < outHeight; y++) {
            for (int x = 0; x < outWidth; x++) {
                out.setRGB(x, y, img.getRGB(
                    (int) Math.min(x / scale_factor, sourceWidth - 1), 
                    (int) Math.min(y / scale_factor, sourceHeight - 1) 
                ));
            }
        }

        return out;
    }

    private float[][] getMaskArray(int size) {
        float[][] mask = new float[size][size];

        float weight = 1.0f / (size*size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mask[i][j] = weight;
            }
        }

        return mask;
    }

    public void save(String outputName) {
        try {
            ImageIO.write(img, getFormatFromExtension(outputName), new File("./img/" + outputName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFormatFromExtension(String outputName) {
        int dotIndex = outputName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == outputName.length() - 1) {
            // di default restituisce png
            return "png";
        }
        // estraggo il cotor string finale e lo metto in minuscolo
        String ext = outputName.substring(dotIndex + 1).toLowerCase();
        switch (ext) {
            case "png":
                return "png";
            case "gif":
                return "gif";
            default:
                return "png"; 
        }
    }
}
