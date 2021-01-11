package ru.job4j.p3_synchronization;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;



@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> allUsers = new HashMap<>();

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
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (this.allUsers.containsKey(user.getId())) {
            this.allUsers.remove(user.getId());
            return true;
        }
        return false;
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

