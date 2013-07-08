package syntaxParseTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/* XXX Could be made immutable if the Corpus class was as well. Since it is
*	   the only one making use of this.addSentence */


/**
 * A collection of DGGraphs
 * 
 * @author glpayson
 *
 */
public class DependencyGrammar implements Grammar<DGRule>{

	// Uniqueness ignores the wc suffix on node labels
	private Map<String, DGRule> uniqueRules
		= new HashMap<String, DGRule>();
	
	/***/
	public DependencyGrammar(){}
	
	/**
	 * Construct a DG from the Sentences in a Corpus
	 * 
	 * @param corpus  the Corpus
	 */
	public DependencyGrammar(Corpus corpus){
		
		// For all Sentences in the Corpus and all DependencyGrammarRule's in 
		//  the Sentence's DependencyGrammarGraph, add the 
		//  DependencyGrammarRule to dg
		for(Sentence sent : corpus.getSentences()){
			uniqueRules.putAll(getUniqueRules(sent.getdGGraph()));
		}
	}

	// Get unique list of rules
	private Map<String, DGRule> getUniqueRules(
			DGGraph dgg) {
		
		// Using a map to find unique elements
		Map<String, DGRule> uniqueRuleMap = 
				new HashMap<String, DGRule>();
		
		for(DGRule rule : dgg.getRules()){
			
			// Key is a string concatenation of edge, source, and 
			// target labels
			String k = rule.getEdgeLabel() + rule.getSourceLabel() 
					+ rule.getTargetLabel();
			
			// Rule is unique iff this key is not in the map yet
			if(!uniqueRuleMap.containsKey(k)){
				uniqueRuleMap.put(k, rule);
			}
		}
		
		return uniqueRuleMap;
	}

	/* (non-Javadoc)
	 * @see syntaxParseTools.Grammar#getRules()
	 */
	public List<DGRule> getRules() {
		return new ArrayList<DGRule>
				(uniqueRules.values());
	}

	/**
	 * Extract all rules from a single Sentence object and add them to this
	 * @param sentence  the Sentence
	 */
	public void addSentence(Sentence sentence) {
		
		for(DGRule rule : 
			sentence.getdGGraph().getRules()){
			
			// Key is a string concatenation of edge, source, and 
			// target labels
			String k = rule.getEdgeLabel() + rule.getSourceLabel() 
							+ rule.getTargetLabel();
						
			// Rule is unique iff this key is not in the map yet
			if(!uniqueRules.containsKey(k)){
					uniqueRules.put(k, rule);
			}
		}
	}	
}
