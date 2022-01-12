
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main2 {
	public ArrayList<WebTree> queryList = new ArrayList<>();
	public String keyword;
	public ArrayList<String>urlist = new ArrayList<>();
	public Main2(String keyword) {
		this.keyword = keyword;
	}
	public void start() throws IOException {
		System.out.print("gggjjjj");
		// TODO Auto-generated method stub
		// root node
//		WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "Soslab");		
//		WebTree tree = new WebTree(rootPage);
		// build childnode
//		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html","Publication")));
//		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html","Projects")));
//		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://vlab.cs.ucsb.edu/stranger/", "Stranger")));
//		tree.root.addChild(ne
		// WebNode(new WebPage("http://soslab.nccu.edu.tw/Members.html", "MEMBER")));
//		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm","Course")));
//		//read 2 Yu 1.2 Fang 1.8 
		
		Keyword d = new Keyword("端火鍋", 5.0);
		//Keyword d = new Keyword("端火鍋".getBytes("GBK"),"UTF-8");		
		Keyword t = new Keyword("çµ±ç¥ž", 5.0);
		Keyword e = new Keyword("ä¸€ä»£ä¸€ä»£", 4.5);
		Keyword m = new Keyword("å¢¨è¥¿å“¥ç²½", 4.0);
		Keyword tuan = new Keyword("è¾›é…¸ç•«é�¢", 4.0);
		Keyword h = new Keyword("æ»‘å€’", 3.5);
		Keyword meme = new Keyword("è¿·å› ", 3.0);
		Keyword gento = new Keyword("æ¢—åœ–", 3.0);
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		this.queryList = new ArrayList<>();
		Keyword k = new Keyword(keyword, 5.0);// store key
		keywords.add(k);
		GoogleQuery g = new GoogleQuery(keyword);
		ArrayList<String> querysearch = g.query();
		for (String aa : querysearch) {
			
			int defaultscore = 200 - 20 * querysearch.indexOf(aa);
			WebPage rootPage = new WebPage(aa, "query result");
			WebTree tree = new WebTree(rootPage, defaultscore);
			ArrayList<WebNode> bfsList = new ArrayList<WebNode>();
			ArrayList<WebNode> nextLayer = new ArrayList<>();
			bfsList.add(tree.root);
			System.out.println("Building Web Tree..");
			for (int i = 0; i < 1; i++) {
				System.out.println(i);
				for (WebNode node : bfsList) {
					try {
						node.buildTree();
						for (WebNode child : node.children) {
							nextLayer.add(child);

						}
					} catch (IOException exception) {
						// System.out.print("ff");
					}
				}

				bfsList = nextLayer;
				nextLayer = new ArrayList<>();
			}
			System.out.println("Calculating Score");
			try {
				tree.setPostOrderScore(k);
				tree.setPostOrderScore(d);
				tree.setPostOrderScore(t);
				tree.setPostOrderScore(e);
				tree.setPostOrderScore(m);
				tree.setPostOrderScore(meme);
				tree.setPostOrderScore(h);
				tree.setPostOrderScore(gento);
				tree.setPostOrderScore(tuan);

			} catch (IOException exception) {

			}
			queryList.add(tree);

		}
		Collections.sort(queryList);
		for (WebTree treee : queryList) {
			System.out.println(treee.root.webPage.url);
			urlist.add(treee.root.webPage.url);
		}

	}
}
