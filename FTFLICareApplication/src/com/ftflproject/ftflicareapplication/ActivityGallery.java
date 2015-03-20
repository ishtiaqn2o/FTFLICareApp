package com.ftflproject.ftflicareapplication;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.adapter.ImageAdapter;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;



public class ActivityGallery extends ActionBarActivity {
	List<String> mImagePathList = new ArrayList<String>();
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.fragment_gallary);
	
	/*File storageDir = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	
	
	for (File imageFile : storageDir.listFiles()) {
		if (imageFile.isFile()) {
			mImagePathList.add(imageFile.getAbsolutePath());
		}
	}
	

	GridView gridview = (GridView)findViewById(R.id.gridview);
	gridview.setAdapter(new ImageAdapter(this, mImagePathList));*/
}
	
	
public void showImage(int ePosition ) {
		/*
		File imageFile = new File(mImagePathList.get(ePosition));
		Uri picUri = Uri.fromFile(imageFile);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(picUri, "image/*");
		startActivity(intent);*/
	}

}