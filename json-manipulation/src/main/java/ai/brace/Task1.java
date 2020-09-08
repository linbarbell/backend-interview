package ai.brace;

import java.util.Map;
import java.util.TreeMap;

public class Task1 implements Task {
    private final Data a1;

    public Task1(Data a1) {
        this.a1 = a1;
    }

    @Override
    public void doWork() {
        printHeader();

        // store id as key and textdata as value. tree map conveniently sorts by key
        Map<Integer, String> map = new TreeMap<>();
        a1.textArray.forEach(text -> map.put(text.id, text.textdata));
        for (int id : map.keySet()) {
            System.out.println(map.get(id));
        }
    }
}
