package org.example.prueba.Utils;

import lombok.RequiredArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
public class ActivationTokenUtils {

    private static final String CIPHER_ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static String createActivationToken() throws Exception {
        String data = String.valueOf(UUID.randomUUID());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM + "/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());

        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedData);
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER_ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

}
