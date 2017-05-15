

package uz.sofia;
import java.io.*;

/*Шифрация (-с) или дешифрация (-d) указанного файла. Выходной файл указывается как -o filename.txt,
по умоланию имя формируется из имени входного файла сдобавлением расширения.
Алгоритм шифрации XOR. Ключ указывается после -c или -d в шестнадцатиричной системе, длина ключа - любое целое количество байт.

Вид коммандной строки:
Ciphering [-c key] [-d key] inputname.txt [-o outputename.txt]

Командная строка для шифрации:
-c cafe in.txt -o out.txt Командная строка для дешифрации: -d cafe out.txt */

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
        key = getBytesFromKey(args[0]);

        boolean isFile = false;
        if (args[1].equals("-c")) {
            inputStream = new ByteArrayInputStream(args[2].getBytes());
        } else {
            isFile = true;
            try {
                inputStream = new FileInputStream(args[1]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                System.exit(0);
            }
        }

        if (!isFile && args.length == 3) {
            outputFileName = "out.txt";
        } else if (isFile && args.length == 2) {
            outputFileName = args[1] + ".out";
        } else if ((!isFile && args.length == 5 && args[3].equals("-o")) || (isFile && args.length == 4 && args[2].equals("-o"))) {
            outputFileName = isFile ? args[3] : args[4];
        } else {
            System.out.println("Incorrect input");
            System.exit(0);
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
