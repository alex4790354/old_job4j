package ru.job4j.p3_synchronization;

public class User {

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
