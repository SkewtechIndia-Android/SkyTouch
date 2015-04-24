package com.skew.skytouch;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

public class DishesActivity extends ActionBarActivity {
	
	TableLayout dishtable;
	ArrayList<String> selectedDishList = new ArrayList<String>();
	String[] temp ;
	int id;
	String tempDish;
	float tempRate;
	Context context;
	Button reviewOrder;
	int count;
//	int dishcounttmp;
	
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
//		finish();
		Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dishes);
		context = getApplicationContext();
		/*Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);
		Drawable add_icon = getResources().getDrawable(R.drawable.addtocart);*/
		
		reviewOrder = (Button) findViewById(R.id.ReviewOrder);
		reviewOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
		Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
		intent.putStringArrayListExtra("selectedDishList", selectedDishList);
				startActivity(intent);
				finish();
			}
		});
		
		for (Iterator<String> it = FoodMenuActivity.dishlist.iterator(); it.hasNext();) {
				temp = it.next().split(",");
				if(temp[0].contains(FoodMenuActivity.mainmenu)){
					selectedDishList.add(temp[1]+","+temp[2]);
				}
			}
		for(int i=0;i<selectedDishList.size();i++){
		    // get a reference for the TableLayout
			dishtable = (TableLayout) findViewById(R.id.dishtable);	
			tempDish = selectedDishList.get(i).toString().split(",")[0];
		    tempRate = Float.parseFloat(selectedDishList.get(i).toString().split(",")[1]);
//		    dishcounttmp = Integer.parseInt(selectedDishList.get(i).toString().split(",")[2]);
			TableRow row = new TableRow(this);
		    
			ImageView icon = new ImageButton(this);
//		    icon.setBackgroundResource(R.drawable.veg);
		    if(tempDish.contains("Chic")){
		    	icon.setBackgroundResource(R.drawable.chickenleg);}
		    else{
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }
		    // options like kebab, noodles etc can also be added
		    TextView dishtext = new TextView(this);
		    dishtext.setText("\t"+tempDish+" ");
//		    dishtext.setGravity(Gravity.CENTER_VERTICAL);
		    dishtext.setTypeface(null, Typeface.BOLD);
		    dishtext.setTextColor(getResources().getColor(R.color.White));
		    
		    TextView rate = new TextView(this);
		    rate.setText(tempRate+" ");
		    rate.setTypeface(null, Typeface.BOLD);
		    
		    
		    final ImageButton ad = new ImageButton(this);
		    ad.setBackgroundResource(R.drawable.adddish);
		    
/*		    TextView dishcounttmpview = new TextView(this);
		    dishcounttmpview.setText(count+"\t\t");
		    dishcounttmpview.setTypeface(null, Typeface.BOLD);
*/		    
		    ad.setId(i);
		    row.addView(icon);
		    row.addView(dishtext);
		    row.addView(rate);
		    row.addView(ad);
		    row.setGravity(Gravity.CENTER_VERTICAL);
		    
		    
		    if(i%2==0){
			    row.setBackgroundColor(getResources().getColor(R.color.evenrow));	
			    dishtext.setTextColor(getResources().getColor(R.color.White));
			    rate.setTextColor(getResources().getColor(R.color.White));
			    }else{
			    	row.setBackgroundColor(getResources().getColor(R.color.White));	
			    	dishtext.setTextColor(getResources().getColor(R.color.black));
			    	rate.setTextColor(getResources().getColor(R.color.black));
			    }
		    count = 0;
		    // add the TableRow to the TableLayout
		    dishtable.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		    // touch listener
		    
		    // action listerner
		    ad.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					ad.setBackgroundResource(R.drawable.adddisha);
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    view1.setBackgroundResource(R.drawable.addedd); 
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setView(view1); 
				    toast.show();
					  id = view.getId();
					  tempDish = selectedDishList.get(id).toString().split(",")[0];
					  tempRate = Float.parseFloat(selectedDishList.get(id).toString().split(",")[1]);
					if(FoodMenuActivity.order.isEmpty()){
//						Toast.makeText(context,"Fresh Order" , Toast.LENGTH_SHORT).show();
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
						FoodMenuActivity.Bill = tempRate;
					}else{
					if(FoodMenuActivity.order.containsKey(tempDish)){
//						Toast.makeText(context,tempDish+"Already on the order" , Toast.LENGTH_SHORT).show();
						// get the count and increment it
						count = Integer.parseInt(FoodMenuActivity.order.get(tempDish).toString().split("#")[1])+1;
						FoodMenuActivity.order.put(tempDish, tempRate+"#"+count);
						FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
					}else{
					FoodMenuActivity.order.put(tempDish, tempRate+"#"+"1");
					FoodMenuActivity.Bill = FoodMenuActivity.Bill + tempRate;
//					Toast.makeText(context,tempDish+"Added to the order" , Toast.LENGTH_SHORT).show();
					}
					}
//					Toast.makeText(context,tempDish+": added" , Toast.LENGTH_SHORT).show();
//					Toast.makeText(context, "Order: "+FoodMenuActivity.order, Toast.LENGTH_SHORT).show();
//					Toast.makeText(context, "Bill(Approx): "+FoodMenuActivity.Bill, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dishes, menu);
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
