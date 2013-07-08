package syntaxParseTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO Maybe CFGTree and DGGraph should have a supertype or shared interface? 


/**
 * Represents a syntax tree corresponding a Sentence's cfg parse.
 * 
 * @author glpayson
 */
public class CFGTree {

	// Stanford parser uses this as the label for the root node of all parses
	private static final String EXPECTED_ROOT_LABEL = "ROOT";
	
	private CFGTreeNode rootNode;			// Root node of the tree
	private String parseString = "";		// Stanford parser's output
	
	// An array of nodes in the tree in  pre-order depth-first order
	private ArrayList<CFGTreeNode> flatArray = null;
	
	/**
	 * Construct a CFGTree from a parse String
	 * 
	 * @param s  the parse String
	 * @throws IllegalArgumentException
	 */
	public CFGTree(String s) throws IllegalArgumentException{
		this.parseString = s;
		this.rootNode = stringToSyntaxTreeParser.parse(this.parseString);
	}
	
	/** @return the parse String from which this was constructed */
	public String getParseString(){
		return parseString;
	}
	
	/** @return the parse String from which this was constructed */
	public String toString(){
		return getParseString();
	}
	
	/** @return the root node for this tree */
	public CFGTreeNode getRootNode(){
		return this.rootNode;
	}
	
	/**
	 * Convert tree to flat array in pre-order depth-first order
	 * 
	 * @return a List of nodes in the aforementioned order
	 */
	public List<CFGTreeNode> toList(){
		
		this.flatArray = new ArrayList<CFGTreeNode>();
		return preOrderDepthFirstTraversal(this.rootNode, this.flatArray);

	}
	
	// Convert tree to flat array in pre-order depth-first order
	private ArrayList<CFGTreeNode> preOrderDepthFirstTraversal(
			CFGTreeNode node, ArrayList<CFGTreeNode> arr){
		
		arr.add(node);
		for(CFGTreeNode child : node.getChildren()){
			preOrderDepthFirstTraversal(child, arr);
		}
		return arr;
	}

	// Static class for converting from parse string to SyntaxTree
	private static class stringToSyntaxTreeParser {

		// For splitting the parse string into an array of Strings
		private static final String rex = 
				"(\\([^\\(\\)\\s]*|[^\\(\\)\\s]+|\\))";
		
		private stringToSyntaxTreeParser(){}
		
		/**
		 * Parse the Stanford parser string into a tree structure and return
		 * the root node
		 * 
		 * @param s  the parse String
		 * @return the root node of the newly created tree
		 * @throws IllegalArgumentException
		 */
		public static CFGTreeNode parse(String s) 
				throws IllegalArgumentException{
			
		    CFGTreeNode curNode = null;
		    
		    // Split the parse string into an ArrayList using the regex, rex
		    ArrayList<String> parseArray = parseStringToArray(s);
		    
		    // Count labels so that all nodes will be given a unique label
		    //  e.g. NP1, NP2, ... NPn
		    HashMap<String, Integer> labelCount 
		    	= new HashMap<String, Integer>();
		    
		    for(String p: parseArray){
		    	
		    	if(p.startsWith("(")){	// if this is the start of a new node
		    		
		    		StringBuilder lbl = new StringBuilder(extractNodeLabel(p));
		    		
		    		// Append a count number to the end of lbl so it is unique 
		    		//  for this sentence
		    		String uniqLbl = lbl 
		    				+ getUniqueLabelNum(lbl.toString(),labelCount);
		    		
		    		// Create new node
		    		curNode = new CFGTreeNode(lbl.toString(), 
		    				uniqLbl.toString(), curNode);
		    		
		    		// Add this node to the children of this node's parent
		    		if(curNode.getParent() != null){
		    			curNode.getParent().addChild(curNode);
		    		}
		    		
		    	}else if(p.equals(")")){ // else if its the end of a 
		    							 // previous node
		    		
		    		// Move back up one level in the tree
		    		curNode = (curNode.getParent() != null) 
		    				? curNode.getParent() 
		    				: curNode;
		    		
		    	}else{						// else its leaf text
		    		String uniqLbl = p + getUniqueLabelNum(p,labelCount);
		    		
		    		// Create new node
		    		curNode = new CFGTreeNode(p, uniqLbl, p, curNode);
		    		
		    		// Add curNode as a child of curNode's parent
		    		curNode.getParent().addChild(curNode);
		    		
		    		// Go back up a level in the tree, if possible
		    		curNode = (curNode.getParent() != null) ? curNode.getParent() 
		    				: curNode;
		    	}
		    }
		    
		    // Make sure we're back at the root node
		    if(!curNode.getLabel().startsWith(EXPECTED_ROOT_LABEL)){
		    	throw new IllegalArgumentException("Bad parse or invalid " +
		    			"root node");
		    }
		    
		    return curNode;		// ...which is the root node at this point	
		}
		
		// From the string of form "(NODELABEL" return NODELABEL
		private static String extractNodeLabel(String s){
			return s.substring(1);
		}
		
		// Split the parse string into an ArrayList using a regex
		//  All elements of returned ArrayList will be strings of one of 
		//  following forms:
		//	*	"(<NODELABEL>"	- Indicates the start of a new node with label
		//						  <NODELABEL>
		//	*	"<LEAFTEXT>"	- Leaf text for the containing node
		//	*	")"				- End of a node
		private static ArrayList<String> parseStringToArray(String s){
			
			Pattern pattern = Pattern.compile(rex);
			Matcher matcher = pattern.matcher(s);
			
			ArrayList<String> parseArray = new ArrayList<String>();
			
		    while (matcher.find()) {
		        parseArray.add(matcher.group());
		    }   
		    return parseArray;
		}
		
		// Get count of how many nodes have had the same label as lbl
		private static String getUniqueLabelNum(String lbl, HashMap<String,
												Integer> labelCount){
   	
    		if(labelCount.containsKey(lbl)){	// Seen before
    			labelCount.put(lbl, labelCount.get(lbl) + 1);
    			return Integer.toString(labelCount.get(lbl));
    		}else{								// Not seen before
    			labelCount.put(lbl, 1);
    			return "1";
    		}
		}
	}

}
