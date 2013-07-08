package syntaxParseTools;


//XXX This particular chart type, GraphViz graphs ("cht=gv&chl=graph") is deprecated by Google

/**
 * From a CFGTree, constructs a URL for use in Google Charts
 * <P>
 * NOTE: This particular chart type, GraphViz graphs is deprecated by Google
 * 
 * @author glpayson
 */
public class CFGGoogleGraph  extends GoogleGraph<CFGTree>
	implements GoogleGraphInterface<CFGTree>{
	
	// Some strings for use in the URL
	private final static String URL_PREFIX = "http://chart.googleapis.com/chart?"+
			"cht=gv&chl=graph%7B";
	
	// Arrow for undirected edges in URL
	private final static String ARROW = "--";
	
	// Google chart URL
	private final String url;
	
	/**
	 * Create a graph with specified height and width from a CFGTree.
	 * <br>
	 * NOTE: h*w cannot exceed 300000 pixels
	 * 
	 * @param t  the CFGTree
	 * @param h  height of the graph in pixels
	 * @param w  width of the graph in pixels
	 * @throws IllegalArgumentException if h*w > 300000
	 */
	public CFGGoogleGraph(CFGTree t, int h, int w) throws IllegalArgumentException{
		if(h*w > MAX_PIXELS){
			throw new IllegalArgumentException("h*w of graph cannot " +
					"exceed 300000 pixels");
		}
		this.url = toURL(t,h,w);
	}
	
	/**
	 * Create a graph with default height and width from a CFGTree
	 * 
	 * @param t  the CFGTree
	 */
	public CFGGoogleGraph(CFGTree t){
		this.url = toURL(t);
	}
	
	// Convert a SyntaxTree object to a URL usable in Google Charts
	private String toURL(CFGTree t, int h, int w) {
		StringBuilder sb = new StringBuilder(toURL(t));
		sb.append(URL_DIM_SUFIX + h + "x" + w);
		return sb.toString();
	}
	
	// ...with default sizes
	private String toURL(CFGTree t){
		
		StringBuilder sb = new StringBuilder(URL_PREFIX);
		StringBuilder graphString = new StringBuilder();
		
		graphString = traverse(t.getRootNode(), graphString);
		sb.append(graphString);
		sb.append(URL_SUFIX);
		return sb.toString();
	}

	// Traverse the tree structure and build the URL string
	private StringBuilder traverse(CFGTreeNode parent, 
									StringBuilder graphString) {
		
		for(CFGTreeNode child: parent.getChildren() ){
			
			// Replace characters that can't be in URL
			String parentLabel = super.formatLabel(parent.getSentenceUniqueLabel());
			String childLabel = super.formatLabel(child.getSentenceUniqueLabel());
			
			// Add parent to child edge to the URL string
			graphString.append(parentLabel);
			graphString.append(ARROW);
			graphString.append(childLabel + ",");
			
			// Recurse on child
			graphString = traverse(child, graphString);
			
		}	
		return graphString;
	}

	/* (non-Javadoc)
	 * Display the graph in a new tab of the browser specified by Desktop
	 * @see syntaxParseTools.GoogleGraphInterface#displayGraph()
	 */
	@Override
	public void displayGraph() {
		super.displayGraph(this.url);
	}
	
	/** @return this graph's URL */
	public String getUrl() {
		return url;
	}
}