package com.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
// arrange property/element order of xml element, this is Optional
@XmlType(propOrder = { "author", "name" })
public class Book {

    private String name;
    private String author;
    private String reviewer;
    private String id;

    // If you like the variable name, e.g. "name", you can easily change this
    // name for your XML-Output:
    @XmlElement(name = "bookName")
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlAttribute
    public String getReviewer() {
        return reviewer;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setId(String id) {
        this.id = id;
    }

}
