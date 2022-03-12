package com.uber.ride.controller;

import java.util.ArrayList;

public class UserLogin {
	
	

	public static void main(String[] args) {
		System.out.println("starting ..");
		
		ArrayList<Object> arraylist = new ArrayList<>();
		arraylist.add("kundan");
		arraylist.add(10);
		arraylist.add(10.89);
		arraylist.add(new ArrayList<String>());
		
		for(Object object:arraylist) {
		
			System.out.println("Arralist:"+object);
		}
	}
}
