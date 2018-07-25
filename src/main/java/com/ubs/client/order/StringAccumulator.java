package com.ubs.client.order;

import java.util.ArrayList;

public class StringAccumulator {
	private static final String DEFAULT_DELIMIT_NUMBER = ",";
	private static final String DEFAULT_DELIMIT_DELIMITER = "\\|";
	
	/**
	 * - Divide the input into delimiter portion and numbers portion
	 * - Extract the delimiter into delimiter list
	 * - Extract the number into number list
	 * - Add up
	 * @param numbers - input string to be extract and add
	 * @return sum of numbers
	 */
	public int add(String numbers) throws InvalidInputException {
		int result = 0;
		if (numbers.trim().length() > 0) {
			// - Divide the input into delimiter portion and numbers portion
			String delimitPortion = "";
			String numbmersPortion = numbers;
			if (numbers.startsWith("//")) {
				int endIndex = numbers.indexOf("\n");
				delimitPortion = numbers.substring(2, endIndex);
				numbmersPortion = numbers.substring(endIndex+1);
			}
			// - Extract the delimiter into delimiter list
			ArrayList<String> delimitList = getDelimitList(delimitPortion);
			ArrayList<String> negativeNumList = new ArrayList<String>();
			// - Extract the number into number list
			String[] tokenList = getTokenList(numbmersPortion, delimitList);
			// - Add up
			for (String tokenStr : tokenList) {
				int tokenInt = Integer.parseInt(tokenStr);
				if (tokenInt < 0) {
					negativeNumList.add(""+tokenInt);
				} else if (tokenInt > 1000) { // ignore
				} else {
					result = result + tokenInt;
				}
			}
			// throw exception when negative found
			if (negativeNumList.size() > 0) {
				String exMsg = "";
				for (String negativeNumber : negativeNumList) {
					exMsg = exMsg + negativeNumber + " ";
				}
				throw new InvalidInputException("negatives not allowed:" + exMsg);
			}
		}
		return result;
	}
	
	/**
	 * Extract the string into list of delimiter
	 * - add , and \n into list
	 * @param input - delimit portion
	 * @return list of delimiter
	 */
	private ArrayList<String> getDelimitList(String input) {
		ArrayList<String> delimitList = new ArrayList<String>();
		if (input.length() > 0) {
			String[] delimitArrary = input.split(DEFAULT_DELIMIT_DELIMITER);
			for (String delimit : delimitArrary) {
				delimitList.add(delimit);
			}
		}
		// all existing scenarios should still be supported even new delimiter is used
		delimitList.add(DEFAULT_DELIMIT_NUMBER);
		delimitList.add("\n");
		return delimitList;
	}
	
	/**
	 * Extract the string into list of tokens
	 * - replace all the delimiter into single delimiter
	 * @param input - input string to be extracted
	 * @param delimitList - list of delimiter to extract input
	 * @return
	 */
	private String[] getTokenList(String input, ArrayList<String> delimitList) {
		String preprocessedInput = input;
		if (delimitList.size() > 0 && input.length() > 0) {
			for (String delimit : delimitList) {
				preprocessedInput = preprocessedInput.replace(delimit, ",");
			}
		} 
		return preprocessedInput.split(DEFAULT_DELIMIT_NUMBER);
	}
	
}
