package ru.job4j.wiley2lvl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;


/**
 * @author   A_Vasiliev  9720560@gmail.com
 * @since    25.03.2018
 * @version  1.0.0
 */

public class TLCTime<T> implements Serializable, Cashe<T> {
   private int nextFileID;
   private final ConcurrentHashMap<Integer, SoftReference<T>> data = new ConcurrentHashMap<>();
   private final CopyOnWriteArrayList<FLCashe> flData = new CopyOnWriteArrayList<FLCashe>();
   private final double secLMaxSize;  // in MBt
   private double secLCurSize;
   private final double fstLMaxSize;  // in MBt
   private double fstLCurSize;
   private final String dir = "./cashe/";
   private static final Logger LOG = LoggerFactory.getLogger(TLCTime.class);


   public TLCTime(double secLMaxSize, double fstLMaxSize) throws IOException, ClassNotFoundException {
       String keyName = null;
       this.secLCurSize = 0;
       this.nextFileID = 1;
       this.secLMaxSize = secLMaxSize;
       this.secLCurSize = 0;
       this.fstLMaxSize = fstLMaxSize;
       this.fstLCurSize = 0;
       double fileSize;
       int sCasheID;
       SoftReference<T> softObj;
       Path path = Paths.get(dir);
       if (!Files.exists(path)) {
          Files.createDirectory(path);
       }
       try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
           File nFile = null;
           for (Path file: stream) {
               keyName = String.format("%s%s",  this.dir, file.getFileName().toString());
               nFile = new File(keyName);
               fileSize = nFile.length();
               if (this.secLMaxSize > this.secLCurSize + fileSize / 1024 / 1024) {
                  this.secLCurSize = this.secLCurSize + fileSize / 1024 / 1024;
                  sCasheID = this.getFileID(file.getFileName().toString());
                  if (sCasheID >= nextFileID) {
                     nextFileID = sCasheID + 1;
                  }
                  if (this.fstLMaxSize > this.fstLCurSize + fileSize / 1024 / 1024) {
                     this.fstLCurSize = this.fstLCurSize + fileSize / 1024 / 1024;
                     flData.add(new FLCashe(sCasheID, fileSize, System.currentTimeMillis()));
                     softObj = new SoftReference<T>(readFromFile(keyName));
                     data.put(sCasheID, softObj);
                  } else {
                     data.put(sCasheID, null);     //  ??? ???
                  }
               }
           }
       } catch (IOException | DirectoryIteratorException e) {
          LOG.error(e.getMessage());
       }
   }


   @Override
   public SoftReference<T> get(int casheID) throws IOException, ClassNotFoundException {
      SoftReference<T> softObj = data.get(casheID);
      String fName = String.format("%s%s.bin", this.dir, casheID);
      if (softObj == null) {
         softObj = new SoftReference<T>(readFromFile(fName));
         data.put(casheID, softObj);
      }
      File slFile = new File(fName);
      double fileSize = slFile.length();
      remFLCashe(casheID);
      flData.add(new FLCashe(casheID, fileSize, System.currentTimeMillis()));
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
      int sCasheID;
      String name;
      name = fName.substring(0, fName.length() - 4);
      try {
         sCasheID = Integer.parseInt(name);
      } catch (NumberFormatException e) {
         LOG.error(e.getMessage());
         return 0;
      }
      return sCasheID;
   }


   @Override
   public boolean addObj(T obj) throws IOException {
      boolean result = false;
      FileOutputStream flOut;
      ObjectOutputStream oos = null;
      int casheID;
      SoftReference<T> softObj;
      try {
         double objSize = getObjectSize(obj);
         if (this.secLMaxSize > this.secLCurSize + objSize / 1024 / 1024) {
            casheID = nextFileID++;
            String newfName = String.format("%s%s.bin", this.dir, casheID);
            this.secLCurSize = this.secLCurSize + objSize / 1024 / 1024;
            flOut = new FileOutputStream(newfName);
            oos = new ObjectOutputStream(flOut);
            oos.writeObject(obj);
            softObj = new SoftReference<T>(obj);
            if (this.fstLMaxSize < this.fstLCurSize + objSize / 1024 / 1024) {
               Collections.sort(this.flData);
               while ((this.fstLMaxSize < this.fstLCurSize + objSize / 1024 / 1024)
                    && this.fstLMaxSize > 0) {
                  Collections.sort(this.flData);
                  FLCashe flCashe = flData.get(0);
                  this.fstLCurSize = this.fstLCurSize - flCashe.getSize();
                  remFLCashe(flCashe.getId());
                  flData.remove(0);
               }
            }
            if (this.fstLMaxSize > this.fstLCurSize + objSize / 1024 / 1024) {
               this.fstLCurSize = this.fstLCurSize + objSize / 1024 / 1024;
               softObj = new SoftReference<T>(obj);
               flData.add(new FLCashe(casheID, objSize, System.currentTimeMillis()));
               data.put(casheID, softObj);
            } else {
               System.out.println("Превышен размер памяти 1-го уровня");
            }
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
      T slCashe;
      boolean result = false;
      double fileSize;
      String fileName = null;
      Path path = Paths.get(this.dir);
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
         File slFile = null;
         for (Path file : stream) {
            fileName = String.format("%s%s", this.dir, file.getFileName().toString());
            slFile = new File(fileName);
            fileSize = slFile.length();
            slCashe = readFromFile(fileName);
            if (slCashe.equals(remObj)) {
               this.secLCurSize = this.secLCurSize - fileSize / 1024 / 1024;
               data.remove(getFileID(file.getFileName().toString()));
               Path remPath = Paths.get(fileName);
               Files.delete(remPath);
               this.fstLCurSize = this.fstLCurSize - fileSize / 1024 / 1024;
               remFLCashe(getFileID(fileName));
               result = true;
               break;
            }
         }
      } catch (IOException | DirectoryIteratorException e) {
         LOG.error(e.getMessage());
      }
      return result;
   }


   private void remFLCashe(int id) {
      Collections.sort(this.flData);
      int indx = 0;
      FLCashe flCashe;
      Iterator it = this.flData.iterator();

      while (it.hasNext()) {
         flCashe = (FLCashe) it.next();
         if (flCashe.getId() == id) {
            this.fstLCurSize = this.fstLCurSize - flCashe.getSize();
            this.flData.remove(indx);
            break;
         }
         indx++;
      }
   }


   @Override
   public int getDataSize() {
      return this.data.size();
   }


}

