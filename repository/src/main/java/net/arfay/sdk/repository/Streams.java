package net.arfay.sdk.repository;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class for copying a stream to another.
 */
public final class Streams {
   
   /**
    * Copies the given input to output.
    */
   @SneakyThrows
   public static void copy(InputStream input, OutputStream output) {
      byte[] buffer = new byte[8192];
      try {
         try {
            
            int bytes = input.read(buffer);
            while (bytes > 0) {
               output.write(buffer, 0, bytes);
               bytes = input.read(buffer);
            }
            
         } catch (Exception exception) {
            exception.printStackTrace();
         } finally {
            input.close();
            output.close();
         }
         
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }
}
