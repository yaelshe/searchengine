import java.io.*;
import java.util.*;
import java.lang.*;

public class Indexer
{

    private Map<String,Term>mp_terms;
    public Indexer(Map<String,Term> parsedWords) {
        mp_terms=new HashMap<>(parsedWords);
    }
    public void WriteToTxt(String s){
        String string =s;
        try {
            //create a temporary file
            //File file = new File("newfile.txt");
            File logFile=new File("C:\\Users\\ibrahim\\Desktop\\11\\ibr.txt");
            System.out.println(logFile.getCanonicalPath());

            //System.out.println(timeLog);
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write (string);
            //writer.write("jalksjgghioqwuiplifjkl");

            //Close writer
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
