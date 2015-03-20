package com.ftflproject.ftflicareapplication.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DatabaseHelperClass;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.database.VaccineTableDataSource;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.model.VaccinationModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.SetDateOnClickDialog;
import com.ftflproject.ftflicareapplication.util.setTimeOnclickDialog;

public class EditVaccinationFragment extends Fragment implements
		OnClickListener {
	
	private EditText mVaccinNameEditText;
	private EditText mVaccinationDateEditText;
	private EditText mVaccinationNotesEditText;
	
	 Fragment currentFragment;
	 FragmentTransaction fragTransaction;
	
	private String mVaccineName, mVaccineDate,mVaccinationNotes;
	public int mIsAlarmCecked = 0;
	private VaccinationModel vaccineModelObj;
	private DatabaseHelperClass dbHelper;
	private int profileId = 1;
	private VaccineTableDataSource vaccinationTableObject;
	private SetDateOnClickDialog datePickerObj;
	private setTimeOnclickDialog timePickerObj;
	private Button mCreateButton;
	private int selectedId;
	Context thiscontext;
	View rootView;
	   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		thiscontext = container.getContext();
		 selectedId = Integer.valueOf(getArguments().getString(FTFLConstants.SELECTED_ID));
		rootView = inflater.inflate(R.layout.fragment_edit_vaccination,
				container, false);

		mCreateButton = (Button) rootView.findViewById(R.id.createVaccine);
		mCreateButton.setOnClickListener(this);
		initialize();
		setValue();
		return rootView;

	}

	public void initialize() {
		vaccinationTableObject = new VaccineTableDataSource(thiscontext);
		// fetch value the selected diet
		vaccineModelObj = vaccinationTableObject.getVaccineById(selectedId);
		mVaccinNameEditText= (EditText) rootView.findViewById(R.id.vaccineNameEditText);
		mVaccinNameEditText.setFocusableInTouchMode(false);
		mVaccinNameEditText.setEnabled(false);
		mVaccinationDateEditText= (EditText) rootView.findViewById(R.id.pickDayTextView);
		mVaccinationNotesEditText= (EditText) rootView.findViewById(R.id.vaccineStatusEditText);
        
		datePickerObj = new SetDateOnClickDialog(mVaccinationDateEditText, thiscontext);

		

		

	}
public void setValue() {
		
	mVaccinNameEditText.setText(vaccineModelObj.getmVaccine());
	mVaccinationDateEditText.setText(vaccineModelObj.getmDate());
	mVaccinationNotesEditText.setText(vaccineModelObj.getmNotes());

	

		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mVaccineName=mVaccinNameEditText.getText().toString();
		mVaccineDate = mVaccinationDateEditText.getText().toString();
	
        mVaccinationNotes=mVaccinationNotesEditText.getText().toString();
		
        
       VaccinationModel vaccineModelObject = new VaccinationModel(profileId,mVaccineName,mVaccineDate,
        		mVaccinationNotes);
       

		vaccinationTableObject= new VaccineTableDataSource(thiscontext);

		long edited = vaccinationTableObject.editVaccine(selectedId, vaccineModelObject);
       // Toast.makeText(thiscontext, mVaccineName+"d"+mVaccineDate+"f"+mVaccinationNotes, 1000).show();
		 Toast.makeText(thiscontext,""+edited , 1000).show();
		 Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 
		 
		
	}
}
