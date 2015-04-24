package com.skew.skytouch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;

public class FoodMenuActivity extends ActionBarActivity {
// 	change the backgroud of activity after time interval
	TableLayout menu;
	int id;
	static ArrayList mainmenuList= new ArrayList();
//	Set mainmenuList = new HashSet();
	static Map order = new HashMap();
	static float Bill = 0;
	static String mainmenu = "";
	static ArrayList dishlist = new ArrayList();
	Button reviewOrder;
//	LayoutParams buttonparam = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_menu);
		/*Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);*/
		
		reviewOrder = (Button) findViewById(R.id.ReviewOrder1);
		reviewOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
		
		Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
		startActivity(intent);
		finish();
			}
		});
		
		
		if(MainActivity.loadmenu){
		setMainMenu();}
		for(int i=0;i<mainmenuList.size();i++){
		    // get a reference for the TableLayout
			menu = (TableLayout) findViewById(R.id.menutable);	
		    TableRow row = new TableRow(this);
		    ImageView icon = new ImageButton(this);
		    if(mainmenuList.get(i).toString().contains("VEG STARTER")){
		    	icon.setBackgroundResource(R.drawable.stuffmushroomhariyalikabab);}
		    else if(mainmenuList.get(i).toString().contains("CHINESE STARTER")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("SHORBA & SOUP")){
		    	icon.setBackgroundResource(R.drawable.tandooribabycorn);
		    }else if(mainmenuList.get(i).toString().contains("SALAD, PAPAD, RAITA")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("MAIN COURSE VEGETARIAN")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("INDIAN BREAD")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("BASMATI-KA-KAMAL")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("MAIN COURSE NON VEGETARIAN")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else if(mainmenuList.get(i).toString().contains("SALAD, FISH, EGGS")){
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }else{
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }
//		    LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		    
	        Button mainmenulist = new Button(this);
	        mainmenulist.setText("\t"+mainmenuList.get(i).toString()+"\t");
	        mainmenulist.setId(i);
	        mainmenulist.setGravity(Gravity.CENTER);
	        mainmenulist.setTypeface(null, Typeface.BOLD);
/*	        mainmenulist.setBackgroundColor(getResources().getColor(R.color.orange));
*/		    mainmenulist.setTextColor(getResources().getColor(R.color.White));
//			mainmenulist.setLayoutParams(lp);

	        // Set width
//		    row.addView(icon);
			
		    row.addView(mainmenulist);
		    
		    // add the TableRow to the TableLayout
		    menu.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		    mainmenulist.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					  finish();
					  Intent intent = new Intent(getApplicationContext(),DishesActivity.class);
					  startActivity(intent);
					  id = view.getId();
					  mainmenu = mainmenuList.get(id).toString();
//					  Toast.makeText(getApplicationContext(), "clicked: "+mainmenuList.get(id), Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
/*	public void HandleClick(View view) {
		Toast.makeText(getApplicationContext(), view.getId(), Toast.LENGTH_LONG).show();
    }
*/
	public void setMainMenu(){
		InputStream inputStream = getResources().openRawResource(R.raw.dishtable);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 	try {
	 		br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
	 		        // use comma as separator
				String[] dishes = line.split(cvsSplitBy);
				if(!mainmenuList.contains(dishes[0])){
				mainmenuList.add(dishes[0]);
				}
				dishlist.add(dishes[0]+","+dishes[1]+","+dishes[2]);
	 		}
	 	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		MainActivity.loadmenu = false;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_menu, menu);
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
