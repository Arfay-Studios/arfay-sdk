package net.arfay.sdk.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a data class used as repository model.
 */
@AllArgsConstructor
@Data
public class Repository {
   
   /**
    * The maven central repository.
    */
   public final static Repository MAVEN_CENTRAL = new Repository("https://repo.maven.apache.org/maven2/");
   
   /**
    * The url of this repository.
    */
   private String url;
   
   @Override
   public String toString() {
      return url;
   }
}
