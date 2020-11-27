package ru.job4j.sharedResources;

public class Cache2 {
    private static Cache2 cache;

    public static synchronized Cache2 instOf() {
        if (cache == null) {
            cache = new Cache2();
        }
        return cache;
    }
}
