package syntaxParseTools;
import java.util.ArrayList;
import java.util.List;


/**
 * A node in a CFGTree. Has a label, an indexed unique label, leaf text 
 * (iff it is a leaf node in the tree), 1 or 0 parents, and 0 or more 
 * children.
 * 
 * @author glpayson
 *
 */
public class CFGTreeNode implements Node<CFGTreeNode>{

	private final String label;
	private final String sentenceUniqueLabel;
	private final String leafText;
	private final CFGTreeNode parent;
	private ArrayList<CFGTreeNode> children = new ArrayList<CFGTreeNode>();
	
	
	/**
	 * Convenience constructor which sets the node's indexed 
	 * unique label to its label. <br>Used if assigning unique labels to nodes
	 * is unnecessary.
	 * 
	 * @param label  the nodes label and unique label
	 * @param parent  the nodes parent node
	 */
	public CFGTreeNode(String label, CFGTreeNode parent){
		this.label = label;
		this.sentenceUniqueLabel = this.label;
		this.parent = parent;
		this.leafText = "";
	}
	
	/**
	 * @param label  the nodes label as it appeared in the parse String
	 * @param sentenceUniqueLabel  an indexed unique label
	 * @param parent  the node's parent node
	 */
	public CFGTreeNode(String label, String sentenceUniqueLabel, 
							CFGTreeNode parent){
		this.label = label;
		this.sentenceUniqueLabel = sentenceUniqueLabel;
		this.parent = parent;
		this.leafText = "";
	}
	
	/**
	 * @param label  the nodes label as it appeared in the parse String
	 * @param sentenceUniqueLabel  an indexed unique label
	 * @param leafText  leaf text as it appeared in the parse String
	 * @param parent  the node's parent node
	 */
	public CFGTreeNode(String label, String sentenceUniqueLabel, 
							String leafText, CFGTreeNode parent){
		this.label = label;
		this.sentenceUniqueLabel = sentenceUniqueLabel;
		this.parent = parent;
		this.leafText = leafText;
	}
	
	/**
	 * NOTE: Returns an empty String if this is not a leaf node. Clients of
	 * this method should perform appropriate checks to the returned value.
	 * 
	 * @return this node's leaf text or an empty String
	 */
	public String getLeafText() {
		return leafText;
	}
	
	/**
	 * @param child  the node to be added to this node's children
	 */
	public void addChild(CFGTreeNode child){
		this.children.add(child);
	}
	
	/* (non-Javadoc)
	 * @see syntaxParseTools.Node#getChildren()
	 */
	public ArrayList<CFGTreeNode> getChildren() {
		return children;
	}
	
	/**
	 * @return this node's parent
	 */
	public CFGTreeNode getParent() {
		return parent;
	}
	
	/**
	 * NOTE: CFGTreeNodes have one or zero parents, so this will always
	 * return a List of size 0 or 1.
	 * @see syntaxParseTools.Node#getParents()
	 */
	@Override
	public List<CFGTreeNode> getParents() {
		List<CFGTreeNode> parents = new ArrayList<CFGTreeNode>();
		parents.add(parent);
		return parents;
	}
	
	/* (non-Javadoc)
	 * @see syntaxParseTools.Node#getLabel()
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * @return this node's indexed unique label
	 */
	public String getSentenceUniqueLabel() {
		return sentenceUniqueLabel;
	}
	
	/**
	 * @return true if this is a leaf node 
	 * (i.e. if it has a non-empty leafText field)
	 */
	public boolean isLeaf(){
		if(this.leafText.isEmpty()) return false;
		return true;
	}
	
	// XXX should <st>maybe</st> move this to an overriden equality method
	/**
	 * @param otherNode  the node to compare to this
	 * @return true if this node and otherNode have the same label string
	 */
	public boolean hasSameLabelAs(CFGTreeNode otherNode){
		if(this.label.equals(otherNode.getLabel())) return true;
		return false;
	}

	// XXX ...but we're defining different levels of equality...
	/**
	 * A wise pig once said, SOME OBJECTS ARE MORE EQUAL THAN OTHERS
	 * 
	 * @param otherNode  the node to compare to this
	 * @return true if this node and otherNode have the same unique indexed 
	 * label
	 */
	public boolean hasSameSentenceUniqueLabelAs(CFGTreeNode otherNode){
		if(this.sentenceUniqueLabel.equals(otherNode.getSentenceUniqueLabel())){
			return true;
		}
		return false;
	}

}
