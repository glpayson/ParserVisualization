package syntaxParseTools;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Abstract superclass for subclasses which take some sort of graph structure
 * and create a URL for use in Google Charts.
 * 
 * @author glpayson
 *
 * @param <T>
 */
public abstract class GoogleGraph<T> implements GoogleGraphInterface<T>{
	
	/**
	 *  String that will need to be in the URL constructed by all
	 *  classes extending this
	 */
	final static String URL_SUFIX = "%7D";
	
	/**
	 *  String that will need to be in the URL constructed by all
	 *  classes extending this
	 */
	final static String URL_DIM_SUFIX = "&chs=";
	
	/**
	 * Google Charts doesn't allow charts st h*w > 300000 pixels
	 */
	final static int MAX_PIXELS = 300000;
	
	/**
	 * Replace chars that would cause problems in the URL
	 * 
	 * @param lbl  the String on which to replace chars
	 * @return the lbl String with chars replaced
	 */
	public static String formatLabel(String lbl){
		
		// XXX There is be a better way to do this...
		lbl = lbl.replaceAll(",", "COMMA");
		lbl = lbl.replaceAll("\\$", "DOL");
		lbl = lbl.replaceAll("\\?", "QMARK");
		lbl = lbl.replaceAll("\\!", "EXCMARK");
		lbl = lbl.replaceAll("'", "");
		
		return lbl;
	}
	
	/**
	 * Display the graph in a new tab of the browser specified by Desktop
	 * 
	 * @param url
	 */
	public void displayGraph(String url){
		try {
			System.out.println(url);
			Desktop.getDesktop().browse(new java.net.URI(url));
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
