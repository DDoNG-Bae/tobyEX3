package com.dasom.ex.cal;

public interface LineCallback<T> {
	T doSomethingWithLine(String line,T value);
}
