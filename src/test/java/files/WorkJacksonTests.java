package files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.jackson.MyJackson;
import model.jackson.MyJackson1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;


// https://www.springcloud.io/post/2022-07/jackson/#gsc.tab=0

public class WorkJacksonTests {
    private ClassLoader cl = WorkWithJsonTests.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Создание из класса JSON строки")
    void createJasonAsString() throws JsonProcessingException {
        MyJackson myFile1 = new MyJackson(1, "Vladimir");

        String resultJson = objectMapper.writeValueAsString(myFile1);
        System.out.println(resultJson);

        Assertions.assertTrue(resultJson.contains("Vladimir"));
        Assertions.assertTrue(resultJson.contains("1"));

    }

    @Test
    @DisplayName("Проверка JSON из файла")
    void createJacksonFromFile() throws Exception {


        File file = new File("src/test/resources/jackson/MyJackson.json");
        MyJackson1 my = objectMapper.readValue(file, MyJackson1.class);
        System.out.println(my);

    }

    @Test
    @DisplayName("Проверка JSON из класса")
    void createJacksonFromModel() throws Exception {

        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("jackson/MyJackson.json"))) {
            MyJackson1 resultJson = objectMapper.readValue(reader, MyJackson1.class);
            System.out.println(resultJson);
            Assertions.assertEquals(1, resultJson.getId());
            Assertions.assertEquals("Ivan", resultJson.getName());
            Assertions.assertEquals("java", resultJson.getSkillList().get(0));
            Assertions.assertEquals("Sample Konfabulator Widget", resultJson.getBook().getTitle());
            Assertions.assertEquals("New book", resultJson.getBook().getName());
            Assertions.assertEquals(500, resultJson.getBook().getNumber());
        }
    }

}
