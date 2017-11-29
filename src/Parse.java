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

    private void numbersHandler(String s) {
        //change numer from 83.333333 to 83.33
        //String tt = "3.5555";
        if (s.indexOf('.')!=-1) {
            if (s.indexOf(',')!=-1)
            {
                s=s.replaceAll(",","");
            }
            int y = s.indexOf('.');

            //System.out.println(l);
            String ttt = s.substring(y + 1);
            if (ttt.length() > 2) {
                String l = s.substring(y + 3, y + 4);
                int xx = Integer.parseInt(l);
                if (xx >= 5) {
                    double x = Double.parseDouble(s);
                    x = x + 0.01;
                    s = Double.toString(x);
                    s = s.substring(0, y + 3);
                    // System.out.println(s);
                } else {
                    //tt = Double.toString(x);
                    s = s.substring(0, y + 3);
                    //System.out.println(s);
                }
            }
        }
    }
    private  static  void  percent (String s){
        if(s.indexOf("%") != -1)
        {
            s = s.replace('%',' ')+"percent";
            //System.out.println(s);
        }
        if(s.indexOf("percentage") != -1)
        {
            s = s.replaceAll("percentage","percent");
            //System.out.println(s);
        }
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



