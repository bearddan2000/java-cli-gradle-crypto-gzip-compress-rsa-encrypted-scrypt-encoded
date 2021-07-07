package example;

import org.bouncycastle.crypto.generators.SCrypt;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

public class Encode {
  private static final String SALT = "abc123";

  // DifficultyFactor
  // These should be powers of 2
  private static final int cpu = 8;
  private static final int memory = 8;
  private static final int parallelism = 8;
  private static final int outputLength = 32;

  private static byte[] compress(String hash) throws IOException {
    return GZIPCompression.compress(hash);
  }

  private static byte[] encrypt(Encryption rsa, String hash) throws Exception {

    byte[] cipherText = rsa.do_RSAEncryption(hash);

    String newHash = DatatypeConverter.printHexBinary(cipherText);

    return compress(newHash);
  }

  public static String hashpw(Encryption rsa, String pass){
    byte[] hash = SCrypt.generate(pass.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);

    try {

      String stored = new String(hash);

      byte[] newHash = encrypt(rsa, stored);

      return DatatypeConverter.printHexBinary(newHash);

    } catch (Exception e) {
      return null;
    }

  }

  public static boolean verify(Encryption rsa, String pass, String hash){

    try{

      pass = hashpw(rsa, pass);

      return pass.equals(hash);

    } catch (Exception e) {

      System.out.println("Encode verify error");

      e.printStackTrace();

      return false;
    }
  }
}
