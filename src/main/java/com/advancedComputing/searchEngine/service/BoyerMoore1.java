package com.advancedComputing.searchEngine.service;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore1 {
	private String pattern;
    public static Map<Character, Integer> badCharShiftTable = new HashMap<>();
//    static List<Integer> indices = new ArrayList<>();
    static int []shift = new int[11 + 1];

    
    public BoyerMoore1(String pattern) {
    	this.pattern=pattern;
    	badCharacterTable(pattern);
//    	goodSuffixPreprocess(pattern);
    }
    public void badCharacterTable(String pattern) {
    	for(int i=0;i<pattern.length();i++) {
    		char c = pattern.charAt(i);
    		badCharShiftTable.put(c,pattern.length()-i-1);
    	}
    }
    
    static void preprocess_strong_suffix(int []shift, int []bpos,
    		char []pat, int m)
    {
    	// m is the length of pattern 
    	int i = m, j = m + 1;
    	bpos[i] = j;

    	while(i > 0)
    	{
				    		/*if character at position i-1 is not 
				equivalent to character at j-1, then 
				continue searching to right of the
				pattern for border */
    		//0		1	2	3	4	5	6
//        	("A		B	B	A	B	A	B");
    		while(j <= m && pat[i - 1] != pat[j - 1])
    		{
    			/* 	the character preceding the occurrence of t 
					in pattern P is different than the mismatching 
					character in P, we stop skipping the occurrences 
					and shift the pattern from i to j */
    			if (shift[j] == 0)
    				shift[j] = j - i;

    			//Update the position of next border 
    			j = bpos[j];
    		}
    		/* 	p[i-1] matched with p[j-1], border is found.
				store the beginning position of border */
    		i--; j--;
    		bpos[i] = j; 
    	}
    }
    
  //Pre processing for case 2
    static void preprocess_case2(int []shift, int []bpos,
                                  char []pat, int m)
    {
        int i, j;
        j = bpos[0];
        for(i = 0; i <= m; i++)
        {
            /* set the border position of the first character 
            of the pattern to all indices in array shift
            having shift[i] = 0 */
            if(shift[i] == 0)
                shift[i] = j;
      
            /* suffix becomes shorter than bpos[0], 
            use the position of next widest border
            as value of j */
            if (i == j)
                j = bpos[j];
        }
    }
    
    public static int search(String pattern, String text) {
    	int textLength=text.length();
    	int patternLength= pattern.length();
    	int i = patternLength - 1;
        while (i < textLength) {
            int j = patternLength - 1;
            while (j >= 0 && text.charAt(i) == pattern.charAt(j)) {
//                i--;
                j--;
            }
            if (j == -1) {
//                // Pattern found
//                indices.add(i + 1);
//                i += patternLength;
            	return i;
            } else {
                // Shift based on the bad character and good suffix rules
                char c = text.charAt(i);
                int badCharShiftAmount = badCharShiftTable.getOrDefault(c, patternLength);
                int goodSuffixShiftAmount = shift[j + 1];
                i += Math.max(badCharShiftAmount, goodSuffixShiftAmount);
            }
        }

        return -1;
    }
    
    
    public static void main(String[] args) {
//    										[-1, 	0,	0,  0,  1,  2, 1, 2]
    										//0		1	2	3	4	5	6
//    	BoyerMoore boyerMoore=new BoyerMoore("A		B	B	A	B	A	B");
//		boyerMoore.goodSuffixPreprocess("ABBABAB");
    	
    	 String text = "abacadabrabracabracadabrabrabracad";
    	 String pattern = "abracadabra";
    	 BoyerMoore1 boyerMoore=new BoyerMoore1(pattern);
    	 int []bpos = new int[pattern.length()+1];   
    	preprocess_strong_suffix(shift, bpos, pattern.toCharArray(), pattern.length());
    	preprocess_case2(shift, bpos, pattern.toCharArray(), pattern.length());
    	
    	
    	System.out.println(search(pattern, text));
	}

/*
 * int i = patternLength - 1;
    while (i < textLength) {
        int j = patternLength - 1;
        while (j >= 0 && text.charAt(i) == pattern.charAt(j)) {
            i--;
            j--;
        }
        if (j == -1) {
            // Pattern found
            indices.add(i + 1);
            i += patternLength;
        } else {
            // Shift based on the bad character and good suffix rules
            char c = text.charAt(i);
            int badCharShiftAmount = badCharShift.getOrDefault(c, patternLength);
            int goodSuffixShiftAmount = goodSuffixShift[j + 1];
            i += Math.max(badCharShiftAmount, goodSuffixShiftAmount);
        }
    }

    return indices;
 */
    
    /*
     * public void goodSuffixPreprocess(String pattern) {
    	int[] goodSuffix= new int[pattern.length()];
    	 int[] border = computeBorder(pattern);
    	    for (int i = pattern.length() - 1; i >= 0; i--) {
    	        int j = pattern.length() - border[i];
    	        goodSuffix[j] = Math.max(goodSuffix[j], i - border[i] + 1);
    	        if (i == border[i]) {
    	            for (int k = pattern.length() - 1 - j; k >= 0; k--) {
    	                goodSuffix[k] = Math.max(goodSuffix[k], j);
    	            }
    	        }
    	    }
    }
    private static int[] computeBorder(String pattern) {
        int patternLength = pattern.length();
        int[] border = new int[patternLength + 1];
        border[0] = -1;
        int i = 0, j = -1;
        while (i < patternLength) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = border[j];
            }
            i++;
            j++;
            border[i] = j;
        }
        return border;
    }
     */
}
