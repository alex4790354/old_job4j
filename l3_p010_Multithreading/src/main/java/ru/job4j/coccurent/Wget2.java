package ru.job4j.coccurent;

import java.io.*;
import java.time.Instant;

public class Wget2 implements Runnable {

    private String fileNameIn = "D://temp/pom.xml"; // "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
    private String fileNameOut = "D://temp/pom_new.xml";
    private int speedLimit = 100;

    public Wget2(String url, int speed) {
        this.fileNameIn = url;
        this.speedLimit = speed;
    }

    public Wget2() {
    }

    @Override
    public void run() {
        long timeStart = Instant.now().toEpochMilli();
        long timeCurrent = Instant.now().toEpochMilli();

        try {
            FileInputStream fileInStream = new FileInputStream(fileNameIn);
            FileOutputStream fileOutStream = new FileOutputStream(fileNameOut);
            int oneByte;
            int bytesCount = 0;
            long waitTime;
            while (fileInStream.available() > 0) {
                bytesCount++;
                timeCurrent = Instant.now().toEpochMilli() + 1;
                if (timeCurrent > timeStart && bytesCount * 1000 / (timeCurrent - timeStart) > speedLimit) {
                    waitTime = timeCurrent - timeStart + bytesCount * 1000 / speedLimit;
                    System.out.println(waitTime);
                    Thread.sleep(waitTime);
                }
                oneByte = fileInStream.read();
                fileOutStream.write(oneByte);
            }
            fileInStream.close();
            fileOutStream.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        /*try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedInputStream in = new BufferedInputStream(fileInputStream);

            FileOutputStream fileOutputStream = new FileOutputStream("D://temp/pom_tmp.xml");
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Strart time: " + Instant.now().toEpochMilli());
        Thread wget;
        if (args.length > 1) {
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            wget = new Thread(new Wget2(url, speed));
        } else {
            wget = new Thread(new Wget2());
        }
        wget.start();
        wget.join();
        System.out.println("End time: " + Instant.now().toEpochMilli());
    }

}
