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
    String id ;
    //String date ;


    public Document(String id,String text, int max_tf, int docLength, String mostCommWord) {
        this.text = text;
        this.max_tf = max_tf;
        this.docLength = docLength;
        this.mostCommWord = mostCommWord;
        this.id=id;
        //this.title = title;
        //this.date=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMax_tf() {
        return max_tf;
    }

    public void setMax_tf(int max_tf) {
        this.max_tf = max_tf;
    }

    public int getDocLength() {
        return docLength;
    }

    public void setDocLength(int docLength) {
        this.docLength = docLength;
    }

    public String getMostCommWord() {
        return mostCommWord;
    }

    public void setMostCommWord(String mostCommWord) {
        this.mostCommWord = mostCommWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
