package com.example.keyboard;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

	String letter = "A";
	static final long timeToWait = 200;
	static long timeStart = 0;
	static final long requiredDisplacement = 25; //pixels
	
	static int rawX1;
	static int rawY1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    // TODO Auto-generated method stub
		if(System.currentTimeMillis() - timeStart > timeToWait){
			
			if(event.getHistorySize() > 0){
				
				float x = event.getHistoricalX(0);
				float x2 = event.getHistoricalX(event.getHistorySize()-1);
				
				if(Math.abs(x - x2) > requiredDisplacement){
				
				    super.onTouchEvent(event);
				    
				    TextView textViewToChange = (TextView) findViewById(R.id.A);
				    
				    Character temp = letter.charAt(0);
				    
				    if(x - x2 < 0)
				    	temp++;
				    else temp--;
					
					if(temp > 'Z')
						temp = 'A';
					if(temp < 'A')
						temp = 'Z';
					
					letter = temp.toString();
					textViewToChange.setText(letter);
					
					timeStart = System.currentTimeMillis();
				
				}
			}
		}	   
	    
	    
	    return true;
	}
	    

}
