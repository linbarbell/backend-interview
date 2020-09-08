package ai.brace;

import java.util.Map;
import java.util.TreeMap;

public class Task2 implements Task {
    private final Data a1;
    private final Data a2;

    public Task2(Data a1, Data a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Override
    public void doWork() {
        printHeader();
        Map<Integer, String> map = new TreeMap<>(); // same as task 1, but also add a2 to the map
        a1.textArray.forEach(text -> map.put(text.id, text.textdata));
        a2.textArray.forEach(text -> map.put(text.id, text.textdata));
        for (int id : map.keySet()) {
            System.out.println(map.get(id));
        }
    }
}
