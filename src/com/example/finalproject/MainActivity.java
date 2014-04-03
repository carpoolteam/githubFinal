package com.example.finalproject;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static final int REQUEST_CODE = 1;
	private Button bestMatchesButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bestMatchesButton = (Button)findViewById(R.id.bestMatches_button);
		
		bestMatchesButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this.getApplicationContext(), BestMatchesActivity.class);
				MainActivity.this.startActivityForResult(i, REQUEST_CODE);
			}
		});
	}		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

}
