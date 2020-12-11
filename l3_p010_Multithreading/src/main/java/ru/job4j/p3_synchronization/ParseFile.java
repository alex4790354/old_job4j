package ru.job4j.p3_synchronization;

import java.io.*;

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder outputSB = new StringBuilder("");
        int data;
        while ((data = i.read()) > 0) {
            outputSB.append((char) data);
        }
        return outputSB.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder outputSB = new StringBuilder("");
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                outputSB.append((char) data);
            }
        }
        return outputSB.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file, true);  // to append content, not overwrite it
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }


}
