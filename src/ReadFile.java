import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

//import java.
public class ReadFile {
    Dictionary <String,Document> documents;
    String mainPath;
    List<String> filesPaths;

    public ReadFile(String path)
    {
        this.mainPath = path;
        this.filesPaths = new ArrayList<String>();

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path1 -> filesPaths.add(path1.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // to remove, put it in new function for loading.
        this.filesPaths.forEach(s -> {
            try {
                out.println(readFileAsString(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

}