package it.unicam.travisbug.c3.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordTool {

    private static final String secret = "TR(r3nTS@QS,C}K.,FvE^jcjJ95{!Wbe>BVs{K";

    public static String getMD5String(String plainText){
        MessageDigest md = null;
        String myHash = null;
        try {
            String secretText = plainText + secret;
            md = MessageDigest.getInstance("MD5");
            md.update(secretText.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return myHash;
    }
}
