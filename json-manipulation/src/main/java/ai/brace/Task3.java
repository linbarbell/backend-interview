package ai.brace;

import java.util.Map;
import java.util.TreeMap;

public class Task3 implements Task {
    private final Data a1;
    private final Data a2;

    public Task3(Data a1, Data a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Override
    public void doWork() {
        printHeader();
        Map<String, Integer> map = new TreeMap<>(); // store word as key and frequency as value
        a1.textArray.forEach(text -> addWords(map, text.textdata));
        a2.textArray.forEach(text -> addWords(map, text.textdata));
        StringBuilder sb = new StringBuilder(); // used string builder bc string manipulation with + is kind of slow
        for (String word : map.keySet()) {
            sb.append("(").append(word).append(')').append(" : ").append(map.get(word)).append('\n');
        }
        System.out.println(sb.toString());
    }


    private void addWords(Map<String, Integer> map, String textdata) {
        String[] words = textdata.split("\\s+"); // split by word
        for (String word : words) {
            String newWord = word.toLowerCase().replaceAll("[^a-zA-Z]",""); // make word lower case and replace nonalpha characters
            map.put(newWord, map.getOrDefault(newWord, 0) + 1);
        }
    }
}
