package services.util;

import services.api.util.IHashGenerator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSHA3Generator implements IHashGenerator {

    @Override
    public String createHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-512");
            byte[] messageDigest =
                    md.digest(password.getBytes(StandardCharsets.UTF_8));
            return convertToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("an error in the encryption algorithm");
        }
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hexText = bigInt.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}