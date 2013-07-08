package syntaxParseTools;

import java.util.List;

/**
 * The Sentence class represents a single sentence in a Corpus.
 * 
 * @author glpayson
 */
public class Sentence {

	private static final String EO_SENTENCE_MARKERS = ".!?";

	private final String cFGParseString;	// Input parse string
	private final String surfaceString;		// Leaf text in surface ordering
	private final CFGTree cFGSynTree;
	private final DGGraph dGGraph;
	private final DGGoogleGraph dGGoogleGraph;
	private final CFGGoogleGraph cFGGoogleGraph;
	
	// JUNG2 DelegateTree for _vis_ualization
	//private final JUNG2Tree visTree = null;	
	
	/**
	 * @param cFGString  the CFG parse String
	 * @param dgStrings  the List of DG rule Strings
	 * @throws IllegalArgumentException
	 */
	public Sentence(String cFGString, List<String> dgStrings) 
						throws IllegalArgumentException{
		this.cFGParseString = cFGString;
		this.cFGSynTree = new CFGTree(this.cFGParseString);
		this.surfaceString = 
				PODFTraversal.computeSurfaceString(this.cFGSynTree);
		this.dGGraph = new DGGraph(dgStrings);
		this.dGGoogleGraph = new DGGoogleGraph(this.dGGraph);
		this.cFGGoogleGraph = new CFGGoogleGraph(this.cFGSynTree);
		//this.visTree = new JUNG2Tree(this.synTree);	
	}

	/**
	 * @return the raw CFG parse string
	 */
	public String getCFGParseString() {
		return cFGParseString;
	}
	
	/**
	 * @return the CFGTree for this Sentence
	 */
	public CFGTree getCFGSynTree() {
		return cFGSynTree;
	}
	
	/**
	 * @return the surface String for this Sentence
	 */
	public String getSurfaceString() {
		return surfaceString;
	}
	
	/**
	 * @return the DGGraph for this sentence
	 */
	public DGGraph getdGGraph() {
		return dGGraph;
	}
	
	/** @return the CFGGoogleGraph for this sentence */
	public CFGGoogleGraph getcFGGoogleGraph() {
		return cFGGoogleGraph;
	}
	
	/** @return the DGGoogleGraph for this sentence */
	public DGGoogleGraph getdGGoogleGraph() {
		return dGGoogleGraph;
	}
	
	//public JUNG2Tree getVisTree() {
	//	return visTree;
	//}

	// Performs a Pre-Order Depth-First traversal for getting the surface 
	//  string.
	private static class PODFTraversal{
		
		private PODFTraversal(){}
		
		// Convenience function for calling getSurfaceOrderLeaves
		private static String computeSurfaceString(CFGTree sTree) {
			return getSurfaceOrderLeaves(sTree.getRootNode(), 
					new StringBuilder() ).toString();
		}
		
		// Recursive pre-order depth-first traversal of the SyntaxTree rooted in
		//   node and resulting in a StringBuilder containing the leaf text in 
		//   surface order.
		private static StringBuilder getSurfaceOrderLeaves(CFGTreeNode node, 
														StringBuilder leaves){
			
			// Get the node's leaf text (if any. Non-leaf nodes will return an
			//  empty String.)
			String leafText = node.getLeafText();
			
			// If this is a leaf node
			if(leafText != ""){
				// Add a space to the end of the string builder if this is not
				//  the first word in the sentence and this is not the 
				//  punctuation at the end of the sentence.
				if(leaves.length() != 0 
						&& !(EO_SENTENCE_MARKERS.contains(leafText))){
					leaves.append(" ");
				}
				leaves.append(leafText);
			}
			
			// Recurse on children
			for(CFGTreeNode child : node.getChildren()){
				leaves = getSurfaceOrderLeaves(child, leaves);
			}
			
			return leaves;
		}
	}
}

