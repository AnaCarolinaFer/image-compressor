import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

class Compressor extends Thread {
    public int id;
    public int qntImg;
    public int nthreads;
    public String imgDir;
    public String outDir;
    public int nt;

    // Construtor
    public Compressor(int id, int qntImg, int nthreads, String imgDir, String outDir, int nt) {
        this.id = id;
        this.qntImg = qntImg;
        this.nthreads = nthreads;
        this.imgDir = imgDir;
        this.outDir = outDir;
        this.nt = nt;
    }

    // Método executado pela thread
    public void run() {

        int inicio = this.id * (this.qntImg / this.nt);
        int fim = ((this.id + 1) * (this.qntImg / this.nt)) + 1;

        // for (int i = inicio; i < fim; i++) {
        for (int i = this.id; i < this.qntImg; i += this.nt) {
            File inputFile = new File("./" + this.imgDir + "/in (" + i + ").jpg");

            BufferedImage inputImage = null;
            try {
                inputImage = ImageIO.read(inputFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = writers.next();

            File outputFile = new File("./" + this.outDir + "/out (" + i + ").jpg");
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
    }
}

class compressBlocks {

    public static void main(String[] args) {

        String imgDir = args[0];
        String outDir = args[1];
        int qntImg = Integer.parseInt(args[2]);
        int nthreads = Integer.parseInt(args[3]);

        Compressor[] compressores = new Compressor[nthreads];

        long start = System.currentTimeMillis();

        for (int i = 0; i < nthreads; i++) {
            compressores[i] = new Compressor(i, qntImg, nthreads, imgDir, outDir, nthreads);
            compressores[i].start();
        }

        for (int i = 0; i < compressores.length; i++) {
            try {
                compressores[i].join();
            } catch (InterruptedException e) {
                return;
            }
        }

        long end = System.currentTimeMillis();

        long duration = end - start;

        System.out.println("Tempo de execução: " + duration + " ms");
    }
}