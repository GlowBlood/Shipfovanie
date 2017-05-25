import org.junit.Test;
import uz.sofia.Ciphering;

import java.io.*;

public class TestCiphering {
    @Test
    public void testEmptyFile() {
        String filename = "empty";
        File empty = new File(filename);
        try {
            empty.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ciphering.main(("cafe " + filename + " -o in.txt").split(" "));
        Ciphering.main("cafe in.txt -o out.txt".split(" "));
        try {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream("out.txt"));
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(filename));
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
        empty.delete();
        assert true;
    }

    @Test
    public void testFileWithSmallContent() {
        String filename = "small";
        File small = new File(filename);
        try {
            small.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(small);
            outputStream.write("abc".getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ciphering.main(("cafe123456 " + filename + " -o in.txt").split(" "));
        Ciphering.main("cafe123456 in.txt -o out.txt".split(" "));
        try {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream("out.txt"));
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(filename));
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
        small.delete();
        assert true;
    }

    @Test
    public void testFileWithContentLengthEqualsToKeyLenght() {
        String filename = "small";
        File small = new File(filename);
        try {
            small.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(small);
            outputStream.write("abcd".getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ciphering.main(("cafe " + filename + " -o in.txt").split(" "));
        Ciphering.main("cafe in.txt -o out.txt".split(" "));
        try {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream("out.txt"));
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(filename));
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
        small.delete();
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

    @Test
    public void testFileWithStringKey() {
        String key = "alppimfmclpp./,,.as";
        String test = "src" + File.separator + "Ciphering.java";
        Ciphering.main(("-c " + key + " " + test + " -o in.txt").split(" "));
        Ciphering.main(("-c " + key + " in.txt -o out.txt").split(" "));
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
