package com.ftflproject.ftflicareapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.database.DatabaseHelperClass;
import com.ftflproject.ftflicareapplication.database.DietTableDataSource;
import com.ftflproject.ftflicareapplication.model.DietModel;
import com.ftflproject.ftflicareapplication.util.FTFLConstants;

public class ViewDietChartFragment extends Fragment {
	private TextView showDietNameTextView;
	private TextView selectedDateTextView;
	private TextView selectedTimeTextView;
	private TextView showMenuTextView;
	private CheckBox alarmSetCheckBox;
	private String mDietName, mDietDate, mDietTime, mDay, mDietMenuDescription;
	public int mIsAlarmCecked = 0;
	private DatabaseHelperClass dbHelper;
	private int profileId = 1;
	private DietTableDataSource dietTableObject;
	private DietModel dietmodel;
	private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		thiscontext = container.getContext();

		rootView = inflater.inflate(R.layout.fragment_view_diet_chart,
				container, false);
		String selectedId = getArguments().getString(FTFLConstants.SELECTED_ID);
		initialize();
		dietTableObject = new DietTableDataSource(thiscontext);
		dietmodel = dietTableObject.getDietById(Integer.valueOf(selectedId));
		setValue();
		return rootView;

	}

	public void initialize() {

		showDietNameTextView = (TextView) rootView
				.findViewById(R.id.showFeastNameTextView);
		selectedDateTextView = (TextView) rootView
				.findViewById(R.id.selectedDateTextView);
		selectedTimeTextView = (TextView) rootView
				.findViewById(R.id.selectedTimeTextView);
		showMenuTextView = (TextView) rootView
				.findViewById(R.id.showMenuTextView);
		alarmSetCheckBox = (CheckBox) rootView
				.findViewById(R.id.alarmSetCheckBox);

	}
	
	public void setValue(){
		showDietNameTextView.setText(dietmodel.getmDietName());
		selectedDateTextView.setText(dietmodel.getmDay());
		selectedTimeTextView.setText(dietmodel.getmDietTime());
		showMenuTextView.setText(dietmodel.getmDietMenu());
	}
	

}
