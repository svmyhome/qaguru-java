package files;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.jackson.MyJackson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;


// https://www.springcloud.io/post/2022-07/jackson/#gsc.tab=0

public class WorkJacksonTests {
    private ClassLoader cl = getClass().getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("Проверка JSON из класса")
    void createJacksonFromModel() throws Exception {

        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("jackson/MyJackson.json"))) {
            MyJackson resultJson = objectMapper.readValue(reader, MyJackson.class);
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
