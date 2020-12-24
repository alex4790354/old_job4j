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

    private String getContentFromParam(int paramValue) throws IOException {
        File currentFile = getFile();
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (data < paramValue) {
                output += (char) data;
            }
        }
        return output;
    }

    public String getContent() throws IOException {
        return getContentFromParam(Integer.MAX_VALUE);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContentFromParam(0x80);
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file, true);  // to append content, not overwrite it
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
