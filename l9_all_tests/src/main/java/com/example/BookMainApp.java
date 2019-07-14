package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * This example shows , how to map nested java object in jaxb 2.0
 *
 * @author Ranjeet.Jha
 *
 */
public class BookMainApp {

    // create a doc folder if not available in side the project
    private static final String BOOKSTORE_XML = "D:/Java_projects/Dropbox/curses/Java/job4j/avasiliev3/l9_all_tests/src/main/java/com/example/allItems1.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        // create book object and populate some values
        Book book1 = new Book();
        book1.setId("123");
        book1.setName("Real-time Analytics with Storm and Cassandra");
        book1.setAuthor("Ranjeet");
        book1.setReviewer("ranjeet kumar jha");
        book1.setId("123");

        // create book object and populate some values
        Book book2 = new Book();
        book2.setId("124");
        book2.setName("Apache Cassandra Essential");
        book2.setAuthor("Ranjeet");
        book2.setReviewer("ranjeet Jha");

        // create bookstore, assigning book
        Bookstore bookstore = new Bookstore();
        bookstore.setName("Book Mark");
        bookstore.setLocation("South Ex, Delhi");

        // add book1 and book2 into bookStore object
        bookstore.addBook(book1);
        bookstore.addBook(book2);

        // create JAXB context and instantiate marshaller
        // here bookStore class is container , which contains Book
        JAXBContext context = JAXBContext.newInstance(Bookstore.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookstore, System.out);

        // Write to File
        m.marshal(bookstore, new File(BOOKSTORE_XML));

        // read out xml file, and populate values in java object
        // here xml created before are going to read
        System.out.println();
        System.out.println("Output from XML File: ");
        Unmarshaller um = context.createUnmarshaller();
        Bookstore bookstore2 = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));
        ArrayList<Book> list = bookstore2.getBooksList();
        for (Book book : list) {
            System.out.println("id: " + book.getId() + " , reviewer " + book.getReviewer());
            System.out.print(" , Book name: " + book.getName() + " , author: " + book.getAuthor());
            System.out.print(" location: " + bookstore2.getLocation());
        }

    }

}
