package syntaxParseTools;

/* XXX This particular chart type, GraphViz digraphs 
 *  ("cht=gv&chl=digraph") is deprecated by Google */

/**
 * From a DGTree, constructs a URL for use in Google Charts<br>
 * NOTE: This particular chart type, GraphViz digraphs is deprecated by Google
 * 
 * @author glpayson
 */
public class DGGoogleGraph extends GoogleGraph<DGGraph>
	implements GoogleGraphInterface<DGGraph>{

	// Some strings for use in the URL
	private final static String URL_PREFIX = "http://chart.googleapis.com/chart?"+
			"cht=gv&chl=digraph%7B";
	private final static String EDGE_LABEL_PREFIX = "[label=";
	private final static String EDGE_LABEL_SUFFIX = "]";
	
	// Arrow for directed edges in URL
	private final static String ARROW = "-%3E";
	
	// Google chart URL
	private final String url;
	
	/**
	 * Create a graph with specified height and width<br>
	 * NOTE: h*w cannot exceed 300000 pixels
	 * 
	 * @param t  the DGGraph used to construct the URL
	 * @param h  height in pixels
	 * @param w  width in pixels
	 * @throws IllegalArgumentException if h*w > 300000
	 */
	public DGGoogleGraph(DGGraph t, int h, int w) throws IllegalArgumentException{
		if(h*w > MAX_PIXELS){
			throw new IllegalArgumentException("h*w of graph cannot exceed " +
					"300000 pixels");
		}
		url = toURL(t,h,w);
	}

	/**
	 * Create a graph with default height and width
	 * 
	 * @param t  the DGGraph used to construct the URL
	 */
	public DGGoogleGraph(DGGraph t){
		url = toURL(t);
	}
	
	// Convert a DGGraph to a URL with specified height and width
	private String toURL(DGGraph t, int h, int w) {
		StringBuilder sb = new StringBuilder(toURL(t));
		sb.append(URL_DIM_SUFIX + h + "x" + w);
		return sb.toString();
	}

	// Convert a DGGraph to a URL with default height and width
	// NOTE for this chart type, edge labels are specified in the URL 
	//  as [label=<EDGELABEL>]
	private String toURL(DGGraph t) {
		StringBuilder sb = new StringBuilder(URL_PREFIX);
		
		for(DGRule rule : t.getRules()){
			
			// XXX This seems like a major bottleneck. Should benchmark it.
			String edgeLabel = formatLabel(rule.getEdgeLabel());
			String sourceLabel = formatLabel(rule.getSourceLabel());
			String targetLabel = formatLabel(rule.getTargetLabel());
			
			// Add node labels to URL
			sb.append(sourceLabel + ARROW + targetLabel);
			
			// Add edge label to URL
			sb.append(EDGE_LABEL_PREFIX + edgeLabel + EDGE_LABEL_SUFFIX + ",");
		}
		sb.append(URL_SUFIX);
		return sb.toString();
	}

	// Display the graph in a new tab of the browser specified by Desktop
	@Override
	public void displayGraph() {
		super.displayGraph(this.url);
	}
	
	/** @return this graph's URL */
	public String getUrl() {
		return url;
	}

}
