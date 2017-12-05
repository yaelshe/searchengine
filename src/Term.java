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
    {
        String termStr="";
        termStr=term.get_term()+" ";
        termStr=termStr+"#"+getnumOfDocIDF();
        termStr=termStr;
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

}
