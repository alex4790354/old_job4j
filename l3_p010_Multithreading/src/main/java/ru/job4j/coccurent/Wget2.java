package ru.job4j.coccurent;

import java.io.*;
import java.time.Instant;

public class Wget2 implements Runnable {

    private final String fileNameIn;
    private final int speedLimit;
    //private String fileNameOut = "D://temp/pom_new.xml";

    public Wget2(String url, int speed) {
        this.fileNameIn = url;
        this.speedLimit = speed;
    }

    @Override
    public void run() {
        long timeStart = Instant.now().toEpochMilli();
        long timeCurrent = Instant.now().toEpochMilli();
        try {
            FileInputStream fileInStream = new FileInputStream(fileNameIn);
            //FileOutputStream fileOutStream = new FileOutputStream(fileNameOut);
            int oneByte;
            int bytesCount = 0;
            long waitTime;
            while (fileInStream.available() > 0) {
                bytesCount++;
                timeCurrent = Instant.now().toEpochMilli() + 1;
                if (bytesCount * 1024  > (timeCurrent - timeStart) * speedLimit) {
                    waitTime = bytesCount * 1024 / speedLimit - (timeCurrent - timeStart);
                    System.out.println("Wait " + waitTime + " mls");
                    Thread.sleep(waitTime);
                }
                oneByte = fileInStream.read();
                //fileOutStream.write(oneByte);
            }
            fileInStream.close();
            //fileOutStream.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Strart time: " + Instant.now().toEpochMilli());
        Thread wget;
        if (args.length > 1) {
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            wget = new Thread(new Wget2(url, speed));
            wget.start();
            wget.join();
        }
        System.out.println("End time: " + Instant.now().toEpochMilli());
    }

}
