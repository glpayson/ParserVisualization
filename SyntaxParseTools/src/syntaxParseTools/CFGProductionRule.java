package syntaxParseTools;

import java.util.List;

// XXX equality methods need refactoring, see notes below.

/**
 * CFG production rules are of the form:<br>
 * NODE1 :- NODE2, ..., NODEn <br>
 * where NODE1 is the only node on the left hand side of the production
 * and there are n nodes on the right hand side of the production.<br>
 * In this program, n is >= 1, but has no upper constraint.
 * 
 * @author glpayson
 *
 */
public class CFGProductionRule implements GrammarRule<CFGProductionRule>{

	// For toString()
	private final static String PRODUCTION_ARROW = " :- ";		
	private final static String RHAND_SEPARATOR = ", ";		
	private String asString = "";
	
	private final CFGTreeNode parent;
	private final List<CFGTreeNode> children;
	
	/**
	 * The parent and child reunion is only a motion away...
	 * 
	 * @param parent  a CFGTreeNode with n children, where n >= 1
	 * @param children  a List of parent's child nodes
	 */
	public CFGProductionRule(CFGTreeNode parent, List<CFGTreeNode> children) {
		this.parent = parent;
		this.children = children;
	}
	
	/**
	 * @return the parent node in the rule
	 */
	public CFGTreeNode getParent() {
		return parent;
	}
	
	/**
	 * @return a List of the child nodes in the rule
	 */
	public List<CFGTreeNode> getChildren() {
		return children;
	}
	

	/**
	 * Converts a CFGProduction rule to a string of the form:<br>
	 * &ltLabel1&gtPRODUCTION_ARROW
	 * &ltLabel2&gtRHAND_SEPARATOR
	 * &ltLabel3&gtRHAND_SEPARATOR...
	 * &ltlabelN&gt
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		if(!this.asString.isEmpty()) return this.asString;
		
		StringBuilder prodSB = new StringBuilder();
		prodSB.append(parent.getLabel() + PRODUCTION_ARROW);
		
		// Don't want to put a separator before the first right hand label
		boolean isFirstChild = true;
		
		for(CFGTreeNode node: children){
			
			/* XXX Using a flag, not very elegant but I didn't want to do a lot 
			*      of string comparisons... */
			if(!isFirstChild){			
				prodSB.append(RHAND_SEPARATOR);
			}
			prodSB.append(node.getLabel());
			isFirstChild = false;
		}

		return prodSB.toString();
	}

	/**
	 * Compare to another CFGProductionRule based on equality of their label
	 * Strings
	 * 
	 * @param that  the rule to be compared with this
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CFGProductionRule that) throws ClassCastException{
		if(!(that instanceof CFGProductionRule)){
			throw new ClassCastException("CFGProductionRule object expected.");
		}
		return this.toString().compareTo(that.toString());
	}
	
	/* XXX see note in isChildrenLabelsEqual. All 4 of the following methods
	*      should be refactored */
	/**
	 * Two rules are equal is they have the same label for the left hand side 
	 * of the production, and the same label_s_ for the right hand side of the
	 * production
	 * 
	 * @param rule2  the rule to wich this is compared
	 * @return true if both have the same associated label Strings
	 */
	public boolean equals(CFGProductionRule rule2) {
		CFGProductionRule rule1 = this;
		if(isParentLabelsEqual(rule1, rule2) 
				&& isChildrenLabelsEqual(rule1, rule2)){ return true; }
		return false;
	}
	
	// Do all right hand side nodes in rule 1 exist in the right hand side of 
	//  rule 2 and vice versa.
	private boolean isChildrenLabelsEqual(CFGProductionRule rule1, 
			CFGProductionRule rule2) {
		
		/* XXX We're defining equality between nodes here as two nodes which 
		*      have the same String value in their label field...Therefore, 
		*      can't just use List.Equals()
		*      ...BUT, can and should override equals(Object o) in CFGTreeNode 
		*  	   class instead of all this mess...*/
		
		List<CFGTreeNode> rule1ChildNodes = rule1.getChildren();
		List<CFGTreeNode> rule2ChildNodes = rule2.getChildren();
		
		// Can't be equal is they have a differing number of nodes
		if(rule1ChildNodes.size() != rule2ChildNodes.size()) return false;
		
		// // Return false if there is a node in rule1's children nodes that  
		//  is not in rule2's children nodes
		for(CFGTreeNode rule1ChildNode: rule1ChildNodes){
			if(!nodeListHasNode(rule1ChildNode, rule2ChildNodes)) return false;
		}
		
		// Return false if there is a node in rule2's children nodes that  
		//  is not in rule1's children nodes
		for(CFGTreeNode rule2ChildNode: rule2ChildNodes){
			if(!nodeListHasNode(rule2ChildNode, rule1ChildNodes)) return false;
		}
		
		return true;
	}
	
	// Does nodeList contain a node with the same label as searchNode 
	private boolean nodeListHasNode(CFGTreeNode searchNode, 
			List<CFGTreeNode> rule2ChildNodes){
		
		for(CFGTreeNode listNode: rule2ChildNodes){
			if(searchNode.hasSameLabelAs(listNode)){
				return true;
			}
		}
		return false;
	}

	private boolean isParentLabelsEqual(CFGProductionRule rule1, 
			CFGProductionRule rule2){
		if(rule1.getParent().hasSameLabelAs(rule2.getParent())){
			return true;
		}
		return false;
	}

}
