package com.advancedComputing.searchEngine.service;

public class PatternLookup {
	
	public static String suggestion = "";
	
	public static String validateWord(String word) {
		AutoCorrect autoCorrect = new AutoCorrect();
		autoCorrect.createDictionary();
		
		String correct[] = word.split(" ");
		String string = "";
		
		for (String str : correct) {
			suggestion = autoCorrect.suggestWords(str);
				if (!(suggestion.equals(str))) {
					//System.out.println("Suggested str for "+correct[i]+" is "+n);
					string += suggestion + " ";
				}
				else {
					string+=str+" ";
				}
			}
		System.out.println("Displaying Results for: "+ string);
		return string;
	}
	
	public static int searchPattern(String data, String word, String fileName) {
		int patternCounter = 0;

		//Searches for multiple matches of word in the same file
		patternCounter=BoyerMoore2.search(word, data).size();
		
//		for (int location = 0; location <= data.length(); location += offset + word.length()) {
//			//Not striping of all the spaces improve accuracy by dividing the words and acting as a matching char.
//			if(data.substring(location).isEmpty()) { //if an empty substring is encountered continue
//				continue;
//			}
//			offset = boyerMoore.search(data.substring(location)); //get substring
//			if ((offset + location) < data.length()) {
//				patternCounter++;
//			}
//		}
		if (patternCounter != 0) {
			System.out.println("Found in HTML file --> " + fileName+" --> "+patternCounter+" times"); // Founded from which HTML file..
			System.out.println("-------------------------------------------------");
		}
		return patternCounter;
		
	}
}
