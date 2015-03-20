package com.ftflproject.ftflicareapplication.adapter;

import java.util.ArrayList;

import com.ftflproject.ftflicareapplication.R;
import com.ftflproject.ftflicareapplication.model.DietModel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class OneDayChartListAdapter extends ArrayAdapter<DietModel> {
	private final Activity mContext;
    private ImageView imageView;
    private  LayoutInflater inflater ;
	static class ViewHolder {
		public TextView name, time, menu, date;
		public ImageView image;
	}

	ArrayList<DietModel> objectArray;

	public OneDayChartListAdapter(Activity context,
			ArrayList<DietModel> objectArray) {
		super(context, R.layout.each_row_daily_chart, objectArray);
		this.mContext = context;
		this.objectArray = objectArray;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = convertView;
		
		if (convertView == null) {
			
			inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_row_daily_chart, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.name = (TextView) rowView
					.findViewById(R.id.feastNameText);
			
			viewHolder.time = (TextView) rowView
					.findViewById(R.id.feastTimeText);
			
			viewHolder.menu = (TextView) rowView.findViewById(R.id.menuView);
			
			viewHolder.date = (TextView) rowView.findViewById(R.id.dateText);

			viewHolder.image = (ImageView) rowView
					.findViewById(R.id.activeAlermImage);

			imageView = (ImageView) rowView
					.findViewById(R.id.activeAlermImage);

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		holder.name.setText(objectArray.get(position).getmDietName());
		
		holder.time.setText(objectArray.get(position).getmDietTime());
		
		holder.menu.setText(objectArray.get(position).getmDietMenu());
		
		holder.date.setText(objectArray.get(position).getmDietDate());

		if (objectArray.get(position).getmIsAlarmChecked() == 1) {

			holder.image.setImageResource(R.drawable.add);
			
		} else {
			
			holder.image.setImageResource(R.drawable.ic_launcher);
		}
		return rowView;
	}
}