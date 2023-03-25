package com.advancedComputing.searchEngine.service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HtmlParser {
	
	public static void writeHtml(Document document, String url) {
		String title = document.title().replaceAll("[^a-zA-Z0-9\\s]", "");
		try {
			PrintWriter html = new PrintWriter(
					new FileWriter(Constants.htmlDirectoryPath + title+ ".html"));
			html.write(document.toString());
			html.close();
			convertToText(Constants.htmlDirectoryPath + title+ ".html", url,title+ ".txt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void convertToText(String htmlfile, String url, String filename) throws Exception {
		FilterInputData filterInputData=new FilterInputData();
		File file = new File(htmlfile);
		Document document = Jsoup.parse(file, "UTF-8");
		String data = document.text().toLowerCase();
		String filteredData=filterInputData.removeStopWords(data);
//		data = url + "::" + data;
		data = url + "::" + filteredData; //change here
		PrintWriter writer = new PrintWriter(Constants.txtDirectoryPath+ filename);
		writer.println(data);
		writer.close();
	}

	public static void main(String[] args) {
		System.out.println(Constants.htmlDirectoryPath);
		System.out.println(Constants.txtDirectoryPath);
	}
}
