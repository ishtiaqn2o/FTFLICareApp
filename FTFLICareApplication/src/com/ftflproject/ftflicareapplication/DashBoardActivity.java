package com.ftflproject.ftflicareapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.fragment.CreateDietChartFragment;
import com.ftflproject.ftflicareapplication.fragment.CreateDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.EditProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.EmergencyFragment;
import com.ftflproject.ftflicareapplication.fragment.FragmentGallery;
import com.ftflproject.ftflicareapplication.fragment.GeneralFragment;
import com.ftflproject.ftflicareapplication.fragment.HealthTipsFragment;

import com.ftflproject.ftflicareapplication.fragment.CreateProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.CreateVaccinationFragment;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DashBoardActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDrawarMenus;
	private FragmentManager fragmentManager;
	Fragment fragment;
	private TextView dietButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		dietButton=(TextView) findViewById(R.id.dietChartButton);
		dietButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent11 = new Intent(getApplicationContext(),
						DietListActivity.class);
				startActivity(intent11);
			}
		});
		//image loader
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true).cacheOnDisk(true).build();
ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
		getApplicationContext()).defaultDisplayImageOptions(
		defaultOptions).build();
ImageLoader.getInstance().init(config);
		
		
		
		
		
		
		
		mTitle = mDrawerTitle = getTitle();
		mDrawarMenus = getResources().getStringArray(R.array.drawer_menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		getSupportActionBar().setLogo(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.item_drawer_list, mDrawarMenus));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setLogo(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * "open drawer" description for
										 * accessibility
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	public void dietChart(View v) {
		
		Intent intent10 = new Intent(getApplicationContext(),
				DietListActivity.class);
		startActivity(intent10);
	}
	public void vaccination(View v) {
		
		Intent intent = new Intent(getApplicationContext(),
				VaccinationListActivity.class);
		startActivity(intent);
	}
	public void myDoctor(View v) {
		//Toast.makeText(this, "ok", 1000).show();
		Intent intent = new Intent(getApplicationContext(),
				DoctorListActivity.class);
		startActivity(intent);
	}
	public void medicalHistory(View v) {
		//Toast.makeText(this, "ok", 1000).show();
		Intent intent = new Intent(getApplicationContext(),
				MedicalHistoryListActivity.class);
		startActivity(intent);
	}
	public void emergencyCall(View v) {
		/*Toast.makeText(this, "ok", 1000).show();*/
		FragmentManager fragmentManager = getFragmentManager();
		fragment = new EmergencyFragment();
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
		.commit();
		setTitle("Emergency Call");
	}
	public void gallary(View v) {
		/*Toast.makeText(this, "ok", 1000).show();*/
		FragmentManager fragmentManager = getFragmentManager();
		fragment = new FragmentGallery();
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
		.commit();
		setTitle("Gallery");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.home:
			Intent intent = new Intent(getApplicationContext(),
					DashBoardActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_settings:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Boolean isIntent=false;
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:
			isIntent=true;
			Intent intent= new Intent(DashBoardActivity.this, ProfileListActivity.class);
			startActivity(intent);
			
			break;
		case 1:
			
			fragment = new HealthTipsFragment();

			break;

		case 2:
			fragment = new GeneralFragment();
			
			break;

		case 3:
			fragment = new GeneralFragment();
			break;
		case 4:
			fragment = new GeneralFragment();
			
		//	fragment = new CreateMedicalHistory();
			break;
		

		default:
			break;
		}
        if(!isIntent){
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();
        }
		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawarMenus[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
