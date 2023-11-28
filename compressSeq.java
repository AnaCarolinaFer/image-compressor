import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class compresSeq {

    public static void main (String[] args) {

        String imgDir = args[0];
        String outDir = args[1];
        int qntImg = Integer.parseInt(args[2]);

        long start = System.currentTimeMillis();

        for(int i = 0; i < qntImg; i++) {

            File inputFile = new File("./"+ imgDir + "/in (" + i + ").jpg");

            BufferedImage inputImage = null;
            try {
                inputImage = ImageIO.read(inputFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = writers.next();

            File outputFile = new File("./"+ outDir +"/out (" + i + ").jpg");
            ImageOutputStream outputStream = null;
            try {
                outputStream = ImageIO.createImageOutputStream(outputFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writer.setOutput(outputStream);

            ImageWriteParam params = writer.getDefaultWriteParam();
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(0.5f);

            try {
                writer.write(null, new IIOImage(inputImage, null, null), params);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writer.dispose();
        }
        
        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Tempo de execução: " + duration + "ms");
    }
}
