package com.advancedComputing.searchEngine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore2 {
	static Map<Character, Integer> badCharShiftTable = new HashMap<>();

	public static void badCharacterTable(String pattern) {
		for(int i=0;i<pattern.length();i++) {
			char c = pattern.charAt(i);
			//badCharShiftTable.put(c,pattern.lastIndexOf(pattern.charAt(i)));
			badCharShiftTable.put(c,pattern.length()-i-1);
		}
	}

	public static List<Integer> search(String pattern, String text) {
		int textLength=text.length();
		int patternLength= pattern.length();
		List<Integer> indices = new ArrayList<>();
		if(textLength==0||patternLength==0)
			return indices;
		
		badCharacterTable(pattern);
		
		int i = patternLength - 1;
		int j = patternLength - 1;
		
		while (i < textLength&& j>=0) {
			if (text.charAt(i) == pattern.charAt(j)) {
				if (j == 0) {

					indices.add(i);
					i += patternLength;
					continue;
				}
				i--;
				j--;  
			}
			else {
				// Shift based on the bad character and good suffix rules
				char c = text.charAt(i);
				int badCharShiftAmount = badCharShiftTable.getOrDefault(c, patternLength);
				//i = i+patternLength - Math.min(j, badCharShiftAmount+1);
		        i += Math.max(badCharShiftAmount, patternLength - j);
				j=patternLength-1;
			}
		}
		return indices;
	}


	public static void main(String[] args) {

		String text = "this is he feild of he computer science he the the the the it the the it the it it ehe ehe ehe ehe";
		String pattern = "the";
		System.out.println(search(pattern, text));
	}
}
