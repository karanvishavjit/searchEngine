package com.advancedComputing.searchEngine.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author KARAN VISHAVJIT
 *
 */
public class SearchEngineMain {
	private static Scanner scanner = new Scanner(System.in);
	private static Hashtable<String, Integer> frequencyInFiles = new Hashtable<String, Integer>();

	
	public static List<String> caller(String keyWord) {
		HelperMethods.deleteFiles();
		List<String> returnList=new ArrayList<>();
		String input = "y";
//		do {
			System.out.println("\n "+Constants.welcomeMessage+" \n");
			System.out.println("---------------------------------------------------");
		
//			String option = scanner.next();
			String option =input;

			switch (option) {
			case "y":
				String[] urls = Constants.urls;
				crawlWebpages(urls);
				returnList=searchPattern(keyWord);
				break;
			case "n":
				System.out.println("Exit...");
				input = "n";
				break;
			default:
				System.out.println("\n Please Select (y/n). \n");
				input="y";
			}
//		}
//		while(input.equalsIgnoreCase("y"));
		System.out.println("\n\n!!!!!!!!!!!!!!\tSee you Soon\t!!!!!!!!!!!!!!\n\n");
			return returnList;
	}
	public static void crawlWebpages(String[] urls) {
		
		System.out.println("Initializing crawling web pages......");
		for(int i = 0;i< urls.length;i++)
			WebPageCrawler.crawler(urls[i], 0); //crawling the URLs one by one
		System.out.println("All web pages crawled");
	}
	
	public static List<String> searchPattern(String keyWord) {
		String choice = "y";
//		do {
			System.out.println("---------------------------------------------------");
			System.out.println("\n Enter field in which you want to Search Scholarships!! \n ");
//			String inputPattern = scanner.nextLine();
			String inputPattern = keyWord;
//			while(inputPattern.isEmpty()) {
//				inputPattern = scanner.nextLine();
//			}
			System.out.println("---------------------------------------------------");
			inputPattern=PatternLookup.validateWord(inputPattern);
			int frequencyOfInput = 0;
			int noOfFiles = 0;
			frequencyInFiles.clear();
			File files = null;
			try {
				System.out.println("\nSearching...");
				files = new File(Constants.txtDirectoryPath);
				File[] fileArray = files.listFiles(); //Store all the files in a file array
				long start,end,total=0;
				
				for (int i = 0; i < fileArray.length; i++) { //Search all the files in the file array
					if(fileArray[i].exists()) {
						In inputData = new In(fileArray[i].getAbsolutePath());
						String str = inputData.readAll();
						inputData.close();
						Pattern p = Pattern.compile("::");
						String[] file_name = p.split(str); //get the name of the file using regex to format
						//Search the word in the file
						start=System.nanoTime();
						frequencyOfInput = PatternLookup.searchPattern(str, inputPattern.toLowerCase(), file_name[0]); // search word in txt files
						end=System.nanoTime();
						total+=end-start;
						if (frequencyOfInput != 0) {
							frequencyInFiles.put(file_name[0], frequencyOfInput);
							noOfFiles++;
						}
					}
				}	
				System.out.println("Time elaspsed: "+total);
					if(noOfFiles>0) {
					System.out.println("\nTotal Number of Sites containing Scholarships related to: " + inputPattern + " is : " + noOfFiles);
					}else {
						System.out.println("\n No Scholarships found for search: "+ inputPattern);
					}



			} 
			catch (Exception e) {
				System.out.println("Exception:" + e);
			}
			System.out.println("\n Want to search in another field(y/n)?");
//			choice = scanner.next();
//		} while (choice.equals("y"));
		return SortFiles.sort(frequencyInFiles, noOfFiles); //rank the files based on frequency of word count
	}
}
