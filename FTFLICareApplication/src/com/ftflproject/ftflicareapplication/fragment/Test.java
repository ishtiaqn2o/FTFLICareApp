package com.ftflproject.ftflicareapplication.fragment;



import com.ftflproject.ftflicareapplication.DashBoardActivity;
import com.ftflproject.ftflicareapplication.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Test extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.test, container, false);
		((DashBoardActivity) getActivity()).setTitle("Test");
		
		/* Button button = (Button) rootView.findViewById(R.id.button1);
			   button.setOnClickListener(new OnClickListener()
			   {
			             @Override
			             public void onClick(View v)
			             {
			                // do something
			            	 
			            	 CreateProfileFragment ldf = new CreateProfileFragment ();
			           	  Bundle args = new Bundle();
			           	  args.putString("YourKey", "YourValue");
			           	  ldf.setArguments(args);

			           	  //Inflate the fragment
			           	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
			             } 
			   }); */
        
        return rootView;
	}
  /*public void click(View v){
	  GrowthFragment ldf = new GrowthFragment ();
	  Bundle args = new Bundle();
	  args.putString("YourKey", "YourValue");
	  ldf.setArguments(args);

	  //Inflate the fragment
	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
  }*/
}
