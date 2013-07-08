package syntaxParseTools;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles parsing the input file, creating the corpus and populating it
 * with sentences.
 * 
 * @author glpayson
 */
public class SyntaxTreeTools {

	private static final String ROOT_NODE_MARKER = "(ROOT";
	
	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    
		// Corpus holds Sentence objects
		Corpus corp = new Corpus();

		String filePath = validateArgs(args);
		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			parseFileToCorpus(br, corp);
			System.out.println("Done!");
			
			// Displaying a JUNG2 tree
			//corp.getSentences().get(0).getVisTree().display();
			
			// Displaying Google Chart graphs
			for(Sentence sent : corp.getSentences()){
				sent.getcFGGoogleGraph().displayGraph();
				sent.getdGGoogleGraph().displayGraph();
			}
			
			// Writing grammar to STDOUT
			//CFGWriter.print(corp.getContextFreeGrammar());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { br.close(); } 
			catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	private static String validateArgs(String[] args){
		if(args.length <= 0 ){
			try {
				throw new IOException("Input file not specified");
			} catch (IOException e) { e.printStackTrace(); }
		}
		
		return args[0];
	}
	
	private static void parseFileToCorpus(BufferedReader br, Corpus corp) 
			throws IOException{
		
		String curLine;
		while((curLine = br.readLine()) != null){

			if(curLine.startsWith(ROOT_NODE_MARKER)){		
				parseSentence(curLine, br, corp);
			}else if(!curLine.equals("")){
				throw new IOException("Unhandled input line: \n\"" 
										+ curLine + "\"" );
			}
		}
	}
	
	// Parses an input sentence which begins with curLine and continues
	//  on some unknown number of subsequent lines in the BufferedReader.
	//  Constructs a new Sentence object from this input and adds it to
	//  the Corpus.
	private static void parseSentence(String curLine, BufferedReader br,
			Corpus corp) throws IOException {
		
		// Get cfg parse string
		String cfgString = readCFGString(curLine, br, corp);
		
		// Get dg rule strings
		curLine = br.readLine();	// This will be the first DG rule
		List<String> dgStrings = 
				readDependencyGrammarStrings(curLine, br);
		
		try {	// Create a new Sentence object and add it to the corpus
			Sentence curSent = new Sentence(cfgString, dgStrings);
			corp.addSentence(curSent);
		} catch (IllegalArgumentException e) {
			System.err.println("Error in parsing sentence:\n"
					 			+ cfgString);
			e.printStackTrace();
		}
	}

	private static String readCFGString(String curLine, BufferedReader br, 
			Corpus corp) throws IOException{
		
		StringBuffer rawParse = new StringBuffer();
		
		// Read lines and append until a blank line
		do{
			rawParse.append(curLine.trim());
		}while( !((curLine = br.readLine()).isEmpty()) );
		
		return rawParse.toString();
	}

	// Get DG rule strings, begining with curLine and continuin on some unknown
	//  number of subsequent lines in br. Return the rules as a List of 
	//  Strings.
	private static List<String> readDependencyGrammarStrings(String curLine,
					BufferedReader br) throws IOException {
		
		List<String> dGStrings = new ArrayList<String>();
		
		// Read lines and add to List until a blank line
		do{
			dGStrings.add(curLine);
		}while(!(curLine = br.readLine()).isEmpty());
		return dGStrings;
	}
}
