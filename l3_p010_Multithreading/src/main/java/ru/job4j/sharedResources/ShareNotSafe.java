package ru.job4j.sharedResources;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public ConcurrentHashMap<Integer, User> findAll() {
        ConcurrentHashMap<Integer, User> result = new ConcurrentHashMap<>();
        result.putAll(users);
        return result;
    }

    public int getSize() {
        return users.size();
    }

}


class User {
    private int id;
    private String name;

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


public class ShareNotSafe {

    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);

        ConcurrentHashMap<Integer, User> usersCloned = cache.findAll();

        /*Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join(); */

        User user2 = User.of("name");
        cache.add(user2);

        System.out.println("users size: " + cache.getSize() + ", usersCloned size: " + usersCloned.size());

    }

}
