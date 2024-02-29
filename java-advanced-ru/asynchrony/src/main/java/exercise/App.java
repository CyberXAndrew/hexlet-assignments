package exercise;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filepath1, String filepath2, String filepath3)
            throws IOException, InterruptedException, ExecutionException {
//            Path path1 = Paths.get(filepath1).toAbsolutePath();
//            Path path2 = Paths.get(filepath2).toAbsolutePath();

        CompletableFuture<String> file1Future = CompletableFuture.supplyAsync(() -> {
            try {
                Path path1 = Paths.get(filepath1).toAbsolutePath();
                if (!Files.exists(path1)) {
                    throw new NoSuchFileException("NoSuchFileException");
                }
                return Files.readString(path1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return "file1Future error ||";
            }
        });
        file1Future.get();

        CompletableFuture<String> file2Future = CompletableFuture.supplyAsync(() -> {
            try {
                Path path2 = Paths.get(filepath2).toAbsolutePath();
                if (!Files.exists(path2)) {
                    throw new NoSuchFileException("NoSuchFileException");
                }
                return Files.readString(path2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return "file2Future error ||";
            }
        });
        file2Future.get();

        CompletableFuture<String> future3 = file1Future.thenCombine(file2Future, (content1 , content2) -> {
            try {
                String resultContent = content1 + content2;
                Path path3 = Paths.get(filepath3).toAbsolutePath();
                if (!Files.exists(path3)) {
                    Files.createFile(path3);
                }
                Path path = Files.writeString(path3, resultContent);
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Exception was thrown: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        });
        return future3;
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(directoryPath);
            try {
                List<Path> files = Files.list(path)
                        .filter(pathToFile -> !Files.isDirectory(pathToFile))
                        .toList();
                long filesSize = 0;
                for (Path file : files) {
                    filesSize += Files.size(file);
                }
                System.out.println(filesSize + " Bytes total");
                return filesSize;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        future.get();

        return future;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt", "src/main/resources/file3.txt");

        getDirectorySize("src/main/resources/");
        // END
    }
}

