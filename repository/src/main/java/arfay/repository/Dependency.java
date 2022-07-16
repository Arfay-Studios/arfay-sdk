package arfay.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Represents a data class used as dependency model.
 */
@AllArgsConstructor
@Data
public class Dependency {
   
   /**
    * The group id of this dependency.
    */
   private String group;
   
   /**
    * The artifact of this dependency.
    */
   private String artifact;
   
   /**
    * The version of this dependency.
    */
   private String version;
   
   /**
    * Creates a new dependency based on given string data.
    */
   public static Dependency of(String dependency) {
      String[] parts = dependency.split(":", 3);
      return new Dependency(parts[0], parts[1], parts[2]);
   }
   
   /**
    * Gets a normalized version of the group of this dependency.
    */
   public String getLinkGroup() {
      return group.replace('.', '/');
   }
   
   /**
    * Gets a normalized version of the artifact of this dependency.
    */
   public String getLinkArtifact() {
      return artifact.replace('.', '/');
   }
   
   /**
    * Returns the link of this dependency based on the given repository.
    */
   public String getLink(Repository repository) {
      String url = repository.getUrl();
      String artifact = getLinkArtifact();
      String group = getLinkGroup();
      return url + group + '/' + artifact + '/' + version + '/' + artifact + '-' + version;
   }
   
   /**
    * Finds the URL location of this dependency on the given repository.
    */
   @SneakyThrows
   public URL findURL(Repository repository) {
      return new URL(getLink(repository));
   }
   
   /**
    * Finds the URL jar location of this dependency on the given repository.
    */
   @SneakyThrows
   public URL findJar(Repository repository) {
      return new URL(getLink(repository) + ".jar");
   }
   
   /**
    * Opens a jar InputStream based on URL jar location of this
    * dependency on the given repository.
    */
   @SneakyThrows
   public InputStream openJarStream(Repository repository) {
      return findJar(repository).openStream();
   }
   
   /**
    * Download this dependency based on the given repository inside the given folder.
    */
   @SneakyThrows
   public File download(Repository repository, File folder) {
      URL url = findJar(repository);
      File file = new File(folder, getFileName(url));
      
      if (file.exists()) {
         return file;
      } else {
         file.createNewFile();
      }
      
      Streams.copy(url.openStream(), new FileOutputStream(file));
      return file;
   }
   
   /**
    * Download this dependency based on maven central repository inside the given folder.
    */
   public File downloadFromMaven(File folder) {
      return download(Repository.MAVEN_CENTRAL, folder);
   }
   
   /**
    * Gets the file name based on the given url.
    */
   private String getFileName(URL url) {
      return url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
   }
   
   @Override
   public String toString() {
      return group + ":" + artifact + ":" + version;
   }
}
