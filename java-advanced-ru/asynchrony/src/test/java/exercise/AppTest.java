package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;
import java.util.concurrent.CompletableFuture;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

class AppTest {
    private String destPath;

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        destPath = Files.createTempFile("test", "tmp").toString();
    }

    @Test
    void testUnion() throws Exception {
        CompletableFuture<String> result = App.unionFiles(
            "src/test/resources/file1.txt",
            "src/test/resources/file2.txt",
            destPath
        );
        result.get();

        String actual = Files.readString(getFullPath(destPath));
        assertThat(actual).contains("Test", "Message");
    }

    @Test
    void testUnionWithNonExistedFile() throws Exception {

        String result = tapSystemOut(() -> {
            App.unionFiles("nonExistingFile", "file", destPath).get();
        });

        assertThat(result.trim()).contains("NoSuchFileException");
    }

    // BEGIN
    @Test
    void TestGetDirectorySize() throws Exception {
        CompletableFuture<Long> sizeFuture = App.getDirectorySize("src/main/resources/");
        assertInstanceOf (CompletableFuture.class, sizeFuture);
        Long size = sizeFuture.get();
        Assertions.assertEquals(24L, size); // 24 Byte

//        String exceptionMassage = tapSystemOut(() -> {
//            App.getDirectorySize("src/main/resources/unexistingdir/");
//        });
//        assertTrue(exceptionMassage.contains("NoSuchFileException"));

        CompletableFuture<Long> sizeFuture2 = App.getDirectorySize("src/main/resources/"); // ces/ - ?
        CompletableFuture<Long> sizeFuture3 = App.getDirectorySize("src/main/resources/subdirectory/");
        CompletableFuture<Void> combined = CompletableFuture.allOf(sizeFuture2, sizeFuture3);
        combined.get();
        assertThat(sizeFuture2.isDone()).isTrue();
        assertThat(sizeFuture3.isDone()).isTrue();
    }
    // END
}
