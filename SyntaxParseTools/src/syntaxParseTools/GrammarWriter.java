package syntaxParseTools;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Static class that handles printing a grammar
 * 
 * @author glpayson
 */
public class GrammarWriter{

	private GrammarWriter(){}
	
	/**
	 * Print grammar to a bufferedWriter
	 * 
	 * @param g  the grammar to print
	 * @param bw  the BufferedWriter to print to
	 */
	public static <T extends GrammarRule<T>> void print(Grammar<T> g,
			BufferedWriter bw){
		List<T> rules = g.getRules();
		Collections.sort(rules);
			try {
				for(GrammarRule<?> rule: rules){
					bw.write(rule.toString());
				}
			} catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * Print grammar to STDOUT
	 * 
	 * @param g  the grammar to print to STDOUT
	 */
	public static <T extends GrammarRule<T>> void print(Grammar<T> g){
		List<T> rules = g.getRules();
		Collections.sort(rules);
		for(GrammarRule<?> rule: rules){
			System.out.println(rule.toString());
		}
	}

}
