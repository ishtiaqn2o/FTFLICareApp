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

import com.ftflproject.ftflicareapplication.adapter.MedicalHistoryListAdapter;
import com.ftflproject.ftflicareapplication.database.MedicalHistoryTableDataSource;
import com.ftflproject.ftflicareapplication.fragment.CreateMedicalHistoryFragment;
import com.ftflproject.ftflicareapplication.fragment.EditMedicalHistoryFragment;
import com.ftflproject.ftflicareapplication.fragment.ViewMedicalHistoryFragment;
import com.ftflproject.ftflicareapplication.model.MedicalHistoryModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.FTFLICareFunctions;

public class MedicalHistoryListActivity extends ActionBarActivity {

	private MedicalHistoryTableDataSource historyTableObject;

	private ArrayList<MedicalHistoryModel> medicalHistoryList;
	private int profileId;
	private ListView medicalHistoryListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;
	private Spinner spinner;
	Fragment fragment;
	FragmentManager fragmentMng;
	Bundle bundleedit;

	private MedicalHistoryTableDataSource medicalHistoryTableObject;

	private ListView MedicalHistoryListView;

	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_history_list);

		fragmentMng = getFragmentManager();
		bundleedit = new Bundle();

		settings = getSharedPreferences(FTFLConstants.PREFS_NAME,
				this.MODE_PRIVATE);
		profileId = settings.getInt(FTFLConstants.PROFILE_ID, 0);
		//Toast.makeText(this, "profile id" + profileId, 1000).show();

		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		medicalHistoryTableObject = new MedicalHistoryTableDataSource(this);
		medicalHistoryList = medicalHistoryTableObject
				.getAllMedicalHistory(profileId);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		MedicalHistoryListAdapter adapter = new MedicalHistoryListAdapter(
				MedicalHistoryListActivity.this, medicalHistoryList);
		MedicalHistoryListView = (ListView) findViewById(R.id.medicalHistoryList);
		MedicalHistoryListView.setAdapter(adapter);

		MedicalHistoryListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								MedicalHistoryListActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								"View",
								"Edit",
								"Delete" };
						alertDialog.setTitle(getString(R.string.options));
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = medicalHistoryList.get(position).getmId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:
											// EDIT 1 code here

											/*Toast.makeText(
													getApplicationContext(),
													"view "
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();*/

											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											ViewMedicalHistoryFragment viewfragobj = new ViewMedicalHistoryFragment();
											viewfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															viewfragobj,
															"edit_Doctor")
													.commit();

											break;
										case 1:

											/*Toast.makeText(
													getApplicationContext(),
													"edit"
															+ String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();*/

											bundleedit.putString(
													FTFLConstants.SELECTED_ID,
													String.valueOf(selectedId));
											// set Fragmentclass Arguments
											EditMedicalHistoryFragment editfragobj = new EditMedicalHistoryFragment();
											editfragobj
													.setArguments(bundleedit);
											fragmentMng
													.beginTransaction()
													.replace(R.id.layout,
															editfragobj,
															"edit_Doctor")
													.commit();
											// function 2 code here
											break;

										case 2:
											MedicalHistoryTableDataSource mHistoryTableDS = new MedicalHistoryTableDataSource(
													getApplicationContext());

											mHistoryTableDS
													.deleteHistory(selectedId); //
											Intent intent = new Intent(
													MedicalHistoryListActivity.this,
													MedicalHistoryListActivity.class);
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

	// Current Date VaccineList

	// upcoming Date Vaccine List

	public void add(View v) {
		FragmentManager fragmentManager = getFragmentManager();

		fragment = new CreateMedicalHistoryFragment();

		fragmentManager.beginTransaction().replace(R.id.layout, fragment)
				.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// do your own thing here
		//	Toast.makeText(getApplicationContext(), "fsdfsdfsdf", 1100).show();

			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
