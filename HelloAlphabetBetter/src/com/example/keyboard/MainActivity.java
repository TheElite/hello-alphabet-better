package com.example.keyboard;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class MainActivity extends Activity{

	String letter = "A";
	static final long timeToWait = 100;
	static long timeStart = 0;
	static final long requiredDisplacement = 25; //pixels
	
	static int rawX1;
	static int rawY1;
	
	TextView message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(message != null){
			if(message.length() != 0)
				message.setText("");
		}
		
		this.message = (TextView) findViewById(R.id.message);
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
	
	static boolean toggle = false;
	@SuppressLint("NewApi")
	@Override
    public boolean dispatchKeyEvent(KeyEvent event) {
		
		if(toggle == true){
			toggle = false;
			return super.dispatchKeyEvent(event);
		}
		

		if(event.getKeyCode() == KeyEvent.KEYCODE_D){
			TextView textViewToChange = (TextView) findViewById(R.id.A);	
			
			Character temp = letter.charAt(0);
		    temp++;
		    
		    
		    
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
			
			toggle = true;
		}
		
		else if(event.getKeyCode() == KeyEvent.KEYCODE_SPACE){
			TextView message = (TextView) findViewById(R.id.message);
			Character currentChar = letter.charAt(0);
			TextView textViewToChange = (TextView) findViewById(R.id.A);
			
			//if(letter.charAt(0) == '_')
			//	currentChar = ' ';
			
			String newMessage = message.getText() + currentChar.toString();
			textViewToChange.setText(letter);
			message.setText(newMessage);
			toggle = true;
		}
		
		return super.dispatchKeyEvent(event);
    }
	
}
