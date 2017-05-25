package uz.sofia;

import java.io.*;

public class Ciphering {
    private static String outputFileName;
    private static byte[] key;
    private static InputStream inputStream;
    private static File outputFile;

    public static void main(String[] args) {
        parseArgs(args);
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            int i = 0;
            int b;

            while ((b = bufferedInputStream.read()) >= 0) {
                bufferedOutputStream.write(b ^ key[i]);
                i++;
                if (i == key.length) {
                    i = 0;
                }
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseArgs(String[] args) {
        if (!(args.length >= 2 && args.length <= 5)) {
            System.out.println("Incorrect input");
            System.exit(0);
        }

        if (args[0].equals("-c")) {
            try {
                key = args[1].getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                inputStream = new FileInputStream(args[2]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                System.exit(0);
            }
            if (args.length == 3) {
                outputFileName = "out.txt";
            } else if (args.length == 5 && args[3].equals("-o")) {
                outputFileName = args[4];
            } else {
                System.out.println("Incorrect input");
                System.exit(0);
            }
        } else {
            key = getBytesFromKey(args[0]);
            try {
                inputStream = new FileInputStream(args[1]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                System.exit(0);
            }
            if (args.length == 2) {
                outputFileName = "out.txt";
            } else if (args.length == 4 && args[2].equals("-o")) {
                outputFileName = args[3];
            } else {
                System.out.println("Incorrect input");
                System.exit(0);
            }
        }
        outputFile = new File(outputFileName);
    }


    private static byte[] getBytesFromKey(String keyString) {
        if (!keyString.matches("^([0-9A-Fa-f]{2})+")) {
            System.out.println("Incorrect input\n" +
                    "Key must be a hex value");
            System.exit(0);
        }
        byte[] bytes = new byte[keyString.length() / 2];
        for (int i = 0; i < keyString.length() / 2; i++) {
            bytes[i] = (byte) Integer.parseInt(keyString.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
