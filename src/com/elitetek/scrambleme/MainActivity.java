package com.elitetek.scrambleme;

import com.parse.ParseUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements MainFragment.OnFragmentInteractionListener, FooterFragment.OnFragmentInteractionListener {

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
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void fromMainFragment() {
		// code to run from MainFragment
	}

	@Override
	public void fromFooterFragment() {
		// code to run from FooterFragment
	}
}
