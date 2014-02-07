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
		    super.onTouchEvent(event);
		    
		    TextView textViewToChange = (TextView) findViewById(R.id.A);
		    
		    Character temp = letter.charAt(0);
		    temp++;
			
			if(temp > 'Z')
				temp = 'A';
			if(temp < 'A')
				temp = 'Z';
			
			letter = temp.toString();
			textViewToChange.setText(letter);
			
			timeStart = System.currentTimeMillis();
		}
	    
	    /*
	    int action = event.getAction() & MotionEvent.ACTION_MASK;
	    float x1 = 0, x2 = 0;

	    switch (action) {
	        case MotionEvent.ACTION_DOWN: {
	              x1 = event.getX();
	            break;
	        }
	        case MotionEvent.ACTION_UP: {
	              x2 = event.getX();
	            break;
	        }
	        
	    }
	    */
	    
	    return true;
	}
	    

}
