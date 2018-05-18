package com.dasom.ex.cal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;
	@Before 
	public void setUp() {
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}
	@Test
	public void sumOfNumbers() throws IOException{
		assertThat(calculator.calcSum(this.numFilepath),is(10));
	}
	@Test
	public void multiplyOfNumbers() throws IOException{
		assertThat(calculator.calcMultiply(this.numFilepath),is(24));
	}
	@Test
	public void concatenateString() throws IOException{
		assertThat(calculator.concaternate(this.numFilepath),is("1234"));
	}
}
