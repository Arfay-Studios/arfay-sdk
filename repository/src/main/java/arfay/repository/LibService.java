package arfay.repository;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a service class for libraries.
 * <p>
 * Used to store all utilized libraries.
 */
public final class LibService {
   
   /**
    * All stored libraries.
    * <p>
    * Mapped by file name as key, and file as value.
    */
   public final static List<File> LIBRARIES = new ArrayList<>();
   
   /**
    * The folder used to store all libraries.
    * <p>
    * By default, is not mutable for safe reasons.
    */
   public final static File FOLDER;
   
   /**
    * The class loader used to load all libraries.
    */
   private final static URLClassLoader CLASS_LOADER;
   
   /**
    * The method of the class {@link URLClassLoader} to load libraries in the system.
    */
   private final static Method ADD_URL;
   
   /**
    * Loads the given url as library.
    */
   @SneakyThrows
   public static void load(URL url) {
      ADD_URL.invoke(CLASS_LOADER, url);
   }
   
   /**
    * Loads the given file as library.
    */
   @SneakyThrows
   public static void load(File file) {
      if (LIBRARIES.contains(file))
         return;
      
      LIBRARIES.add(file);
      load(file.toURI().toURL());
   }
   
   /**
    * Makes the download of the given dependency based on the given repository
    * and loads them.
    */
   public static void load(Dependency dependency, Repository repository) {
      File downloaded = dependency.download(repository, FOLDER);
      load(downloaded);
   }
   
   /**
    * Makes the download of the given dependency based on the given repository
    * and loads them.
    */
   public static void load(String dependency, Repository repository) {
      load(Dependency.of(dependency), repository);
   }
   
   /**
    * Makes the download of the given dependency based on the given repository
    * and loads them.
    */
   public static void load(String dependency, String repository) {
      load(Dependency.of(dependency), new Repository(repository));
   }
   
   /**
    * Makes the download of the given dependency based on the maven central
    * repository and loads them.
    */
   public static void loadFromMaven(Dependency dependency) {
      File downloaded = dependency.downloadFromMaven(FOLDER);
      load(downloaded);
   }
   
   /**
    * Makes the download of the given dependency based on the maven central
    * repository and loads them.
    */
   public static void loadFromMaven(String dependency) {
      loadFromMaven(Dependency.of(dependency));
   }
   
   /**
    * Loads all jar libraries at the given folder.
    */
   public static void loadAll(File folder) {
      if (!folder.isDirectory())
         return;
      
      for (File file : Objects.requireNonNull(folder.listFiles())) {
         if (file.getName().endsWith(".jar")) {
            load(file);
         }
      }
   }
   
   /**
    * Loads all jar libraries at the default folder.
    */
   public static void loadAll() {
      loadAll(FOLDER);
   }
   
   /**
    * Converts the given file as library.
    * <p>
    * This loads the file as library and implements a copy of them in the default folder.
    */
   @SneakyThrows
   public static void convertAsLib(File file) {
      File result = new File(FOLDER, file.getName());
      if (result.exists())
         return;
      
      result.createNewFile();
      Streams.copy(new FileInputStream(file), new FileOutputStream(result));
      load(result);
   }
   
   /**
    * Loads all dependencies of Arfay SDK.
    */
   public static void loadSDK() {
      // kotlin
      loadFromMaven("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2");
      
      // database
      loadFromMaven("org.jetbrains.exposed:exposed-core:0.37.3");
      loadFromMaven("org.jetbrains.exposed:exposed-dao:0.37.3");
      loadFromMaven("org.jetbrains.exposed:exposed-jdbc:0.37.3");
      loadFromMaven("com.zaxxer:HikariCP:5.0.1");
      loadFromMaven("org.xerial:sqlite-jdbc:3.36.0.1");
      
      // serialization
      loadFromMaven("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3");
      loadFromMaven("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3");
      loadFromMaven("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3");
      loadFromMaven("com.charleskorn.kaml:kaml:0.46.0");
      loadFromMaven("net.benwoodworth.knbt:knbt:0.11.1");
      
      // korlibs
      loadFromMaven("com.soywiz.korlibs.korma:korma-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.korim:korim-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.klock:klock-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.krypto:krypto-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.kmem:kmem-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.kds:kds-jvm:3.0.0-Beta6");
      loadFromMaven("com.soywiz.korlibs.korio:korio-jvm:3.0.0-Beta6");
      
      // others
      loadFromMaven("com.github.ben-manes.caffeine:caffeine:3.0.6");
      loadFromMaven("it.unimi.dsi:fastutil:8.5.8");
      loadFromMaven("net.jafama:jafama:2.3.2");
   }
   
   static {
      FOLDER = new File("libraries");
      FOLDER.mkdirs();
      
      CLASS_LOADER = (URLClassLoader) ClassLoader.getSystemClassLoader();
      
      try {
         Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
         method.setAccessible(true);
         ADD_URL = method;
      } catch (NoSuchMethodException e) {
         throw new RuntimeException(e);
      }
   }
}
