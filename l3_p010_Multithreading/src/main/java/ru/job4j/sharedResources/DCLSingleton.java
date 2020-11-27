package ru.job4j.sharedResources;

public class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            // Может возникнуть ситуация, что после сравнения (inst == null) переменная inst будет уже инициализирована
            // Произойдет повторная инициализация и переменной inst будет вторично присвоено новое значение. Что может привести к ошибке.

            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
