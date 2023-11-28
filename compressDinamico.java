import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

class Contador {
    private int count;

    public Contador() {
        this.count = 0;
    }

    public synchronized int getCount() {
        return this.count;
    }

    public synchronized int getAndInc() {
        int get = this.count;
        this.count++;
        return get;
    }
}

class Compressor extends Thread {
    public int id; //identificador da thread
    public Contador C;
    public int qntImg;
    public String imgDir;
    public String outDir;

    // Construtor
    public Compressor (int id, Contador C, int qntImg, String imgDir, String outDir ) {
        this.id = id;
        this.C = C;
        this.qntImg = qntImg;
        this.imgDir = imgDir;
        this.outDir = outDir;
    }

    // Método executado pela thread
    public void run () {

        while( this.C.getCount() < this.qntImg) {
            int i = C.getAndInc();

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

public class compressDinamico {
    public static void main(String[] args){

        String imgDir = args[0];
        String outDir = args[1];
        int qntImg = Integer.parseInt(args[2]);
        int nthreads = Integer.parseInt(args[3]);

        Compressor[] compressores = new Compressor[nthreads];
        Contador C = new Contador();

       long start = System.currentTimeMillis();

        for (int i = 0; i < nthreads; i++) {
            compressores[i] = new Compressor(i, C, qntImg, imgDir, outDir);
            compressores[i].start();
        }

        for (int i = 0; i < compressores.length ; i++) {
            try { 
                compressores[i].join(); 
            } catch (InterruptedException e) { 
                return; }
        }

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Tempo de execução: " + duration + " ms");
    }
}
