package com.ftflproject.ftflicareapplication.fragment;



import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DatabaseHelperClass;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.database.DoctorTableDataSource;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.SetDateOnClickDialog;
import com.ftflproject.ftflicareapplication.util.setTimeOnclickDialog;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDoctorsProfileFragment extends Fragment implements OnClickListener{
	private EditText mDoctortNameEditText;
	private EditText mSpecialityEditText;
	private EditText mPhoneEditText;
	private EditText mEmailEditText;
	private EditText mChamberEditText;
	private int mProfileId=1;
	private String mDoctortName, mSpeciality, mPhone,mEmail, mChamber;
	public int mIsAlarmCecked = 0;
	private DatabaseHelperClass dbHelper;
    private DoctorModel doctorModelObject;
	private DoctorTableDataSource doctorTableObject;
	private Button mCreateButton;
	 Context thiscontext;
	View rootView;
	private SharedPreferences settings;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		settings = thiscontext.getSharedPreferences(FTFLConstants.PREFS_NAME, thiscontext.MODE_PRIVATE);
		 mProfileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
		 Toast.makeText(thiscontext, "profile id"+mProfileId, 1000).show();
		 rootView = inflater.inflate(R.layout.fragment_create_doctors_profile, container, false);
		 mCreateButton=(Button) rootView.findViewById(R.id.buttonSubmit);
	     mCreateButton.setOnClickListener(this);
	     initialize();
        return rootView;
	}
	
	public void initialize(){
		mDoctortNameEditText=(EditText) rootView.findViewById(R.id.doctorNameEditText);
		mSpecialityEditText=(EditText) rootView.findViewById(R.id.specialityEditText);
		mPhoneEditText=(EditText) rootView.findViewById(R.id.phoneEditText);
		mEmailEditText=(EditText) rootView.findViewById(R.id.emailEditText);
		mChamberEditText=(EditText) rootView.findViewById(R.id.chamberEditText);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mDoctortName = mDoctortNameEditText.getText().toString();

		mSpeciality = mSpecialityEditText.getText().toString();

		mPhone = mPhoneEditText.getText().toString();
		mEmail = mEmailEditText.getText().toString();
		mChamber = mChamberEditText.getText().toString();
		
		doctorModelObject = new DoctorModel(mDoctortName, mSpeciality, mPhone, mEmail, mChamber);
		

		doctorTableObject = new DoctorTableDataSource(thiscontext);
        doctorModelObject.setmProfileId(mProfileId);
		long numberOfRowAdded =doctorTableObject.createDoctorProfile(doctorModelObject);
		

		Toast.makeText(getActivity(), String.valueOf(numberOfRowAdded),
				Toast.LENGTH_SHORT).show();
		Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 
	}
}
