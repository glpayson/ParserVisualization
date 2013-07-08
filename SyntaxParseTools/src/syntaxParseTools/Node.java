package syntaxParseTools;
import java.util.List;

/**
 * Interface for nodes in a graph
 * 
 * @author glpayson
 *
 * @param <T>  a node type
 */
public interface Node<T> {

	public String getLabel();
	public List<T> getChildren();
	public List<T> getParents();
}
