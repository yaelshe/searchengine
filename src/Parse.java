import java.nio.charset.CharacterCodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse
{
    List<String> m_StopWords;
    private Dictionary<Document,Term>m_terms;
    private ArrayList<String> beforeTerms;
    Dictionary<String,Document>m_documents;




    public Parse(List<String> m_StopWords, Dictionary<Document, Term> m_terms, String beforeTerms,
                 Dictionary<String,Document>documents) {
        //this.m_StopWords = new List<String>(m_StopWords);
        //this.m_terms = m_terms;
        //this.beforeTerms = beforeTerms;
        m_documents=documents;
    }
    public void ParseAll()
    {
       /** for (Map.Entry<String,Document> entry : m_documents.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            // do stuff
        }

              {

        }*/

    }


    private boolean isNumber(){return false;}
private boolean isDate(){return false;}
private boolean isCapital()
{return false;}

    private void numbersHandler(String s){

    }
    public static void dateHandler(){
        String input = "Thu Jun 18 20:56:02 EDT 2009";
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date date = null;
        try {
            date = parser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);

    }
    /**private void capitalHandler(String term){
        if (Character.isUpperCase(term.charAt(0)))
        {
            m_terms.
        }
    }*/


}



