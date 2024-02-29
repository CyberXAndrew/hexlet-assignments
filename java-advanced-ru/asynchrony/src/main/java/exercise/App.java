package exercise;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    private static Path getAbsolutePath(String filepath) {return Paths.get(filepath).toAbsolutePath().normalize();}
    public static CompletableFuture<String> unionFiles(String filepath1, String filepath2, String filepath3) {

        CompletableFuture<String> file1Future = CompletableFuture.supplyAsync(() -> {
            String content;
            try {
                content = Files.readString(getAbsolutePath(filepath1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });
//        file1Future.get();

        CompletableFuture<String> file2Future = CompletableFuture.supplyAsync(() -> {
            String content;
            try {
                content = Files.readString(getAbsolutePath(filepath2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });
//        file2Future.get();

        return file1Future.thenCombine(file2Future, (content1 , content2) -> {
            String resultContent = content1 + content2;
            try {
                Path path = Files.writeString(getAbsolutePath(filepath3), resultContent, StandardOpenOption.CREATE);
                return Files.readString(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Exception was thrown: " + ex.getMessage());
            return "file3 mistake";
        });
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

