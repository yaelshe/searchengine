import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static java.util.regex.Pattern.MULTILINE;

import static java.lang.System.out;

//import java.
public class ReadFile {
    Dictionary <String,Document> documents;
    String mainPath;
    List<String> filesPaths;
    List<String> allMatchesofdocno = new ArrayList<String>();
    List<String> allMatchesoftext = new ArrayList<String>();
    int sizofmydictionary;
    //List<String> allMatchesofheader = new ArrayList<String>();

    public ReadFile(String path)
    {
  //      documents=new  <String,Document>();
        this.mainPath = path;
        this.filesPaths = new ArrayList<String>();
        this.sizofmydictionary=0;

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path1 -> filesPaths.add(path1.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        kfdjkd();

    }

    private void kfdjkd()
    {
        List<String> mydocuments;

        mydocuments = new ArrayList<String>();
        this.filesPaths.forEach(s -> {
            try {
                //out.println(readFileAsString(s));
                mydocuments.add(readFileAsString(s));
                //out.println(documents[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //ttt(mydocuments.get(0));
        mydocuments.forEach(s -> {
            //try {
                //out.println(readFileAsString(s));
                ttt(s);
                //out.println(documents[0]);
            //} catch (IOException e) {
                //e.printStackTrace();
            //}
        });
        //out.println(documents.get(0));
   }

   private  void ttt(String stringfile)
   {
       String regex = "<DOCNO>(.+?)</DOCNO>";
       String regex1 = "<TEXT>(?s)(.+?)</TEXT>";
       Matcher m = Pattern.compile(regex)
           .matcher(stringfile);
       while (m.find()) {
           allMatchesofdocno.add(m.group(1));
       }

       Matcher m1 = Pattern.compile(regex1)
               .matcher(stringfile);
       while (m1.find()) {
           allMatchesoftext.add(m1.group(1));
       }
       //System.out.println();
       for(int i=sizofmydictionary;i<allMatchesofdocno.size();i++)
       {

         Document S = new Document(allMatchesoftext.get(i),0,allMatchesoftext.get(i).length(),"ldkjf");
           documents.put(allMatchesofdocno.get(i),S);

       }
       sizofmydictionary=documents.size();
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