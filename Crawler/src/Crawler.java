import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {




	public static void main(String[] args) {

		String startURL = "http://ciir.cs.umass.edu"; 
		Queue<String> frontier = new LinkedList<String>(); // links in line for crawling
		List<String> urls = new LinkedList<String>();// list of urls
		List<Document> downloadedPages = new LinkedList<Document>();// list of downloaded pages/ explored links
		boolean restricted = true;// restiction for only cs.umass.edu urls
        int crawlSize = 100; //number of urls to gather 100 or 1000
        
        
		frontier.add(startURL);
     

		while(!frontier.isEmpty() && urls.size()<crawlSize){
			//System.out.println(urls.size()+" "+downloadedPages.size());
		
			Document doc;
			// 10 second delay
					try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				String url = frontier.remove();
			//	if(seen.contains(url)){continue;}

			//	seen.add(url);
				doc = Jsoup.connect(url).get();
				downloadedPages.add(doc);

				// Robot.txt check here
				URL robot = new URL(url+"/robots.txt");
				List<String> disallows = new LinkedList<String>(); // list of disallowed paths
				try{
					BufferedReader in = new BufferedReader(new InputStreamReader(robot.openStream()));
					String line = null;
					while((line = in.readLine()) != null) {
						if(line.contains("Disallow: ")){
							disallows.add(line.substring(10));
						}
					}
					//	System.out.println(disallows.toString());
				}catch (IOException f) {
					//f.printStackTrace();
				}



				boolean disallowed = false;
				//parses web pages links
				Elements linksFromPage = doc.select("a");
				for (Element link : linksFromPage){
					String absHref = link.attr("abs:href");
					//System.out.println(absHref);
					for(String disallow : disallows){
						if(absHref.contains(disallow)){
							disallowed = true;
						}
					}
					if ( disallowed == true){continue;}
					if(restricted){
						if(absHref.contains("cs.umass.edu")){
							if(urls.contains(absHref)){
								//don't add it
							}else{
								frontier.add(absHref);
								urls.add(absHref);
							}
						}
					}else{
						if(urls.contains(absHref)){
							//don't add it
						}else{
							frontier.add(absHref);
							urls.add(absHref);
						}
					}
				}
			} catch (IOException e) {
			//	e.printStackTrace();
			}

		}
		/*int count = 0;
		for(String url : urls){
			count++;
			System.out.println(count+": "+url);

		}
		System.out.println(downloadedPages.size());*/
	}

}

