package com.ftflproject.ftflicareapplication.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.fragment.EditDoctorsProfileFragment;
import com.ftflproject.ftflicareapplication.fragment.EditProfileFragment;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.model.ProfileModel;
import com.ftflproject.ftflicareapplication.model.VaccinationModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;

public class ProfileListAdapter extends ArrayAdapter<ProfileModel> {
	private final Activity mContext;
	FragmentManager fragmentManager;
	private int profileId=1;
	private LayoutInflater inflater;
	 FragmentManager fragmentMng;
	 Bundle bundleedit;
	 EditProfileFragment editfragobj; 
	 View rowView;
	 private SharedPreferences settings;
	 private ImageView profilePic;
	static class ViewHolder {
		public TextView name, age;
		ImageView edit,delete;
		LinearLayout activeProfile;
		
	}

	private ArrayList<ProfileModel> objectArray;
	private LinearLayout activeProfile;

	public ProfileListAdapter(Activity context,
			ArrayList<ProfileModel> objectArray) {
		super(context, R.layout.each_profile_row, objectArray);
		this.mContext = context;
		this.objectArray = objectArray;
		fragmentMng =  mContext.getFragmentManager();
		 bundleedit = new Bundle();
		 editfragobj = new  EditProfileFragment();
		 settings = mContext.getSharedPreferences(FTFLConstants.PREFS_NAME, mContext.MODE_PRIVATE);
	     //server.setText(servername);  // EditText
		 profileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
    
		 rowView = convertView;

		if (convertView == null) {

		
			inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_profile_row, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();

			

			viewHolder.name = (TextView) rowView.findViewById(R.id.nameTextView);

			viewHolder.age = (TextView) rowView
					.findViewById(R.id.ageTextView);
			viewHolder.edit = (ImageView) rowView
					.findViewById(R.id.editImage);
			viewHolder.delete = (ImageView) rowView
					.findViewById(R.id.deleteImage);
			activeProfile = (LinearLayout) rowView
					.findViewById(R.id.selectLayout);
			
			profilePic = (ImageView) rowView
					.findViewById(R.id.profileImageView);
             

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();

		holder.name.setText(objectArray.get(position).getmName());

		
		holder.age.setText(objectArray.get(position).getmAge());
		if(objectArray.get(position).getmId()==profileId){
			profilePic.setImageResource(R.drawable.ic_launcher);
			}
		holder.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(objectArray.get(position).getmId()));
				 editfragobj.setArguments( bundleedit);
				 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj,"edit_Doctor")
				.commit();
				//Toast.makeText(getContext(), "edit"+objectArray.get(position).getmId(), 1000).show();
			}
		});
		
		holder.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), "delete"+objectArray.get(position).getmId(), 1000).show();
				 
					// set Fragmentclass Arguments
				 bundleedit.putString(FTFLConstants.SELECTED_ID, String.valueOf(objectArray.get(position).getmId()));
					 editfragobj.setArguments( bundleedit);
					 fragmentMng.beginTransaction().replace(R.id.layout,editfragobj,"edit_Doctor")
					.commit();
			}
		});
		
activeProfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), "profile changed"+objectArray.get(position).getmId(), 1000).show();
				SharedPreferences.Editor edit = settings.edit();
				 
				// Set/Store data
				edit.putInt(FTFLConstants.PROFILE_ID,objectArray.get(position).getmId());
			  // Commit the changes
				edit.commit();
				Intent intent = mContext.getIntent();
				mContext.finish();
				mContext.startActivity(intent);
			}
		});
		//holder.delte.setText(objectArray.get(position).getmAge());

		return rowView;
	}
}