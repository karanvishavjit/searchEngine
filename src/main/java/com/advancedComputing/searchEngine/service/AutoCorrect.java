package com.advancedComputing.searchEngine.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class AutoCorrect {
	public static TST<Integer> st = new TST<Integer>();
	
	public void createDictionary() {
		try {
			FileReader fileReader = new FileReader(Constants.dictionaryPath);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				String word = line.toLowerCase();
				if (!line.contains(" ")) {
					st.put(word, (st.get(word)!=null)?st.get(word):0+1);
				} else {
					String[] strs = line.split("\\s");
					for (String str : strs) {
						st.put(str, (st.get(word)!=null)?st.get(word):0+1);
					}
				}
			}
			fileReader.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public String suggestWords(String inputWord) {
		TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> map = new TreeMap<>();
		String str = inputWord.toLowerCase();
		String result = null;
        
		if (inputWord.length() == 0 || inputWord == null || inputWord.length()==1) {
			return result;
		}
		
		for (String word : st.keys()) {
			 //   System.out.println("inside for "+word);
				int distance = EditDistance.editDistance(word, str);
				TreeMap<Integer, TreeSet<String>> similarWords = map.getOrDefault(distance, new TreeMap<>());
				int frequency = st.get(word);
				TreeSet<String> set = similarWords.getOrDefault(frequency, new TreeSet<>());
				set.add(word);
				similarWords.put(frequency, set);
				map.put(distance, similarWords);
			}
	    result = map.firstEntry().getValue().lastEntry().getValue().first();
	    System.out.println("results "+result);
        
		return result;
	}
	
	public static void main(String[] args) {
//		createDictionary();
//		System.out.println(suggestWords("compter"));
	}

}
