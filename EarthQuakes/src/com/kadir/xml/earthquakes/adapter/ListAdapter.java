package com.kadir.xml.earthquakes.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kadir.xml.earthquakes.R;
import com.kadir.xml.earthquakes.model.Quakes;

public class ListAdapter extends BaseAdapter {

	private final ArrayList<Quakes> quakes;
	private final Context context;

	public ListAdapter(Context context, ArrayList<Quakes> quakes) {
		this.context = context;
		this.quakes = quakes;
	}

	@Override
	public int getCount() {
		return quakes.size();
	}

	@Override
	public Object getItem(int position) {
		return quakes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView timeListVW;
		TextView locationListVW;
		TextView indensityListVW;

		Quakes currentQuake = quakes.get(position);
		convertView = LayoutInflater.from(context).inflate(R.layout.gps_list,
				null);
		timeListVW = (TextView) convertView.findViewById(R.id.time_list_vw);
		locationListVW = (TextView) convertView
				.findViewById(R.id.location_list_vw);
		indensityListVW = (TextView) convertView
				.findViewById(R.id.indensity_list_vw);

		timeListVW.setText(currentQuake.getName());
		locationListVW.setText(currentQuake.getLokasyon());
		indensityListVW.setText(String.valueOf(currentQuake.getMag()));

		return convertView;
	}
}
