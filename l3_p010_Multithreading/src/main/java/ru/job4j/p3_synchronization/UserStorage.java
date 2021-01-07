package ru.job4j.p3_synchronization;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> allUsers;

    public UserStorage() {
        this.allUsers = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        if (!this.allUsers.containsKey(user.getId())) {
            this.allUsers.put(user.getId(), user);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean update(User user) {
        if (this.allUsers.containsKey(user.getId())) {
            this.allUsers.put(user.getId(), user);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean delete(User user) {
        if (this.allUsers.containsKey(user.getId())) {
            this.allUsers.remove(user.getId());
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean transfer(int idA, int idB, int ammount) {
        boolean result = false;
        if (this.allUsers.containsKey(idA) && this.allUsers.containsKey(idB)) {
            User userA = this.allUsers.get(idA);
            User userB = this.allUsers.get(idB);
            if (userA.getAmmount() > ammount) {
                userA.setAmmount(userA.getAmmount() - ammount);
                userB.setAmmount(userB.getAmmount() + ammount);
                allUsers.put(userA.getId(), userA);
                // not sure what to do, if first update will work, but second not: will crash
                // (lets say becouse of the problems with JVM or electricity )
                allUsers.put(userB.getId(), userB);
                result = true;
            }
        }
        return result;
    }


}

class User {
    private int id;
    private int ammount;

    public User(int id, int ammount) {
        this.id = id;
        this.ammount = ammount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        User userO = (User) o;
        return this.id == userO.getId();
    }
}
