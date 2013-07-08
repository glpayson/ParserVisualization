package syntaxParseTools;

import java.util.ArrayList;
import java.util.List;

/**
 * A node in a DGraph
 * 
 * @author glpayson
 */
public class DGNode implements Node<DGNode>, Comparable<DGNode>{

	private String label;
	private String sentenceUniqueLabel;		// Contains wc suffix
	private List<DGRule> outgoingEdges;
	private List<DGRule> incomingEdges;
	
	/**
	 * Construct a new DGNode with a given label and unique indexed label
	 * 
	 * @param label  this node's label
	 * @param unique  this node's unique indexed label
	 */
	public DGNode(String label, String unique){
		this.label = label;
		this.sentenceUniqueLabel = unique;
	}
	
	/* (non-Javadoc)
	 * @see syntaxParseTools.Node#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * Get all nodes which have an incoming edge from this node
	 * 
	 * @see syntaxParseTools.Node#getChildren()
	 */
	@Override
	public List<DGNode> getChildren() {
		List<DGNode> children = new ArrayList<DGNode>();
		for(DGRule edge : this.outgoingEdges){
			children.add(edge.getTarget());
		}
		return children;
	}
	
	/**
	 * Get all nodes which have an outgoing edge to this node
	 * 
	 * @see syntaxParseTools.Node#getParents()
	 */
	@Override
	public List<DGNode> getParents() {
		List<DGNode> parents = new ArrayList<DGNode>();
		for(DGRule edge : this.incomingEdges){
			parents.add(edge.getTarget());
		}
		return parents;
	}
	
	/**
	 * @param edge  a DGRule to be added to this
	 */
	public void addIncomingEdge(DGRule edge){
		this.incomingEdges.add(edge);
	}
	
	/**
	 * @param edge
	 */
	public void addOutgoingEdge(DGRule edge){
		this.outgoingEdges.add(edge);
	}
	
	/**
	 * @return a List of DGRules which have this as the target node
	 */
	public List<DGRule> getIncomingEdges() {
		return this.incomingEdges;
	}
	
	/**
	 * @return a List of DGRules which have this as the source node
	 */
	public List<DGRule> getOutgoingEdges() {
		return this.outgoingEdges;
	}

	/**
	 * @return this node's unique indexed label
	 */
	public String getSentenceUniqueLabel() {
		return this.sentenceUniqueLabel;
	}

	/**
	 * Compare based on label field and String's compareTo method.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DGNode that) {
		return this.label.compareTo(that.getLabel());
	}

}
