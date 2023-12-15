import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Task5 {
    private static String cryptoCesar(String text, char act){
        char[] data = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          if(c != ' '){
              if(act == 'c'){
                  c = (char) (c - 3);
              }else if(act == 'd'){
                  c = (char) (c + 3);
              }
          }
          data[i] = c;
        }
        return new String(data);
    }

    private static String crypto(String text, char act) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //представляет криптографический алгоритм
        //Представляет криптографический алгоритм.                               //Принимает строку, определяющую, какой алгоритм шифрования использовать, а также некоторые другие параметры алгоритма.
                                                                                 //AES — алгоритм шифрования
                                                                                 //ECB — это режим, в котором может работать алгоритм AES.
                                                                                 //PKCS5Padding — это то, как алгоритм AES должен обрабатывать последние байты данных для шифрования.
        String keyString = "ThisIsASecretKey";
        byte[] keyBytes = keyString.getBytes();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES"); // Устанавливаем ключ для шифрования/дешифрования.

        // Инициализируем шифр в режиме шифрования или дешифрования в зависимости от значения act.
            cipher.init((act == 'c') ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key);

            byte[] input; // Подготавливаем входные данные для шифрования/дешифрования.
            if (act == 'c') {
                input = text.getBytes(StandardCharsets.UTF_8); // Если действие - шифрование, преобразуем строку в массив байтов.
            } else {
                input = Base64.getDecoder().decode(text); // Если действие - дешифрование, декодируем строку из Base64.
            }

            byte[] result = cipher.doFinal(input); // Выполняем шифрование/дешифрование и получаем результат.

            if (act == 'c') {
                return Base64.getEncoder().encodeToString(result);// Если действие - шифрование, возвращаем результат в виде строки Base64.
            } else {
                return new String(result, StandardCharsets.UTF_8); // Если действие - дешифрование, возвращаем результат в виде строки UTF-8.
            }

    }
                                                                                                     
    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encrypted = cryptoCesar("If you can read this... well, it's bad", 'c');
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cryptoCesar(encrypted, 'd');
        System.out.println("Decrypted: " + decrypted);

        System.out.println();

        String encryptedText = crypto("I don't know what Base64 is(", 'c');
        System.out.println("Encrypted: " + encryptedText);


        String decryptedText = crypto(encryptedText, 'd');
        System.out.println("Decrypted: " + decryptedText);
    }
}
