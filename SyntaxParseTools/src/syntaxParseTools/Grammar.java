package syntaxParseTools;

import java.util.List;

/**
 * An interface for classes with some type T that implements the 
 * GrammarRule interface<br>
 * i.e. classes that contain a list of some type implementing GrammarRule
 * 
 * @author glpayson
 *
 * @param <T>
 */
public interface Grammar<T extends GrammarRule<?>> {

	public List<T> getRules();
}
