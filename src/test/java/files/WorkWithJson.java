package files;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

public class WorkWithJson {
    private ClassLoader cl = WorkWithJson.class.getClassLoader();
    private final static Gson gson = new Gson();

    @Test
    @DisplayName("Чтение из JSON")
    void readJSON() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("glossary.json"))) {

            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(1234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
            Assertions.assertEquals("SGML", inner.get("Acronym").getAsString());
        }
    }

    @Test
    @DisplayName("Чтение из JSON")
    void improveReadJSON() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("glossary.json"))) {

            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(1234, actual.getId());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
            Assertions.assertEquals("SGML", actual.getGlossary().getAcronym());
        }
    }

}