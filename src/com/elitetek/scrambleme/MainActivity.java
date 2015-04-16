package com.elitetek.scrambleme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

public class MainActivity extends Activity implements MainFragment.OnFragmentInteractionListener,
													  FooterFragment.OnFragmentInteractionListener,
													  ScrambleFragment.OnFragmentInteractionListener {

	String pathToPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getFragmentManager().beginTransaction()
    		.add(R.id.container, new MainFragment(), "main")
    		.add(R.id.footer, new FooterFragment(), "footer")
    		.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.logout) {
			
			ParseUser.logOut();
			finish();
			Intent backToLogin = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(backToLogin);
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void fromMainFragment() {
		// code to run from MainFragment
	}

	/**
	 * Prevent back button from leaving the app
	 */
	@Override
	public void onBackPressed() { Log.d("click", "in main back pressed"); }

	@Override
	public void fromFooterFragment(String path) {
		// code to run from FooterFragment
		/*
		Bundle stuffForScrambleFrag = new Bundle();
		stuffForScrambleFrag.putString("pathToPhoto", path);
		ScrambleFragment scrambleReference = new ScrambleFragment();
		scrambleReference.setArguments(stuffForScrambleFrag);
		*/
		getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("footer")).commit();
		getFragmentManager().beginTransaction().replace(R.id.container, new ScrambleFragment(path), "scramble").commit();		
	}

	@Override
	public void fromScrambleFragment() {
		// code to run from ScrambleFragment
		getFragmentManager().beginTransaction()
			.replace(R.id.container, new MainFragment(), "main")
			.add(R.id.footer, new FooterFragment(), "footer")
			.commit();
	}
}
