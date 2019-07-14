package com.example.demo;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private UsersRepository repository;

    @Override
    public List<Users> findAll() {

        List<Users> allUsers = (List<Users>) repository.findAll();

        return allUsers;
    }
}
