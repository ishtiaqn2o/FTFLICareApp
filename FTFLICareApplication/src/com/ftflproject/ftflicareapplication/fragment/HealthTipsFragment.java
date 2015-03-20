package com.ftflproject.ftflicareapplication.fragment;



import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DatabaseHelperClass;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.SetDateOnClickDialog;
import com.ftflproject.ftflicareapplication.util.setTimeOnclickDialog;



public class HealthTipsFragment extends Fragment {
	
	 Context thiscontext;
	 ListView listView;
	 View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		String[] values = new String[] { 
				"1. Take 10 deep breaths filling air from the diaphragm up and exhaling completely," +
				" whenever you feel stressed.",
				
				
				"2. Drink 3 liters of good clean water everyday in addition to any other beverage you have",
				
				"3. Eat something raw from the plant kingdom at every meal and make it the biggest part of the meal: vegetables, fruits, legumes, whole grains should be the bulk of what you eat.",
				
				
                "4. Go to sleep at the same time and get up at same time 6-or-more days of the week. Get at least 8 hours of sleep daily.",
                
                "5. Take a clinically validated, whole food based supplement (www.juiceplus.com)", 
                
                "6. Eat processed foods sparingly, less than once a week if possible",
                
                "7. Make breakfast and lunch your biggest meals, preferably between 10 am and 4 pm when your digestions is strongest. Try to eat these two meals at the same time each day; your digestive organs will get the benefit of expecting to go to work on a schedule. This helps tremendously with proper digestion and absorption of nutrients.", 
                
                "8. Get outside and move a little bit each day. Fresh air and deep breathing are far more important than getting a workout.",
                
                "9. Start each day with a Green Drink.",
                
		"10. Think good, healthy thoughts." };
		
		rootView = inflater.inflate(R.layout.fragment_health_tips, container, false);
		
		listView=(ListView) rootView.findViewById(R.id.list);
	            // use your custom layout
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(thiscontext,
		              android.R.layout.simple_list_item_1, android.R.id.text1, values);
			  listView.setAdapter(adapter); 
        return rootView;
        
	}


	
}
