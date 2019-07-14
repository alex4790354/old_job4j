package com.example2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class LogMainApp {

    // create a doc folder if not available in side the project
    private static final String LOG_XML = "D:/Java_projects/Dropbox/curses/Java/job4j/avasiliev3/l9_all_tests/src/main/java/com/example2/log.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<log>\n" +
                        "    <entry id=\"1\">\n" +
                        "        <message>Application started</message>\n" +
                        "    </entry>\n" +
                        "    <entry id=\"2\">\n" +
                        "        <message>Application ended</message>\n" +
                        "    </entry>\n" +
                        "</log>";

        // read out xml file, and populate values in java object
        // here xml created before are going to read
        JAXBContext context = JAXBContext.newInstance(AllLog.class);
        System.out.println("Output from XML File: ");
//        Unmarshaller um = context.createUnmarshaller();
//        AllLog allLog = (AllLog) um.unmarshal(new FileReader(LOG_XML));
//        ArrayList<Entry> list = allLog.getLogsList();
        Unmarshaller um = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        AllLog allLog = (AllLog) um.unmarshal(reader);
        ArrayList<Entry> list = allLog.getLogsList();

        for (Entry entry : list) {
            System.out.println("id: " + entry.getId() + ", message: " + entry.getName());

        }

    }

}
