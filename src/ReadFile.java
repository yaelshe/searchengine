import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static java.util.regex.Pattern.MULTILINE;

import static java.lang.System.out;

//import java.
public class ReadFile {
    Map <String,Document> documents ;
    String mainPath;
    List<String> filesPaths;
    List<String> allMatchesofdoc;
    int sizofmydictionary;
    int nextFile;

    public ReadFile(String path)
    {

        this.mainPath = path;
        this.filesPaths = new ArrayList<String>();
        this.sizofmydictionary=0;
        this.nextFile=0;

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path1 -> filesPaths.add(path1.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void breakToFiles()
    {
        List<String> mydocuments;


        /**this.filesPaths.forEach(s -> {
            try {
                //out.println(readFileAsString(s));
                mydocuments.add(readFileAsString(s));
                //out.println(documents[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
        mydocuments = new ArrayList<String>();
        documents = new HashMap<>();
        sizofmydictionary=documents.size();
        allMatchesofdoc = new ArrayList<String>();
        for (int i = nextFile;i <this.filesPaths.size()&& i < nextFile+10 ;i++)
        {
            try {
                mydocuments.add(readFileAsString(this.filesPaths.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        nextFile=nextFile+10;
        mydocuments.forEach(s -> {
            breakToDocs(s);

        });
   }

   private  void breakToDocs(String stringfile)
   {
       String regex = "<DOC>(?s)(.+?)</DOC>";
       //String regex1 = "<TEXT>(?s)(.+?)</TEXT>";
       Matcher m = Pattern.compile(regex)
           .matcher(stringfile);
       while (m.find()) {
           allMatchesofdoc.add(m.group(1));
       }

       for(int i=sizofmydictionary;i<allMatchesofdoc.size();i++)
       {


           if (allMatchesofdoc.get(i).contains("<TEXT>")){
               int first = allMatchesofdoc.get(i).indexOf("<TEXT>");
               int last = allMatchesofdoc.get(i).indexOf("</TEXT>");
               String mytext = allMatchesofdoc.get(i).substring(first+6,last);
               if(mytext.length()>0) {
                   first = allMatchesofdoc.get(i).indexOf("<DOCNO>");
                   last = allMatchesofdoc.get(i).indexOf("</DOCNO>");
                   String mydocno = allMatchesofdoc.get(i).substring(first+7,last);
                   Document S = new Document(mytext, 0, mytext.length(), "ldkjf");
                   documents.put(mydocno, S);
                   //System.out.println(mydocno);
               }

       }
       sizofmydictionary=documents.size();
   }}
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