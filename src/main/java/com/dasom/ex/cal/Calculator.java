package com.dasom.ex.cal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	
	public <T> T lineReadTemplate(String filepath,LineCallback<T> callback,T initVal) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line=null;
			while((line=br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		}catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}finally {
			if(br != null) {
				try {
					br.close();
				}catch(IOException e) {System.out.println(e.getMessage());}
			}
		}
	}
	
	public Integer calcMultiply(String filepath) throws IOException {
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
			
			public Integer doSomethingWithLine(String line, Integer value) {
				// TODO Auto-generated method stub
				return value*Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filepath, multiplyCallback, 1);
	}
	
	public Integer calcSum(String filepath) throws IOException {
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			
			public Integer doSomethingWithLine(String line, Integer value) {
				// TODO Auto-generated method stub
				return value+Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filepath, sumCallback, 0);
	}
	
	public String concaternate(String filepath) throws IOException{
		LineCallback<String> concaternateCallback = new LineCallback<String>() {
			
			public String doSomethingWithLine(String line, String value) {
				// TODO Auto-generated method stub
				return value+line;
			}
		};
		
		return lineReadTemplate(filepath, concaternateCallback, "");
	}
	
}
