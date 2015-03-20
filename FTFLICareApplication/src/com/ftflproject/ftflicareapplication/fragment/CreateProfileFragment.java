package com.ftflproject.ftflicareapplication.fragment;



import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DoctorTableDataSource;
import com.ftflproject.ftflicareapplication.database.ProfileTableDataSource;
import com.ftflproject.ftflicareapplication.model.ProfileModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;


import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

public class CreateProfileFragment extends Fragment implements OnClickListener{
	private EditText mNameEditText, mAgeEditText, mWeightEditText, mHeightEditText;
private ProfileModel profileModelObj;
private RadioGroup radioSexGroup;
private RadioButton radioSexButton;



Editor editor;
Button submitButton, deleteButton;

private RadioButton female;
private RadioButton male;
String profileString;
View rootView;
ProfileModel profileObject;
private Context thiscontext;
private ProfileTableDataSource profileTableObject;
private SharedPreferences settings;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 rootView = inflater.inflate(R.layout.fragment_create_profile, container, false);
		 thiscontext = container.getContext();
		 settings = thiscontext.getSharedPreferences(FTFLConstants.PREFS_NAME, thiscontext.MODE_PRIVATE);
		 initialize();
		submitButton.setOnClickListener(this);
        return rootView;
	}
	public void initialize() {

		mNameEditText = (EditText) rootView.findViewById(R.id.editName);
		mAgeEditText = (EditText) rootView.findViewById(R.id.editAge);

		
		mWeightEditText = (EditText) rootView.findViewById(R.id.editWeight);
		
		mHeightEditText = (EditText) rootView.findViewById(R.id.editHeight);
		
		submitButton = (Button) rootView.findViewById(R.id.buttonSubmit);
		
		
		
		

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		
		int selectedId = radioSexGroup.getCheckedRadioButtonId();
		
		radioSexButton = (RadioButton) rootView.findViewById(selectedId);
		
		String gender = radioSexButton.getText().toString();
		
		profileModelObj=new ProfileModel(mNameEditText.getText().toString(), gender, mAgeEditText.getText().toString(), mWeightEditText.getText().toString(), mHeightEditText.getText().toString());
		
		
		profileTableObject = new ProfileTableDataSource(thiscontext);
        
		long numberOfRowAdded =profileTableObject.createProfile(profileModelObj);
		SharedPreferences.Editor edit = settings.edit();
		 
		// Set/Store data
		edit.putInt(FTFLConstants.PROFILE_ID,(int) numberOfRowAdded);
	  // Commit the changes
		edit.commit();

		Toast.makeText(getActivity(), String.valueOf(numberOfRowAdded),
				Toast.LENGTH_SHORT).show();Toast.makeText(getActivity(), ""+numberOfRowAdded, 1000).show();
				Intent intent = getActivity().getIntent();
				 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
				 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
				 getActivity().overridePendingTransition(0, 0);
				 getActivity().finish();
				  
				 getActivity().overridePendingTransition(0, 0);
				 startActivity(intent); 
	
	}
	
	
}
