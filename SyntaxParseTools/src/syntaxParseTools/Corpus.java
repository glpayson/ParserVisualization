package syntaxParseTools;
import java.util.ArrayList;
import java.util.List;

/**
 * A corpus object contains a list of Sentences and the grammars derived from
 * those Sentences.
 * 
 * @author glpayson
 *
 */
public class Corpus {

	/* XXX I'd like for this class to immutable. Possibly using a builder?
	 *     The addSentence method is what is currently preventing the class
	 *     from being immutable. */
	
	private List<Sentence> sentences = new ArrayList<Sentence>();
	private ContextFreeGrammar cfg;
	private DependencyGrammar dg;
	
	/**
	 * 
	 */
	public Corpus(){
		cfg = new ContextFreeGrammar();
		dg = new DependencyGrammar();									
	}
	
	/* XXX This, in turn, is preventing the the DependencyGrammar 
	 * and ContextFreeGrammar classes from being immutable */
	/**
	 * @param sent  is added to the Corpus
	 */
	public void addSentence(Sentence sent){
		sentences.add(sent);
		cfg.addSentence(sent);
		dg.addSentence(sent);
	}
	
	/**
	 * @return the List of Sentences
	 */
	public List<Sentence> getSentences() {
		return sentences;
	}
	
	/**
	 * @return the CFG for this Corpus
	 */
	public ContextFreeGrammar getContextFreeGrammar() {
		return cfg;
	}
	
	/**
	 * @return the DG for this Corpus
	 */
	public DependencyGrammar getDependencyGrammmar() {
		return dg;
	}
	
	
	/**
	 * @return true if there are no Sentences in the Corpus
	 */
	public boolean isEmpty(){
		if(sentences.isEmpty()) return true;
		return false;
	}
}
