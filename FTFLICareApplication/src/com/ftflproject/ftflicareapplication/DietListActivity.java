package com.ftflproject.ftflicareapplication;



import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.adapter.OneDayChartListAdapter;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.fragment.CreateDietChartFragment;
import com.ftflproject.ftflicareapplication.fragment.EditDietChartFragment;
import com.ftflproject.ftflicareapplication.fragment.ViewDietChartFragment;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.FTFLICareFunctions;






public class DietListActivity extends ActionBarActivity {

	private DietTableDataSource dietTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<DietModel> CurrentDateDietList;
	private ArrayList<DietModel> upcomingDietObject;
	private ListView todaysDietListView, upcomingDietListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
    
    Fragment fragment;
	private SharedPreferences settings;
	private int profileId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diethome);
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();
		
		settings = getSharedPreferences(FTFLConstants.PREFS_NAME, this.MODE_PRIVATE);
		 profileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
		// Toast.makeText(this, "profile id"+profileId, 1000).show();
		
		dietTableObject = new DietTableDataSource(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	//	Current Date DietList
		CurrentDateDietList = dietTableObject.getCurrentDateDietList(profileId,
				mCurrentDate);

		OneDayChartListAdapter adapter = new OneDayChartListAdapter(
				DietListActivity.this, CurrentDateDietList);
		todaysDietListView = (ListView) findViewById(R.id.dailyDietList);
		todaysDietListView.setAdapter(adapter);
		todaysDietListView
		.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						DietListActivity.this);
				// Setting Dialog Title
				final String[] menuList = {
						getString(R.string.view_diet),
						getString(R.string.edit_diet),
						getString(R.string.delete_diet)
						};
				alertDialog.setTitle(getString(R.string.options));
				alertDialog.setIcon(R.drawable.ic_launcher);
				selectedId = CurrentDateDietList.get(position)
						.getmDietId();
				alertDialog.setItems(menuList,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int item) {
								switch (item) {
								case 0:
									// EDIT 1 code here
								/*	Intent editIntent = new Intent(
											FTFLICareHomeActivity.this,
											FTFLICareEditDietActivity.class);
									editIntent
											.putExtra(
													FTFLICareConstants.SELECTED_ID,
													selectedId);
									startActivity(editIntent);*/
									/*Toast.makeText(
											getApplicationContext(),
											String.valueOf(selectedId),
											Toast.LENGTH_SHORT).show();*/
									FragmentManager fragmentManager = getFragmentManager();
									Bundle bundle = new Bundle();
									bundle.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
									// set Fragmentclass Arguments
								    ViewDietChartFragment fragobj = new ViewDietChartFragment();
									fragobj.setArguments(bundle);
									fragmentManager.beginTransaction().replace(R.id.layout, fragobj)
									.commit();
									break;
								case 1:
									
									 
									/* Toast.makeText(
												getApplicationContext(),
												String.valueOf(selectedId),
												Toast.LENGTH_SHORT).show();*/
										FragmentManager fragmentMng = getFragmentManager();
										Bundle bundleedit = new Bundle();
										 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
										// set Fragmentclass Arguments
										 EditDietChartFragment editfragobj = new  EditDietChartFragment();
										 editfragobj.setArguments( bundleedit);
										 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj)
										.commit();
									// function 2 code here
									break;
								
									case 2:
										DietTableDataSource mDietTableDS = new DietTableDataSource(
												getApplicationContext());

										mDietTableDS.deleteDiet(selectedId); //
										Intent intent = new Intent(
												DietListActivity.this,
												DietListActivity.class);
										startActivity(intent);
										break;
									}
							}
						});
				AlertDialog menuDrop = alertDialog.create();
				menuDrop.show();

			}

		});
//upcoming Date Diet List 

/*upcomingDietObject = dietTableObject
		.getUpcommingDietListByDate(mCurrentDate, 1);

upcommingDates = new ArrayList<String>();
for (int i = 0; i < upcomingDietObject.size(); i++) {
	upcommingDates.add(upcomingDietObject.get(i).getmDietDate()
			.toString()
			+ "  " + upcomingDietObject.get(i).getmDay());
}

upcomingDateListAdapter = new ArrayAdapter<String>(this, R.layout.row,
		R.id.label, upcommingDates);
upcomingDietListView = (ListView) findViewById(R.id.upComingDietList);
upcomingDietListView.setAdapter(upcomingDateListAdapter);
upcomingDietListView
		.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String str = upcommingDates.get(position).toString();
				String[] splited = str.split("\\s+");
				String dates = splited[0];
				Toast.makeText(getApplicationContext(), dates,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(FTFLICareHomeActivity.this,
						FTFLICareDietlistBySelectedDate.class);
				intent.putExtra("selectedDate", dates);
				startActivity(intent);

			}
		});*/

		//upcoming Date Diet List 
		
		upcomingDietObject = dietTableObject
				.getUpcommingDietListByDate(mCurrentDate,profileId);

		upcommingDates = new ArrayList<String>();
		for (int i = 0; i < upcomingDietObject.size(); i++) {
			upcommingDates.add(upcomingDietObject.get(i).getmDietDate()
					.toString()
					+ "  " + upcomingDietObject.get(i).getmDay());
		}

		upcomingDateListAdapter = new ArrayAdapter<String>(this, R.layout.row,
				R.id.label, upcommingDates);
		upcomingDietListView = (ListView) findViewById(R.id.upComingDietList);
		upcomingDietListView.setAdapter(upcomingDateListAdapter);
		upcomingDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						String str = upcommingDates.get(position).toString();
						String[] splited = str.split("\\s+");
						String dates = splited[0];
					/*	Toast.makeText(getApplicationContext(), dates,
								Toast.LENGTH_SHORT).show();*/
						Intent intent = new Intent(DietListActivity.this,
								UpcomingDietListActivity.class);
						intent.putExtra(FTFLConstants.SELECTED_DATE, dates);
						intent.putExtra(FTFLConstants.PROFILE_ID, profileId);
						
						startActivity(intent);

					}
				});

      
    }
	public void add(View v){
		FragmentManager fragmentManager = getFragmentManager();
	    

		fragment = new CreateDietChartFragment();
	

    fragmentManager.beginTransaction().replace(R.id.layout, fragment).commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        //do your own thing here
	    	/*Toast.makeText(getApplicationContext(), "fsdfsdfsdf", 1000).show();*/
	    	
	    	finish();
	        return true;
	    default: return super.onOptionsItemSelected(item);  
	    }
	}

 
}
