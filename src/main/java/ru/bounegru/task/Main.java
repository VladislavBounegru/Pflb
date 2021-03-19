package ru.bounegru.task;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            new FirstTask().resolve();
        } else {
            new SecondTask().resolve(args[0]);
        }
    }


}
