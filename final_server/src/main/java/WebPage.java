
import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url,String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
	}
	
	public void setScore(Keyword k) throws IOException{
		score = 0;
//		3.calculate score
		
			score+=this.counter.countKeyword(k.name)*k.weight;
		
	}
	
}
