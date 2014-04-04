/**
 * 
 */
package webcrawler;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author xuke
 *
 */
public class IRCrawler extends WebCrawler{
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
			+ "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|pdf" 
			+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * You should implement this function to specify whether
	 * the given url should be crawled or not (based on your
	 * crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.contains(".ics.uci.edu");
	}

	/**
	 * This function is called when a page is fetched and ready 
	 * to be processed by your program.
	 */
	@Override
	public void visit(Page page) {          
		int docid = page.getWebURL().getDocid();
		String id=Integer.toString(docid);
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

		try{
			WriteIntoFile.WritePage(id, "Docid: "+id);
			WriteIntoFile.WritePage(id, "URL: "+url);
			WriteIntoFile.WritePage(id, "Domain "+domain);
			WriteIntoFile.WritePage(id, "Sub-domain "+subDomain);
			WriteIntoFile.WriteURL("URL: "+url+" Domain "+domain+" Sub-domain "+subDomain);
			WriteIntoFile.WritePage(id, "Path: "+path);
			WriteIntoFile.WritePage(id, "Parent page: "+parentUrl);
			WriteIntoFile.WritePage(id, "Anchor text: "+anchor);
		}catch(IOException e1){
			System.out.println("writing file error!");
			e1.printStackTrace();
		}

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			try{
				WriteIntoFile.WritePage(id, "Text length: "+text.length());
				WriteIntoFile.WritePage(id, "Html length: "+html.length());
				WriteIntoFile.WritePage(id, "Number of outgoing links: "+links.size()+"\n");
				WriteIntoFile.WritePage(id, "Text: \n");				
				WriteIntoFile.WritePage(id, text);				
			}catch(IOException e2){
				System.out.println("writing file error!");
				e2.printStackTrace();
			}
		}
	}
}
