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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.adapter.DoctorListAdapter;
import com.ftflproject.ftflicareapplication.database.DoctorTableDataSource;
import com.ftflproject.ftflicareapplication.fragment.CreateDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.EditDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.ViewDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.FTFLICareFunctions;

public class DoctorListActivity extends ActionBarActivity {

	private DoctorTableDataSource doctorTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<DoctorModel> doctorList;
	private int profileId;
	private ListView doctorListView, upcomingVaccineListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
	private Spinner spinner;
	Fragment fragment;
	FragmentManager fragmentMng;
	Bundle bundleedit;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_list);
		 fragmentMng = getFragmentManager();
		 bundleedit = new Bundle();
		 settings = getSharedPreferences(FTFLConstants.PREFS_NAME, this.MODE_PRIVATE);
		 profileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
		// Toast.makeText(this, "profile id"+profileId, 1000).show();
		
		
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		doctorTableObject = new DoctorTableDataSource(this);
		doctorList=doctorTableObject.getAllDoctor(profileId);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		DoctorListAdapter adapter = new DoctorListAdapter(
				DoctorListActivity.this, doctorList);
		doctorListView = (ListView) findViewById(R.id.doctorList);
		doctorListView.setAdapter(adapter);

		doctorListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								DoctorListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								"View",
								"Edit",
								"Delete" };
						alertDialog.setTitle(getString(R.string.options));
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = doctorList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:
											// EDIT 1 code here
											
										/*	 
											Toast.makeText(
													getApplicationContext(),
													"view "
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();*/
											
											 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
											// set Fragmentclass Arguments
											 ViewDoctorsProfileFragment viewfragobj = new  ViewDoctorsProfileFragment();
											 viewfragobj.setArguments( bundleedit);
											 fragmentMng.beginTransaction().replace(R.id.layout,viewfragobj,"edit_Doctor")
											.commit();
											 
											break;
										case 1:

											/*Toast.makeText(
													getApplicationContext(),
													"edit"
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();*/
										
											 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
											// set Fragmentclass Arguments
											 EditDoctorsProfileFragment editfragobj = new  EditDoctorsProfileFragment();
											 editfragobj.setArguments( bundleedit);
											 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj,"edit_Doctor")
											.commit();
											// function 2 code here
											break;

										case 2:
											DoctorTableDataSource mDoctorTableDS = new DoctorTableDataSource(
													getApplicationContext());

											mDoctorTableDS.deleteDoctor(selectedId); //
											Intent intent = new Intent(
													DoctorListActivity.this,
													DoctorListActivity.class);
											startActivity(intent);
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
	
	 		   // Toast.makeText(parent.getContext(),String.valueOf(spinner.getSelectedItem()+"fdfs"+id), Toast.LENGTH_LONG).show();

	 		}

	 	
	      

		// Current Date VaccineList
		

		
		// upcoming Date Vaccine List

	

		

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateDoctorsProfileFragment();

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
