package syntaxParseTools;

/**
 * A DGRule is a representation of the semantic relationship between two words,
 * in which the relationship is specified by the edge label of a directed 
 * edge connecting two nodes which represent the two words.
 * 
 * @author glpayson
 */
public class DGRule implements GrammarRule<DGRule>{

	private final String edgeLabel;
	private final DGNode source;
	private final DGNode target;
	
	/**
	 * Construct a DGRule with a given source and target node and a
	 * given label 
	 * 
	 * @param source  the source DGNode
	 * @param target  the target DGNoe
	 * @param edgeLabel  the DGRule's relationship label
	 */
	public DGRule(DGNode source, DGNode target, String edgeLabel) {
		this.source = source;
		this.target = target;
		this.edgeLabel = edgeLabel;
	}
	
	/** @return this rule's edge label*/
	public String getEdgeLabel() 	{ return this.edgeLabel; }
	/** @return this rule's source node*/
	public DGNode getSource() 		{ return this.source; }
	/** @return this rule's source node's label*/
	public String getSourceLabel() 	{ return this.source.getLabel(); }
	/** @return this rule's source node's unique indexed label*/
	public String getSourceUniqueLabel() { 
		return this.source.getSentenceUniqueLabel(); 
	}
	/** @return this rule's target node*/
	public DGNode getTarget() 		{ return this.target; }
	/** @return this rule's target node label*/
	public String getTargetLabel() 	{ return this.target.getLabel(); }
	/** @return this rule's target node's unique indexed label*/
	public String getTargetUniqueLabel() { 
		return this.target.getSentenceUniqueLabel(); 
	}
	
	/**
	 * Two DGRules are equal if they have the same edge, target, and source
	 * labels.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof DGRule)) return false;
		DGRule that = (DGRule) obj;
		
		if(!(that.getEdgeLabel().equals(this.edgeLabel))) return false;
		
		if(!(that.getSource().getLabel().equals(this.source.getLabel()))){
			return false;
		}
		
		if(!(that.getTarget().getLabel().equals(this.target.getLabel()))){
			return false;
		}
		return true;			
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DGRule that) throws ClassCastException {
		if(!(that instanceof DGRule)){
			throw new ClassCastException("DependencyGrammarRule expected");
		}
		return this.toString().compareTo(that.toString());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.edgeLabel + "(" + this.source.getLabel() + ", " 
				+ this.target.getLabel() + ")";
	}
	
	/**
	 * @return string with wc suffixes on the node labels
	 */
	public String toUniqueString(){
		return this.edgeLabel + "(" + this.source.getSentenceUniqueLabel()
				+ ", " + this.target.getSentenceUniqueLabel() + ")";
	}

}
