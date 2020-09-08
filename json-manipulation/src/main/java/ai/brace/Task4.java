package ai.brace;

import com.google.gson.Gson;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Task4 implements Task {
    private final Data a1;
    private final Data a2;
    private final Gson gson;

    public Task4(Data a1, Data a2, Gson gson) {
        this.a1 = a1;
        this.a2 = a2;
        this.gson = gson;
    }

    @Override
    public void doWork() {
        printHeader();
        final Data output = merge(a1, a2);
        createFile(output, "output.json");
        System.out.println("done");
    }

    private void createFile(Data output, String fileName) {
        try {
            System.out.println(new File(fileName).getAbsoluteFile());
            Writer writer = Files.newBufferedWriter(Paths.get(fileName));
            gson.toJson(output, writer);
            writer.append('\n'); // expected output had new line
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Data merge(Data a1, Data a2) {
        Data newer, older; // determine if a1 or a2 is newer
        if (a1.lastModified.compareTo(a2.lastModified) > 0) {
            newer = a1;
            older = a2;
        } else {
            newer = a2;
            older = a1;
        }
        Data output = new Data();
        output.version = getValue(older.version, newer.version);
        output.uuid = UUID.randomUUID().toString();
        output.lastModified = LocalDateTime.now();
        output.title = getValue(older.title, newer.title);
        output.author = getValue(older.author, newer.author);
        output.translator = getValue(older.translator, newer.translator);
        output.releaseDate = getValue(older.releaseDate, newer.releaseDate);
        output.language = getValue(older.language, newer.language);

        // combine the textArray in order of id
        Map<Integer, Data.Text> map = new TreeMap<>();
        if (a1.textArray != null) a1.textArray.forEach(text -> map.put(text.id, text));
        if (a2.textArray != null) a2.textArray.forEach(text -> map.put(text.id, text));
        output.textArray = new ArrayList<>(map.values());
        return output;
    }

    // return the older value if newer value is null, otherwise the newer value
    private <T> T getValue(T older, T newer) {
        return newer == null ? older : newer;
    }
}
