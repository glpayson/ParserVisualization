package syntaxParseTools;
import java.util.ArrayList;
import java.util.List;

/**
 * An array list containing all CFG production rules for a given corpus<br>
 * CFG production rules are of the form:<br>
 * NODE1 :- NODE2, ..., NODEn<br>
 * where NODE1 is the only node on the left hand side of the production
 * and there are n nodes on the right hand side of the production.<br>
 * In this program, n is >= 1, but has no upper constraint.
 * 
 * @author glpayson
 */
public class ContextFreeGrammar implements Grammar<CFGProductionRule>{
	
	private List<CFGProductionRule> cFGRules =
			new ArrayList<CFGProductionRule>();
	
	/**
	 * 
	 */
	public ContextFreeGrammar(){}
	
	/**
	 * @param corpus a Corpus containing Sentences from which this grammar will
	 * be extracted
	 * @throws IllegalArgumentException
	 */
	public ContextFreeGrammar(Corpus corpus) 
			throws IllegalArgumentException {
		
		// Make sure the corpus isn't empty
		if(!corpus.isEmpty()){
			this.cFGRules = extractCFGProductionRules(corpus);
		}else{
			throw new IllegalArgumentException(
					"Can't extract CFG from empty corpus.");
		}
	}
	
	// Extract CFG production rules from the corpus
	private List<CFGProductionRule> extractCFGProductionRules(
			Corpus corpus){
		
		// For each sentence in the corpus, extract the cfg rules from the 
		//  sentence's SyntaxTree and store them in an ArrayList of 
		//  CFGProductionRule's to be used to set this.cFGRules
		ArrayList<CFGProductionRule> cfg = new ArrayList<CFGProductionRule>();
		for(Sentence sent: corpus.getSentences()){
			extractCFGProductionRules(sent.getCFGSynTree().getRootNode(), cfg);	
		}
				
		return cfg;
	}
	
	// Extract CFG rules from a SyntaxTre via tree traversal
	// For a subtree: (A (B) (C)) we extract a CFG Rule A :- B, C
	//  where A is the "left hand side" of the production rule and B, C is the "right hand side" 
	//  of the production rule
	private void extractCFGProductionRules( CFGTreeNode parent,
									List<CFGProductionRule> cfg){
		
		// In Order, Depth First
		
		/*TODO: This is the third time I've coded this type of traversal in
		 *  this project. Should have a static class with closure instead?
		 */
		
		// Create an ArrayList containing parent's children. This will be 
		//  the right hand side of the new rule.
		ArrayList<CFGTreeNode> rh = new ArrayList<CFGTreeNode>();
		for(CFGTreeNode child: parent.getChildren()){
			rh.add(child);
		}
		
		// Would be empty only if parent is a leaf node
		if(!rh.isEmpty()){
			CFGProductionRule rule = new CFGProductionRule(parent, rh);
			
			// Add the rule if it doesn't already exist in the CFG
			if(!ruleExistsInCFG(rule, cfg)) cfg.add(rule);
		}
		
		// Recurse over children
		for(CFGTreeNode child: rh){
			extractCFGProductionRules(child, cfg);
		}
	}
	
	/* (non-Javadoc)
	 * @see syntaxParseTools.Grammar#getRules()
	 */
	public List<CFGProductionRule> getRules() {
		return cFGRules;
	}
	
	/* TODO this is much slower, but more straightforward than the way I'm
	 *       finding unique rules in the DepenedencyGrammar class */
	
	// Return true if rule1 exists already in this.cFGRules
	// We say a rule, rule1 already exists if for some rule2 in this.cFGRules
	//  - rule1 and rule2's left hand nodes have the same label
	//  - for each node in rule1's right hand nodes, 
	//      there is a node in rule2's right hand nodes that has the same label
	//  - for each node in rule2's right hand nodes,
	//      there is a node in rule1's right hand nodes that has the same label
	private boolean ruleExistsInCFG(CFGProductionRule rule1, 
									List<CFGProductionRule> cfgRules){
		for(CFGProductionRule rule2: cfgRules){
			if(rule1.equals(rule2)) return true;
		}
		return false;
	}
	
	/**
	 * Extract CFG rules from a sentence and add them to this
	 * @param sent  the Sentence from which to extract CFG rules
	 */
	public void addSentence(Sentence sent) {
		extractCFGProductionRules(sent.getCFGSynTree().getRootNode(), 
				this.cFGRules);
	}
}
