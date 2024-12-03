package org.example;

import java.util.ArrayList;
import java.util.List;

public class Xor {
    public static String convertToHex(String value) {
        StringBuilder hexString = new StringBuilder();
        for (char c : value.toCharArray()) {
            hexString.append(Integer.toHexString(c)).append(" ");
        }
        System.out.println(value + " in hex : " + hexString);
        return hexString.toString().trim();
    }

    public static List<String> convertToHexArray(String value) {
        List<String> hexArray = new ArrayList<>();
        for (char c : value.toCharArray()) {
            hexArray.add(Integer.toHexString(c));
        }
        return hexArray;
    }

    public static List<String> xorEncrypt(String message, String key) {
        List<String> messageHex = convertToHexArray(message);
        List<String> keyHex = createKey(messageHex, convertToHexArray(key));
        List<String> encryptedMessage = new ArrayList<>();

        for (int i = 0; i < messageHex.size(); i++) {
            int xor = Integer.parseInt(messageHex.get(i), 16) ^ Integer.parseInt(keyHex.get(i), 16);
            encryptedMessage.add(Integer.toHexString(xor));
        }
        return encryptedMessage;
    }

    public static String xorDecrypt(List<String> cipher, String key) {
        List<String> keyHex = createKey(cipher, convertToHexArray(key));
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < cipher.size(); i++) {
            int decryptedCharHex = Integer.parseInt(cipher.get(i), 16) ^ Integer.parseInt(keyHex.get(i), 16);
            decryptedMessage.append((char) decryptedCharHex);
        }
        return decryptedMessage.toString();
    }

    public static List<String> createKey(List<String> message, List<String> key) {
        List<String> newKey = new ArrayList<>();
        int messageLength = message.size();
        int keyLength = key.size();

        for (int i = 0; i < messageLength; i++) {
            newKey.add(key.get(i % keyLength));
        }
        return newKey;
    }



    public static void main(String[] args) {
        String message = "Geheimnachricht";
        String key = "secret";
        System.out.println("TEst: " + convertToHex(message));


        System.out.println("Message Hex Array: " + convertToHexArray(message));
        List<String> encryptedMessage = xorEncrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = xorDecrypt(encryptedMessage, key);
        System.out.println("Decrypted Message: " + decryptedMessage);

        // Example from lecture
        String klartext = "Klartext";
        String schluessel = "Schluess";

        List<String> encrypted = xorEncrypt(klartext, schluessel);
        System.out.println("Encrypted: " + encrypted);

        System.out.println("Decrypted: " + xorDecrypt(encrypted, schluessel));
    }

}
