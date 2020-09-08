package ai.brace;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {

    public static Gson gson;

    public static void main(String[] args) throws FileNotFoundException {
        gson = createGson();
        Data a1 = createData("a1.json");
        Data a2 = createData("a2.json");

        new Task1(a1).doWork();
        new Task2(a1, a2).doWork();
        new Task3(a1, a2).doWork();
        new Task4(a1, a2, gson).doWork();
    }

    // read data from file here, so we don't do so multiple times per task
    private static Data createData(String fileName) throws FileNotFoundException {
        String url = ClassLoader.getSystemResource(fileName).getFile();
        FileReader fileReader = new FileReader(url);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, Data.class);
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting() // add newlines and tabs
                .disableHtmlEscaping() // print ' instead of \u0027
                // deserialize epoch seconds to LocalDateTime
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                        LocalDateTime.ofEpochSecond(json.getAsJsonPrimitive().getAsLong(), 0, ZoneOffset.UTC))
                // serialize LocalDateTime into ISO format
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (date, typeOfT, context) ->
                        new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))))
                .create();
    }
}
