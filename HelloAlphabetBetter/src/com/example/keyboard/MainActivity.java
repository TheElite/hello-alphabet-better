package com.example.keyboard;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class MainActivity extends Activity{

	//String letter = "A";
	static final long timeToWait = 100;
	static long timeStart = 0;
	static final long requiredDisplacement = 25; //pixels
	
	static int rawX1;
	static int rawY1;
	
	TextView message;
	InputStream in;
	
	//String alphabet = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Alphabet dynamalpha = new Alphabet();
	int currentPos = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(message != null){
			if(message.length() != 0)
				message.setText("");
		}
		
		this.message = (TextView) findViewById(R.id.message);
		
		
		try {
			//in = openFileInput("words.txt");
			in = getAssets().open("words.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("ERROR3", e.getStackTrace().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("ERROR4", e.getStackTrace().toString());
		}
		
	}
	
	
	@Override
	protected void onResume(){
		
		super.onResume();
		
		if(message != null){
			if(message.length() != 0)
				message.setText("");
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    // TODO Auto-generated method stub
		
		super.onTouchEvent(event);
		
		if(System.currentTimeMillis() - timeStart > timeToWait){
			
			if(event.getHistorySize() > 0){
				
				float x = event.getHistoricalX(0);
				float x2 = event.getHistoricalX(event.getHistorySize()-1);
				
				if(Math.abs(x - x2) > requiredDisplacement){

				    TextView textViewToChange = (TextView) findViewById(R.id.A);
				    
				    Character temp = letter.charAt(0);
				    
				    if(x - x2 < 0)
				    	temp--;
				    else temp++;
					
				    if(temp == '_'+1)
				    	temp = 'A';	    
				    else if(temp < 'A')
						temp = '_';
				    else if(temp == '_'-1)
				    	temp = 'Z';
				    else if(temp == 'Z'+1)
				    	temp = '_';	
					
					letter = temp.toString();
					textViewToChange.setText(letter);
					
					timeStart = System.currentTimeMillis();
				}
			}
		}	   
	    
	    return true;
	}
	*/
	boolean pressed = false;
	boolean released = true;
	boolean waitTillNextRound = false;
	boolean waitTillNextRound2 = false;
	
	static long time0;
	
	
	static long waitTime = System.currentTimeMillis();
	
	
	@Override
    public boolean dispatchKeyEvent(KeyEvent event) {
		
		
		
		
		if(event.getAction() == KeyEvent.ACTION_DOWN && pressed == false){
			pressed = true;
			released = false;
			time0 = System.currentTimeMillis();
			waitTillNextRound2 = false;
		}
		if(event.getAction() == KeyEvent.ACTION_UP){
			pressed = false;
			released = true;
			waitTillNextRound = false;
		}
		
		
		if(released == true && event.getKeyCode() == KeyEvent.KEYCODE_D && System.currentTimeMillis()-time0 > 2000 && waitTillNextRound == false){
			
			
			TextView message = (TextView) findViewById(R.id.message);
			Character currentChar = dynamalpha.newAlphabet.charAt(currentPos);
			
			TextView textViewToChange = (TextView) findViewById(R.id.A);
			
			String newMessage = message.getText() + currentChar.toString();		
			
			Character letter = dynamalpha.newAlphabet.charAt(0);
			textViewToChange.setText(letter.toString());
			currentPos = 0;
			message.setText(newMessage);
			
			
			
			
			
			CharSequence text = message.getText();
			Character[] text2 = new Character[text.length()];
			
			for(int i = 0; i < text2.length; i++)
				text2[i] = text.charAt(i);
			
			String temp2 = "";
			for(int i = 0; i < text2.length; i++)
				temp2 += text2[i].toString();
			
			String[] tokens = temp2.split("_");
			
			//AlphabetAI organize = new AlphabetAI();
			
			dynamalpha = new Alphabet();
			//dynamalpha = new Alphabet(organize.getCommon(tokens[tokens.length-1]));
			
			
			//letter = dynamalpha.newAlphabet.charAt(0);
			
			
			//char[] choiceArray = organize.getCommon("M", in);
			//choiceArray[0] = 'A'
		
			
			
			/*
			Character temp[] = new Character[choiceArray.length];
			for(int i = 0; i < choiceArray.length; i++)
				temp[i] = choiceArray[i];
			
			String temp3 = "";
			for(int i = 0; i < temp.length; i++){
				temp3 += temp[i].toString();
			}
			
			temp3 = temp3.trim();
			*/			
			/*
			String list = "";
			for(int i = 0; i < choiceArray.length; i++){
				
				if(choiceArray[i] >= 'A' && choiceArray[i] <= 'Z'){
					Character temp = choiceArray[i];
					list += temp;
				}
				
			}
			*/
			
			/*
			Integer[] list = new Integer[choiceArray.length];
			for(int i = 0; i < choiceArray.length; i++)
				list[i] = (int) choiceArray[i];
				
			for(int i = 0; i < choiceArray.length; i++)
				Log.d("DEBUG", list[i].toString());
			
			Integer asdf = list.length;
			textViewToChange.setText(asdf.toString());
			*/
			
			
			
			
			
			waitTillNextRound = true;
			waitTillNextRound2 = true;
		}
		
		else if(released == true  && waitTillNextRound2 == false && event.getKeyCode() == KeyEvent.KEYCODE_D){
			
			if(System.currentTimeMillis() - waitTime > 500){
			
				TextView textViewToChange = (TextView) findViewById(R.id.A);	
	
			    currentPos++;
			    
			    if(currentPos > dynamalpha.newAlphabet.length()-1)
			    	currentPos = 0;
			    
			    Character letter = dynamalpha.newAlphabet.charAt(currentPos);
				textViewToChange.setText(letter.toString());
				
				waitTime = System.currentTimeMillis();
			}
		}
		
		
		
		
		
		return super.dispatchKeyEvent(event);
    }
	
}
