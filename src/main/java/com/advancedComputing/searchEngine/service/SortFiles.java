package com.advancedComputing.searchEngine.service;

import java.util.*;

public class SortFiles {

	// Merge-sort for ranking of the pages
		public static List<String> sort(Hashtable<String, Integer> htmlLinks, int occur) {
			List<String> stringList=new ArrayList<>();
			// Transfer as List and sort it
			ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<Map.Entry<String, Integer>>(htmlLinks.entrySet());

			Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() {

				public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
					return obj2.getValue().compareTo(obj1.getValue());
				}
			});

//			Collections.reverse(arrayList);

			if (occur != 0) {

				System.out.println("------Top 5 search results -----");

				int noOfFetch = 5;
				int j = 0;
				int i=1;
				while (arrayList.size() > j && noOfFetch > 0) {


					if(arrayList.get(j).getKey()!=null) {
						stringList.add(arrayList.get(j).getKey());
						System.out.println("(" + i + ") " + arrayList.get(j).getKey());
						j++;
						i++;
					}
					noOfFetch--;

				}
			}
			return stringList;
		}
}
