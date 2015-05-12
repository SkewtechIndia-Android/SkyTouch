package com.skew.skytouch;
import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	Button foodmenu;
	static boolean loadmenu = true;
	Button feedback;
	Button registerMe;
	Button todayspecialbtn;
	Button yourorders;
	Button closeapp;
	Button tablebooking;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ParseAnalytics.trackAppOpenedInBackground(getIntent());
		// Save the current Installation to Parse.
		ParseInstallation.getCurrentInstallation().saveInBackground();
		// check for the network and then procees for the feedback and other option
		WebView mWebView = null;
	    mWebView = (WebView) findViewById(R.id.webView1);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl("file:///android_asset/slide.html");
	    todayspecialbtn = (Button) findViewById(R.id.Todayspecial);
		feedback = (Button) findViewById(R.id.feedback);
	    foodmenu = (Button)findViewById(R.id.FoodMenu);
	    registerMe = (Button) findViewById(R.id.Registerme);
	    yourorders = (Button) findViewById(R.id.yourorders);
	    closeapp = (Button) findViewById(R.id.closeapp);
	    tablebooking = (Button) findViewById(R.id.bookatable);
	    
	    tablebooking.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),BookatableActivity.class);
				  startActivity(intent);
			}
		});
		
	    
	    closeapp.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				MainActivity.this.finish();
				System.exit(0);	
			}
		});
	    
	    yourorders.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  Intent intent = new Intent(getApplicationContext(),YourOrdersActivity.class);
				  startActivity(intent);
				  finish();
			}
		});
	    
		foodmenu.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				  startActivity(intent);
				  finish();
			}
		});
		
		todayspecialbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),TodaySpecialActivity.class);
				  startActivity(intent);
			}
		});
		
		feedback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  finish();
				  Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
				  startActivity(intent);
			}
		});
		
		registerMe.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				  Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
				  startActivity(intent);
				  finish();
			}
		});
	}

	public void onBackPressed() {
	finish();
	System.exit(0);		
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
