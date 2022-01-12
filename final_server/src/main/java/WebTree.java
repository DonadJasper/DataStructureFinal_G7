
import java.io.IOException;
import java.util.ArrayList;


public class WebTree implements Comparable<WebTree>{
	public WebNode root;
	public int compareTo(WebTree tree) {
		if((this.root.nodeScore - tree.root.nodeScore)<0) {
			return -1;
		}else if((this.root.nodeScore - tree.root.nodeScore)==0){
			return 0;
			}
		else return 1;
	}
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	public WebTree(WebPage rootPage,int defaultscore){
		this.root = new WebNode(rootPage);
		this.root.nodeScore = defaultscore;
	}
	
	public void setPostOrderScore(Keyword k) throws IOException{
		setPostOrderScore(root, k, 0);
	}
	
	private void setPostOrderScore(WebNode startNode, Keyword k, int layer) throws IOException{
		//1.compute the score of children nodes postorder
		for(WebNode child : startNode.children){
			setPostOrderScore(child,k,layer+1);
		}
		int weight = 40;
		for(int i=0;i<layer;i++) {
			weight*=0.8;
		}
		//**setNode score of startNode
		startNode.setNodeScore(k,weight);
		}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		//print "("
		System.out.print("(");
		//print "name","score"
		System.out.print(startNode.webPage.name+","+startNode.nodeScore);
		
		//2.print child preorder
		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		
		//print ")"
		System.out.print(")");
		
		/*for example
		(Soslab,459.0
				(Publication,286.2)
				(Projects,42.0
						(Stranger,0.0)
				)
				(MEMBER,12.0)
				(Course,5.3999999999999995)
		)
		*/
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}
}
