package example.stdlib;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BasicChannelExample {
    public static void main(String[] args) throws IOException {
        URL fname = BasicChannelExample.class.getClassLoader().getResource("example/stdlib/nio-data.txt");
        RandomAccessFile raf = new RandomAccessFile(fname.getPath(), "rw");
        FileChannel fc = raf.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(48);
        int byteRead = fc.read(bb);
        while (byteRead != -1) {
            System.out.println("Read " + byteRead);
            bb.flip();
            while (bb.hasRemaining()) {
                System.out.println((char) bb.get());
            }
            bb.clear();
            byteRead = fc.read(bb);
        }
        fc.close();
        raf.close();
    }
}
