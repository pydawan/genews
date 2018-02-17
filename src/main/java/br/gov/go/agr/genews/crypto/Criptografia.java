package br.gov.go.agr.genews.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public abstract class Criptografia {
   
   private static final String encrypt(String value, String algorithm) {
      String result = value;
      if (value != null) {
         MessageDigest md;
         try {
            md = MessageDigest.getInstance(algorithm);
            md.update(value.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) {
               result = "0" + result;
            }
         } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
         }
      }
      return result;
   }
   
   public static final String encryptMD5(String value) {
      return encrypt(value, "MD5");
   }
   
   public static final String encryptSHA1(String value) {
      return encrypt(value, "SHA-1");
   }
   
}
