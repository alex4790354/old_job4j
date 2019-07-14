package ru.job4j.tsk3_example.intf;

import ru.job4j.tsk3_example.entities.ContactEntity;

import java.util.List;

public interface ContactService  {

   List<ContactEntity> findAll();
   List<ContactEntity> findByFirstName(String firstName);
   List<ContactEntity> findByFirstNameAndLastName(String firstName, String lastName);
}