import java.util.*;
public class Term
{
    int numOfDocIDF;//amount of docs the term appear in
    Map <String,Integer> docs;//list for the numbers of the
                                // documents the word is in and number of appearances in each


    public Term(int numOfDocIDF, Map<String, Integer> docs) {
        this.numOfDocIDF = numOfDocIDF;
        this.docs = docs;
    }

}
