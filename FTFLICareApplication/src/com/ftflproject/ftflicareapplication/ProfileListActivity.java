package com.ftflproject.ftflicareapplication;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ftflproject.ftflicareapplication.adapter.DoctorListAdapter;
import com.ftflproject.ftflicareapplication.adapter.ProfileListAdapter;
import com.ftflproject.ftflicareapplication.database.DoctorTableDataSource;
import com.ftflproject.ftflicareapplication.database.ProfileTableDataSource;
import com.ftflproject.ftflicareapplication.database.VaccineTableDataSource;
import com.ftflproject.ftflicareapplication.fragment.CreateDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.CreateProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.EditDietChartFragment;
import com.ftflproject.ftflicareapplication.fragment.EditDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.ViewDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.model.ProfileModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.FTFLICareFunctions;

public class ProfileListActivity extends ActionBarActivity {

	private ProfileTableDataSource profileTableObject;
	
	private ArrayList<ProfileModel> profileList;
	private int profileId = 1;
	private ListView profileListView, upcomingVaccineListView;
	private TextView addProfile;
	private int selectedId;
	private FTFLICareFunctions functions;
	private Spinner spinner;
	Fragment fragment;
	FragmentManager fragmentMng;
	Bundle bundleedit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_list);
		 fragmentMng = getFragmentManager();
		 bundleedit = new Bundle();
		/*addProfile=(TextView) findViewById(R.id.addProfile);
		addProfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				    Toast.makeText(getApplicationContext(),String.valueOf(spinner.getSelectedItem()+"fdfs"), Toast.LENGTH_LONG).show();
			}
		});*/
		
		functions = new FTFLICareFunctions();
		

		profileTableObject = new ProfileTableDataSource(this);
		profileList=profileTableObject.getAllProfile();
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		ProfileListAdapter adapter = new ProfileListAdapter(
				ProfileListActivity.this, profileList);
		profileListView = (ListView) findViewById(R.id.profileList);
		profileListView.setAdapter(adapter);
		/*profileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});*/
/*
		doctorListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								ProfileListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.view_diet),
								getString(R.string.edit_diet),
								getString(R.string.delete_diet) };
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
											
											 
											Toast.makeText(
													getApplicationContext(),
													"view "
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();
											
											 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
											// set Fragmentclass Arguments
											 ViewDoctorsProfileFragment viewfragobj = new  ViewDoctorsProfileFragment();
											 viewfragobj.setArguments( bundleedit);
											 fragmentMng.beginTransaction().replace(R.id.layout,viewfragobj,"edit_Doctor")
											.commit();
											 
											break;
										case 1:

											Toast.makeText(
													getApplicationContext(),
													"edit"
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();
										
											 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(selectedId));
											// set Fragmentclass Arguments
											 EditDoctorsProfileFragment editfragobj = new  EditDoctorsProfileFragment();
											 editfragobj.setArguments( bundleedit);
											 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj,"edit_Doctor")
											.commit();
											// function 2 code here
											break;

										case 2:
											// function 2 code here
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
	
	 		   // Toast.makeText(parent.getContext(),String.valueOf(spinner.getSelectedItem()+"fdfs"+id), Toast.LENGTH_LONG).show();
*/
	 		}

	 	
	      

		// Current Date VaccineList
		

		
		// upcoming Date Vaccine List

	

		

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateProfileFragment();

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
