import org.junit.Test;
import uz.sofia.Ciphering;

import java.io.*;

public class TestCiphering {
    @Test
    public void testSimpleString() {
        String test = "abcdef";
        Ciphering.main(("cafe -c " + test + " -o in.txt").split(" "));
        Ciphering.main("cafe in.txt -o out.txt".split(" "));
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("out.txt"));
            int i = 0;
            int b;
            while ((b = bufferedInputStream.read()) >= 0) {
                if (test.getBytes()[i++] != b) {
                    assert false;
                }
            }
            if (i != test.getBytes().length) {
                assert false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File("in.txt").delete();
        new File("out.txt").delete();
        assert true;
    }

    @Test
    public void testFile() {
        String test = "src" + File.separator + "Ciphering.java";
        Ciphering.main(("cafe " + test + " -o in.txt").split(" "));
        Ciphering.main("cafe in.txt -o out.txt".split(" "));
        try {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream("out.txt"));
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(test));
            int b1 = 0;
            int b2 = 0;
            while ((b1 = bufferedInputStream1.read()) >= 0 & (b2 = bufferedInputStream2.read()) >= 0) {
                if (b1 != b2) {
                    assert false;
                }
            }
            if (b1 != b2) {
                assert false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File("in.txt").delete();
        new File("out.txt").delete();
        assert true;
    }
}
