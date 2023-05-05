package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.Map;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void testSet() {
        String path = "src/test/java/exercise/testFile";
        KeyValueStorage db = new FileKV(path, Map.of("key1", "value1"));
        db.set("key3", "value3");
        assertThat(db.get("key3", "default")).isEqualTo("value3");
    }
    public void testUnset() {
        String path = "src/test/java/exercise/testFile";
        KeyValueStorage db = new FileKV(path, Map.of("key1", "value1", "key5", "value5"));
        db.unset("key5");
        assertThat(db.get("key5", "default1")).isEqualTo("default1");
    }
    public void testGet() {
        String path = "src/test/java/exercise/testFile";
        KeyValueStorage db = new FileKV(path, Map.of("key1", "value1"));
        assertThat(db.get("key2", "default")).isEqualTo("default");
        assertThat(db.get("key1", "default")).isEqualTo("value1");
    }
    public void testToMap() {
        String path = "src/test/java/exercise/testFile";
        KeyValueStorage db = new FileKV(path, Map.of("key1", "value1", "key5", "value5"));
        db.unset("key5");
        assertThat(db.toMap()).isEqualTo(Map.of("key1", "value1"));
    }
    // END
}
