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
    public void parseDoc(Document doc)
    {
        String []termsDoc=doc.getText().split("\\s+");
        //here we need to put a loop that goes over all the terms on the text
        //the terms are in "termsDoc"
        boolean flagCapital=false;
        int count=0;
        for (int i=0;i<termsDoc.length;i++)
        {
            termsDoc[i] = removeExtra(termsDoc[i]);
            if (isNumber(termsDoc[i]))/////
            {
                termsDoc[i] = numbersHandler(termsDoc[i]);// numb 25-27,21/05/1991,29-word done

                if (  termsDoc[i].charAt(termsDoc[i].length()-1) == '%'||isPercent(removeExtra(termsDoc[i + 1]))) {
                    String mypercent = percent(termsDoc[i]);
                    System.out.println(mypercent + "--1");
                    addToTerm(mypercent);
                    if (i+1<termsDoc.length){
                    if (isPercent(termsDoc[i + 1])) {
                        i++;
                    }}
                } else {
                    //System.out.println(isDate(termsDoc[i - 1], termsDoc[i + 1]));
                    if (isDate(removeExtra(termsDoc[i - 1]), removeExtra(termsDoc[i + 1])) && !termsDoc[i].contains(".")) {//216
                        String s4="";
                        String s3="";
                        if (i+2<termsDoc.length)
                        {
                            s4 = removeExtra(termsDoc[i + 2]);
                        }
                        if (i+1<termsDoc.length)
                        {
                            s3 = removeExtra(termsDoc[i + 1]);
                        }
                        String mydate = dateHandler(removeExtra(termsDoc[i - 1]), removeExtra(termsDoc[i]), s3,s4 );
                        System.out.println(mydate + "--2");
                        addToTerm(mydate);// to update i ....
                    } else {
                        System.out.println(termsDoc[i] + "--3");
                        addToTerm(termsDoc[i]);
                    }

                }

            } else {
                if (termsDoc[i].contains("-")) {
                    //checks if the word has an - in the middle and
                    // save the word before and after it and also for twice -
                    termsDoc[i]=handleMakaf(termsDoc[i]);
                    addToTerm(termsDoc[i]);
                    /**int makaf = termsDoc[i].indexOf("-");
                    String part1 = termsDoc[i].substring(0, makaf);
                    System.out.println(part1 + "--5");
                    addToTerm(part1);
                    String part2 = termsDoc[i].substring(makaf + 1, termsDoc[i].length());
                    if ((part2.contains("-")) && ((part2.substring(part2.indexOf('-') + 1, part2.length())).length() > 0)) {
                        String part3 = part2.substring(0, part2.indexOf('-'));
                        String part4 = part2.substring(part2.indexOf('-') + 1, part2.length());
                        //System.out.println(part3);
                        // System.out.println(part4);
                        addToTerm(part3);
                        addToTerm(part4);
                        termsDoc[i] = (part1 + " " + part3 + " " + part4);
                    } else {
                        System.out.println(part2);
                        addToTerm(part2);
                        termsDoc[i] = part1 + " " + part2;
                    }
*/
                }
               else {
                    if (Character.isUpperCase(termsDoc[i].charAt(0))) {
                        //check if the term capital letter up to phrase of 4 words.
                        String str1 = null, str2 = null, str3 = null, total = null;
                        count = 0;
                        if (i + 1 < termsDoc.length) {
                            str1 = termsDoc[i + 1];
                            count = 1;
                            if (i + 2 < termsDoc.length) {
                                str2 = termsDoc[i + 2];
                                count = 2;
                                if (i + 3 < termsDoc.length) {
                                    str3 = termsDoc[i + 3];
                                    count = 3;
                                }
                            }
                            List<String> ph = capitalTerm(termsDoc[i], str1, str2, str3);
                            flagCapital=true;// so we won't check again the same words !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            for (int j = 0; j < ph.size(); j++) {
                               // ph.get(j).toLowerCase();

                                total = total + (ph.get(j).toLowerCase());
                                //add term to dictionary
                            }
                            termsDoc[i]=total;


                        }
                    }
                    else if (termsDoc[i].contains("\'")) {
                        //checks if the word has an apostrphe in the middle and
                        // save the word without it and the part before it
                        termsDoc[i]=handleApostrophe(termsDoc[i]);
                        /**int makaf = termsDoc[i].indexOf("\'");
                         String part1 = termsDoc[i].substring(0, makaf);
                         System.out.println(part1 + "--6");
                         addToTerm(part1);
                         String part2 = termsDoc[i].replace("\'", "");

                         termsDoc[i] = (part2);*/

                    }

                    System.out.println(termsDoc[i] + "--7");
                    addToTerm(termsDoc[i]);
                    if(flagCapital)
                        i=i+count;
                }
            }
        }
    }

    private void addToTerm(String str)
    {
        str=str.toLowerCase();
        if(!m_StopWords.contains(str)) {
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

    }
    private String handleMakaf (String str)
    {
        int makaf = str.indexOf("-");
        String part1 = str.substring(0, makaf);
        part1= checkAgain(part1);
        System.out.println(part1 + "--5");
        addToTerm(part1);
        String part2 = str.substring(makaf + 1, str.length());
        if ((part2.contains("-")) && ((part2.substring(part2.indexOf('-') + 1, part2.length())).length() > 0)) {
            String part3 = part2.substring(0, part2.indexOf('-'));
            String part4 = part2.substring(part2.indexOf('-') + 1, part2.length());
            //System.out.println(part3);
            // System.out.println(part4);
            part3=checkAgain(part3);
            part4=checkAgain(part4);
            addToTerm(part3);
            addToTerm(part4);
            str = (part1 + " " + part3 + " " + part4);
        } else {
            part2=checkAgain(part2);
            System.out.println(part2);
            addToTerm(part2);
            str = part1 + " " + part2;

        }
        return str;
    }
    private String handleApostrophe (String str)
    {
        if (str.contains("\'")) {
            //checks if the word has an apostrphe in the middle and
            // save the word without it and the part before it
            int makaf = str.indexOf("\'");
            String part1 = str.substring(0, makaf);
            System.out.println(part1 + "--6");
            addToTerm(part1);
            String part2 = str.replace("\'", "");

            str = (part2);

        }
        return str;
    }
    public boolean isNumber(String str){
        // a function to check if the term is a number
       // System.out.println(str);
        if (str.length()==0){
            return false;}
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
           // System.out.println(str+21);
            if (str.length()>2&&str.substring(str.length()-2)=="th")
            {
                str=str.substring(0,str.length()-2);
                //System.out.println(str+3);
            }
            System.out.println(str.charAt(str.length()-1));
            //str.charAt(str.length()-1)
            if (str.length()>1&&str.charAt(str.length()-1)=='%')
            {
                //System.out.println(str+222);
                double d = Double.parseDouble(str.substring(0, str.length() - 1));//klajfd;
            }
            else
            {
                //System.out.println(str+1);
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
        //System.out.println(Months.containsKey(s1.toLowerCase()));
        //System.out.println(Months.containsKey(s2.toLowerCase()));
        //System.out.println(s2);
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
            return s.replaceAll("%","")+" percent";
            //System.out.println(s);
        }else
        {
            return (s + " percent");

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
    public  List<String> capitalTerm(String s1, String s2,String s3,String s4) {
        //ADD 4 STRING TO FUNC RETURN NUMBER OF WORDS IN phrase
        List<String> phrase = new LinkedList<String>();
        phrase.add(s1);
        if (s2!=null&&Character.isUpperCase(s2.charAt(0))&&!Character.isDigit(s2.charAt(0)))
        {
            s2=checkApo(s2);
            phrase.add(s2);
            if (s3!=null&&Character.isUpperCase(s3.charAt(0))&&!Character.isDigit(s3.charAt(0)))
            {
                s3=checkApo(s3);
                phrase.add(s3);
                if (s4!=null&&Character.isUpperCase(s4.charAt(0))&&!Character.isDigit(s4.charAt(0))){
                    s4=checkApo(s4);
                    phrase.add(s4);
                }
                    }
        }
        return phrase;
    }
    private String checkApo(String str)
    {
        if(str.contains("\'")) {
            int makaf = str.indexOf("\'");
            String part1 = str.substring(0, makaf);
            addToTerm(part1);
            str = str.replace("\'", "");
        }
        return str;
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
    public static String removeExtra(String str)
    {
        str=str.replaceAll("[,$#!?*(){}\":;+=|\\[\\]]","");
        char last=str.charAt(str.length()-1);
        char first= str.charAt(0);
        if(first=='<'||first=='\''||first=='^')
            str=str.substring(1,str.length()-1);
            //  str=str.substring(0,str.length()-2);
        else if(last=='.'||last=='\''||last=='^'||last=='-'||last=='>')
            str=str.substring(0,str.length()-1);
        return str;
    }
    public String checkAgain(String str)
    {
        if (str.contains("\'"))
        {
            str=checkApo(str);
        }
        else if(isNumber(str))
        {
            str=numbersHandler(str);
            if (str.charAt(str.length()-1)=='%'){
                str=percent(str);
            }
        }


        //is number to check for 13.3334
        //check is percent
        //check for '
    return str;
    }

}



