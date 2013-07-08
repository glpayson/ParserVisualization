package syntaxParseTools;

import java.util.List;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO Maybe CFGTree and DGGraph should have a supertype or shared interface?

/**
 * DependencyGrammarGraph represents a single DG graph corresponding to a 
 * single sentence. 
 * 
 * @author glpayson
 */
public class DGGraph {
	
	private final List<DGRule> rules;
	private final Set<DGNode> nodes;
	
	// Pattern for extracting labels from raw parse string
	// Group 1 - Edge label
	// Group 2 - Source node label
	// Group 3 - Source node label wc suffix
	// Group 4 - Target node label
	// Group 5 - Target node label wc suffix
	private static final Pattern rulePattern = 
			Pattern.compile("(.*?)\\((.*?)(-.*?), (.*?)(-.*?)\\)");
	
	/**
	 * Construct a DGGraph from a List of DG rules
	 * 
	 * @param parseStrings  a List of DG rules
	 * @throws IllegalArgumentException
	 */
	public DGGraph(List<String> parseStrings) 
			throws IllegalArgumentException{
		
		// Parse string to this.rules
		this.rules = stringToDGG(parseStrings);
		
		// Populate nodes set
		nodes = new TreeSet<DGNode>();
		for(DGRule rule : this.rules){
			nodes.add(rule.getSource());
			nodes.add(rule.getTarget());
		}
	}

	// Create new nodes and rules from the parse string list
	private List<DGRule> stringToDGG(
			List<String> parseStrings) {
		
		List<DGRule> dgg = new ArrayList<DGRule>();
		
		for(String rule : parseStrings){
			Matcher matcher = rulePattern.matcher(rule);
			matcher.find();
			
			// Construct new source and target nodes using substrings captured
			//  by matcher
			DGNode sourceNode = new DGNode(
					matcher.group(2), matcher.group(2) + matcher.group(3)); 
			DGNode targetNode = new DGNode(
					matcher.group(4), matcher.group(4) + matcher.group(5)); 

			dgg.add(new DGRule(sourceNode, targetNode, matcher.group(1)));
		}
		
		return dgg;
	}

	/**
	 * @return a List of the DGRules for this 
	 */
	public List<DGRule> getRules() { return rules; }
	
	
	/**
	 * @return a List of the DGNodes contained in this' DGRules
	 */
	public List<DGNode> getNodes() {
		return new ArrayList<DGNode>(nodes);
	}
	
	/** @return a list of DGRule Strings */
	public List<String> toStringList(){
		List<String> ruleStrings = new ArrayList<>();
		for(DGRule rule : this.rules){
			ruleStrings.add(rule.toString());
		}
		return ruleStrings;
	}
	
	/** @return a list of DGRule Strings with unique wc labels */
	public List<String> toUniqueStringList(){
		List<String> ruleStrings = new ArrayList<>();
		for(DGRule rule : this.rules){
			ruleStrings.add(rule.toUniqueString());
		}
		return ruleStrings;
	}
}

