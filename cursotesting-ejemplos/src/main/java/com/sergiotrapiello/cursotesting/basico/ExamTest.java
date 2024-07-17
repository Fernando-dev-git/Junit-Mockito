package com.sergiotrapiello.cursotesting.basico;

import java.util.ArrayList;

public class ExamTest {

	public static void main(String[] args) {
		ArrayList lst = new ArrayList();
		String[] mr;
		try {
			while (true) {
				lst.add(new String("cad"));
			}
		} catch (RuntimeException ex) {
			System.out.println("Is a RuntimeException");
		} catch (Exception ex) {
			System.out.println("Is a Exception");
		}
		System.out.println("End");
	}
}
