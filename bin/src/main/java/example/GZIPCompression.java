package example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompression {
  public static byte[] compress(final String str) throws IOException {
    if ((str == null) || (str.length() == 0)) {
      return null;
    }
    ByteArrayOutputStream obj = new ByteArrayOutputStream();
    GZIPOutputStream gzip = new GZIPOutputStream(obj);
    gzip.write(str.getBytes("UTF-8"));
    gzip.flush();
    gzip.close();
    byte[] result = obj.toByteArray();

    System.out.println(String.format("The Encrypted Compressed Text length is: %d", result.length));

    return result;
  }
}
