# Shipfovanie
Вторая задача

Шифрация (-с) или дешифрация (-d) указанного файла. Выходной файл указывается как -o filename.txt,
по умоланию имя формируется из имени входного файла сдобавлением расширения.
Алгоритм шифрации XOR. Ключ указывается после -c или -d в шестнадцатиричной системе, длина ключа - любое целое количество байт.

Вид коммандной строки:
Ciphering [-c key] [-d key] inputname.txt [-o outputename.txt]

Командная строка для шифрации:
-c cafe in.txt -o out.txt Командная строка для дешифрации: -d cafe out.txt