import java.io.*;
import java.util.*;
import java.lang.*;

public class Indexer
{

    private Map<String,Term>mp_terms;
    private int mytxt;
    private String newLine;
    public Indexer(Map<String,Term> parsedWords,int i) {
        mp_terms=new HashMap<>(parsedWords);
        mytxt = i;
         newLine = System.getProperty("line.separator");
    }
    public  void www() throws IOException {
       // File logFile=new File("C:\\Users\\ibrahim\\Desktop\\11\\ibr.txt");
        File logFile=new File("C:\\Users\\ibrahim\\Desktop\\11\\"+mytxt+".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
        //System.out.println(logFile.getCanonicalPath());
        //int i = 1;

        for (String docnum: mp_terms.keySet()){
            //if (i<10){
            String value = mp_terms.get(docnum).toString();
            WriteToTxt(value,logFile,writer);
            //i++;//}else {
            //break;
            //}
            // System.out.println(i);
            //String key =docnum;
            //String value =docs.get(docnum).toString();
            //System.out.print(key + "-" + value+" ");
        }
        writer.close();
    }
    public void WriteToTxt(String s, File logFile, BufferedWriter writer){
        String string =s;
        //System.out.println(s);
        try {
            //create a temporary file
            //File file = new File("newfile.txt");

            //System.out.println(logFile.getCanonicalPath());

            //System.out.println(timeLog);

            writer.write (string+newLine);
            //writer.write("jalksjgghioqwuiplifjkl");

            //Close writer
            //writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public String mergTowFile(String path1,String path2,String path3) throws IOException {
        File logFile=new File(path3+0+".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
        String line="";
        String line2="";
        String s1="";
        String s2="";
        try {
            try(BufferedReader br = new BufferedReader(new FileReader(path1))) {

                line = br.readLine();
                s1=line.substring(0,line.indexOf("#")-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try(BufferedReader br = new BufferedReader(new FileReader(path2))) {

                line2 = br.readLine();
                s2=line2.substring(0,line2.indexOf("#")-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(s1.equals(s2))
        {
            //System.out.println(s1.indexOf('&'));
            //System.out.println(s2.indexOf('&'));
            String docs=line.substring(line.indexOf("&"),line.length())+line2.substring(line2.indexOf("&")+1,line2.length());
            int number = Integer.parseInt(line.substring(line.indexOf("#")+1,line.indexOf("&")-1))+Integer.parseInt(line2.substring(line2.indexOf("#")+1,line2.indexOf("&")-1));
            line = s1+" "+"#"+" "+number+" "+docs;
            writer.write (line+System.getProperty("line.separator"));
            writer.close();
        }else
        {
            if (s1.compareTo(s2)>0){
                System.out.println(s1);
                writer.write (line+System.getProperty("line.separator"));
                writer.close();
            }else {
                System.out.println(s2);
                writer.write (line2+System.getProperty("line.separator"));
                writer.close();
            }
        }
        return "";
    }
}
