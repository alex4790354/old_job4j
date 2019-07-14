package com.mindgeek;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class LogParser {

    @XmlRootElement(name = "entry")
    public class Entry {

        private String id;
        private String name;

        @XmlElement(name = "message")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    @XmlRootElement(name = "log")
    public class AllLog {

        @XmlElement(name = "entry")
        private ArrayList<com.example2.Entry> entryList;

        public void setEntryList(ArrayList<com.example2.Entry> entryList) {
            this.entryList = entryList;
        }

        public ArrayList<com.example2.Entry> getLogsList() {
            return entryList;
        }

        public void addEntry(com.example2.Entry entry) {
            try {
                if (entryList == null) {
                    entryList = new ArrayList<com.example2.Entry>();
                }
                entryList.add(entry);

            } catch (Exception e) {
            }
        }
    }


    private static List<Integer> getIdsByMessage(String xml, String testString) throws Exception {
        List<Integer> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(com.example2.AllLog.class);
        System.out.println("Output from XML File: ");
        Unmarshaller um = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        com.example2.AllLog allLog = (com.example2.AllLog) um.unmarshal(reader);
        ArrayList<com.example2.Entry> list = allLog.getLogsList();

        for (com.example2.Entry entry : list) {
            if (entry.getName().equals(testString)) {
                result.add(Integer.parseInt(entry.getId()));
            }
            //System.out.println("id: " + entry.getId() + ", message: " + entry.getName());
        }
        return result;
    }


    public static void main(String[] args) throws Exception {

        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<log>\n" +
                        "    <entry id=\"1\">\n" +
                        "        <message>Application started</message>\n" +
                        "    </entry>\n" +
                        "    <entry id=\"2\">\n" +
                        "        <message>Application ended</message>\n" +
                        "    </entry>\n" +
                        "    <entry id=\"102\">\n" +
                        "        <message>Application ended</message>\n" +
                        "    </entry>\n" +
                        "</log>";

        List<Integer> ids = getIdsByMessage(xml, "Application ended");
        for(int id: ids)
            System.out.println(id);

    }



}
