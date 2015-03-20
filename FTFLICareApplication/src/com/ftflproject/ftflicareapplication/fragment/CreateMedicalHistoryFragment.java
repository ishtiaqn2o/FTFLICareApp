package com.ftflproject.ftflicareapplication.fragment;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DatabaseHelperClass;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.database.DoctorTableDataSource;
import com.ftflproject.ftflicareapplication.database.MedicalHistoryTableDataSource;
import com.ftflproject.ftflicareapplication.database.VaccineTableDataSource;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.model.MedicalHistoryModel;
import com.ftflproject.ftflicareapplication.model.VaccinationModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;
import com.ftflproject.ftflicareapplication.util.SetDateOnClickDialog;
import com.ftflproject.ftflicareapplication.util.setTimeOnclickDialog;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemSelectedListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateMedicalHistoryFragment extends Fragment implements OnClickListener,android.widget.AdapterView.OnItemSelectedListener{
	static final int REQUEST_IMAGE_CAPTURE = 1;
	private ImageView mIvPhotoView = null;
	private String mCurrentPhotoPath;
	private Bitmap mBitmap;
	private int mProfileId=1;
	private DatabaseHelperClass dbHelper;
    private DoctorModel doctorModelObject;
	private DoctorTableDataSource doctorTableObject;
	
	private Button mCreateButton,mImageCapture;
	 Context thiscontext;
	View rootView;
	private boolean isImageCaptured=false;
	private ArrayList<DoctorModel> doctorList;
	private List<String> doctorNames;
	private String name[];
	private Spinner spinner;
	private SetDateOnClickDialog datePickerObj;
	private EditText mDateEditText;
	private EditText mPurposeEditText;
	private String mDoctorName;
	private String mDate;
	private String mPurpose;
	private MedicalHistoryTableDataSource history_tbl_obj;
	private SharedPreferences settings;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();
		settings = thiscontext.getSharedPreferences(FTFLConstants.PREFS_NAME, thiscontext.MODE_PRIVATE);
		mProfileId= settings.getInt(FTFLConstants.PROFILE_ID,0);
		 Toast.makeText(thiscontext, "profile id"+mProfileId, 1000).show();
		
		 rootView = inflater.inflate(R.layout.fragment_create_medical_history, container, false);
		 mCreateButton=(Button) rootView.findViewById(R.id.btnInsert);
		 mImageCapture=(Button) rootView.findViewById(R.id.btnTakePhoto);
	     mCreateButton.setOnClickListener(this);
	     mImageCapture.setOnClickListener(this);
	     initialize();
        return rootView;
	}
	
	public void initialize(){
		mIvPhotoView = (ImageView) rootView.findViewById(R.id.ivTakePhoto);
		doctorTableObject = new DoctorTableDataSource(thiscontext);
		doctorNames=doctorTableObject.getAllNames();
		
		spinner = (Spinner) rootView.findViewById(R.id.doctorsName);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(thiscontext,
                android.R.layout.simple_spinner_item, doctorNames);
		
		dataAdapter
        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		mDateEditText=(EditText) rootView.findViewById(R.id.dateEditText);
		datePickerObj = new SetDateOnClickDialog(mDateEditText, thiscontext);
		mPurposeEditText=(EditText) rootView.findViewById(R.id.purposeEditText);

		//spinner.setOnItemSelectedListener(this);
		
	}
	
   
	
	public void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(thiscontext.getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try 
			{
				photoFile = createImageFile();
			} 
			catch (IOException ex) 
			{
				Toast.makeText(thiscontext, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) 
			{
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) 
		{
			setPic();
			//galleryAddPic();
		}
	}
	
	/**
	 * If user wants to load photo into gallery
	 */
	private void galleryAddPic() 
	{
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    thiscontext.sendBroadcast(mediaScanIntent);
	}


	private void setPic() {
		// Get the dimensions of the View
		/*int targetW = mIvPhotoView.getWidth();
		int targetH = mIvPhotoView.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		mBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(mBitmap);
		
		isImageCaptured=true;*/
		
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 4;
		
		mBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(mBitmap);
		
		isImageCaptured=true;
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		
		/**************************
		|---- You will get the image location from this variable which you will save into database
		*/
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	if(v.getId()==R.id.btnTakePhoto){
		dispatchTakePictureIntent();
		Toast.makeText(thiscontext, "photo", Toast.LENGTH_SHORT)
		.show();
	}else if(v.getId()==R.id.btnInsert){
		
			mDoctorName = String.valueOf(spinner.getSelectedItem());
			mDate = mDateEditText.getText().toString();
			mPurpose = mPurposeEditText.getText().toString();
			MedicalHistoryModel historModelObject = new MedicalHistoryModel(
					mCurrentPhotoPath, mDate, mDoctorName, mPurpose);
			historModelObject.setmProfileId(mProfileId);

			history_tbl_obj = new MedicalHistoryTableDataSource(thiscontext);

			long numberOfRowAdded = history_tbl_obj
					.insertHistory(historModelObject);
			Toast.makeText(thiscontext, "" + numberOfRowAdded, 1000).show();
			Intent intent = getActivity().getIntent();
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_NO_ANIMATION);
			getActivity().overridePendingTransition(0, 0);
			getActivity().finish();

			getActivity().overridePendingTransition(0, 0);
			startActivity(intent);
	}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(thiscontext, "insert"+position, Toast.LENGTH_SHORT)
		.show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	


}
