import java.nio.charset.CharacterCodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse
{
    private ArrayList<String> m_StopWords;
    private Map<String,Term>m_terms;
    private ArrayList<String> beforeTerms;
    private Map<String,Document>m_documents;
    String currDoc;




    public Parse(ArrayList<String> m_StopWords, Map<String, Term> m_terms, String beforeTerms,
                 Map<String,Document>documents) {
        this.m_StopWords = new ArrayList<String>(m_StopWords.size());
        this.m_terms = new HashMap<>(m_terms);
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
        for (int i=0;i<termsDoc.length;i++)
        {
            if (isNumber(termsDoc[i]))
            {
                termsDoc[i]= numbersHandler(termsDoc[i]);
                if (isPercent(termsDoc[i+1]))
                {
                    String mypercent = percent(termsDoc[i]);
                    if (m_terms.containsKey(mypercent))
                    {
                        //think what have to update
                    }else
                    {
                        Map<String,Integer> docss=new HashMap<>();//jkdj
                        Term newterm = new Term(1,docss);
                      m_terms.put(mypercent,newterm)  ;
                    }
                    i++;
                }
                else
                {
                    if (isDate(termsDoc[i-1],termsDoc[i],termsDoc[i+1],termsDoc[i+2]))
                    {
                        String mydate = dateHandler(termsDoc[i-1],termsDoc[i],termsDoc[i+1],termsDoc[i+2]);
                        if (m_terms.containsKey(mydate))
                        {
                            //think what have to update
                        }else
                        {
                            Map<String,Integer> docss=new HashMap<>();//jkdj
                            Term newterm = new Term(1,docss);
                            m_terms.put(mydate,newterm)  ;
                        }
                    }
                    else
                    {
                        if (m_terms.containsKey(termsDoc[i]))
                        {
                            //think what have to update
                        }else
                        {
                            Map<String,Integer> docss=new HashMap<>();//jkdj
                            Term newterm = new Term(1,docss);
                            m_terms.put(termsDoc[i],newterm)  ;
                            newterm.docs.put(currDoc,1);//update the list of docs the term is in
                        }
                    }

                }

            }
            else
            {
                //cheek if the term capital ...
            }

        }





    }
    /**private List<String> breakTextToString (String text)
    {

        List<String> termsDoc =new ArrayList<String>();
        return termsDoc;
    }*/


    private boolean isNumber(String str){
        // a function to check if the term is a number
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
        }
private boolean isDate(String s1,String s2,String s3,String s4){
    // a function to check if the term is part of a date
        return false;}
    private boolean isPercent(String s){
        // a function to check if the term is part of a percent
        return false;}
private boolean isCapital()
{// // a function to check if the term has capital letter
    return false;}

    private String numbersHandler(String s) {
        //change numer from 83.333333 to 83.33
        //String tt = "3.5555";
        String percent="";
        if (s.indexOf('%')!=-1)
        {
            percent="%";
        }
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
        return  s+percent;
    }
    private  String  percent (String s){
        if(s.indexOf("%") != -1)
        {
            return s.replace('%',' ')+"percent";
            //System.out.println(s);
        }else
        {
            return (s + "percent");

        }
        /**if(s.indexOf("percentage") != -1)
        {
            s = s.replaceAll("percentage","percent");
            //System.out.println(s);
        }*/
        //return s;
    }
    public String dateHandler(String S1,String s2,String s3,String s4){
        //change the format of the date in the text to the rule we have


        return "";
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



