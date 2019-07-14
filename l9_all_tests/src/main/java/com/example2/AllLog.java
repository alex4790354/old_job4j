package com.example2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "log")
public class AllLog {

    @XmlElement(name = "entry")
    private ArrayList<Entry> entryList;

    public void setEntryList(ArrayList<Entry> entryList) {
        this.entryList = entryList;
    }

    public ArrayList<Entry> getLogsList() {
        return entryList;
    }

    public void addEntry(Entry entry) {
        try {
            if (entryList == null) {
                entryList = new ArrayList<Entry>();
            }
            entryList.add(entry);

        } catch (Exception e) {
        }
    }
}
