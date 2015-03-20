package com.ftflproject.ftflicareapplication.fragment;



import com.ftflproject.ftflicareapplication.R;
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



public class EditProfileFragment extends Fragment implements OnClickListener{
	private EditText mNameEditText, mAgeEditText, mWeightEditText, mHeightEditText;
private ProfileModel profileModelObj;
private RadioGroup radioSexGroup;
private RadioButton radioSexButton;

SharedPreferences sharedPref;

Editor editor;
Button submitButton, deleteButton;

private RadioButton female;
private RadioButton male;
String profileString;
View rootView;
ProfileModel profileObject;
private int selectedId;
private Context thiscontext;
private ProfileTableDataSource profileTableObject;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		selectedId = Integer.valueOf(getArguments().getString(FTFLConstants.SELECTED_ID));
		 rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
		 
		 thiscontext = container.getContext();
		 initialize();
		 setValue();
		submitButton.setOnClickListener(this);
        return rootView;
	}
	public void initialize() {

		mNameEditText = (EditText) rootView.findViewById(R.id.editName);
		mAgeEditText = (EditText) rootView.findViewById(R.id.editAge);

		
		mWeightEditText = (EditText) rootView.findViewById(R.id.editWeight);
		
		mHeightEditText = (EditText) rootView.findViewById(R.id.editHeight);
		male = (RadioButton) rootView.findViewById(R.id.radioButtonMale);
		
		female = (RadioButton) rootView.findViewById(R.id.radioButtonFemale);
		submitButton = (Button) rootView.findViewById(R.id.buttonSubmit);
	
		
		
		

	}
	
	public void setValue(){
	profileTableObject = new ProfileTableDataSource(thiscontext);
        
		profileModelObj =profileTableObject.getProfileById(selectedId);
		mNameEditText.setText(profileModelObj.getmName().toString());
		mAgeEditText.setText(profileModelObj.getmAge().toString());

		
		mWeightEditText.setText(profileModelObj.getmWeight().toString());
		
		mHeightEditText.setText(profileModelObj.getmHeight().toString());
		
		if (profileModelObj.getmGender().equals("Male")) {
			male.setChecked(true);
		} else {

			female.setChecked(true);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
		
		int selectedId = radioSexGroup.getCheckedRadioButtonId();
		
		radioSexButton = (RadioButton) rootView.findViewById(selectedId);
		
		String gender = radioSexButton.getText().toString();
		
		profileModelObj=new ProfileModel(mNameEditText.getText().toString(), gender, mAgeEditText.getText().toString(), mWeightEditText.getText().toString(), mHeightEditText.getText().toString());
		profileModelObj.mId=this.selectedId;
		long edited=profileTableObject.editProfile(this.selectedId, profileModelObj);
		
		
		 Intent intent = getActivity().getIntent();
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
		 | Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 getActivity().overridePendingTransition(0, 0);
		 getActivity().finish();
		  
		 getActivity().overridePendingTransition(0, 0);
		 startActivity(intent); 
		Toast.makeText(thiscontext, ""+edited, 1000).show();
		
		

		
	}
	
	
}
