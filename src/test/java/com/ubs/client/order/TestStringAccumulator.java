package com.ubs.client.order;

import org.junit.Test;

public class TestStringAccumulator {

	@Test
	public void test() {
		StringAccumulator stringAccumulator = new StringAccumulator();
		try {
			// empty string
			System.out.println(stringAccumulator.add(""));
			System.out.println(stringAccumulator.add(" "));
			// single number
			System.out.println(stringAccumulator.add("1"));
			System.out.println(stringAccumulator.add("8"));
			// simple sum
			System.out.println(stringAccumulator.add("1,2"));
			System.out.println(stringAccumulator.add("1,2,3,"));
			System.out.println(stringAccumulator.add("1,2,3,999"));
			System.out.println(stringAccumulator.add("1,2,3,1000"));
			// > 1000 check
			System.out.println(stringAccumulator.add("1,2,3,1001"));
			System.out.println(stringAccumulator.add("1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1001"));
			System.out.println(stringAccumulator.add("1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1001,3,3,3,3,3,3,3,3,3,3,3,3"));
			System.out.println(stringAccumulator.add("1,2,3,3,3,3,3,3,3,3,3,3,3,3,999999,3,3,3,3,3,3,3,3,1001"));
			System.out.println(stringAccumulator.add("1\n2"));
			System.out.println(stringAccumulator.add("1,2\n2"));
			System.out.println(stringAccumulator.add("1\n3,2"));
			System.out.println(stringAccumulator.add("1\n3,2\n3\n9\n9\n9\n9\n9\n9\n9\n9\n"));
			// change delimiter
			System.out.println(stringAccumulator.add("//;\n1;2"));
			System.out.println(stringAccumulator.add("//_\n91_92"));
			System.out.println(stringAccumulator.add("//@\n118@982"));
			// multiple length delimiter
			System.out.println(stringAccumulator.add("//___\n91___92"));
			System.out.println(stringAccumulator.add("//***\n91***92"));
			// multiple delimiter
			System.out.println(stringAccumulator.add("//***\n91***92,92"));
			System.out.println(stringAccumulator.add("//***|;|__\n91***92,92,71;44__79"));
			System.out.println(stringAccumulator.add("//*|%\n1*2%3"));
			// negative number
			System.out.println(stringAccumulator.add("-1000"));
//			System.out.println(stringAccumulator.add("-1000.-999,12,34,3243,4234,23,23"));
//			System.out.println(stringAccumulator.add("12,34,-1000,-999,3243,4234,-23,23"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}

}
