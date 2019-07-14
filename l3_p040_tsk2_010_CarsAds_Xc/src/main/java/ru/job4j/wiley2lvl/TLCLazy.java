package ru.job4j.wiley2lvl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

/**
 * @author   A_Vasiliev  9720560@gmail.com
 * @since    25.03.2018
 * @version  1.0.0
 */

public class TLCLazy<T> implements Serializable, Cashe<T> {
   private int nextFileID;
   private final ConcurrentHashMap<Integer, SoftReference<T>> data = new ConcurrentHashMap<>();
   private final double secLMaxSize;  // in MBt
   private double secLCurSize;
   private final String dir = "./cashe/";
   private static final Logger LOG = LoggerFactory.getLogger(TLCLazy.class);


   public TLCLazy(double secLMaxSize) throws IOException, ClassNotFoundException {
       String keyName = null;
       this.secLCurSize = 0;
       this.nextFileID = 1;
       this.secLMaxSize = secLMaxSize;
       this.secLCurSize = 0;
       double fileSize;
       int sCasheID;
       SoftReference<T> softObj;
       Path path = Paths.get(dir);
       if (!Files.exists(path)) {
          Files.createDirectory(path);
       }
       try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
           File nFile = null;
           for (Path file : stream) {
               keyName = String.format("%s%s",  this.dir, file.getFileName().toString());
               nFile = new File(keyName);
               fileSize = nFile.length();
               if (this.secLMaxSize > this.secLCurSize + fileSize / 1024 / 1024) {
                  this.secLCurSize = this.secLCurSize + fileSize / 1024 / 1024;
                  sCasheID = getFileID(file.getFileName().toString());
                  if (sCasheID >= nextFileID) {
                     nextFileID = sCasheID + 1;
                  }
                  softObj = new SoftReference<T>(readFromFile(keyName));
                  data.put(sCasheID, softObj);
               }
           }
       } catch (IOException | DirectoryIteratorException e) {
          LOG.error(e.getMessage());
       }
   }


   @Override
   public SoftReference<T> get(int casheID) throws IOException, ClassNotFoundException {
      String fName = String.format("%s%s.bin", this.dir, casheID);
      SoftReference<T> softObj = data.get(casheID);
      if (softObj == null) {
         softObj = new SoftReference<T>(readFromFile(fName));
         data.put(casheID, softObj);
      }
      return softObj;
   }


   private T readFromFile(String fName) throws IOException, ClassNotFoundException {
      ObjectInputStream ois = null;
      Object obj = null;
      try {
         File file = new File(fName);
         FileInputStream fis = new FileInputStream(fName);
         ois = new ObjectInputStream(fis);
         obj = ois.readObject();
      } finally {
         if (ois != null) {
            ois.close();
         }
      }
      return (T) obj;
   }


   private int getFileID(String fName) {
      int sCasheID = 0;
      try {
         sCasheID = Integer.parseInt(fName.substring(0, fName.length() - 4));
      } catch (NumberFormatException e) {
         LOG.error(e.getMessage());
         return sCasheID;
      }
      return sCasheID;
   }


   @Override
   public boolean addObj(T obj) throws IOException {
      FileOutputStream flOut;
      ObjectOutputStream oos = null;
      int casheID;
      SoftReference<T> softObj;
      boolean result = false;
      try {
         double objSize = getObjectSize(obj);
         if (this.secLMaxSize > this.secLCurSize + objSize / 1024 / 1024) {
            this.secLCurSize = this.secLCurSize + objSize / 1024 / 1024;
            casheID = nextFileID++;
            String newfName = String.format("%s%s.bin", this.dir, casheID);
            flOut = new FileOutputStream(newfName);
            oos = new ObjectOutputStream(flOut);
            oos.writeObject(obj);
            softObj = new SoftReference<T>(obj);
            data.put(casheID, softObj);
            result = true;
         } else {
            System.out.println("Превышен допустимый размер памяти 2-го уровня");
         }
      } catch (Exception e) {
         LOG.error(e.getMessage());
      } finally {
         if (oos != null) {
            oos.close();
         }
      }
      return result;
   }


   @Override
   public boolean remObj(T remObj) throws IOException, ClassNotFoundException {
      boolean result = false;
      String fileName = null;
      double fileSize;
      T slCashe;

      Path path = Paths.get(this.dir);
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
         File slFile = null;
         for (Path file : stream) {
            fileName = String.format("%s%s", this.dir, file.getFileName().toString());
            slFile = new File(fileName);
            fileSize = slFile.length();
            slCashe = readFromFile(fileName);
            if (slCashe.equals(remObj)) {
               data.remove(getFileID(file.getFileName().toString()));
               this.secLCurSize = this.secLCurSize - fileSize / 1024 / 1024;
               Path remPath = Paths.get(fileName);
               Files.delete(remPath);
               result = true;
               break;
            }
         }
      } catch (IOException | DirectoryIteratorException e) {
         LOG.error(e.getMessage());
      }
      return result;
   }

   @Override
   public int getDataSize() {
      return this.data.size();
   }


}

