

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
    private static byte[] key;
    public static void main(String[] args) {
        if (args.length != 3 && args.length != 5) {
            System.out.println("Incorrect input");
            return;
        }
        if (args[0].equals("-c")) {

        } else if (args[0].equals("-d")) {

        } else {
            System.out.println("Incorrect input\n" +
                    "First argument must be \"-c\" or \"-d\"");
            return;
        }
        int size = (args[1].length() + 1) / 2;
        key = new byte[size];
        long k;
        try {
            k = Long.parseLong(args[1], 16);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input\n" +
                    "Key must be a hex value");
            return;
        }
        while (k != 0) {
            byte b = (byte)(k & 0b11111111);
            key[--size] = b;
            k >>>= 8;
        }
        File inputFile = new File(args[2]);
        File outputFile;
        if (args.length == 5) {
            if (!args[3].equals("-o")) {
                System.out.println("Incorrect input");
                return;
            }
            outputFile = new File(args[4]);
        } else {
            outputFile = new File(args[2] + ".out");
        }
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        try {
            fileInputStream = new FileInputStream(inputFile);
            fileOutputStream = new FileOutputStream(outputFile);
            int i = 0;
            int b;
            while ((b = fileInputStream.read()) >= 0) {
                fileOutputStream.write(b ^ key[i]);
                i++;
                if (i == key.length) {
                    i = 0;
                }
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
