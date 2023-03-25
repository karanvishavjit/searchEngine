package com.advancedComputing.searchEngine.service;

public class test {
	public static int[] makeBadCharTable(String pattern) {
	    int[] table = new int[256];
	    int len = pattern.length();
	    for (int i = 0; i < 256; i++) {
	        table[i] = len;
	    }
	    for (int i = 0; i < len - 1; i++) {
	        table[pattern.charAt(i)] = len - i - 1;
	    }
	    return table;
	}

	public static int search(String text, String pattern) {
	    int[] table = makeBadCharTable(pattern);
	    int lenText = text.length();
	    int lenPattern = pattern.length();
	    int i = lenPattern - 1;
	    while (i < lenText) {
	        int j = lenPattern - 1;
	        while (text.charAt(i) == pattern.charAt(j)) {
	            if (j == 0) {
	                return i;
	            }
	            i--;
	            j--;
	        }
	        i += Math.max(table[text.charAt(i)], lenPattern - j);
	    }
	    return -1;
	}
	public static void main(String[] args) {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "abracadabra";

		System.out.println("Indices of pattern matches: " + search(text, pattern));
	}

}
