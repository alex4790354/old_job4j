package ru.job4j.wiley;


import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class TwoLvlCache<T> implements Serializable  {

   private int nextFileID;
   private ConcurrentHashMap<String, SoftReference<T>> data = new ConcurrentHashMap<>();
   private String filesStorage = "./Storage.txt";      //  Этот функционал лишний. Нужно было обходится без этого файла.
   private double secLMaxSize;  // in MBt
   private double secLCurSize;
   public TwoLvlCache(double secLMaxSize) {
      String fileName = null;
      this.secLCurSize = 0;
      nextFileID = 1;
      this.secLMaxSize = secLMaxSize;
      this.secLCurSize = 0;
      double fileSize;
      int singleCashID;
      SoftReference<T> softObj;
      try (Scanner sc = new Scanner(new File(filesStorage))) {
         while (sc.hasNextLine()) {
            // Во время иницилизации я загружаю лишь имена файлов, а вайлы - "по требованию", что бы не загружать кеш.
            fileName = sc.nextLine();
            File file = new File(fileName);
            if (file.exists()) {
               fileSize = file.length();
               if (this.secLMaxSize > this.secLCurSize + fileSize / 1024 / 1024) {
                  this.secLCurSize = this.secLCurSize + fileSize / 1024 / 1024;
                  singleCashID = this.getFileID(fileName);
                  if (singleCashID >= nextFileID) {
                     nextFileID = singleCashID + 1;
                  }

                  softObj = new SoftReference<T>(readFromFile(fileName));
                  data.put(fileName, softObj);
               }
            } else {
               removeFile(fileName);
               System.out.println(String.format("File %s was removed from Storage.", fileName));
            }
         }
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   public SoftReference<T> get(String fileName) throws IOException, ClassNotFoundException {
      //MyUser obj = data.get(fileName);
      SoftReference<T> softObj = data.get(fileName);
      //Object file = data.get(fileName);
      if (softObj == null) {
         softObj = new SoftReference<T>(readFromFile(fileName));
         data.put(fileName, softObj);
      }
      return softObj;
   }

   private T readFromFile(String fileName) throws IOException, ClassNotFoundException {
      Object obj = null;
      ObjectInputStream ois = null;
      int singleCashID;
      try {
         File file = new File(fileName);
         if (file.exists()) {
            FileInputStream fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            obj = ois.readObject();
         } else {
            removeFile(fileName);
            System.out.println(String.format("File %s was removed from Storage.", fileName));
         }
      } finally {
         if (ois != null) {
            ois.close();
         }
      }
      return (T) obj;
   }


   public void removeFile(String fileName) {
      boolean result = false;
      StringBuffer stringBuffer = new StringBuffer();
      String currentLine = null;
      String ls = System.lineSeparator();
      double fileSize = 0;
      try (BufferedReader buffer = new BufferedReader(new FileReader(new File(this.filesStorage)))) {
         while ((currentLine = buffer.readLine()) != null) {
            if (!currentLine.equals(fileName)) {
               stringBuffer.append(currentLine).append(ls);
            } else {
               File file = new File(fileName);
               if (file.exists()) {
                  this.secLCurSize = this.secLCurSize - fileSize / 1024 / 1024;
               }
            }
         }
         try (FileWriter writer = new FileWriter(this.filesStorage, false)) {
            writer.write(stringBuffer.toString());
            writer.close();
            result = true;
         } catch (IOException e) {
            e.printStackTrace();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      data.remove(fileName);
   }


   public boolean addObj(T obj) throws IOException {
      boolean result = false;
      ObjectOutputStream oos = null;
      FileOutputStream fout = null;
      int curFileID;
      SoftReference<T> softObj;

      try {
         double objSize = getObjectSize(obj);
         if (this.secLMaxSize > this.secLCurSize + objSize / 1024 / 1024) {

            this.secLCurSize = this.secLCurSize + objSize / 1024 / 1024;
            curFileID = nextFileID++;
            String newfName = String.format("./%s.bin", curFileID);
            fout = new FileOutputStream(newfName);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(obj);
            String s = String.format("%s%s", newfName, System.lineSeparator());
            Path path = Paths.get(this.filesStorage);
            try {
               Files.write(path, s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
               System.err.println(e);
            }
            softObj = new SoftReference<T>(obj);
            data.put(newfName, softObj);
            result = true;
         } else {
            System.out.println("Превышен допустимый размер памяти 2-го уровня");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         if (oos != null) {
            oos.close();
         }
      }
      return result;
   }

   private int getFileID(String fileName) {
      int singleCashID;
      String name;
      name = fileName.substring(0, fileName.length() - 4).substring(2);
      try {
         singleCashID = Integer.parseInt(name);
      } catch (NumberFormatException e) {
         return 0;
      }
      return singleCashID;
   }

   public int getDataSize() {
      return this.data.size();
   }

   public static void main(String[] args) throws IOException {
      TwoLvlCache myCache = new TwoLvlCache(64000);
      int hsBefore = myCache.getDataSize();
      myCache.addObj(new MyUser(1,  1));
      myCache.addObj(new MyUser(12, 12));
      myCache.addObj(new MyUser(13, 13));
      myCache.addObj(new MyUser(14, 14));
      myCache.addObj(new MyUser(15, 15));
   }

}
