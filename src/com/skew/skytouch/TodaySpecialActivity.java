package com.skew.skytouch;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class TodaySpecialActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today_special);
		
		// check if internet connection is available
		
		WebView mWebView = null;
	    mWebView = (WebView) findViewById(R.id.webViewToday);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl("http://meghae.com/resto/admin/todayspecial.php");
	}
	    
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.today_special, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
