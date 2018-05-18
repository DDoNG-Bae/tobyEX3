package com.dasom.ex.cal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public Integer fileReadTemplate(String filepath,BufferedReaderCallback callback) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			int ret = callback.doSomthingWithReader(br);
			return ret;
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
		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
			
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				// TODO Auto-generated method stub
				Integer multiply =1;
				String line = null;
				while((line=br.readLine())!=null) {
					multiply*=Integer.valueOf(line);
				}
				return multiply;
			}
		};
		
		return fileReadTemplate(filepath, multiplyCallback);
	}
	
	public Integer calcSum(String filepath) throws IOException {
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				// TODO Auto-generated method stub
				Integer sum = 0;
				String line = null;
				while((line=br.readLine())!=null) {
					sum+=Integer.valueOf(line);
				}
				return sum;
			}
		};
		
		return fileReadTemplate(filepath, sumCallback);
	}
	
}
