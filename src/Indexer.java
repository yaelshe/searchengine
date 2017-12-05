import java.io.*;
import java.util.*;
import java.lang.*;

public class Indexer
{

    private Map<String,Term>mp_terms;
    public Indexer(Map<String,Term> parsedWords) {
        mp_terms=new HashMap<>(parsedWords);
    }
}
