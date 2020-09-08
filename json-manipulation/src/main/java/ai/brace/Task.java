package ai.brace;

/*
* There are multiple tasks, and all the tasks do or print something, so I split each class by task and made an interface to
* make sure each task does work. Also made a default printHeader to more easily separate test cases
* */
public interface Task {
    void doWork();
    default void printHeader() {
        System.out.println("============================================");
        System.out.print(getClass().getSimpleName());
        System.out.println(":\n");
    }
}
