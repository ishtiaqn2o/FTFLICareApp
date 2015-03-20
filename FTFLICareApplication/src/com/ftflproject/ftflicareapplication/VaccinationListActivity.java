package com.ftflproject.ftflicareapplication;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.adapter.VaccinationListAdapter;
import com.ftflproject.ftflicareapplication.database.VaccineTableDataSource;
import com.ftflproject.ftflicareapplication.fragment.CreateVaccinationFragment;
import com.ftflproject.ftflicareapplication.fragment.EditVaccinationFragment;
import com.ftflproject.ftflicareapplication.model.VaccinationModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.FTFLICareFunctions;

public class VaccinationListActivity extends ActionBarActivity {

	private VaccineTableDataSource vaccineTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<VaccinationModel> vaccineList;
	private int profileId = 1;
	private ListView todaysVaccineListView, upcomingVaccineListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
	private Spinner spinner;
	Fragment fragment;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaccination_list);
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();
		settings = getSharedPreferences(FTFLConstants.PREFS_NAME, this.MODE_PRIVATE);
		 profileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
		// Toast.makeText(this, "profile id"+profileId, 1000).show();
		vaccineTableObject = new VaccineTableDataSource(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		spinner = (Spinner) findViewById(R.id.selectList);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.vaccineListSpinner,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(spinnerAdapter);
		
		
		
		
	     spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

	 		@Override
	 		public void onItemSelected(AdapterView<?> parent, View view,
	 				int position, long id) {
	 			// TODO Auto-generated method stub
	 		if(id==0){
	 			vaccineList = vaccineTableObject.getTodaysVaccinationList(profileId,
	 					mCurrentDate);
	 			selectedList();
	 		}else if(id==1){
	 			vaccineList = vaccineTableObject.getRemainingVaccinationList(profileId,
	 					mCurrentDate);
	 			selectedList();
	 		}else{
	 			vaccineList = vaccineTableObject.getGivenVaccinationList(profileId,
	 					mCurrentDate);
	 			selectedList();
	 		}
	 		   // Toast.makeText(parent.getContext(),String.valueOf(spinner.getSelectedItem()+"fdfs"+id), Toast.LENGTH_LONG).show();

	 		}

	 		@Override
	 		public void onNothingSelected(AdapterView<?> parent) {
	 			// TODO Auto-generated method stub
	 			
	 		}
	 	});
	      

		// Current Date VaccineList
		

		}
		// upcoming Date Vaccine List

	public void selectedList(){

		VaccinationListAdapter adapter = new VaccinationListAdapter(
				VaccinationListActivity.this, vaccineList);
		todaysVaccineListView = (ListView) findViewById(R.id.vaccineList);
		todaysVaccineListView.setAdapter(adapter);

		todaysVaccineListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_diet),
								getString(R.string.edit_diet),
								getString(R.string.delete_diet) };
						alertDialog.setTitle(getString(R.string.options));
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = vaccineList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:
											// EDIT 1 code here
											/*
											 * Intent editIntent = new Intent(
											 * FTFLICareHomeActivity.this,
											 * FTFLICareEditVaccineActivity
											 * .class); editIntent .putExtra(
											 * FTFLICareConstants.SELECTED_ID,
											 * selectedId);
											 * startActivity(editIntent);
											 */
											Toast.makeText(
													getApplicationContext(),
													"view "
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();
											
											 
											break;
										case 1:

											Toast.makeText(
													getApplicationContext(),
													"edit"
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();
											FragmentManager fragmentMng = getFragmentManager();
											Bundle bundleedit = new Bundle();
											 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
											// set Fragmentclass Arguments
											 EditVaccinationFragment editfragobj = new EditVaccinationFragment();
											 editfragobj.setArguments( bundleedit);
											 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj,"edit_Vaccination")
											.commit();
											// function 2 code here
											break;

										case 2:
											VaccineTableDataSource mVaccineTableDS = new VaccineTableDataSource(
													getApplicationContext());

											mVaccineTableDS
													.deleteVaccine(selectedId); //
											Intent intent = new Intent(
													VaccinationListActivity.this,
													VaccinationListActivity.class);
											startActivity(intent);
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
	}

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateVaccinationFragment();

		fragmentManager.beginTransaction().replace(R.id.layout, fragment)
				.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
			Toast.makeText(getApplicationContext(), "fsdfsdfsdf", 1100).show();

			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
