import java.nio.charset.CharacterCodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse
{
    private ArrayList<String> m_StopWords;
    private Map<Term,Term>m_terms;
    private ArrayList<String> beforeTerms;
    private Map<String,Document>m_documents;
    String currDoc;




    public Parse(ArrayList<String> m_StopWords, Map<Document, Term> m_terms, String beforeTerms,
                 Map<String,Document>documents) {
        this.m_StopWords = new ArrayList<String>(m_StopWords.size());
        //this.m_terms = m_terms;
        //this.beforeTerms = beforeTerms;
        m_documents=new HashMap<>(documents);
    }
    public void ParseAll()
    {
        for (Map.Entry<String,Document> entry : m_documents.entrySet()) {

            Document value = entry.getValue();
            currDoc= entry.getKey();
            parseDoc(value);

            // do stuff
        }



    }
    private void parseDoc(Document doc)
    {
        String []termsDoc=doc.getText().split("\\s+");
        //here we need to put a loop that goes over all the terms on the text
        //the terms are in "termsDoc"



    }
    /**private List<String> breakTextToString (String text)
    {

        List<String> termsDoc =new ArrayList<String>();
        return termsDoc;
    }*/


    private boolean isNumber(){
        // a function to check if the term is a number
        return false;}

private boolean isDate(){
    // a function to check if the term is part of a date
        return false;}
private boolean isCapital()
{// // a function to check if the term has capital letter
    return false;}

    private void numbersHandler(String s){
    //change numer from 83.333333 to 83.33

    }
    public static void dateHandler(){
        //change the format of the date in the text to the rule we have


    }
    /**private void capitalHandler(String term){
     * //change the format of the term in the text to the rule we have about capital
     * // like if Adam Tal need to have 3 terms -> "adam" , "tal" and "adam tal"
     * also NBA-> nba
     * also if roGer-> roger
        if (Character.isUpperCase(term.charAt(0)))
        {
            m_terms.
        }
    }*/

    //we need to add 2 more rules like handling word with ' like aren't-> arent or something


}



