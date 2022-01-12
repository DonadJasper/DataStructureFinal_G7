import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;
	public GoogleQuery(String url,boolean boo){
		this.url =url;
	}

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";

	}


	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public ArrayList<String> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();
			System.out.print(content);
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		ArrayList<String> siteList = new ArrayList<>();
		
		Document doc = Jsoup.parse(content);
//		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT");
//		 System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 

			{
				String citeUrl = li.select("a").get(0).attr("href");
				citeUrl = citeUrl.substring(citeUrl.indexOf("?q=")+3);
				citeUrl = citeUrl.substring(0,citeUrl.indexOf("&sa"));
				siteList.add(citeUrl);
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}

			

		}

		
		
		return siteList;

	}
	public ArrayList<String> HyperList() throws IOException{
		if(content==null)

		{

			content= fetchContent();
		}
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("a");
		ArrayList<String>list = new ArrayList<String>();
		for(Element li : lis) {
			String url = li.attr("href");
			list.add(url);
			
			}
		return list;
	}
	

	

	

}