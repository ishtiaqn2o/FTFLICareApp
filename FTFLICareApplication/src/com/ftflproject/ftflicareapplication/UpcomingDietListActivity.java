package com.ftflproject.ftflicareapplication;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class UpcomingDietListActivity extends ActionBarActivity {

	private DietTableDataSource dietTableObject;
	private ArrayList<String> upcommingDates;

	private ArrayList<DietModel> upcomingDietObject;
	private ListView todaysDietListView, upcomingDietListView;
	private String mSelectedDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;

	Fragment fragment;
	private String date;
	private ArrayList<DietModel> dietListByDate;
	private int profileId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming_diethome);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		date = getIntent().getExtras().getString(FTFLConstants.SELECTED_DATE);
		profileId = getIntent().getExtras().getInt(FTFLConstants.PROFILE_ID);
		//Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
		dietTableObject = new DietTableDataSource(this);
		dietListByDate = dietTableObject
				.getCurrentDateDietList(profileId, date);

		OneDayChartListAdapter adapter = new OneDayChartListAdapter(
				UpcomingDietListActivity.this, dietListByDate);
		todaysDietListView = (ListView) findViewById(R.id.dailyDietList);
		todaysDietListView.setAdapter(adapter);
		todaysDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								UpcomingDietListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								"View",
								"Edit",
								"Delete" };
						alertDialog.setTitle(getString(R.string.options));
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = dietListByDate.get(position).getmDietId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:
											
											
											FragmentManager fragmentManager = getFragmentManager();
											Bundle bundle = new Bundle();
											bundle.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											ViewDietChartFragment fragobj = new ViewDietChartFragment();
											fragobj.setArguments(bundle);
											fragmentManager
													.beginTransaction()
													.replace(R.id.layout,
															fragobj).commit();
											break;
										case 1:

											/*Toast.makeText(
													getApplicationContext(),
													String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();*/
											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditDietChartFragment editfragobj = new EditDietChartFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj)
													.commit();
											// function 2 code here
											break;

										case 2:
											DietTableDataSource mDietTableDS = new DietTableDataSource(
													getApplicationContext());

											mDietTableDS.deleteDiet(selectedId); //
											Intent intent = new Intent(
													UpcomingDietListActivity.this,
													DietListActivity.class);
											startActivity(intent);// function 2
																	// code here
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
		// upcoming Date Diet List

	}

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateDietChartFragment();

		fragmentManager.beginTransaction().replace(R.id.layout, fragment)
				.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			//Toast.makeText(getApplicationContext(), "fsdfsdfsdf", 1100).show();

			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
