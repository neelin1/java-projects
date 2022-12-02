import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Continuation {
    public int order;
    public String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    public HashMap<String, ArrayList<Character>> letterOdds;
    public int mostCommonLen;
    public String mostCommonStr;

    public Continuation(String filename, int bookLength) throws IOException {
        this(5, filename, bookLength);
    }

    public Continuation(int newOrder, String filename, int bookLength) throws IOException {
        mostCommonLen = 0;
    	order = newOrder;
        BufferedReader reader= new BufferedReader(new FileReader(new File(filename)));

        //Read through characters
        letterOdds = new HashMap<String, ArrayList<Character>>();
        
        StringBuilder lastStr=new StringBuilder();
        while (reader.ready()) {
        	char t = (char) reader.read();
        	if (lastStr.length() >= order)
        	{
        		addLetter(lastStr.toString(),t);
        		lastStr.deleteCharAt(0);
        	}
        	lastStr.append(t);
        }
        reader.close();

        //Generate new book
        StringBuilder newText = new StringBuilder(mostCommonStr);
        for (int i = order; i< bookLength; i++)
        {
        	newText.append(newLetter(newText.substring(i-order, i)));
        }
        PrintWriter writer = new PrintWriter("output.txt");
        writer.print(newText.toString());
        writer.close();
    }
    
    public void addLetter(String key, char t) {
    	if (!letterOdds.containsKey(key)) {
    		//System.out.println(key);
    		letterOdds.put(key, new ArrayList<Character>());
    	}
    	letterOdds.get(key).add(t);
    	if (letterOdds.get(key).size() > mostCommonLen)
    	{
    		mostCommonStr = key;
    		mostCommonLen = letterOdds.get(key).size();
    	}
    }
    
    public String newLetter(String input) {
    	if (!letterOdds.containsKey(input)) {
    		double pos = Math.random()*26;
    		return alphabet[(int)pos];
    		//for (int i=0;i<26;i++) {
    		//	AddLetter(input,(char)('a'+i));
    		//}
    	}
    	ArrayList list=letterOdds.get(input);
    	double pos = Math.random()*list.size();
    	return list.get((int)pos).toString();
    }
}