package ai.brace;

import java.time.LocalDateTime;
import java.util.List;

// Class to represent data in a1 and a2
public class Data {
    public String version;
    public String uuid;
    public LocalDateTime lastModified;
    public String title;
    public String author;
    public String translator;
    public String releaseDate;
    public String language;
    public List<Text> textArray;

    static class Text {
        public int id;
        public String textdata;
    }
}
