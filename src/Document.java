import java.util.*;
import java.io.BufferedReader;
import java.lang.*;
import java.io.InputStreamReader;
public class Document
{
    String text;
    int max_tf;//number of appearances most ferquent term
    int docLength;
    String mostCommWord;
    List<String> list;

    public Document(String text, int max_tf, int docLength, String mostCommWord) {
        this.text = text;
        this.max_tf = max_tf;
        this.docLength = docLength;
        this.mostCommWord = mostCommWord;
    }
    
}
