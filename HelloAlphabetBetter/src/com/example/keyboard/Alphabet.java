package com.example.keyboard;

public class Alphabet {
	
	
	String newAlphabet;
	String alphabetlphabet = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Alphabet(String arg1){
		
		if(arg1 == null)
			newAlphabet = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		else
			newAlphabet = arg1;
	}
	
	public Alphabet(){
		newAlphabet = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}
	

}
