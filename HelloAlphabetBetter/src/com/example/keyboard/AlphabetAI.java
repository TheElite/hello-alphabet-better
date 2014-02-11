package com.example.keyboard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import android.app.Activity;
import android.util.Log;


public class AlphabetAI {
	
	public AlphabetAI(){
		
	}
	
	private int maxCountIndex(int[] temp){
		
		int max = 0;
		int index = 0;
		
		for(int i = 0; i < temp.length; i++){
			
			if(temp[i] > max){
				max = temp[i];
				index = i;
			}
			
		}
		
		return index;
	}
	
	/*
	public static void main(String[] args){
		
		AlphabetAI AI = new AlphabetAI();
		//AI.getCommon("LIS");
		
		String message = "M";
		String[] tokens = message.split("_");
		char[] alphatemp = AI.getCommon(tokens[tokens.length-1]);	
		
		for(int i = 0; i < alphatemp.length; i++)		
			System.out.print(alphatemp[i]);
		
	}
	*/
	
	public int[] memset(int temp[]){
		for(int i = 0; i < temp.length; i++){
			temp[i] = 0;
		}
		return temp;
	}
	
	public char[] memset(char temp[]){
		for(int i = 0; i < temp.length; i++){
			temp[i] = 0;
		}
		return temp;
	}
	
	private boolean isEqual(char[] word, char[] startWith){
		
		for(int i = 0; i < startWith.length; i++){
			if(word[i] != startWith[i])
				return false;
		}
		
		if(word.length == startWith.length)
			return false;

		return true;
	}
	
	public char[] getCommon(String startWith2, InputStream in){
		
		if(in == null)
			return new char[10];
		
		char[] word = new char[80];
		char[] startWith = new char[80];
		char[] choiceArray = new char[80];
		int[] letters = new int[26];
		int startWithSize;
		int maxIdx;
		
		startWith = startWith2.toCharArray();
		
		try {

			for(int i = 0; i < startWith.length; i++){
				
				startWith[i] = Character.toUpperCase(startWith[i]);
				if(startWith[i] < 'A' || startWith[i] >'Z'){
					startWith[i] = 'A';
				}
				
			}
			
			startWithSize = startWith.length;
			letters = memset(letters);
			choiceArray = memset(choiceArray);
			
			InputStreamReader input = new InputStreamReader(in);         
			BufferedReader br = new BufferedReader(input);
			
			if(in != null){
				
				String line;
				while((line = br.readLine()) != null){
					word = line.toCharArray();
					if(isEqual(word, startWith)){
						break;
					}
				}
				
				if(word != null){

					//for(int j = 0; j < word.length; j++)
					//	System.out.print(word[j]);
					
					letters[word[startWithSize]-'A']++;
					
					
					while((line = br.readLine()) != null){
						word = line.toCharArray();
						if(isEqual(word, startWith))
							letters[word[startWithSize]-'A']++;
					}
					
					for(int i = 0; i < letters.length; i++)
						System.out.print(letters[i] + " ");
					System.out.println();
					System.out.println();
					
					maxIdx = maxCountIndex(letters);
					
					int i = 0;
					//int counter = 0;
					while(letters[maxIdx] != 0){
						
						choiceArray[i] = (char) ('A' + maxIdx);
						i++;
						letters[maxIdx] = 0;
						maxIdx = maxCountIndex(letters);
					}
					
					for(int j = 0; j < choiceArray.length; j++)
						System.out.print(choiceArray[j]);
					System.out.println();

				}
				else {
					System.out.println("Word not found in Dictionary");
					Log.d("ERROR", "Word not found in Dictionary");
				}
				
			}
			
			br.close();
			input.close();	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("ERROR", e.getStackTrace().toString());
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("ERROR2", e.getStackTrace().toString());
		}
		
		
		
		Integer[] list = new Integer[choiceArray.length];
		for(int i = 0; i < choiceArray.length; i++)
			list[i] = (int) choiceArray[i];
		
		for(int i = 0; i < choiceArray.length; i++)
			Log.d("DEBUG2", list[i].toString());
		
		
		
		return choiceArray.clone();
		
	}

}
