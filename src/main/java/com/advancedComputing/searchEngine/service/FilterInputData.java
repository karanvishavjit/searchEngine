package com.advancedComputing.searchEngine.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterInputData {
	Set<String> set = new HashSet<String>();
	int removedWords;
	List<String> filteredlist = new ArrayList<String>();
	
	public String removeStopWords(String data) {
		try {
			FileReader fileReader = new FileReader(Constants.stopWordsPath);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				String word = line.toLowerCase();
				set.add(word);
			}
			fileReader.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		String[] splitdata=data.split("\\s");
		for(String str:splitdata) {
			if(!set.contains(str)) {
				filteredlist.add(str);
			}
			else if(str.length()>0) {
				removedWords++;
			}
		}
		String filteredText= String.join(" ", filteredlist);
		return filteredText;
	}
	public static void main(String[] args) {
/*		removedWords=0;
//		removeStopWords();
		System.out.println(set.size());
		File files = new File(System.getProperty("user.dir")+"/output/TextFiles/Agricultural Campus  Dalhousie University.txt");
		String filteredString="";
		In inputData = new In(files.getAbsolutePath());
		String str = inputData.readAll();
		System.out.println("str before:\n"+str);
		String[] splitstr=str.split("\\s");
		for(String st:splitstr) {
			if(!set.contains(st)) {
				filteredString+=st+" ";
			}
			else if(st.length()>0) {
				removedWords++;
			}
		}
		inputData.close();
		System.out.println("str after:\n"+filteredString);
		System.out.println("No of words removed: "+removedWords);
	*/	
	}
}
