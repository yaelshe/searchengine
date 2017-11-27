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

    }
    private List<String> breakTextToString (String text)
    {

        List<String> termsDoc =new ArrayList<String>();
        return termsDoc;
    }


    private boolean isNumber(){return false;}
private boolean isDate(){return false;}
private boolean isCapital()
{return false;}

    private void numbersHandler(String s){

    }
    public static void dateHandler(){


    }
    /**private void capitalHandler(String term){
        if (Character.isUpperCase(term.charAt(0)))
        {
            m_terms.
        }
    }*/


}



