package syntaxParseTools;
//import java.awt.Dimension;
//import java.awt.Paint;
//import java.awt.Color;
//import java.awt.Shape;
//import java.awt.geom.RoundRectangle2D;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//import org.apache.commons.collections15.Transformer;
//
//import edu.uci.ics.jung.algorithms.layout.TreeLayout;
//import edu.uci.ics.jung.graph.DelegateTree;
//import edu.uci.ics.jung.visualization.VisualizationImageServer;
//import edu.uci.ics.jung.visualization.decorators.EdgeShape;
//import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
//import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

// Was used to display a JUNG2 DelegateTree.
/**
 * @deprecated
 * @author glpayson
 *
 */
@Deprecated
public class JUNG2Tree {
//
//	private DelegateTree<String, Integer> delTree;
//	
//	public JUNG2Tree(SyntaxTree synTree) {
//
//		delTree = SyntaxTreeToDelegateTreeConverter.convert(synTree);
//	}
//	
//	public void display(){
//
//	    VisualizationImageServer<String, Integer> vv =
//	      new VisualizationImageServer<String, Integer>(
//	        new TreeLayout<String, Integer>(delTree), new Dimension(600, 600));
//	    
//	    vv.getRenderContext().setEdgeShapeTransformer(
//	    		new EdgeShape.Line<String,Integer>());
//	    
//	    Transformer<String,Paint> vertexPaint = 
//	    		new Transformer<String,Paint>() {
//	    	public Paint transform(String s) {
//	    		return Color.WHITE;
//	    	}
//	    };
//	    
//	    Transformer<String,Shape> vertexSize 
//	    	= new Transformer<String,Shape>(){
//            public Shape transform(String s){
//                RoundRectangle2D rr = new RoundRectangle2D.Double(-25, -15, 50, 20, 5, 5);
//                // in this case, the vertex is twice as large
//                //if(s.equals("PRP")) return AffineTransform.getScaleInstance(1.1, 1.1).createTransformedShape(rr);
//                //else return rr;
//                return rr;
//            }
//        };
//	    
//	    vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
//	    vv.getRenderContext().setVertexLabelTransformer(
//	    		new ToStringLabeller<String>());
//	    vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
//	    vv.getRenderContext().setVertexShapeTransformer(vertexSize);
//	    	
//	    JFrame frame = new JFrame();
//	    frame.getContentPane().add(vv);
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.pack();
//	    frame.setVisible(true);
//	    
//	}
//	
//	private static class SyntaxTreeToDelegateTreeConverter{
//		
//		private SyntaxTreeToDelegateTreeConverter(){}
//		
//		private static DelegateTree<String, Integer> convert(SyntaxTree synTree){
//			
//			DelegateTree<String, Integer> delTree = 
//					new DelegateTree<String, Integer>();
//			
//			ArrayList<SyntaxTreeNode> flatArray = synTree.toArray();
//			
//			boolean rootSet = false;
//			int i = 0;
//			for(SyntaxTreeNode sTNode : flatArray){
//				if(!rootSet){
//					delTree.setRoot(sTNode.getSentenceUniqueLabel());
//					rootSet = true;
//					continue;
//				}
//				delTree.addChild(i, sTNode.getParent().getSentenceUniqueLabel(), 
//						sTNode.getSentenceUniqueLabel());
//				++i;
//			}
//			return delTree;
//		}
//	}

}
