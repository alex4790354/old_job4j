package ru.job4j.tsk3_example.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.tsk3_example.entities.ContactEntity;

import java.util.List;

public interface ContactRepository extends CrudRepository<ContactEntity, Integer>{

   List<ContactEntity> findByFirstName(String firstName);
   List<ContactEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
