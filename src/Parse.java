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
    Map <String,String> Months=new HashMap<String, String>(){{
        put("january","01"); put("february","02"); put("march","03");put("april","04");put("may","05");
        put("june","06");put("july","07");put("august","08");put("september","09");put("october","10");
        put("november","11");put("december","12");put("jan", "01");put("feb","02");put("mar","03");
        put("apr","04");put("may","05");put("jun","06");put("jul","07");put("aug","08");put("sep","09");
        put("oct","10");put("nov","11");put("dec","03");}};
    String currDoc;
    //private  Map<String,String> ;
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
        for (int i=0;i<termsDoc.length;i++) {
            if (isNumber(termsDoc[i].substring(0, termsDoc[i].length())))/////
            {
                termsDoc[i] = numbersHandler(termsDoc[i]);// numb 25-27,21/05/1991,29-word done
                if (isPercent(termsDoc[i + 1]) || termsDoc[i].substring(termsDoc[i].length() - 1) == "%") {
                    String mypercent = percent(termsDoc[i]);
                    addToTerm(mypercent);
                    if (isPercent(termsDoc[i + 1])) {
                        i++;
                    }
                } else {
                    if (isDate(termsDoc[i - 1], termsDoc[i + 1])&& !termsDoc[i].contains(".")) {//2165
                        String mydate = dateHandler(termsDoc[i - 1], termsDoc[i], termsDoc[i + 1], termsDoc[i + 2]);
                        addToTerm(mydate);
                    } else {
                        addToTerm(termsDoc[i]);
                    }

                }

            } else if (!Character.isLowerCase(termsDoc[i].charAt(0))) {
                //cheek if the term capital ...
                List<String> ph = capitalTerm(termsDoc[i], termsDoc[i + 1], termsDoc[i + 2], termsDoc[i + 3]);
                if (ph.size() == 1)
                    ph.get(0).toLowerCase();
                    //add to term dictionary
                else {
                    for (int j = 0; j < ph.size() - 1; j++) {
                        ph.get(0).toLowerCase();
                        //add term to dictionary
                    }
                }

            }
            if(termsDoc[i].contains("-"))
            {
                int makaf=termsDoc[i].indexOf("-");
                String part1=termsDoc[i].substring(0,makaf);
                String part2=termsDoc[i].substring(makaf+1,termsDoc[i].length());
                //add part1, part 2 part 1 &part 2 together and with - to terms

            }
            if(termsDoc[i].contains("\'"))
            {
                int makaf=termsDoc[i].indexOf("\'");
                String part1=termsDoc[i].substring(0,makaf);
                String part2=termsDoc[i].substring(makaf+1,termsDoc[i].length());
                //add part1, part 2 part 1 &part 2 together and with - to terms

            }


        }

    }
    /**private List<String> breakTextToString (String text)
    {

        List<String> termsDoc =new ArrayList<String>();
        return termsDoc;
    }*/

    private void addToTerm(String str)
    {
        if (m_terms.containsKey(str)) {
            //think what have to update
            if (m_terms.get(str).docs.containsKey(currDoc))//if i have the doc in the map of docs
            {
                m_terms.get(str).docs.put(currDoc, m_terms.get(str).docs.get(currDoc) + 1);//update

            } else {
                m_terms.get(str).docs.put(currDoc, 1);
            }
        } else {
            Map<String, Integer> docss = new HashMap<>();//jkdj
            docss.put(currDoc, 1);
            Term newterm = new Term(docss);
            m_terms.put(str, newterm);
            //newterm.docs.put(currDoc,1);//update the list of docs the term is in
        }

    }
    private boolean isNumber(String str){
        // a function to check if the term is a number
        if (str.substring(1,str.length()).contains("-"))
        {
            return false;
        }
        try
        {
            /**
             * if (i have -) done
             * 122,222 done
             */
            str=str.replaceAll(",","");
            if (str.substring(str.length()-2)=="th")
            {
                str=str.substring(0,str.length()-2);
            }
            if (str.substring(str.length()-1)=="%")
            {
                double d = Double.parseDouble(str.substring(0, str.length() - 1));//klajfd;
            }
            else
            {
                double d = Double.parseDouble(str);
            }
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
        }//
    private boolean isDate(String s1,String s2)
    {
    // a function to check if the term is part of a date
        if (Months.containsKey(s1.toLowerCase())|| Months.containsKey(s2.toLowerCase()))
        {
            return  true;
        }
        return false;
    }
    private boolean isPercent(String s)
    {
        // a function to check if the term is part of a percent
        if (s=="percent" || s == "percentage")
        {
            return true;
        }
        return false;
    }//
    private boolean isCapital()
    {
        // // a function to check if the term has capital letter
    return false;
    }

    private String numbersHandler(String s) {
        //change numer from 83.333333 to 83.33
        //String tt = "3.5555";
        String percent="";
        if (s.indexOf('%')!=-1)
        {
            percent="%";
            s=s.substring(0,s.length()-1);
        }
        if (s.indexOf("th")!=-1)
        {

            s=s.substring(0,s.length()-2);
        }
        if (s.indexOf(',')!=-1)
        {
            s=s.replaceAll(",","");
        }
        if (s.indexOf('.')!=-1) {

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
            return s.replaceAll("%","")+"percent";
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
    public String dateHandler(String s1,String s2,String s3,String s4){//(termsDoc[i - 1], termsDoc[i], termsDoc[i + 1], termsDoc[i + 2])
        //change the format of the date in the text to the rule we have
        String day="";
        String month="";
        String year="";
        if (Months.containsKey(s3.toLowerCase()))
        {
            int intday = Integer.parseInt(s2);
            day = cmpToDay(intday);
            month=Months.get(s3.toLowerCase());
            if (isNumber(s4))
            {
                if (s4.length() == 4)
                {
                    year = s4;
                    return day+"/"+month+"/"+year;
                }
                if (s4.length()==2)
                {
                    year = "19"+s4;
                    return day+"/"+month+"/"+year;
                }

            }
            return day+"/"+month;
        }
        else
        {
            month=Months.get(s1.toLowerCase());
            if (isNumber(s3)&& s3.length()==4)
            {
                year=s3;
                int intday = Integer.parseInt(s2);
                day = cmpToDay(intday);
                return day+"/"+month+"/"+year;
            }
            else
            {
                if (s2.length()<3)
                {
                    int intday = Integer.parseInt(s2);
                    day = cmpToDay(intday);
                    return day+"/"+month;
                }else
                {
                    year = s2;
                    return month+"/"+year;
                }
            }
        }

    }
    public static List<String> capitalTerm(String s1, String s2,String s3,String s4) {
        //ADD 4 STRING TO FUNC RETURN NUMBER OF WORDS IN phrase
        List<String> phrase = new LinkedList<String>();
        phrase.add(s1);
        if (!Character.isLowerCase(s2.charAt(0))&&!Character.isDigit(s2.charAt(0))) {
            phrase.add(s2);
            if (!Character.isLowerCase(s3.charAt(0))&&!Character.isDigit(s3.charAt(0)))
                phrase.add(s3);
            if (!Character.isLowerCase(s4.charAt(0))&&!Character.isDigit(s4.charAt(0)))
                phrase.add(s4);
        }
        return phrase;
    }
    private String cmpToDay(int d){
        String day = "";
        if (d<10)
        {
            day = "0"+Integer.toString(d);
        }
        else
        {
            day = Integer.toString(d);
        }
        return  day;
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



