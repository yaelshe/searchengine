import java.util.*;
public class Term
{
    String _term;
    int numOfDocIDF;//amount of docs the term appear in
    Map <String,Integer> docs;//list for the numbers of the
                                // documents the word is in and number of appearances in each


    public Term( String term, Map<String, Integer> docs) {
        _term=term;
        this.docs = docs;
        this.numOfDocIDF = docs.size();

    }
    public String toString(Term term)
    {//term #numberofDocs &docname-number docname-number....
        String termStr="";
        termStr=term.get_term()+" ";
        termStr=termStr+"#"+getnumOfDocIDF()+" ";
        termStr=termStr+"&"+get_docs();
        return termStr;
    }

    public String get_term() {
        return _term;
    }
    public String getnumOfDocIDF(){
        String num="";
        num=Integer.toString(numOfDocIDF);
        return num;
    }
    public String get_docs(){
        String str="";
        for (String docnum: docs.keySet()){

            String key =docnum;
            String value =docs.get(docnum).toString();
            System.out.print(key + "-" + value+" ");
        }
        return str;
    }

}
