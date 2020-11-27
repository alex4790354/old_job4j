package ru.job4j.sharedResources;

public class Cache {

    private static Cache cache;

    private static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        // Reading and assigning a value occurs in 3 steps (reading, initialization, assignment).
        // So this is not an atomic value .
        return cache;
    }

}
