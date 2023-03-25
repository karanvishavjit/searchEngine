package com.advancedComputing.searchEngine.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPageCrawler {
	
	private static Set<String> crawledPageSet = new HashSet<String>();
	private static int pageDepth = 2; //greater the depth more time it will take to crawl
	private static String htmlRegex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

	public static void crawler(String url, int depth) {
		Pattern patternObject = Pattern.compile(htmlRegex);
		if (depth <= pageDepth) {
			try {
				Document document = Jsoup.connect(url).get();
				HtmlParser.writeHtml(document, url);
				depth++;
				if (depth < pageDepth) {
					Elements links = document.select("a[href]");
					for (Element page : links) {

						if (validateLinks(page.attr("abs:href")) && patternObject.matcher(page.absUrl("href")).find()) {
							
							System.out.println(page.absUrl("href"));
							crawler(page.absUrl("href"), pageDepth);
							crawledPageSet.add(page.absUrl("href"));
						}
					}
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	private static boolean validateLinks(String url) {
		if (crawledPageSet.contains(url)) {
			return false;
		}
		if (url.endsWith(".jpeg") || url.endsWith(".jpg") || url.endsWith(".png")
				|| url.endsWith(".pdf") || url.contains("#") || url.contains("?")
				|| url.contains("mailto:") || url.startsWith("javascript:") || url.endsWith(".gif")
				||url.endsWith(".xml")) {
			return false;
		}
		return true;
	}
}
