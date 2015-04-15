package com.elitetek.scrambleme;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

	EditText email;
	EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		/* UI element setup ****************************************************************************************/
		findViewById(R.id.buttonLogin).setOnClickListener(this);
		findViewById(R.id.buttonCreateAccount).setOnClickListener(this);
		findViewById(R.id.buttonFacebook).setOnClickListener(this);
		findViewById(R.id.buttonTwitter).setOnClickListener(this);
		
		TextView title = (TextView) findViewById(R.id.textViewTitle);
		
		email = (EditText) findViewById(R.id.editTextEmailSignIn);
		password = (EditText) findViewById(R.id.editTextPasswordSignIn);
		
		Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/title.TTF");
		title.setTypeface(titleFont);
		title.setTextSize(getResources().getDimension(R.dimen.title_text_size));
		/****   /UI element setup ********************************************************************************************/
		
		
		
		
		// Check to see if the user is already logged in
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			// go to main activity
			Toast.makeText(LoginActivity.this, "Welcome " + currentUser.getString("nameWithCase"), Toast.LENGTH_LONG).show();
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
	    	finish();
	    	startActivity(intent);
		}	
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
			case R.id.buttonLogin:
				
				if (email.getText().length() == 0 && password.getText().length() > 0) 
					Toast.makeText(LoginActivity.this, "Email Blank!", Toast.LENGTH_LONG).show();
				else if (password.getText().length() == 0 && email.getText().length() > 0)
					Toast.makeText(LoginActivity.this, "Password Blank!", Toast.LENGTH_LONG).show();
				else if (email.getText().length() == 0 && password.getText().length() == 0)
					Toast.makeText(LoginActivity.this, "Email and Password Field Are Blank!", Toast.LENGTH_LONG).show();
				else {
				
					ParseUser.logInInBackground(email.getText().toString().toLowerCase(), password.getText().toString(), new LogInCallback() {
						  public void done(ParseUser user, ParseException e) {
						    if (user != null) {
						    	// User Logged In
						    	Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						    	finish();
						    	startActivity(intent);				    	
						    } else {					      
						    	Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
						    	email.setText("");
						    	password.setText("");
						    }
						  }
					});	
				}
				
				break;
			case R.id.buttonCreateAccount:
				
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				finish();
				startActivity(intent);
				
				break;
			case R.id.buttonFacebook:
				
				break;
			case R.id.buttonTwitter:
				
				break;
		}
		
	}
}
